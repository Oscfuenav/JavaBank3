import Access.AccessScreen;
import Account.*;
import Person.Employee;
import Person.Gerente;
import Person.GestionFicheros;
import Person.User;
import Seguros.InsuranceManager;
import TiendaObjetos.Tienda;

import java.time.LocalDate;
import java.util.ArrayList;

import static Account.Tarjetas.tarjetas;
import static Account.registro.registros;
import static Account.registro.toStringTransferencia;
import static Person.Employee.employees;
import static Person.Gerente.gerentes;
import static Person.User.users;
import static Account.BankAccount.bankAccounts;
import static Seguros.CarInsurance.carInsurance;
import static Seguros.HealthInsurance.healthInsurance;
import static Seguros.HomeInsurance.homeInsurance;
import static Seguros.LifeInsurance.lifeInsurance;

public class Main {

    public static void main(String[] args) {

        //GestionFicheros.Borrar();
        //Gerente g1 = new Gerente("a", "a", "a", "0G");
        //gerentes.add(g1);
        //Employee e1 = new Employee("EnriqueCrackMaster67", "a", "67/67/67", "0E");
        //employees.add(e1);
        //User u1 = new User("a", "a", "a", "0U");
        //users.add(u1);

        //Cargar del fichero
        GestionFicheros gestionFicheros = new GestionFicheros();
        GestionFicheros.cargarTodo(gerentes, employees, users, bankAccounts, registros, tarjetas, lifeInsurance, homeInsurance, carInsurance, healthInsurance);
        gestionFicheros.mostrarClientes(gerentes, employees, users, bankAccounts, registros);
        registro.toStringTransferencia();

        //Pagar deudas
        for (User u : User.users) {
            System.out.println("\n--- Comprobando deudas de: " + u.name + " ---");
            u.pagarDeudasMensuales(u);
        }
        for (User u : User.users) {
            u.pagarDeudaProductosEn12Plazos(u);
        }

        //Pagar seguros
        InsuranceManager insuranceManager = new InsuranceManager();
        insuranceManager.renovarSeguros();

        //Prestamos
        Loan myLoan = new Loan(5000.0, 8.5, 24, "PREST-001");
        myLoan.showLoanDetails();
        BankAccount cuenta = (BankAccount) null;
        for (int i = 0; i < BankAccount.bankAccounts.size(); i++) {
            if (bankAccounts.get(i) instanceof CreditAccount ) {
                cuenta = (CreditAccount) bankAccounts.get(i);
                myLoan.payQuota(cuenta);
            } else if (bankAccounts.get(i) instanceof DebitAccount ) {
                cuenta = (DebitAccount) bankAccounts.get(i);
                myLoan.payQuota(cuenta);
            }
        }

        //Menu principal
        AccessScreen accessScreen = new AccessScreen();
        accessScreen.menu();
    }
}