package Person;

import Account.BankAccount;
import Account.CreditAccount;
import Account.DebitAccount;
import Account.registro;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import static Account.registro.registros;

public class User extends Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public static ArrayList<User> users = new ArrayList<>();

    public String userid;

    public User(String name, String password, String birthDate, String userid) {
        super(name, password, birthDate);
        this.userid = userid;
        this.active = true;
    }

    @Override
    public String toString() {
        return "User: " + name + " | Birthdate: " + birthDate + " | ID: " + userid;
    }

    @Override
    public void accountMenu(User currentUser) {

        Scanner scan2 = new Scanner(System.in);
        int option = 0;

        while(option != 7){

            System.out.println("\nWelcome " + name);
            System.out.println("1. Create BankAccount");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Recharge SIM");
            System.out.println("6. Ver Registros");
            System.out.println("7. Log out");
            option = scan2.nextInt();
            scan2.nextLine();

            switch (option){

                case 1:
                    System.out.println("1. Debit");
                    System.out.println("2. Credit");
                    int type = scan2.nextInt();
                    scan2.nextLine();

                    if (type == 1) {
                        DebitAccount d = new DebitAccount("","","","", "", currentUser.userid);
                        d.createBankAccount();
                    } else {
                        System.out.println("Credit limit:");
                        double limit = scan2.nextDouble();
                        System.out.println("Credit percentage:");
                        double perc = scan2.nextDouble();
                        scan2.nextLine();

                        CreditAccount c = new CreditAccount("","","","", "", limit, perc, currentUser.userid);
                        c.createBankAccount();
                    }
                    break;

                case 2:
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
                    String Elegido = scan2.nextLine();
                    scan2.nextLine();
                    BankAccount opcion = null;
                    for (int i = 0; i < BankAccount.bankAccounts.size(); i++) {
                        if (Elegido.equals(BankAccount.bankAccounts.get(i).IBAN)) {
                            opcion = BankAccount.bankAccounts.get(i);
                        }
                    }
                    double balanceantes =opcion.balance;

                    System.out.println("Amount:");
                    int deposito = scan2.nextInt();
                    scan2.nextLine();
                    opcion.deposit(deposito, opcion);
                    double balancedespues=opcion.balance;
                    registro r=new registro(deposito, opcion.IBAN, balancedespues,balanceantes,"Depositar");
                    registros.add(r);
                    for (registro rr : registros) {
                        System.out.println(rr);
                    }
                    break;

                case 3:
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
                    String opcion2 = scan2.nextLine();
                    scan2.nextLine();
                    BankAccount opcion1 = null;
                    for (int i = 0; i < BankAccount.bankAccounts.size(); i++) {
                        if (opcion2.equals(BankAccount.bankAccounts.get(i).IBAN)) {
                            opcion1 = BankAccount.bankAccounts.get(i);
                        }
                    }
                    balanceantes=opcion1.balance;
                    System.out.println("Amount:");
                    int sacar = scan2.nextInt();
                    scan2.nextLine();
                    opcion1.withdraw(sacar,opcion1);
                    balancedespues=opcion1.balance;
                    registro d=new registro(sacar, opcion1.IBAN, balancedespues,balanceantes,"Retirar");
                    registros.add(d);
                    for (registro rr : registros) {
                        System.out.println(rr);
                    }
                    break;

                case 4:
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

                case 5:
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
                case 6:
                    for (int i = 0; i < BankAccount.bankAccounts.size(); i++) {
                        if (currentUser.userid.equals(BankAccount.bankAccounts.get(i).userid)) {
                            System.out.println((i+1) + ". " + BankAccount.bankAccounts.get(i).IBAN);

                        }
                    }
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Dime un IBAN de la cuenta quieres ver: ");
                    String enriqueIban = scan.nextLine();
                    System.out.println("\n--- REGISTROS ---");
                    registro.toStringDepo2(enriqueIban);
                    break;
                case 7:
                    System.out.println("Logging out...");
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
}
