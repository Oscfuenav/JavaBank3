package Account;

import Person.User;

import java.io.Serial;
import java.util.ArrayList;

public class CreditAccount extends BankAccount {

    @Serial
    private static final long serialVersionUID = 1L;

    double creditLimit = 0.0;
    double creditPercentage = 0.0;

    public CreditAccount(String entity, String office, String accNumber, String dc, String IBAN,
                         String accountAlias, double creditLimit, double creditPercentage){
        super(entity, office, accNumber, dc, IBAN, accountAlias);
        this.creditLimit = creditLimit;
        this.creditPercentage = creditPercentage;
    }

    public CreditAccount(String entity, String office, String accNumber, String dc, String IBAN,
                         double creditLimit, double creditPercentage){
        super(entity, office, accNumber, dc, IBAN);
        this.creditLimit = creditLimit;
        this.creditPercentage = creditPercentage;
    }

    @Override
    public void deposit(int amount, BankAccount account) {
        account.balance += amount;
        System.out.println("Deposit successful.");
    }

    @Override
    public void withdraw(int amount, BankAccount account) {
        double available = account.balance + creditLimit;

        if (amount > available) {
            System.out.println("Insufficient funds.");
            return;
        }

        account.balance -= amount;
        System.out.println("Withdrawal successful.");
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
}
