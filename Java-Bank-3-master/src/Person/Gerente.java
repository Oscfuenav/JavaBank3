package Person;

import Account.*;
import Seguros.CarInsurance;
import Seguros.HealthInsurance;
import Seguros.HomeInsurance;
import Seguros.LifeInsurance;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import static Account.BankAccount.bankAccounts;
import static Account.Tarjetas.tarjetas;
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
    public void accountMenu(User currentUser, ArrayList<registro>registros) {
        Scanner scan2 = new Scanner(System.in);
        int option = 0;
        while (option != 9) {
            System.out.println("Welcome " + name);
            System.out.println("1. Create Client");
            System.out.println("2. Create Employee");
            System.out.println("3. Ver registros");
            System.out.println("4. Desbloquear usuarios");
            System.out.println("5. Recharge SIM card");
            System.out.println("6. Create Bankaccount");
            System.out.println("7. Create Card");
            System.out.println("8. Create Insurance");
            System.out.println("9. Log Out");

            System.out.println("Please enter your numbered choice (1, 2, 3, 4, 5, 6, 7, 8 or 9)");
            option = scan2.nextInt();
            switch (option) {
                case 1:
                    gerenteClientRegister(users);
                    break;
                case 2:
                    gerenteEmployeeRegister();
                    break;
                case 3:
                    System.out.println("\n--- REGISTROS ---");
                    for (registro r : registros) System.out.println(r);
                    registro.toStringTransferencia();
                    break;
                case 4:
                    habilitarUser(users);
                    break;
                case 5:
                    System.out.println("SIM RECARGADA");
                    break;
                case 6:
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Dime el id del usuario que quiere crear la cuenta: ");
                    String userid = sc.nextLine();
                    User userEncontrado = null;
                    for (int i = 0; i < users.size(); i++) {
                        if (users.get(i).userid.equals(userid)) {
                            userEncontrado = users.get(i);
                        } else {
                            System.out.println("No existe.");
                        }
                    }

                    System.out.println("1. Debit");
                    System.out.println("2. Credit");
                    int type = sc.nextInt();

                    if (type == 1) {
                        String tipo = "debit";
                        DebitAccount d = new DebitAccount("","","","", "", userEncontrado.userid, "debit");
                        d.createBankAccount(tipo);
                    } else {
                        String tipo = "credit";
                        System.out.println("Elige tu cuenta de credito: ");
                        System.out.println("1. 500€ de deuda.");
                        System.out.println("2. 1000€ de dueda.");
                        System.out.println("3. 5000€ de dueda.");
                        System.out.println("Escribe 1, 2 o 3");
                        int opcion = sc.nextInt();
                        if (opcion == 1) {
                            CreditAccount c = new CreditAccount("","","","", "", userEncontrado.userid, 500, 0, "credit");
                            c.createBankAccount(tipo);
                        }

                        if (opcion == 2) {
                            CreditAccount c = new CreditAccount("","","","", "", userEncontrado.userid, 1000, 0, "credit");
                            c.createBankAccount(tipo);
                        }

                        if (opcion == 3) {
                            CreditAccount c = new CreditAccount("","","","", "", userEncontrado.userid, 5000, 0, "credit");
                            c.createBankAccount(tipo);
                        }
                    }
                    break;
                case 7:
                    Scanner scan = new Scanner(System.in);

                    for (int i = 0; i < bankAccounts.size(); i++) {
                        System.out.println((i+1) + ". " + bankAccounts.get(i).IBAN);
                    }
                    System.out.println("Escribe la cuenta al que deseas crearle una tarjeta.");
                    String ibanElegido = scan.nextLine();
                    BankAccount cuentaEncontrada = null;
                    for (int i = 0; i < bankAccounts.size(); i++) {
                        if (bankAccounts.get(i).IBAN.equals(ibanElegido)) {
                            cuentaEncontrada = bankAccounts.get(i);
                            Tarjetas tarjeta = new Tarjetas(cuentaEncontrada.IBAN);
                            tarjetas.add(tarjeta);
                            System.out.println("Tarjeta creada correctamente.");
                        }
                    }
                    break;
                case 8:
                    Scanner scan5 = new Scanner(System.in);
                    System.out.println("Elige que seguro quieres crear");
                    System.out.println("1. Life insurance.");
                    System.out.println("2. Home insurance.");
                    System.out.println("3. Car insurance.");
                    System.out.println("4. Health insurance.");
                    int opcion = scan5.nextInt();
                    if (opcion == 1) {
                        LifeInsurance life = new LifeInsurance(0, null, null, null);
                        life.menuLife();
                    } else if (opcion == 2) {
                        HomeInsurance casa = new HomeInsurance(0, null, null, null);
                        casa.menuHome();
                    } else if (opcion == 3) {
                        CarInsurance coche = new CarInsurance(0, null, null, null);
                        coche.menuCar();
                    } else if (opcion == 4) {
                        HealthInsurance health = new HealthInsurance(0, null, null, null);
                        health.menuHealth();
                    } else {
                        System.out.println("Opcion invalida.");
                    }
                    break;
                case 9:
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

    public void habilitarUser(ArrayList<User> users) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduce el id del usuario que deseas desbloquear: ");
        String desbloquear = scan.nextLine();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).userid.equals(desbloquear) && users.get(i).active==(false)) {
                users.get(i).active=true;
            }
        }
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
