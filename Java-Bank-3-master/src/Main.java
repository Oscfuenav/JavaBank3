import Access.AccessScreen;
import Account.BankAccount;
import Account.CreditAccount;
import Account.DebitAccount;
import Account.registro;
import Person.Employee;
import Person.Gerente;
import Person.GestionFicheros;
import Person.User;

import java.util.ArrayList;

import static Account.registro.registros;
import static Account.registro.toStringTransferencia;
import static Person.Employee.employees;
import static Person.Gerente.gerentes;
import static Person.User.users;
import static Account.BankAccount.bankAccounts;

public class Main {

    public static void main(String[] args) {
        GestionFicheros.Borrar();
        Gerente g1 = new Gerente("a", "a", "a", "0G");
        gerentes.add(g1);
        User u1 = new User("a", "a", "a", "0U");
        users.add(u1);
        GestionFicheros gestionFicheros = new GestionFicheros();
        GestionFicheros.cargarTodo(gerentes, employees, users, bankAccounts, registros);
        gestionFicheros.mostrarClientes(gerentes, employees, users, bankAccounts, registros);
        registro.toStringTransferencia();
        AccessScreen accessScreen = new AccessScreen();
        accessScreen.menu();
    }
}