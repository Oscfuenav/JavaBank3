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
    public void accountMenu(User currentUser, ArrayList<registro>registros) {

        Scanner scan2 = new Scanner(System.in);
        int option = 0;

        while(option != 7){

            System.out.println("\nWelcome " + name);
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Recharge SIM");
            System.out.println("5. Ver Registros");
            System.out.println("6. Pagar deuda");
            System.out.println("7. Log out");
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
                    registro r=new registro(deposito, opcion.IBAN, balancedespues,balanceantes,"Depositar");
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
                        credit.withdraw(sacar, debit, credit);
                        balancedespues = credit.balance;

                        registro d = new registro(
                                sacar, credit.IBAN, balancedespues, balanceantes, "Retiro"
                        );
                        registros.add(d);

                    } else if (cuentaSeleccionada instanceof DebitAccount) {
                        DebitAccount debit = (DebitAccount) cuentaSeleccionada;
                        CreditAccount credit = null;
                        balanceantes = debit.balance;
                        debit.withdraw(sacar, debit, credit);
                        balancedespues = debit.balance;

                        registro d = new registro(
                                sacar, debit.IBAN, balancedespues, balanceantes, "Retiro"
                        );
                        registros.add(d);
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
                    registro.toStringDepo2(enriqueIban);
                    break;
                case 6:





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
