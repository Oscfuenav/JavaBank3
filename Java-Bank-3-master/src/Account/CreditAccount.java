package Account;

import Person.User;

import java.io.Serial;
import java.util.ArrayList;

public class CreditAccount extends BankAccount {
    public String userid = "";
    @Serial
    private static final long serialVersionUID = 1L;

    double creditLimit = 0.0;
    double creditPercentage = 0.0;
    double maxDeuda = 0;
    double deudaCredit = 0.0;
    public String tipo = "credit";

    public CreditAccount(String entity, String office, String accNumber, String dc, String IBAN,
                         String accountAlias, String userid, double maxDeuda, double deudaCredit, String tipo) {
        super(entity, office, accNumber, dc, IBAN, accountAlias);
        this.userid = userid;
        this.maxDeuda = maxDeuda;
        this.deudaCredit = deudaCredit;
        this.tipo = tipo;
    }

    public CreditAccount(String entity, String office, String accNumber, String dc, String IBAN, String userid, double maxDeuda, double deudaCredit, String tipo) {
        super(entity, office, accNumber, dc, IBAN, userid);
        this.maxDeuda = maxDeuda;
        this.deudaCredit = deudaCredit;
        this.tipo = tipo;
    }

    @Override
    public void deposit(int amount, BankAccount account) {
        account.balance += amount;
        System.out.println("Deposit successful.");
    }

    @Override
    public void withdraw(int amount, DebitAccount account, CreditAccount credit) {
            double available = credit.balance - amount;
            if (available < 0) {
                credit.deudaCredit += available*-1;
                if(credit.deudaCredit > maxDeuda){
                    System.out.println("Mal");
                    credit.deudaCredit -= available*-1;
                    return;
                }
                credit.balance=0;
                System.out.println("Tienes " + credit.deudaCredit + " de deuda.");
                return;
            }
            credit.balance -= amount;
            System.out.println("Withdraw successful.");
    }

    @Override
    public void transfer(double amount, BankAccount account) {
        System.out.println("Transfer must be handled from menu.");
    }

    @Override
    public void rechargeSIM(int amount, BankAccount account) {
        System.out.println("Recharge must be handled from menu.");
    }

    @Override
    public void selectAccount(User user) {}

    @Override
    public void selectAccount(User user, ArrayList<BankAccount> bankAccounts) {}

    public void pagarDeuda() {
        System.out.println("Deuda credit is " + deudaCredit + "%");
    }
}
