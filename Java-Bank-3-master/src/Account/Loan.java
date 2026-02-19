package Account;

import java.io.Serializable;
import static Account.registro.registros;
public class Loan implements Serializable{
    private static final long serialVersionUID = 1L;
    private double principal;
    private double apr;
    private int termMonths;
    private double monthlyPayment;
    private int remainingMonths;
    private String loanID;

    public Loan(double principal, double apr, int termMonths, String loanID) {
        this.principal = principal;
        this.termMonths = termMonths;
        this.remainingMonths = termMonths;
        this.loanID = loanID;
        setValidApr(apr);

    }
    public void setValidApr(double apr) {
        if (apr < 5.3) this.apr = 5.3;
        else if (apr > 14.0)
            this.apr = 14.0;
        else
            this.apr = apr;
        this.monthlyPayment = calculateMonthlyPayment();
    }
    private double calculateMonthlyPayment() {
        if (principal <= 0 || termMonths <= 0) return 0;
        double r = (apr / 100) / 12;
        if (r == 0) return principal / termMonths;
        return (principal * r * Math.pow(1 + r, termMonths)) / (Math.pow(1 + r, termMonths) - 1);
    }
    public void payQuota(BankAccount account) {
        if (remainingMonths <= 0) {
            System.out.println("El prestamo " + loanID + "ya sido pagado en su totalidad");
            return;
        }
        if (account.balance >= monthlyPayment) {
            double balanceBefore = account.balance;
            account.balance -= monthlyPayment;
            remainingMonths--;
            registro r = new registro(monthlyPayment, account.IBAN, account.balance, balanceBefore, "Retiro");
            registros.add(r);
            System.out.printf("Installment of %.2f€ paid. New account balance: %.2f€%n", monthlyPayment, account.balance);

        } else {
            System.out.println("Error: Insufficient funds to pay the loan installment.");
        }
    }
    public void showLoanDetails() {
        System.out.println("\n--- LOAN DETAILS ---");
        System.out.printf("ID: %s | Capital: %.2f€ | APR: %.2f%%%n", loanID, principal, apr);
        System.out.printf("Monthly Payment: %.2f€ | Term: %d months%n", monthlyPayment, termMonths);
        System.out.println("--------------------------\n");
    }
    public String getLoanID() { return loanID;}
    public double getMonthlyPayment() {return monthlyPayment;}
}


