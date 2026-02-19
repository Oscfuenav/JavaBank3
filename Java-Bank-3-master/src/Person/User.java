package Person;

import Access.AccessScreen;
import Account.*;
import Seguros.CarInsurance;
import Seguros.HealthInsurance;
import Seguros.HomeInsurance;
import Seguros.LifeInsurance;
import TiendaObjetos.Producto;
import TiendaObjetos.Tienda;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

import static Account.Tarjetas.tarjetas;
import static Account.registro.registros;
import static Seguros.CarInsurance.carInsurance;
import static Seguros.HealthInsurance.healthInsurance;
import static Seguros.HomeInsurance.homeInsurance;
import static Seguros.LifeInsurance.lifeInsurance;

public class User extends Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public static ArrayList<User> users = new ArrayList<>();

    public String userid;
    public Boolean moroso;

    public User(String name, String password, String birthDate, String userid) {
        super(name, password, birthDate);
        this.userid = userid;
        this.active = true;
        this.moroso = false;
    }

    @Override
    public String toString() {
        return "User: " + name + " | Birthdate: " + birthDate + " | ID: " + userid;
    }

    @Override
    public void accountMenu(User currentUser, ArrayList<registro> registros) {
        Scanner scan2 = new Scanner(System.in);
        int option = 0;

        while(option != 10){

            System.out.println("\nWelcome " + name);
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Recharge SIM");
            System.out.println("5. Ver Registros");
            System.out.println("6. Comprobar Pagar deuda");
            System.out.println("7.Tienda");
            System.out.println("8. Ver tarjetas");
            System.out.println("9. Ver seguros");
            System.out.println("10. Log out");

            option = scan2.nextInt();

            switch (option){

                case 1:
                    Scanner sc = new Scanner(System.in);
                    if (BankAccount.bankAccounts.isEmpty()) {
                        System.out.println("No accounts.");
                        break;
                    }

                    for (int i = 0; i < BankAccount.bankAccounts.size(); i++) {
                        if (currentUser.userid.equals(BankAccount.bankAccounts.get(i).userid)) {
                            System.out.println((i+1) + ". " + BankAccount.bankAccounts.get(i).IBAN);
                        }
                    }
                    System.out.println("Escribe el IBAN: ");
                    String Elegido = sc.nextLine();
                    BankAccount opcion = null;
                    for (int i = 0; i < BankAccount.bankAccounts.size(); i++) {
                        if (Elegido.equals(BankAccount.bankAccounts.get(i).IBAN)) {
                            opcion = BankAccount.bankAccounts.get(i);
                        }
                    }
                    double balanceantes = opcion.balance;

                    System.out.println("Amount:");
                    int deposito = scan2.nextInt();
                    opcion.deposit(deposito, opcion);
                    double balancedespues=opcion.balance;
                    registro r=new registro(deposito, opcion.IBAN, balancedespues,balanceantes,"Depositar", opcion.userid,0);
                    registros.add(r);
                    for (registro rr : registros) {
                        System.out.println(rr);
                    }
                    break;

                case 2:
                    Scanner scan3 = new Scanner(System.in);
                    if (BankAccount.bankAccounts.isEmpty()) {
                        System.out.println("No accounts.");
                        break;
                    }

                    for (int i = 0; i < BankAccount.bankAccounts.size(); i++) {
                        if (currentUser.userid.equals(BankAccount.bankAccounts.get(i).userid)) {
                            System.out.println((i+1) + ". " + BankAccount.bankAccounts.get(i).IBAN);
                        }
                    }
                    System.out.println("Escribe el IBAN: ");
                    String opcion2 = scan3.nextLine();
                    BankAccount cuentaSeleccionada = null;
                    for (BankAccount acc : BankAccount.bankAccounts) {
                        if (opcion2.equals(acc.IBAN)) {
                            cuentaSeleccionada = acc;
                            break;
                        }
                    }

                    System.out.println("Amount:");
                    int sacar = scan3.nextInt();

                    if (cuentaSeleccionada instanceof CreditAccount) {
                        CreditAccount credit = (CreditAccount) cuentaSeleccionada;
                        DebitAccount debit = null;
                        balanceantes = credit.balance;
                        credit.withdraw(sacar, debit, credit,sacar, (int) balanceantes);
                    } else if (cuentaSeleccionada instanceof DebitAccount) {
                        DebitAccount debit = (DebitAccount) cuentaSeleccionada;
                        CreditAccount credit = null;
                        balanceantes = debit.balance;
                        debit.withdraw(sacar, debit, credit, sacar, (int) balanceantes);
                    }
                    break;

                case 3:
                    if (BankAccount.bankAccounts.isEmpty()) {
                        System.out.println("No accounts.");
                        break;
                    }

                    for (int i = 0; i < BankAccount.bankAccounts.size(); i++) {
                        if (currentUser.userid.equals(BankAccount.bankAccounts.get(i).userid)) {
                            System.out.println((i+1) + ". " + BankAccount.bankAccounts.get(i).IBAN +" Money (Enrique): " +BankAccount.bankAccounts.get(i).balance);
                        }
                    }
                    System.out.println("Escribe el iban: ");
                    String opcionT = scan2.nextLine();
                    scan2.nextLine();
                    BankAccount cuentatrans = null;
                    for (int i = 0; i < BankAccount.bankAccounts.size(); i++) {
                        if (opcionT.equals(BankAccount.bankAccounts.get(i).IBAN)) {
                            cuentatrans = BankAccount.bankAccounts.get(i);
                        }
                    }

                    System.out.println("Amount:");
                    double dinero = scan2.nextDouble();
                    scan2.nextLine();
                    for (int x = 0;  x < BankAccount.bankAccounts.size(); x++) {
                        System.out.println((x+1) + ". " + BankAccount.bankAccounts.get(x).accNumber);
                    }
                    cuentatrans.transfer(dinero,cuentatrans);

                    break;

                case 4:
                    if (BankAccount.bankAccounts.isEmpty()) {
                        System.out.println("No accounts.");
                        break;
                    }

                    for (int i = 0; i < BankAccount.bankAccounts.size(); i++) {
                        System.out.println((i+1) + ". " + BankAccount.bankAccounts.get(i).accountAlias + BankAccount.bankAccounts.get(i).balance);
                    }

                    int simIndex = scan2.nextInt();
                    BankAccount simAcc = BankAccount.bankAccounts.get(simIndex - 1);

                    System.out.println("Amount:");
                    int simAmount = scan2.nextInt();

                    simAcc.rechargeSIM(simAmount, simAcc);
                    break;

                case 5:
                    for (int i = 0; i < BankAccount.bankAccounts.size(); i++) {
                        if (currentUser.userid.equals(BankAccount.bankAccounts.get(i).userid)) {
                            System.out.println((i+1) + ". " + BankAccount.bankAccounts.get(i).IBAN);

                        }
                    }
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Dime un IBAN de la cuenta quieres ver: ");
                    String enriqueIban = scan.nextLine();
                    System.out.println("\n--- REGISTROS ---");
                    for (registro r2: registros){
                        if (enriqueIban.equals(r2.IBAN)) {
                            System.out.println(r2);
                        }
                    }
                    break;

                case 6:
                    ComprobarDeuda(currentUser);
                    break;

                case 7:
                    Tienda tienda = new Tienda();
                    tienda.mostrarMenu( currentUser);
                    break;

                case 8:
                    Scanner scan4 = new Scanner(System.in);
                    for (int i = 0; i < BankAccount.bankAccounts.size(); i++) {
                        if (currentUser.userid.equals(BankAccount.bankAccounts.get(i).userid)) {
                            System.out.println((i+1) + ". " + BankAccount.bankAccounts.get(i).IBAN);
                        }
                    }
                    System.out.println("Escribe el iban para ver sus tarjetas.");
                    String ibanElegido = scan4.nextLine();
                    for (int x = 0; x < tarjetas.size(); x++) {
                        if (ibanElegido.equals(tarjetas.get(x).iban)) {
                            for (Tarjetas tar: tarjetas) {
                                System.out.println(tar.toString());
                            }
                        }
                    }
                    break;
                case 9:
                    System.out.println("Tus seguros de vida.");
                    for (int i = 0; i < lifeInsurance.size(); i++) {
                        if (lifeInsurance.get(i).userid.equals(currentUser.userid)) {
                            for (LifeInsurance l: lifeInsurance) {
                                System.out.println(l.toString());
                            }
                        }
                    }
                    System.out.println("Tus seguros de salud.");
                    for (int i = 0; i < healthInsurance.size(); i++) {
                        if (healthInsurance.get(i).userid.equals(currentUser.userid)) {
                            for (HealthInsurance l: healthInsurance) {
                                System.out.println(l.toString());
                            }
                        }
                    }
                    System.out.println("Tus seguros de coche.");
                    for (int i = 0; i < carInsurance.size(); i++) {
                        if (carInsurance.get(i).userid.equals(currentUser.userid)) {
                            for (CarInsurance l: carInsurance) {
                                System.out.println(l.toString());
                            }
                        }
                    }
                    System.out.println("Tus seguros de casa.");
                    for (int i = 0; i < homeInsurance.size(); i++) {
                        if (homeInsurance.get(i).userid.equals(currentUser.userid)) {
                            for (HomeInsurance l: homeInsurance) {
                                System.out.println(l.toString());
                            }
                        }
                    }
                    break;
                case 10:
                    System.out.println("Login out.");
                    break;
            }
        }
    }

    @Override
    public boolean checkDate(String date){
        String regex = "^(0[1-9]|[12][0-9]|3[01])[\\/\\-.](0[1-9]|1[0-2])[\\/\\-.](19|20)\\d{2}$";
        return date.matches(regex);
    }

    @Override
    public boolean checkPassword(String password){
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        return password.matches(pattern);
    }

    public void ComprobarDeuda(User currentUser){

        // 1. Buscar la cuenta de crédito del usuario
        CreditAccount credit = null;

        for (BankAccount acc : BankAccount.bankAccounts) {
            if (currentUser.userid.equals(acc.userid) && acc instanceof CreditAccount) {
                credit = (CreditAccount) acc;
                break;
            }
        }

        if (credit == null) {
            System.out.println("El usuario no tiene cuenta de crédito.");
            return;
        }

        double deuda = credit.deudaCredit;

        if (deuda <= 0) {
            System.out.println("No tienes deuda pendiente.");
            return;
        }

        // 2. Sumar el saldo de TODAS las cuentas del usuario EXCEPTO la de crédito
        double saldoDisponible = 0;

        for (BankAccount acc : BankAccount.bankAccounts) {
            if (currentUser.userid.equals(acc.userid) ) {
                saldoDisponible += acc.balance;
            }
        }

        // 3. Comprobar si el saldo total puede cubrir la deuda
        if (saldoDisponible >= deuda) {
            System.out.println("puedes pagar");
        } else {
            System.out.println("no puedes pagar");
        }
    }


    // ---------------------------------------------------------
    // FUNCIÓN YA EXISTENTE: PAGAR DEUDA EN 12 PLAZOS
    // ---------------------------------------------------------
    public void pagarDeudaProductosEn12Plazos(User user) {

        // 1. Buscar todas las deudas de productos del usuario
        ArrayList<registro> deudas = new ArrayList<>();

        for (registro r : registros) {
            if (r.regid.equals(user.userid) && r.tipo2.equals("Deuda producto")) {
                deudas.add(r);
            }
        }

        if (deudas.isEmpty()) {
            System.out.println("No tienes deudas de productos." +user.userid);
            return;
        }

        for (registro deuda : deudas) {

            if (deuda.cuotasPagadas >= deuda.cuotasTotales) {
                System.out.println("La deuda del producto ya está completamente pagada.");
                continue;
            }

            double cuotaMensual = deuda.deudapagos / 12.0;
            boolean pagado = false;

            // Intentar pagar con alguna cuenta del usuario
            for (BankAccount acc : BankAccount.bankAccounts) {

                if (acc.userid.equals(user.userid) && acc.balance >= cuotaMensual) {

                    double antes = acc.balance;
                    acc.balance -= cuotaMensual;

                    registro pagoReg = new registro(
                            cuotaMensual,
                            acc.IBAN,
                            acc.balance,
                            antes,
                            "Pago mensual deuda producto",
                            user.userid,
                            0
                    );
                    registros.add(pagoReg);
                    deuda.cuotasPagadas++;
                    pagado = true;

                    System.out.println("La cuenta " + acc.IBAN + " ha pagado " + cuotaMensual + "€");
                    System.out.println("Cuotas restantes: " + (deuda.cuotasTotales - deuda.cuotasPagadas));

                    if (deuda.cuotasPagadas == deuda.cuotasTotales) {
                        System.out.println("¡Deuda del producto completamente pagada!");
                    }
                }
                if (pagado) {
                    break;
                }
            }

            if (!pagado) {
                System.out.println("No se pudo pagar la cuota completa.");
                user.moroso = true;
            }
        }
    }





    public void pagarDeudasMensuales(User user) {
        LocalDate hoy = LocalDate.now();


        CreditAccount credit = null;
        for (BankAccount acc : BankAccount.bankAccounts) {
            if (user.userid.equals(acc.userid) && acc instanceof CreditAccount) {
                credit = (CreditAccount) acc;
                break;
            }
        }

        if (credit == null) {
            System.out.println("El usuario no tiene cuenta de crédito.");
            return;
        }

        double deuda = credit.deudaCredit;

        if (deuda <= 0) {
            System.out.println("No hay deuda pendiente.");
            return;
        }

        System.out.println("Deuda inicial: " + deuda + "€");

        for (BankAccount acc : BankAccount.bankAccounts) {

            if (!user.userid.equals(acc.userid)) continue;

            if (acc.balance > 0 && deuda > 0) {

                double cantidadAPagar = Math.min(acc.balance, deuda);

                acc.balance -= cantidadAPagar;
                deuda -= cantidadAPagar;

                System.out.println("La cuenta " + acc.IBAN +
                        " ha pagado " + cantidadAPagar +
                        "€. Deuda restante: " + deuda + "€");
            }
        }

        if (deuda > 0) {
            System.out.println("No puedes pagar la deuda con tus cuentas actuales.");
            System.out.println("Se ha descontado todo el saldo disponible, pero sigues debiendo " + deuda + "€.");
            moroso = true;
        } else {
            System.out.println("¡Deuda pagada completamente!");
            moroso = false;
        }

        credit.deudaCredit = deuda;
    }

}
