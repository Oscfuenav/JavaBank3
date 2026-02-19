package Person;

import Account.BankAccount;
import Account.Tarjetas;
import Account.registro;
import Seguros.CarInsurance;
import Seguros.HealthInsurance;
import Seguros.HomeInsurance;
import Seguros.LifeInsurance;

import java.io.*;
import java.util.ArrayList;

import static Account.Tarjetas.tarjetas;
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
    public static void guardarClientes(ArrayList<Gerente> gerentes, ArrayList<Employee> employees, ArrayList<User> users, ArrayList<BankAccount> bankAccounts, ArrayList<registro> registros, ArrayList<Tarjetas> tarjetas, ArrayList<LifeInsurance> lifeInsurance, ArrayList<HomeInsurance> homeInsurance, ArrayList<CarInsurance>  carInsurance, ArrayList<HealthInsurance> healthInsurance) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {

            // ORDEN CORRECTO
            oos.writeObject(gerentes);
            oos.writeObject(employees);
            oos.writeObject(users);
            oos.writeObject(bankAccounts);
            oos.writeObject(registros);
            oos.writeObject(tarjetas);
            oos.writeObject(lifeInsurance);
            oos.writeObject(homeInsurance);
            oos.writeObject(carInsurance);
            oos.writeObject(healthInsurance);
            System.out.println("Datos guardados correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Carga todas las listas desde el fichero */
    public static void cargarTodo(ArrayList<Gerente> gerentes, ArrayList<Employee> employees, ArrayList<User> users, ArrayList<BankAccount> bankAccounts, ArrayList<registro> registros, ArrayList<Tarjetas> tarjetas, ArrayList<LifeInsurance> lifeInsurance, ArrayList<HomeInsurance> homeInsurance, ArrayList<CarInsurance>  carInsurance, ArrayList<HealthInsurance> healthInsurance) {

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
            ArrayList<Tarjetas> c = (ArrayList<Tarjetas>) ois.readObject();
            ArrayList<LifeInsurance> l = (ArrayList<LifeInsurance>) ois.readObject();
            ArrayList<HomeInsurance> h = (ArrayList<HomeInsurance>) ois.readObject();
            ArrayList<CarInsurance> cs = (ArrayList<CarInsurance>) ois.readObject();
            ArrayList<HealthInsurance> he = (ArrayList<HealthInsurance>) ois.readObject();
            gerentes.addAll(g);
            employees.addAll(e);
            users.addAll(u);
            bankAccounts.addAll(b);
            registros.addAll(r);
            tarjetas.addAll(c);
            lifeInsurance.addAll(l);
            homeInsurance.addAll(h);
            carInsurance.addAll(cs);
            healthInsurance.addAll(he);
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
