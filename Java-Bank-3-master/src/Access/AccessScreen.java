package Access;

import Account.BankAccount;
import Person.Employee;
import Person.Gerente;
import Person.Person;
import Person.User;
import Person.GestionFicheros;

import java.util.Scanner;

import static Account.BankAccount.bankAccounts;
import static Account.registro.registros;
import static Person.Employee.employees;
import static Person.Gerente.gerentes;
import static Person.User.users;

public class AccessScreen {

    Scanner sc = new Scanner(System.in);

    Gerente gerente = new Gerente(null, null, null, null);
    Employee employee = new Employee(null, null, null, null);

    public void menu() {

        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== JAVA BANK ===");
            //System.out.println("1. Create Account");
            System.out.println("1. Log In");
            System.out.println("2. Close Application");
            System.out.print("Choose option: ");

            int option = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (option) {
                //case 1:
                    //crearCuenta();
                    //break;

                case 1:
                    login();
                    break;

                case 2:
                    GestionFicheros.guardarClientes(gerentes, employees, users, bankAccounts, registros);
                    System.out.println("Data saved. Closing application...");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public void login() {
        System.out.print("Enter user ID: ");
        String id = sc.nextLine();

        User currentUser = null;
        Gerente currentGerente = null;
        Employee currentEmployee = null;

        for (User u : users)
            if (u.userid.equals(id)) currentUser = u;

        for (Gerente g : gerentes)
            if (g.idGerente.equals(id)) currentGerente = g;

        for (Employee e : employees)
            if (e.employeeID.equals(id)) currentEmployee = e;

        if (currentUser == null && currentGerente == null && currentEmployee == null) {
            System.out.println("ID not found.");
            return;
        }

        Person loggedPerson =
                currentUser != null ? currentUser :
                        currentGerente != null ? currentGerente :
                                currentEmployee;

        if (!loggedPerson.active) {
            System.out.println("This account is blocked.");
            return;
        }

        int tries = 0;

        while (tries < 3) {
            System.out.print("Enter password: ");
            String pass = sc.nextLine();

            if (pass.equals(loggedPerson.password)) {
                System.out.println("Login successful.");
                loggedPerson.accountMenu(currentUser);
                return;
            } else {
                System.out.println("Wrong password.");
                tries++;
            }
        }

        System.out.println("Account blocked after 3 failed attempts.");
        loggedPerson.active = false;
    }

    public void crearCuenta() {
        System.out.println("\nCreate account for:");
        System.out.println("1. Gerente");
        System.out.println("2. Empleado");
        System.out.print("Choose option: ");

        String opcion = sc.nextLine();

        switch (opcion) {
            case "1":
                gerente.gerenteRegister();
                break;

            case "2":
                employee.employeeRegister();
                break;

            default:
                System.out.println("Invalid option.");
        }
    }
}
