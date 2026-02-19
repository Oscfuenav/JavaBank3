package Account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class registro implements Serializable {

    public int cuotasTotales = 12;
    public int cuotasPagadas = 0;

    public Date fecha;
    public double amounTrans;
    public String IBAN;

    public double finalmount;
    public double beforeamunt;

    public String tipo2;

    public String IBANT;
    public String IBANR;

    public double finalmountT;
    public double beforeamuntT;

    public double finalmountR;
    public double beforeamuntR;

    public String regid;
    public int deudapagos;

    public static ArrayList<registro> registros = new ArrayList<>();

    // ---------------------------------------------------------
    // ✔ Constructor para depósitos, retiros y compras normales
    // ---------------------------------------------------------
    public registro(double amounTrans, String IBAN, double finalmount, double beforeamunt,
                    String tipo2, String userid, int deudapagos) {

        this.amounTrans = amounTrans;
        this.IBAN = IBAN;
        this.beforeamunt = beforeamunt;
        this.finalmount = finalmount;
        this.tipo2 = tipo2;
        this.fecha = new Date();
        this.regid = userid;
        this.deudapagos = deudapagos;
    }

    // ---------------------------------------------------------
    // ✔ Constructor para transferencias (completo)
    // ---------------------------------------------------------
    public registro(double amounTrans, String IBAN, String IBANR, double finalmountT, double beforeamuntT,
                    String tipo2, double finalmountR, double beforeamuntR, String userid, int deudapagos) {

        this.amounTrans = amounTrans;
        this.IBAN = IBAN;
        this.IBANR = IBANR;

        this.finalmountT = finalmountT;
        this.beforeamuntT = beforeamuntT;

        this.finalmountR = finalmountR;
        this.beforeamuntR = beforeamuntR;

        this.tipo2 = tipo2;
        this.fecha = new Date();
        this.regid = userid;
        this.deudapagos = deudapagos;
    }

    // ---------------------------------------------------------
    // ✔ Constructor para transferencias sin deudapagos
    // ---------------------------------------------------------
    public registro(double amounTrans, String IBAN, String IBANR, double finalmountT, double beforeamuntT,
                    String tipo2, double finalmountR, double beforeamuntR, String userid) {

        this.amounTrans = amounTrans;
        this.IBAN = IBAN;
        this.IBANR = IBANR;

        this.finalmountT = finalmountT;
        this.beforeamuntT = beforeamuntT;

        this.finalmountR = finalmountR;
        this.beforeamuntR = beforeamuntR;

        this.tipo2 = tipo2;
        this.fecha = new Date();
        this.regid = userid;
    }

    // ---------------------------------------------------------
    // ✔ NUEVO: Constructor para deudas a plazos
    // ---------------------------------------------------------
    public registro(String userid, String nombreProducto, int precioTotal) {

        this.tipo2 = "Deuda producto";
        this.regid = userid;

        this.amounTrans = precioTotal; // precio total
        this.deudapagos = precioTotal;

        this.IBAN = nombreProducto; // guardamos el nombre del producto

        this.fecha = new Date();
        this.cuotasTotales = 12;
        this.cuotasPagadas = 0;
    }

    // ---------------------------------------------------------
    // ✔ NUEVO: Constructor para pagos mensuales de deuda
    // ---------------------------------------------------------
    public registro(double pago, String IBAN, double finalmount, double beforeamunt, String userid) {

        this.tipo2 = "Pago mensual deuda producto";

        this.amounTrans = pago;
        this.IBAN = IBAN;
        this.beforeamunt = beforeamunt;
        this.finalmount = finalmount;

        this.regid = userid;
        this.fecha = new Date();
    }

    // ---------------------------------------------------------
    // TO STRING
    // ---------------------------------------------------------
    @Override
    public String toString() {

        if (this.tipo2.equals("Deuda producto")) {
            return "DEUDA CREADA | Producto: " + this.IBAN +
                    " | Total: " + this.deudapagos +
                    "€ | Usuario: " + this.regid +
                    " | Fecha: " + this.fecha;
        }

        if (this.tipo2.equals("Pago mensual deuda producto")) {
            return "PAGO DE PLAZO | Pago: " + this.amounTrans +
                    "€ | Cuenta: " + this.IBAN +
                    " | Antes: " + this.beforeamunt +
                    " | Después: " + this.finalmount +
                    " | Usuario: " + this.regid +
                    " | Fecha: " + this.fecha;
        }

        if (this.tipo2.equals("Depositar")) {
            return "DEPÓSITO | " + this.amounTrans +
                    "€ | Antes: " + this.beforeamunt +
                    " | Después: " + this.finalmount +
                    " | IBAN: " + this.IBAN +
                    " | Usuario: " + this.regid +
                    " | Fecha: " + this.fecha;
        }

        if (this.tipo2.equals("Retiro")) {
            return "RETIRO | " + this.amounTrans +
                    "€ | Antes: " + this.beforeamunt +
                    " | Después: " + this.finalmount +
                    " | IBAN: " + this.IBAN +
                    " | Usuario: " + this.regid +
                    " | Fecha: " + this.fecha;
        }

        if (this.tipo2.equals("transfer")) {
            return "TRANSFERENCIA | Monto: " + this.amounTrans +
                    " | Origen IBAN: " + this.IBAN +
                    " (" + this.beforeamuntT + " → " + this.finalmountT + ")" +
                    " | Destino IBAN: " + this.IBANR +
                    " (" + this.beforeamuntR + " → " + this.finalmountR + ")" +
                    " | Usuario: " + this.regid +
                    " | Fecha: " + this.fecha;
        }

        return "OPERACIÓN | " + this.tipo2 +
                " | Monto: " + this.amounTrans +
                " | IBAN: " + this.IBAN +
                " | Usuario: " + this.regid +
                " | Fecha: " + this.fecha;
    }
    public static void toStringTransferencia() {
        for (registro r : registros) {
            if (r.tipo2.equals("transfer")) {
                System.out.println("Transferencia  | Monto: " + r.amounTrans +
                        " | Origen IBAN: " + r.IBAN + " | Antes: " + r.beforeamuntT +
                        " | Después: " + r.finalmountT + " || Receptor IBAN: " + r.IBANR +
                        " | Antes: " + r.beforeamuntR + " | Después: " + r.finalmountR +
                        " fecha " + r.fecha + " " + r.regid);
            }
        }
    }
}
