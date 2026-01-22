package Person;

import Account.BankAccount;
import Person.User;

import java.io.*;
import java.util.ArrayList;

public class GestionFicheros {
    private static final String ruta = "C:\\Users\\Usuario\\Downloads\\Usuarios.dat";

    public GestionFicheros() {

    }
    public static void Borrar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject("");
            System.out.println("Lista guardada correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void guardarClientes(ArrayList<Gerente> gerentes, ArrayList<Employee> employees, ArrayList<User> users ) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(gerentes);
            oos.writeObject(employees);
            oos.writeObject(users);
            System.out.println("Lista guardada correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void cargarTodo(ArrayList<Gerente> gerentes, ArrayList<Employee> employees, ArrayList<User>users ) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            ArrayList<Gerente> g = (ArrayList<Gerente>) ois.readObject();
            ArrayList<Employee> e = (ArrayList<Employee>) ois.readObject();
            ArrayList<User> b = (ArrayList<User>) ois.readObject();
            gerentes.addAll(g);
            employees.addAll(e);
            users.addAll(b);
            System.out.println("Listas cargadas correctamente.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void mostrarClientes(ArrayList<Gerente> gerentes,ArrayList<Employee> employees,  ArrayList<User> users) {
        System.out.println("\n--- GERENTES EN FICHERO ---");
        for (Gerente g : gerentes) {
            System.out.println(g);
        }
        System.out.println("\n--- EPLOYEES EN FICHERO ---");
        for (Employee e : employees) {
            System.out.println(e);
        }
        System.out.println("\n--- USERS EN FICHERO ---");
        for (User u : users) {
            System.out.println(u);
        }
    }
}
