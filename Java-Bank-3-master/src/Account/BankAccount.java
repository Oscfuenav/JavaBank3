package Account;

import Person.User;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class BankAccount implements Serializable {
    public String userid = "";
    @Serial
    private static final long serialVersionUID = 1L;


    public static ArrayList<BankAccount> bankAccounts = new ArrayList<>();

    public static String entity = "9999";
    public static String office = "8888";
    public String dc = "";
    public String accNumber = "";
    public String IBAN = "";
    public String accountAlias = "";
    public double balance = 0.0;
    int NumAuto = 0;

    public BankAccount(String entity, String office, String accNumber, String dc, String IBAN, String accountAlias, String userid) {
        this.entity = entity;
        this.office = office;
        this.accNumber = accNumber;
        this.dc = dc;
        this.IBAN = IBAN;
        this.accountAlias = accountAlias;
        this.userid = userid;
    }

    public BankAccount(String entity, String office, String accNumber, String dc, String IBAN, String userid) {
        this(entity, office, accNumber, dc, IBAN, "Account " + accNumber, userid);
    }

    @Override
    public String toString() {
        return "Alias: " + accountAlias + " | IBAN: " + IBAN + " | Balance: " + balance + " | ID: " + userid;
    }

    public static String calcDC(String entity, String office, String accNumber) {
        entity = String.format("%04d", Integer.parseInt("9999"));
        office = String.format("%04d", Integer.parseInt("8888"));
        accNumber = String.format("%010d", Long.parseLong(accNumber));

        int[] w1 = {4,8,5,10,9,7,3,6};
        int[] w2 = {1,2,4,8,5,10,9,7,3,6};

        String bloque1 = entity + office;
        int suma1 = 0;
        for (int i = 0; i < 8; i++) suma1 += (bloque1.charAt(i) - '0') * w1[i];
        int r1 = suma1 % 11;
        int d1 = 11 - r1;
        if (d1 == 11) d1 = 0;
        else if (d1 == 10) d1 = 1;

        String bloque2 = accNumber;
        int suma2 = 0;
        for (int i = 0; i < 10; i++) suma2 += (bloque2.charAt(i) - '0') * w2[i];
        int r2 = suma2 % 11;
        int d2 = 11 - r2;
        if (d2 == 11) d2 = 0;
        else if (d2 == 10) d2 = 1;

        return "" + d1 + d2;
    }

    public static String calcIBAN(String entity, String office, String accNumber) {
        String dc = calcDC(entity, office, accNumber);

        entity = String.format("%04d", Integer.parseInt("9999"));
        office = String.format("%03d", Integer.parseInt("888"));
        accNumber = String.format("%09d", Long.parseLong(accNumber));

        String bban = entity + office + dc + accNumber;

        String numeric = bban + "142800";

        BigInteger num = new BigInteger(numeric);
        int resto = num.mod(BigInteger.valueOf(97)).intValue();
        int cd = 98 - resto;
        String cdStr = String.format("%02d", cd);

        return "ES" + cdStr + bban;
    }

    public void createBankAccount(String tipo) {
        NumAuto = bankAccounts.size();
        DecimalFormat formato = new DecimalFormat("000000000");
        accNumber = formato.format(NumAuto);

        dc = calcDC(entity, office, accNumber);
        IBAN = calcIBAN(entity, office, accNumber);

        accountAlias = changeAccountAlias();

        bankAccounts.add(this);

        System.out.println("Account created successfully!");
        System.out.println("IBAN: " + IBAN);
        System.out.println("Alias: " + accountAlias);
    }

    public String changeAccountAlias() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to give an alias to your account? (yes/no)");
        String check = sc.nextLine();

        if (check.equalsIgnoreCase("yes")) {
            System.out.println("Enter alias:");
            return sc.nextLine();
        }

        return "Account " + accNumber;
    }

    public abstract void deposit(int amount, BankAccount account);
    public abstract void withdraw(int amount, DebitAccount account, CreditAccount credit,int sacar, int balanceantes);
    public abstract void transfer(double amount, BankAccount account);
    public abstract void rechargeSIM(int amount, BankAccount account);
    public abstract void selectAccount(User user);
    public abstract void selectAccount(User user, ArrayList<BankAccount> bankAccounts);
}
