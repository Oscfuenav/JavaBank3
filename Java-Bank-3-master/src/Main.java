import Access.AccessScreen;
import Account.BankAccount;
import Person.Employee;
import Person.GestionFicheros;
import Person.Gerente;

import static Person.Employee.employees;

import static Person.Gerente.gerentes;
import static Person.User.users;
import static Person.User.users;

import Person.User;
public class Main {
    public static void main(String[] args){
        GestionFicheros gestionFicheros = new GestionFicheros();
        GestionFicheros.cargarTodo(gerentes, employees, users);
        gestionFicheros.mostrarClientes(gerentes, employees,users);
        AccessScreen accessScreen = new AccessScreen();
        accessScreen.menu();
    }
}
