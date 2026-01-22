package Access;

import Account.BankAccount;
import Person.Employee;
import Person.Gerente;
import Person.Person;
import Person.User;
import Person.GestionFicheros;
import java.util.Scanner;
import java.util.ArrayList;

import static Person.Employee.employees;
import static Person.Gerente.gerentes;
import static  Person.User.users;
public class AccessScreen {
    Scanner sc = new Scanner(System.in);
    Gerente gerente = new Gerente(null, null, null, 0);
    Employee employee = new Employee(null, null, null, 0);

    public void menu() {
        GestionFicheros gestionFicheros = new GestionFicheros();

        boolean enrique = false;

        while (!enrique) {
            System.out.println("Welcome to JavaBank ");
            System.out.println("1. Create Account");
            System.out.println("2. Log In");
            System.out.println("3. Close Application");
            System.out.println("Please enter your numbered choice (1, 2 or 3)");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    crearCuenta();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    gestionFicheros.guardarClientes(gerentes, employees, users);
                    System.out.println("Todo guardado crack master");
                    enrique = true;
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1, 2 or 3.");
            }
        }
    }

    public void login() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter user id: ");
        int id = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        User currentUser = null;
        Gerente currentGerente = null;
        Employee currentEmployee = null;

        for (User u : users)
            if (u.id == id) currentUser = u;

        for (Gerente g : gerentes)
            if (g.id == id) currentGerente = g;

        for (Employee e : employees)
            if (e.id == id) currentEmployee = e;

        if (currentUser == null && currentGerente == null && currentEmployee == null) {
            System.out.println("ID not found");
            return;
        }

        Person loggedPerson =
                currentUser != null ? currentUser :
                        currentGerente != null ? currentGerente :
                                currentEmployee;

        if (!loggedPerson.active) {
            System.out.println("Account is blocked");
            return;
        }

        int tries = 0;
        while (tries < 3) {
            System.out.println("Please enter password:");
            String pass = sc.nextLine();

            if (pass.equals(loggedPerson.password)) {
                System.out.println("You have successfully logged in");
                loggedPerson.accountMenu();
                return;
            } else {
                System.out.println("Wrong password");
                tries++;
            }
        }

        System.out.println("Account blocked after 3 attempts");
        loggedPerson.active = false;
    }

    public void crearCuenta() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Elige para quien es la cuenta:");
        System.out.println("1. Gerente \n2. Empleado");

        String opcion = scan.nextLine();

        switch (opcion) {
            case "1":
                gerente.gerenteRegister();
                break;
            case "2":
                employee.employeeRegister();
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }
}
