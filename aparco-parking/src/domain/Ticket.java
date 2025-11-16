package domain;

public class Ticket {
  private final String id;
  private final String placa;
  private final int plazaId;
  private final long entradaMillis;
  private Long salidaMillis;
  private long valor;
  private boolean abierto = true; // <<< nuevo

  public Ticket(String id, String placa, int plazaId, long entradaMillis) {
    this.id = id; this.placa = placa; this.plazaId = plazaId; this.entradaMillis = entradaMillis;
  }

  public void cerrar(long tSalida, long valorC) {
    this.salidaMillis = tSalida;
    this.valor = valorC;
    this.abierto = false; // marcar como cerrado
  }

  public String getId() { return id; }
  public String getPlaca() { return placa; }
  public int getPlazaId() { return plazaId; }
  public long getEntradaMillis() { return entradaMillis; }
  public Long getSalidaMillis() { return salidaMillis; }
  public long getValor() { return valor; }
  public boolean isAbierto() { return abierto; }
  public void setAbierto(boolean abierto) { this.abierto = abierto; } // útil para UNDO

  @Override
  public String toString() {
    return "Ticket{" +
            "placa='" + placa + '\'' +
            ", plaza=" + plazaId +
            ", entrada=" + entradaMillis +
            (salidaMillis != null ? ", salida=" + salidaMillis : "") +
            ", valor=" + valor +
            '}';
  }
}
