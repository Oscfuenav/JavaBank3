package Person;

import Account.BankAccount;
import Account.registro;

import java.io.*;
import java.util.ArrayList;

import static Account.registro.registros;
import static Account.registro.toStringTransferencia;

public class GestionFicheros   {

    /** Ruta del archivo donde se guardan los datos */
    private static final String ruta = "Usuarios.dat";

    public GestionFicheros() {}

    /** Elimina el archivo completo */
    public static void Borrar() {
        File f = new File(ruta);
        if (f.exists()) {
            f.delete();
            System.out.println("Archivo eliminado correctamente.");
        } else {
            System.out.println("No hay archivo que borrar.");
        }
    }

    /** Guarda todas las listas en el fichero */
    public static void guardarClientes(ArrayList<Gerente> gerentes, ArrayList<Employee> employees, ArrayList<User> users, ArrayList<BankAccount> bankAccounts, ArrayList<registro> registros) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {

            // ORDEN CORRECTO
            oos.writeObject(gerentes);
            oos.writeObject(employees);
            oos.writeObject(users);
            oos.writeObject(bankAccounts);
            oos.writeObject(registros);
            System.out.println("Datos guardados correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Carga todas las listas desde el fichero */
    public static void cargarTodo(ArrayList<Gerente> gerentes, ArrayList<Employee> employees, ArrayList<User> users, ArrayList<BankAccount> bankAccounts, ArrayList<registro>registros) {

        File f = new File(ruta);

        if (!f.exists() || f.length() == 0) {
            System.out.println("Archivo vacío o inexistente. No se cargó nada.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {

            // ORDEN CORRECTO (MISMO QUE EN GUARDAR)
            ArrayList<Gerente> g = (ArrayList<Gerente>) ois.readObject();
            ArrayList<Employee> e = (ArrayList<Employee>) ois.readObject();
            ArrayList<User> u = (ArrayList<User>) ois.readObject();
            ArrayList<BankAccount> b = (ArrayList<BankAccount>) ois.readObject();
            ArrayList<registro> r = (ArrayList<registro>) ois.readObject();
            gerentes.addAll(g);
            employees.addAll(e);
            users.addAll(u);
            bankAccounts.addAll(b);
            registros.addAll(r);
            System.out.println("Datos cargados correctamente.");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /** Muestra el contenido del fichero */
    public void mostrarClientes(ArrayList<Gerente> gerentes, ArrayList<Employee> employees, ArrayList<User> users, ArrayList<BankAccount> bankAccounts,ArrayList<registro>registros) {

        System.out.println("\n--- CUENTAS BANCARIAS ---");
        for (BankAccount b : bankAccounts) System.out.println(b);

        System.out.println("\n--- GERENTES ---");
        for (Gerente g : gerentes) System.out.println(g);

        System.out.println("\n--- EMPLEADOS ---");
        for (Employee e : employees) System.out.println(e);

        System.out.println("\n--- USUARIOS ---");
        for (User u : users) System.out.println(u);

        System.out.println("\n--- REGISTROS ---");
        for (registro r : registros) System.out.println(r);
    }
}
