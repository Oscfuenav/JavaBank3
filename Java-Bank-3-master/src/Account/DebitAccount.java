package Account;

import Person.User;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Scanner;

import static Account.registro.registros;

public class DebitAccount extends BankAccount {
    public String userid = "";
    public String tipo = "debit";
    @Serial
    private static final long serialVersionUID = 1L;

    public DebitAccount(String entity, String office, String accNumber, String dc, String IBAN, String accountAlias, String userid, String tipo) {
        super(entity, office, accNumber, dc, IBAN, accountAlias);
        this.userid = userid;
        this.tipo = tipo;
    }

    public DebitAccount(String entity, String office, String accNumber, String dc, String IBAN, String userid, String tipo) {
        super(entity, office, accNumber, dc, IBAN, userid);
        this.userid = userid;
        this.tipo= tipo;
    }

    @Override
    public void deposit(int amount, BankAccount account) {
        account.balance += amount;
        System.out.println("Deposited " + amount);
    }

    @Override
    public void withdraw(int amount, BankAccount account) {
        if (amount > account.balance) {
            System.out.println("Insufficient funds");
            return;
        }
        account.balance -= amount;
        System.out.println("Operation successful");
    }

    @Override
    public void transfer(double amount, BankAccount account) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter destination account number:");
        String destinationAcc = sc.nextLine();
        BankAccount destAcc = null;

        for (BankAccount ba : BankAccount.bankAccounts) {
            if (ba.accNumber.equals(destinationAcc)) {
                destAcc = ba;
                break;
            }
        }

        if (destAcc == null) {
            System.out.println("Destination account not found.");
            return;
        }

        if (amount > account.balance) {
            System.out.println("Insufficient funds.");
            return;
        }
        String IBAN=account.IBAN;
        String IBANR=destAcc.IBAN;
        double balanceAntesT= account.balance;
        double balanceAntesR=destAcc.balance;
        String tipo="transfer";
        account.balance -= amount;
        destAcc.balance += amount;
        double balanceDespuesT= account.balance;
        double balanceDespuesR=destAcc.balance;
        registro r=new registro(amount,IBAN, IBANR,balanceDespuesT,balanceAntesT,tipo,balanceDespuesR,balanceAntesR);
        registros.add(r);
        System.out.println("Transfer successful.");
        registro.toStringTransferencia();
    }

    @Override
    public void rechargeSIM(int amount, BankAccount account) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter phone number:");
        String number = sc.nextLine();

        if (number.length() != 9) {
            System.out.println("Invalid number.");
            return;
        }

        if (amount > account.balance) {
            System.out.println("Insufficient funds.");
            return;
        }

        account.balance -= amount;
        System.out.println("Recharge successful.");
    }

    @Override
    public void selectAccount(User user) {}

    @Override
    public void selectAccount(User user, ArrayList<BankAccount> bankAccounts) {}
}
