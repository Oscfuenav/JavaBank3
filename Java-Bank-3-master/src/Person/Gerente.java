package Person;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import static Person.Employee.employees;
import static Person.User.users;

/**
 * Clase que representa a un gerente dentro del sistema bancario.
 *
 * <p>Los gerentes tienen permisos ampliados respecto a los empleados y usuarios.
 * Pueden:
 * <ul>
 *     <li>Registrar nuevos clientes.</li>
 *     <li>Registrar nuevos empleados.</li>
 *     <li>Realizar operaciones bancarias.</li>
 *     <li>Acceder a un menú avanzado.</li>
 * </ul>
 * </p>
 *
 * Extiende la clase abstracta {@link Person}.
 */
public class Gerente extends Person implements Serializable {
    int Size = gerentes.size();
    public String idGerente=Size+"G";
    /** Lista global de gerentes registrados en el sistema. */
    public static ArrayList<Gerente> gerentes = new ArrayList<>();

    /**
     * Constructor de gerente.
     *
     * @param name nombre completo del gerente
     * @param password contraseña del gerente
     * @param birthDate fecha de nacimiento
     * @param idGerente identificador único
     */
    public Gerente(String name, String password, String birthDate, String idGerente) {
        super(name, password, birthDate);
        this.idGerente = idGerente;
    }
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Representación en texto del gerente.
     *
     * @return cadena con nombre, fecha de nacimiento e ID
     */
    @Override
    public String toString() {
        return "Gerente: " + name + " | Birthdate: " + birthDate + " | ID: " + idGerente;
    }

    /**
     * Muestra el menú principal del gerente.
     *
     * <p>Permite:
     * <ul>
     *     <li>Crear clientes</li>
     *     <li>Crear empleados</li>
     *     <li>Realizar operaciones bancarias</li>
     *     <li>Cerrar sesión</li>
     * </ul>
     * </p>
     */
    @Override
    public void accountMenu(User currentUser) {
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
                    gerenteEmployeeRegister();
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

    /**
     * Registra un nuevo gerente solicitando datos por consola.
     *
     * <p>Valida:
     * <ul>
     *     <li>Contraseña mediante regex</li>
     *     <li>Fecha de nacimiento mediante regex</li>
     * </ul>
     * </p>
     */
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
        int size =gerentes.size();
        String idGerente = size+"G";
        Gerente gerenteUser = new Gerente(name, password, birthdate, idGerente);

        System.out.println("The register process has ended");
        System.out.println("Name: " + name);
        System.out.println("Birthdate: " + birthdate);
        System.out.println("Password: " + password);
        System.out.println("Id: " + idGerente);

        gerentes.add(gerenteUser);

        System.out.println("Gerentes: " + gerentes.size());
    }

    /**
     * Registra un nuevo empleado solicitando datos por consola.
     *
     * @see Employee
     */
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

        int numEmployee= employees.size();
        String Employeeid=(numEmployee+"E");
        Employee employeeUser = new Employee(name, password, birthdate,Employeeid);

        System.out.println("Employee created:");
        System.out.println("Name: " + name);
        System.out.println("Birthdate: " + birthdate);
        System.out.println("Password: " + password);
        System.out.println("Id: " + Employeeid);

        employees.add(employeeUser);
    }

    /**
     * Registra un nuevo cliente solicitando datos por consola.
     *
     * @param users lista global de usuarios
     */
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
        int numUser= users.size();
        String userid=(numUser+"U");
        User newUser = new User(name, password, birthdate, userid);

        System.out.println("Client created:");
        System.out.println("Name: " + name);
        System.out.println("Birthdate: " + birthdate);
        System.out.println("Password: " + password);
        System.out.println("Id: " + userid);

        users.add(newUser);
    }

    /**
     * Valida una fecha mediante expresión regular.
     *
     * @param date fecha a validar
     * @return true si cumple el formato dd/mm/yyyy
     */
    @Override
    public boolean checkDate(String date) {
        String regex = "^(0[1-9]|[12][0-9]|3[01])[\\/\\-.](0[1-9]|1[0-2])[\\/\\-.](19|20)\\d{2}$";
        return date.matches(regex);
    }

    /**
     * Valida una contraseña mediante expresión regular.
     *
     * @param password contraseña a validar
     * @return true si cumple los requisitos
     */
    @Override
    public boolean checkPassword(String password) {
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        return password.matches(pattern);
    }
}
