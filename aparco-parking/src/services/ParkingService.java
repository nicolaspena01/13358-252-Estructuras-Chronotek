package services;

import datastructures.ArrayQueue;
import datastructures.ArrayStack;
import datastructures.BinarySearchTree;
import datastructures.MinHeap;
import datastructures.SimpleStack;
import domain.Plaza;
import domain.Ticket;
import domain.Vehiculo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class ParkingService {

  public static class ActionRecord {
    public enum Tipo {
      ASIGNAR, SALIDA
    }

    public final Tipo tipo;
    public final Ticket ticket;
    public final Vehiculo vehiculo;

    public ActionRecord(Tipo t, Ticket tk, Vehiculo v) {
      this.tipo = t;
      this.ticket = tk;
      this.vehiculo = v;
    }
  }

  private final Plaza[] plazas;
  private final ArrayQueue<Vehiculo> colaEntrada = new ArrayQueue<>(1000);
  private final ArrayQueue<Ticket> colaSalida = new ArrayQueue<>(1000);
  private final SimpleStack<ActionRecord> undoStack = new ArrayStack<>(1000);

  private final BinarySearchTree<String, Ticket> bstPorPlaca = new BinarySearchTree<>();
  private final MinHeap<Ticket> heapPorEntrada = new MinHeap<>(32, Comparator.comparingLong(Ticket::getEntradaMillis));

  // Tarifa configurable
  private long tarifaHora = 3000L;

  public ParkingService(int capacidad) {
    plazas = new Plaza[capacidad];
    for (int i = 0; i < capacidad; i++)
      plazas[i] = new Plaza(i);
  }

  // --- Configuración ---

  public long getTarifaHora() {
    return tarifaHora;
  }

  public void setTarifaHora(long tarifaHora) {
    if (tarifaHora > 0)
      this.tarifaHora = tarifaHora;
  }

  // --- Lógica principal ---

  public void llegada(Vehiculo v) {
    colaEntrada.enqueue(v);
  }

  /** Asignar usando la cola (FIFO). */
  public Ticket asignarPlaza() {
    Vehiculo v = colaEntrada.dequeue();
    if (v == null)
      return null;
    int idx = buscarPlazaLibre();
    if (idx < 0) {
      colaEntrada.enqueue(v); // sin plaza, se devuelve a la cola
      return null;
    }
    plazas[idx].ocupar(v.getPlaca());
    Ticket t = new Ticket(UUID.randomUUID().toString(), v.getPlaca(), idx, System.currentTimeMillis());

    bstPorPlaca.put(t.getPlaca(), t);
    heapPorEntrada.add(t);

    undoStack.push(new ActionRecord(ActionRecord.Tipo.ASIGNAR, t, v));
    return t;
  }

  /** NUEVO: Asignar directo a una plaza específica, sin usar la cola. */
  public Ticket asignarPlazaDirecta(int plazaId, Vehiculo v) {
    if (plazaId < 0 || plazaId >= plazas.length || v == null)
      return null;
    Plaza p = plazas[plazaId];
    if (!p.libre())
      return null;

    p.ocupar(v.getPlaca());
    Ticket t = new Ticket(UUID.randomUUID().toString(), v.getPlaca(), plazaId, System.currentTimeMillis());

    bstPorPlaca.put(t.getPlaca(), t);
    heapPorEntrada.add(t);
    undoStack.push(new ActionRecord(ActionRecord.Tipo.ASIGNAR, t, v));

    return t;
  }

  public Ticket registrarSalidaPorPlaca(String placa, long tarifaHoraParametro) {
    long tarifa = tarifaHoraParametro > 0 ? tarifaHoraParametro : this.tarifaHora;

    Ticket t = bstPorPlaca.get(placa.toUpperCase());
    if (t == null || !t.isAbierto())
      return null;

    int idx = t.getPlazaId();
    plazas[idx].liberar();

    long ahora = System.currentTimeMillis();
    long valor = tarifa; // puedes cambiarlo luego a cálculo por horas
    t.cerrar(ahora, valor);

    colaSalida.enqueue(t);
    bstPorPlaca.remove(t.getPlaca());
    // El heap se limpia perezosamente (ignorando cerrados en las consultas)

    undoStack.push(new ActionRecord(ActionRecord.Tipo.SALIDA, t, new Vehiculo(placa, Vehiculo.Tipo.CARRO)));
    return t;
  }

  public boolean undo() {
    ActionRecord rec = undoStack.pop();
    if (rec == null)
      return false;

    if (rec.tipo == ActionRecord.Tipo.ASIGNAR) {
      plazas[rec.ticket.getPlazaId()].liberar();
      rec.ticket.setAbierto(false);
      bstPorPlaca.remove(rec.ticket.getPlaca());
      colaEntrada.enqueue(rec.vehiculo);
      return true;
    } else {
      plazas[rec.ticket.getPlazaId()].ocupar(rec.ticket.getPlaca());
      rec.ticket.setAbierto(true);
      bstPorPlaca.put(rec.ticket.getPlaca(), rec.ticket);
      heapPorEntrada.add(rec.ticket);
      return true;
    }
  }

  // --- Consultas ---

  public Plaza[] getPlazas() {
    return plazas;
  }

  public int getCapacidad() {
    return plazas.length;
  }

  public int getColaEntradaSize() {
    return colaEntrada.size();
  }

  public int getColaSalidaSize() {
    return colaSalida.size();
  }

  public Vehiculo[] getColaEntradaSnapshot() {
    Object[] raw = colaSalida.toArray(); // OJO: esto estaba MAL antes, debe usar colaEntrada
    // CORREGIMOS:
    raw = colaEntrada.toArray();
    Vehiculo[] res = new Vehiculo[raw.length];
    for (int i = 0; i < raw.length; i++)
      res[i] = (Vehiculo) raw[i];
    return res;
  }

  public Ticket buscarTicketPorPlaca(String placa) {
    if (placa == null)
      return null;
    return bstPorPlaca.get(placa.toUpperCase());
  }

  public List<Ticket> getProximosEnSalir(int k) {
    List<Ticket> result = new ArrayList<>();
    List<Ticket> temp = new ArrayList<>();

    while (result.size() < k && !heapPorEntrada.isEmpty()) {
      Ticket t = heapPorEntrada.peek();
      if (t == null)
        break;
      if (!t.isAbierto()) {
        heapPorEntrada.poll(); // limpiar cerrados
        continue;
      }
      t = heapPorEntrada.poll();
      result.add(t);
      temp.add(t);
    }
    for (Ticket t : temp)
      heapPorEntrada.add(t);

    return result;
  }

  public Ticket[] getTicketsCerradosSnapshot() {
    Object[] raw = colaSalida.toArray();
    Ticket[] res = new Ticket[raw.length];
    for (int i = 0; i < raw.length; i++)
      res[i] = (Ticket) raw[i];
    return res;
  }

  public void addTicketsExternos(Ticket[] ts) {
    if (ts == null)
      return;
    for (Ticket t : ts) {
      if (t == null)
        continue;
      t.setAbierto(false);
      colaSalida.enqueue(t);
    }
  }

  // --- Helpers privados ---

  private int buscarPlazaLibre() {
    for (int i = 0; i < plazas.length; i++)
      if (plazas[i].libre())
        return i;
    return -1;
  }
}
