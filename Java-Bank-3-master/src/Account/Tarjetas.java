package Account;

import java.util.ArrayList;
import java.util.Random;

public class Tarjetas {
    public static ArrayList<Tarjetas> tarjetas = new ArrayList<>();
    private String numero;
    private int cvc;
    private String fechaVencimiento;
    public String iban = "";

    // Constructor que genera todo automáticamente
    public Tarjetas(String iban) {
        this.numero = generarNumero();
        this.cvc = generarCVC();
        this.fechaVencimiento = generarFechaVencimiento();
        this.iban = iban;
    }

    // Método para generar número tipo Visa válido
    private static String generarNumero() {
        Random rnd = new Random();
        StringBuilder numero = new StringBuilder("4"); // Empieza en 4 (Visa simulada)

        for (int i = 0; i < 14; i++) {
            numero.append(rnd.nextInt(10));
        }

        numero.append(calcularDigitoLuhn(numero.toString()));
        return numero.toString();
    }

    // Algoritmo de Luhn
    private static int calcularDigitoLuhn(String numero) {
        int suma = 0;
        boolean duplicar = true;

        for (int i = numero.length() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(numero.charAt(i));
            if (duplicar) {
                digito *= 2;
                if (digito > 9) digito -= 9;
            }
            suma += digito;
            duplicar = !duplicar;
        }

        return (10 - (suma % 10)) % 10;
    }

    // Generar CVC aleatorio
    private static int generarCVC() {
        Random rnd = new Random();
        return 100 + rnd.nextInt(900); // Número entre 100 y 999
    }

    // Generar fecha de vencimiento simulada
    private static String generarFechaVencimiento() {
        Random rnd = new Random();
        int mes = 1 + rnd.nextInt(12);
        int año = 26 + rnd.nextInt(5); // 2026 - 2030
        return String.format("%02d/%d", mes, año);
    }

    // Getters
    public String getNumero() {
        return numero;
    }

    public int getCvc() {
        return cvc;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    @Override
    public String toString() {
        return "Tarjeta " +
                "numero='" + numero + '\'' +
                ", cvc=" + cvc +
                ", fechaVencimiento='" + fechaVencimiento;
    }
}
