package Person;

import Account.BankAccount;

import java.io.Serializable;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;
import static Person.User.users;

public class Employee extends Person implements Serializable {
    public static ArrayList<Employee> employees = new ArrayList<>();
    public Employee(String name, String password, String birthDate, int id) {
        super( name, password, birthDate, id);
    }
    @Override
    public String toString() {
        return "Empleado: " + name + " | Birthdate: " + birthDate + " | ID: " + id;
    }
    @Override
    public void accountMenu() {
        Scanner scan2 = new Scanner(System.in);
        int option=0;
        System.out.println("Welcome " + name);
        System.out.println("1. Create Client");
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
                     clientRegister();//bankAccount  newBA = new bankAccount(dummyBankAccount.getEntity(), dummyBankAccount.getOffice(),  dummyBankAccount.calcDC(), null, null, null);
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

    public void employeeRegister(){
        Scanner sc = new Scanner(System.in);
        String name, birthdate, password;
        boolean checkP=false, checkD=false;
        System.out.println("Please enter your name and surnames");
        name = sc.nextLine();

        System.out.println("Please enter your password");
        password = sc.nextLine();
        checkPassword(password);
        if (checkPassword(password)==true) {
            checkP=true;
        }
        while (!checkP){
            System.out.println("The password you entered is incorrect");
            System.out.println("The password must contain:");
            System.out.println("* 1 uppercase letter");
            System.out.println("* 1 lowercase letter");
            System.out.println("* 1 number");
            System.out.println("* 1 special character");
            password = sc.nextLine();
            checkPassword(password);
        }

        System.out.println("Please enter your birthdate (dd/mm/yyyy)");
        birthdate = sc.nextLine();
        checkD = checkDate(birthdate);
        while(!checkD){
            System.out.println("The date you entered is incorrect, please try again");
            System.out.println("Remember to use the following format: dd/mm/yyyy");
            birthdate = sc.nextLine();
            checkD = checkDate(birthdate);
        }
        id += 1;
        Employee employeeUser = new Employee(name, password, birthdate, id);
        System.out.println("The register process has ended");
        System.out.println("Your data:");
        System.out.println("Name: " + name);
        System.out.println("Birthdate: " + birthdate);
        System.out.println("Password: " + password);
        System.out.println("Id: " + id);
        employees.add(employeeUser);
    }

    public void clientRegister(){
        Scanner sc = new Scanner(System.in);
        String name, birthdate, password;
        boolean checkP=false, checkD=false;
        System.out.println("Please enter your name and surnames");
        name = sc.nextLine();

        System.out.println("Please enter your password");
        password = sc.nextLine();
        checkPassword(password);
        if (checkPassword(password)==true) {
            checkP=true;
        }
        while (!checkP){
            System.out.println("The password you entered is incorrect");
            System.out.println("The password must contain:");
            System.out.println("* 1 uppercase letter");
            System.out.println("* 1 lowercase letter");
            System.out.println("* 1 number");
            System.out.println("* 1 special character");
            password = sc.nextLine();
            checkPassword(password);
        }

        System.out.println("Please enter your birthdate (dd/mm/yyyy)");
        birthdate = sc.nextLine();
        checkD = checkDate(birthdate);
        while(!checkD){
            System.out.println("The date you entered is incorrect, please try again");
            System.out.println("Remember to use the following format: dd/mm/yyyy");
            birthdate = sc.nextLine();
            checkD = checkDate(birthdate);
        }
         id += 1;
        User newUser = new User(name, password, birthdate, id);
        System.out.println("The register process has ended");
        System.out.println("Your data:");
        System.out.println("Name: " + name);
        System.out.println("Birthdate: " + birthdate);
        System.out.println("Password: " + password);
        System.out.println("Id: " + id);
        users.add(newUser);
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

    @Override
    public boolean checkDate(String date){
        String regex = "^(0[1-9]|[12][0-9]|3[01])[\\/\\-.](0[1-9]|1[0-2])[\\/\\-.](19|20)\\d{2}$";

        if (date.matches(regex)) {
            return true; // luego validas con LocalDate
        } else {
            return false;
        }
    }

}
