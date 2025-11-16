package domain;

public class Plaza {
  public enum Estado { LIBRE, OCUPADA }
  private final int id;
  private Estado estado = Estado.LIBRE;
  private String placaActual;

  public Plaza(int id) { this.id = id; }

  public boolean libre() { return estado == Estado.LIBRE; }
  public void ocupar(String placa) { estado = Estado.OCUPADA; placaActual = placa; }
  public void liberar() { estado = Estado.LIBRE; placaActual = null; }

  public int getId() { return id; }
  public Estado getEstado() { return estado; }
  public String getPlaca() { return placaActual; }

  @Override
  public String toString() {
    return "#" + id + " - " + (libre() ? "LIBRE" : ("OCUPADA " + placaActual));
  }
}

