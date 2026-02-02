package Account;

import java.io.Serializable;
import java.util.ArrayList;
public class registro implements Serializable {
    String fecha;
    double amounTrans;
    public String IBAN;
    double finalmount;
    double beforeamunt;
    String tipo2;
    String IBANT;
    String IBANR;
    double finalmountT;
    double beforeamuntT;
    double finalmountR;
    double beforeamuntR;

    public registro(double amounTrans, String IBAN, double finalmount, double beforeamunt,String tipo2) {
        this.amounTrans = amounTrans;
        this.IBAN = IBAN;
        this.beforeamunt=beforeamunt;
        this.finalmount = finalmount;
        this.tipo2=tipo2;
    }

    public registro(double amounTrans, String IBAN,String IBANR , double finalmountT, double beforeamuntT,String tipo2, double finalmountR, double beforeamuntR) {
        this.amounTrans = amounTrans;
        this.IBAN = IBAN;
        this.finalmountT = finalmountT;
        this.beforeamuntT = beforeamuntT;
        this.tipo2=tipo2;
        this.finalmount=finalmountR;
        this.beforeamuntR=beforeamuntR;
        this.finalmountR=finalmountR;
        this.IBANR=IBANR;
    }
    public static    ArrayList<registro> registros = new ArrayList<>();

    @Override
    public String toString() {
        if (this.tipo2.equals("Depositar")) {
            return "Deposito " + this.amounTrans + " | Balance antes: " + this.beforeamunt + " | Balance despues: " + this.finalmount + " | IBAN: " + this.IBAN;
        }
        if (this.tipo2.equals("Retiro")) {
            return "Retiro " + this.amounTrans + " | Balance antes: " + this.beforeamunt + " | Balance despues: " + this.finalmount + " | IBAN: " + this.IBAN;
        }
        return "" ;
    }

    public static void toStringTransferencia() {
        for (registro r : registros) {
            if(r.tipo2.equals("transfer")){
                System.out.println("Transferencia  | Monto: " + r.amounTrans + " | Origen IBAN: " + r.IBAN + " | Antes: " + r.beforeamuntT + " | Después: " + r.finalmountT + " || Receptor IBAN: " + r.IBANR + " | Antes: " + r.beforeamuntR + " | Después: " + r.finalmountR);
            }
        }  }

    public static void toStringDepo2(String enriqueIban) {
        for (registro r : registros) {
        if (r.IBAN.equals(enriqueIban)) {
            if (r.tipo2.equals("Depositar") || r.tipo2.equals("Retiro")) {
            System.out.println(r.tipo2 + " " + r.amounTrans + " | Balance antes: " + r.beforeamunt + " | Balance despues: " + r.finalmount + " | IBAN: " + r.IBAN);
            } else {
                System.out.println("Transferencia  | Monto: " + r.amounTrans + " | Origen IBAN: " + r.IBAN + " | Antes: " + r.beforeamuntT + " | Después: " + r.finalmountT + " || Receptor IBAN: " + r.IBANR + " | Antes: " + r.beforeamuntR + " | Después: " + r.finalmountR);
            }
            }
        }
    }
}
