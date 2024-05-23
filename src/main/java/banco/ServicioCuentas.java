package banco;

public interface ServicioCuentas {
    boolean agregarCuenta(Cuenta cuenta);
    boolean cancelarCuenta(Integer numero);
    void abonarCuenta(Integer numero,Double abono);
    void retirar(Integer numero, Double retiro);
    Cuenta[] obtenerCuentas();
}
