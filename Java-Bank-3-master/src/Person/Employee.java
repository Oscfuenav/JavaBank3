package Person;

import Account.BankAccount;

import java.io.Serial;
import java.io.Serializable;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

import static Person.User.users;

/**
 * Clase que representa a un empleado del sistema bancario.
 *
 * <p>Los empleados pueden:
 * <ul>
 *     <li>Registrar nuevos clientes.</li>
 *     <li>Realizar operaciones bancarias básicas.</li>
 *     <li>Acceder a un menú propio con opciones limitadas.</li>
 * </ul>
 * </p>
 *
 * Extiende la clase abstracta {@link Person}.
 */
public class Employee extends Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    int numero=employees.size();
    public String employeeID=numero+"E";
    /** Lista global de empleados registrados en el sistema. */
    public static ArrayList<Employee> employees = new ArrayList<>();

    /**
     * Constructor de empleado.
     *
     * @param name nombre completo del empleado
     * @param password contraseña del empleado
     * @param birthDate fecha de nacimiento
     * @param employeeID identificador único
     */
    public Employee(String name, String password, String birthDate, String employeeID) {
        super( name, password, birthDate);
        this.employeeID = employeeID;
    }

    /**
     * Representación en texto del empleado.
     *
     * @return cadena con nombre, fecha de nacimiento e ID
     */
    @Override
    public String toString() {
        return "Empleado: " + name + " | Birthdate: " + birthDate + " | ID: " + employeeID;
    }

    /**
     * Muestra el menú principal del empleado.
     *
     * <p>Permite:
     * <ul>
     *     <li>Registrar clientes</li>
     *     <li>Realizar operaciones bancarias</li>
     *     <li>Cerrar sesión</li>
     * </ul>
     * </p>
     */
    @Override
    public void accountMenu() {
        Scanner scan2 = new Scanner(System.in);
        int option=0;
        while(option!=6){
            System.out.println("Welcome " + name);
            System.out.println("1. Create Client");
            System.out.println("2. Make a deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer Money");
            System.out.println("5. Recharge SIM card");
            System.out.println("6. Log Out");
            System.out.println("Please enter your numbered choice (1, 2, 3, 4, 5 or 6)");
            option = scan2.nextInt();
            switch (option){
                case 1:
                    clientRegister();
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

    /**
     * Registra un nuevo empleado solicitando datos por consola.
     *
     * <p>Valida:
     * <ul>
     *     <li>Contraseña mediante regex</li>
     *     <li>Fecha de nacimiento mediante regex</li>
     * </ul>
     * </p>
     */
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
        int size=employees.size();
        String employeeID=size+"E";
        Employee employeeUser = new Employee(name, password, birthdate, employeeID);

        System.out.println("The register process has ended");
        System.out.println("Your data:");
        System.out.println("Name: " + name);
        System.out.println("Birthdate: " + birthdate);
        System.out.println("Password: " + password);
        System.out.println("Id: " + employeeID);

        employees.add(employeeUser);
    }

    /**
     * Registra un nuevo cliente solicitando datos por consola.
     *
     * <p>Valida:
     * <ul>
     *     <li>Contraseña mediante regex</li>
     *     <li>Fecha de nacimiento mediante regex</li>
     * </ul>
     * </p>
     */
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
        int numUser=users.size();
        String userid=numUser+"U";
        User newUser = new User(name, password, birthdate,userid);

        System.out.println("The register process has ended");
        System.out.println("Your data:");
        System.out.println("Name: " + name);
        System.out.println("Birthdate: " + birthdate);
        System.out.println("Password: " + password);
        System.out.println("Id: " + userid);

        users.add(newUser);

    }

    /**
     * Valida una contraseña mediante expresión regular.
     *
     * @param password contraseña a validar
     * @return true si cumple los requisitos, false en caso contrario
     */
    @Override
    public boolean checkPassword(String password){
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        return password.matches(pattern);
    }

    /**
     * Valida una fecha mediante expresión regular.
     *
     * @param date fecha a validar
     * @return true si cumple el formato dd/mm/yyyy
     */
    @Override
    public boolean checkDate(String date){
        String regex = "^(0[1-9]|[12][0-9]|3[01])[\\/\\-.](0[1-9]|1[0-2])[\\/\\-.](19|20)\\d{2}$";
        return date.matches(regex);
    }
}
