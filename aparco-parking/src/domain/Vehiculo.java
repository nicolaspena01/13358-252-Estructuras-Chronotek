package domain;

public class Vehiculo {
  public enum Tipo { CARRO, MOTO }
  private final String placa;
  private final Tipo tipo;

  public Vehiculo(String placa, Tipo tipo) {
    this.placa = placa.trim().toUpperCase();
    this.tipo = tipo;
  }

  public String getPlaca() { return placa; }
  public Tipo getTipo() { return tipo; }

  @Override
  public String toString() { return placa + " (" + tipo + ")"; }
}

