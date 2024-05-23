package banco;

public class CuentaDeCheque extends Cuenta{

    private Double costoMensual;

    public CuentaDeCheque(Double saldo, Integer numero, Double costoMensual) {
        super(saldo, numero);
        this.costoMensual = costoMensual;
    }

    @Override
    public String toString() {
        return "CuentaDeCheque{" +
                "costoMensual=" + costoMensual +
                '}';
    }
}
