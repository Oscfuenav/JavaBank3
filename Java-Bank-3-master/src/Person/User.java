package Person;

import Account.BankAccount;
import Account.BankAccount.*;
import Account.CreditAccount;
import Account.DebitAccount;

import java.io.Serializable;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class User extends Person implements Serializable {
    public static ArrayList<User> users = new ArrayList<>();
    public User(String name, String password, String birthDate, int id) {
        super(name, password, birthDate, id);
        this.active=true;
    }
    @Override
    public String toString() {
        return "User: " + name + " | Birthdate: " + birthDate + " | ID: " + id;
    }
    @Override
    public void accountMenu(){
        Scanner scan2 = new Scanner(System.in);
        int option=0;
        System.out.println("Welcome " + name);
        System.out.println("1. Create BankAccount");
        System.out.println("2. Make a deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer Money");
        System.out.println("5. Recharge SIM card");
        System.out.println("6. Log Out");
        System.out.println("Please enter your numbered choice (1, 2, 3, 4, 5 or 6)");
        option = scan2.nextInt();
        while(option!=6){
            switch (option){
                case 1:
                    BankAccount newAccount = new DebitAccount("2","2","2","2", "2");
                    newAccount.createBankAccount();
                break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
            }
        }
    }



    @Override
    public boolean checkDate(String date){
        String regex = "^(0[1-9]|[12][0-9]|3[01])[\\/\\-.](0[1-9]|1[0-2])[\\/\\-.](19|20)\\d{2}$";

        if (date.matches(regex)) {
            return true; // luego validas con LocalDate
        } else {
            return false;
        }
    }

    @Override
    public boolean checkPassword(String password){ //regex password
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        if(password.matches(pattern)){
            return true;
        }
        else {
            return false;
        }
    }

}