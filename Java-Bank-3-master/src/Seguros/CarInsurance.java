package Seguros;

import Account.BankAccount;
import Account.CreditAccount;
import Account.DebitAccount;
import Person.User;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

import static Account.BankAccount.bankAccounts;
import static Person.User.users;

public class CarInsurance {
    int precio = 0;
    String tipo = "";
    public String userid = "";
    String ibanPaga = "";
    private LocalDate fechaSeguro;

    public static ArrayList<CarInsurance> carInsurance = new ArrayList<>();

    public CarInsurance(int precio, String tipo, String userid, String ibanPaga) {
        this.precio = precio;
        this.tipo = tipo;
        this.userid = userid;
        this.ibanPaga = ibanPaga;
        this.fechaSeguro = LocalDate.now();
    }

    public LocalDate getFechaSeguro() {
        return fechaSeguro;
    }

    public void setFechaSeguro(LocalDate fechaSeguro) {
        this.fechaSeguro = fechaSeguro;
    }

    public int getPrecio() {
        return precio;
    }

    public String getIbanPaga() {
        return ibanPaga;
    }

    public void menuCar() {
        String opcion = "";
        while (!opcion.equals("0")) {
            Scanner sc = new Scanner(System.in);
            System.out.println("===SEGURO DE COCHE===");
            System.out.println("Elige que seguro quieres: ");
            System.out.println("1. Basico");
            System.out.println("2. Moderado");
            System.out.println("3. Premium");
            System.out.println("0. Salir");
            opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    basico();
                    break;
                case "2":
                    moderado();
                    break;
                case "3":
                    premium();
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        }
    }

