package Person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import static Person.Employee.employees;
import static Person.User.users;

public class Gerente extends Person implements Serializable {
    public static ArrayList<Gerente> gerentes = new ArrayList<>();
    @Override
    public String toString() {
        return "Gerente: " + name + " | Birthdate: " + birthDate + " | ID: " + id;
    }
    public Gerente(String name, String password, String birthDate, int id) {
        super(name, password, birthDate, id);
    }



    @Override
    public void accountMenu() {
        Scanner scan2 = new Scanner(System.in);
        int option = 0;
        System.out.println("Welcome " + name);
        System.out.println("1. Create Client");
        System.out.println("2. Create Employee");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer Money");
        System.out.println("5. Recharge SIM card");
        System.out.println("6. Log Out");

        while (option != 6) {
            System.out.println("Please enter your numbered choice (1, 2, 3, 4, 5 or 6)");
            option = scan2.nextInt();
            switch (option) {
                case 1:
                    gerenteClientRegister(users);
                    break;
                case 2:

                    break;
                case 3: break;

                case 4: break;
                case 5: break;
                case 6: break;
            }
        }
    }

    public void gerenteRegister() {
        Scanner sc = new Scanner(System.in);
        String name, birthdate, password;
        boolean checkP = false, checkD = false;

        System.out.println("Please enter your name and surnames");
        name = sc.nextLine();

        System.out.println("Please enter your password");
        password = sc.nextLine();
        checkP = checkPassword(password);

        while (!checkP) {
            System.out.println("The password you entered is incorrect");
            System.out.println("The password must contain:");
            System.out.println("* 1 uppercase letter");
            System.out.println("* 1 lowercase letter");
            System.out.println("* 1 number");
            System.out.println("* 1 special character");
            password = sc.nextLine();
            checkP = checkPassword(password);
        }

        System.out.println("Please enter your birthdate (dd/mm/yyyy)");
        birthdate = sc.nextLine();
        checkD = checkDate(birthdate);

        while (!checkD) {
            System.out.println("The date you entered is incorrect, please try again");
            birthdate = sc.nextLine();
            checkD = checkDate(birthdate);
        }

        id += 1;
        Gerente gerenteUser = new Gerente(name, password, birthdate, id);

        System.out.println("The register process has ended");
        System.out.println("Name: " + name);
        System.out.println("Birthdate: " + birthdate);
        System.out.println("Password: " + password);
        System.out.println("Id: " + id);

        gerentes.add(gerenteUser);
;

        System.out.println("Gerentes: " + gerentes.size());
    }

    public void gerenteEmployeeRegister() {
        Scanner sc = new Scanner(System.in);
        String name, birthdate, password;
        boolean checkP = false, checkD = false;

        System.out.println("Please enter your name and surnames");
        name = sc.nextLine();

        System.out.println("Please enter your password");
        password = sc.nextLine();
        checkP = checkPassword(password);

        while (!checkP) {
            System.out.println("Incorrect password, try again");
            password = sc.nextLine();
            checkP = checkPassword(password);
        }

        System.out.println("Please enter your birthdate (dd/mm/yyyy)");
        birthdate = sc.nextLine();
        checkD = checkDate(birthdate);

        while (!checkD) {
            System.out.println("Incorrect date, try again");
            birthdate = sc.nextLine();
            checkD = checkDate(birthdate);
        }

        id += 1;
        Employee employeeUser = new Employee(name, password, birthdate, id);

        System.out.println("Employee created:");
        System.out.println("Name: " + name);
        System.out.println("Birthdate: " + birthdate);
        System.out.println("Password: " + password);
        System.out.println("Id: " + id);

        employees.add(employeeUser);
    }

    public void gerenteClientRegister(ArrayList<User> users) {
        Scanner sc = new Scanner(System.in);
        String name, birthdate, password;
        boolean checkP = false, checkD = false;

        System.out.println("Please enter your name and surnames");
        name = sc.nextLine();

        System.out.println("Please enter your password");
        password = sc.nextLine();
        checkP = checkPassword(password);

        while (!checkP) {
            System.out.println("Incorrect password, try again");
            password = sc.nextLine();
            checkP = checkPassword(password);
        }

        System.out.println("Please enter your birthdate (dd/mm/yyyy)");
        birthdate = sc.nextLine();
        checkD = checkDate(birthdate);

        while (!checkD) {
            System.out.println("Incorrect date, try again");
            birthdate = sc.nextLine();
            checkD = checkDate(birthdate);
        }

        id += 1;
        User newUser = new User(name, password, birthdate, id);

        System.out.println("Client created:");
        System.out.println("Name: " + name);
        System.out.println("Birthdate: " + birthdate);
        System.out.println("Password: " + password);
        System.out.println("Id: " + id);

        users.add(newUser);
    }

    @Override
    public boolean checkDate(String date) {
        String regex = "^(0[1-9]|[12][0-9]|3[01])[\\/\\-.](0[1-9]|1[0-2])[\\/\\-.](19|20)\\d{2}$";
        return date.matches(regex);
    }

    @Override
    public boolean checkPassword(String password) {
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        return password.matches(pattern);
    }
}
