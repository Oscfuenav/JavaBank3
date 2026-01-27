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
    public void accountMenu() {

        Scanner scan2 = new Scanner(System.in);
        int option = 0;

        while(option != 6){

            System.out.println("\nWelcome " + name);
            System.out.println("1. Create BankAccount");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Recharge SIM");
            System.out.println("6. Log Out");

            option = scan2.nextInt();
            scan2.nextLine();

            switch (option){

                case 1:
                    System.out.println("1. Debit");
                    System.out.println("2. Credit");
                    int type = scan2.nextInt();
                    scan2.nextLine();

                    if (type == 1) {
                        DebitAccount d = new DebitAccount("","","","", "");
                        d.createBankAccount();
                    } else {
                        System.out.println("Credit limit:");
                        double limit = scan2.nextDouble();
                        System.out.println("Credit percentage:");
                        double perc = scan2.nextDouble();
                        scan2.nextLine();

                        CreditAccount c = new CreditAccount("","","","", "", limit, perc);
                        c.createBankAccount();
                    }
                    break;

                case 2:
                    if (BankAccount.bankAccounts.isEmpty()) {
                        System.out.println("No accounts.");
                        break;
                    }

                    for (int i = 0; i < BankAccount.bankAccounts.size(); i++) {
                        System.out.println((i+1) + ". " + BankAccount.bankAccounts.get(i).accountAlias);
                    }

                    int Elegido = scan2.nextInt();
                    scan2.nextLine();

                    BankAccount opcion = BankAccount.bankAccounts.get(Elegido - 1);
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
                        System.out.println((i+1) + ". " + BankAccount.bankAccounts.get(i).accountAlias);
                    }

                    int opcion2 = scan2.nextInt();
                    scan2.nextLine();

                    BankAccount opcion1 = BankAccount.bankAccounts.get(opcion2 - 1);
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
                        System.out.println((i+1) + ". " + BankAccount.bankAccounts.get(i).accountAlias +" Money (Enrique): " +BankAccount.bankAccounts.get(i).balance);
                    }
                    int opcionT = scan2.nextInt();
                    scan2.nextLine();

                    BankAccount cuentatrans = BankAccount.bankAccounts.get(opcionT - 1);

                    System.out.println("Amount:");
                    double dinero = scan2.nextDouble();
                    scan2.nextLine();
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