    public void basico() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa el id del usuario que quiere el seguro.");
        String userElegido = sc.nextLine();
        User userEncontrado = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).userid.equals(userElegido)) {
                userEncontrado = users.get(i);
            }
        }

        for (int i = 0; i < bankAccounts.size(); i++) {
            System.out.println((i+1) + ". " + bankAccounts.get(i).IBAN);
        }

        System.out.println("Elige el iban de la cuenta que paga: ");
        String ibanElegido = sc.nextLine();
        BankAccount bankAccountEncontrado = null;
        for (int i = 0; i < BankAccount.bankAccounts.size(); i++) {
            if (bankAccounts.get(i).IBAN.equals(ibanElegido)) {
                bankAccountEncontrado = bankAccounts.get(i);
            }
        }
        int precio2 = 160;
        if (bankAccountEncontrado instanceof DebitAccount) {
            DebitAccount debit = (DebitAccount) bankAccountEncontrado;
        if (debit.balance >= precio2) {
            debit.balance = - 160;
            CarInsurance coche = new CarInsurance(160, "basico", userEncontrado.userid, debit.IBAN);
            carInsurance.add(coche);
        } else {
            System.out.println("Dinero insuficiente.");
        }
        } else if (bankAccountEncontrado instanceof CreditAccount) {
            CreditAccount credit = (CreditAccount) bankAccountEncontrado;
            if (credit.balance >= precio2) {
                credit.balance = - 160;
                CarInsurance coche = new CarInsurance(160, "basico", userEncontrado.userid, credit.IBAN);
                carInsurance.add(coche);
            } else {
                double available = credit.balance - precio2;
                if (available < 0) {
                    credit.deudaCredit += available*-1;
                    if (credit.deudaCredit > credit.maxDeuda) {
                        System.out.println("Superas incluso la deuda.");
                        credit.deudaCredit -= available*-1;
                        return;
                    }
                    credit.balance=0;
                    System.out.println("Tienes " + credit.deudaCredit + " de deuda.");
                    CarInsurance coche = new CarInsurance(160, "basico", userEncontrado.userid, credit.IBAN);
                    carInsurance.add(coche);
                    return;
                }
                credit.balance -= precio2;
                System.out.println("Bien.");
            }
        }
    }

    public void moderado() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa el id del usuario que quiere el seguro.");
        String userElegido = sc.nextLine();
        User userEncontrado = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).userid.equals(userElegido)) {
                userEncontrado = users.get(i);
            }
        }

        for (int i = 0; i < bankAccounts.size(); i++) {
            System.out.println((i+1) + ". " + bankAccounts.get(i).IBAN);
        }

        System.out.println("Elige el iban de la cuenta que paga: ");
        String ibanElegido = sc.nextLine();
        BankAccount bankAccountEncontrado = null;
        for (int i = 0; i < BankAccount.bankAccounts.size(); i++) {
            if (bankAccounts.get(i).IBAN.equals(ibanElegido)) {
                bankAccountEncontrado = bankAccounts.get(i);
            }
        }
        int precio2 = 340;
        if (bankAccountEncontrado instanceof DebitAccount) {
            DebitAccount debit = (DebitAccount) bankAccountEncontrado;
            if (debit.balance >= precio2) {
                debit.balance = - 340;
                CarInsurance coche = new CarInsurance(340, "basico", userEncontrado.userid, debit.IBAN);
                carInsurance.add(coche);
            } else {
                System.out.println("Dinero insuficiente.");
            }
        } else if (bankAccountEncontrado instanceof CreditAccount) {
            CreditAccount credit = (CreditAccount) bankAccountEncontrado;
            if (credit.balance >= precio2) {
                credit.balance = - 340;
                CarInsurance coche = new CarInsurance(340, "basico", userEncontrado.userid, credit.IBAN);
                carInsurance.add(coche);
            } else {
                double available = credit.balance - precio2;
                if (available < 0) {
                    credit.deudaCredit += available*-1;
                    if (credit.deudaCredit > credit.maxDeuda) {
                        System.out.println("Superas incluso la deuda.");
                        credit.deudaCredit -= available*-1;
                        return;
                    }
                    credit.balance=0;
                    System.out.println("Tienes " + credit.deudaCredit + " de deuda.");
                    CarInsurance coche = new CarInsurance(340, "basico", userEncontrado.userid, credit.IBAN);
                    carInsurance.add(coche);
                    return;
                }
                credit.balance -= precio2;
                System.out.println("Bien.");
            }
        }
    }

    public void premium() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa el id del usuario que quiere el seguro.");
        String userElegido = sc.nextLine();
        User userEncontrado = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).userid.equals(userElegido)) {
                userEncontrado = users.get(i);
            }
        }

        for (int i = 0; i < bankAccounts.size(); i++) {
            System.out.println((i + 1) + ". " + bankAccounts.get(i).IBAN);
        }

        System.out.println("Elige el iban de la cuenta que paga: ");
        String ibanElegido = sc.nextLine();
        BankAccount bankAccountEncontrado = null;
        for (int i = 0; i < BankAccount.bankAccounts.size(); i++) {
            if (bankAccounts.get(i).IBAN.equals(ibanElegido)) {
                bankAccountEncontrado = bankAccounts.get(i);
            }
        }
        int precio2 = 600;
        if (bankAccountEncontrado instanceof DebitAccount) {
            DebitAccount debit = (DebitAccount) bankAccountEncontrado;
            if (debit.balance >= precio2) {
                debit.balance = -600;
                CarInsurance coche = new CarInsurance(600, "basico", userEncontrado.userid, debit.IBAN);
                carInsurance.add(coche);
            } else {
                System.out.println("Dinero insuficiente.");
            }
        } else if (bankAccountEncontrado instanceof CreditAccount) {
            CreditAccount credit = (CreditAccount) bankAccountEncontrado;
            if (credit.balance >= precio2) {
                credit.balance = -600;
                CarInsurance coche = new CarInsurance(600, "basico", userEncontrado.userid,  credit.IBAN);
                carInsurance.add(coche);
            } else {
                double available = credit.balance - precio2;
                if (available < 0) {
                    credit.deudaCredit += available * -1;
                    if (credit.deudaCredit > credit.maxDeuda) {
                        System.out.println("Superas incluso la deuda.");
                        credit.deudaCredit -= available * -1;
                        return;
                    }
                    credit.balance = 0;
                    System.out.println("Tienes " + credit.deudaCredit + " de deuda.");
                    CarInsurance coche = new CarInsurance(600, "basico", userEncontrado.userid, credit.IBAN);
                    carInsurance.add(coche);
                    return;
                }
                credit.balance -= precio2;
                System.out.println("Bien.");
            }
        }
    }

    static void comprobarYCobrarCar(CarInsurance car) {

        if (car.getFechaSeguro().plusYears(1).isBefore(LocalDate.now())) {

            BankAccount cuenta = buscarCuenta(car.getIbanPaga());

            if (cuenta != null) {

                int precio = car.getPrecio();

                if (cuenta instanceof DebitAccount) {

                    DebitAccount debit = (DebitAccount) cuenta;

                    if (debit.balance >= precio) {
                        debit.balance -= precio;
                        car.setFechaSeguro(LocalDate.now());
                        System.out.println("Seguro de vida renovado.");
                    } else {
                        System.out.println("Fondos insuficientes para renovar seguro de vida.");
                    }

                } else if (cuenta instanceof CreditAccount) {

                    CreditAccount credit = (CreditAccount) cuenta;

                    double disponible = credit.balance - precio;

                    if (disponible >= 0) {
                        credit.balance -= precio;
                    } else {
                        credit.deudaCredit += Math.abs(disponible);
                        credit.balance = 0;
                    }

                    car.setFechaSeguro(LocalDate.now());
                    System.out.println("Seguro de vida renovado con crédito.");
                }
            }
        }
    }

    private static BankAccount buscarCuenta(String iban) {
        for (BankAccount cuenta : bankAccounts) {
            if (cuenta.IBAN.equals(iban)) {
                return cuenta;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Seguro " +
                "precio='" + precio + '\'' +
                ", tipo=" + tipo +
                ", iban= " + ibanPaga +
                ", fecha= " + fechaSeguro;
    }

}
