package TiendaObjetos;

import Account.BankAccount;
import Account.CreditAccount;
import Account.DebitAccount;
import Account.registro;
import Person.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import static Account.registro.registros;
import static Person.User.users;
import static Account.BankAccount.bankAccounts;
public class Tienda {

    Catalogo catalogo;

    public Tienda() {
        this.catalogo = new Catalogo();
        catalogo.tecnologiaYElectronica.productos.add(
                new Producto("Smartphone Galaxy S22", 799.99, "Pantalla AMOLED, 128GB, triple cámara", 1)
        );
        catalogo.tecnologiaYElectronica.productos.add(
                new Producto("PlayStation 5", 549.99, "Consola de nueva generación con SSD ultrarrápido", 2)
        );
        catalogo.tecnologiaYElectronica.productos.add(
                new Producto("Auriculares Sony WH-1000XM4", 299.99, "Cancelación de ruido y sonido premium", 3)
        );
        catalogo.hogar.productos.add(
                new Producto("Aspiradora Dyson V11", 499.99, "Potente aspiradora inalámbrica con autonomía extendida", 4)
        );
        catalogo.hogar.productos.add(
                new Producto("Robot de Cocina Moulinex", 349.99, "Multifunción: cocina, tritura, mezcla y más", 5)
        );
        catalogo.hogar.productos.add(
                new Producto("Cafetera De'Longhi Magnifica", 429.99, "Cafetera automática con molinillo integrado", 6)
        );
        catalogo.hogar.productos.add(
                new Producto("Purificador Xiaomi Mi Air", 129.99, "Filtro HEPA y control mediante app", 7)
        );
        catalogo.movilidadYOcio.productos.add(
                new Producto("Patinete Eléctrico Xiaomi Pro 2", 449.99, "Autonomía de 45 km y velocidad de 25 km/h", 8)
        );
        catalogo.movilidadYOcio.productos.add(
                new Producto("Bicicleta de Montaña Orbea MX", 599.99, "Cuadro de aluminio y suspensión delantera", 9)
        );
        catalogo.movilidadYOcio.productos.add(
                new Producto("Skateboard Cruiser", 89.99, "Tabla ligera ideal para desplazamientos urbanos", 10)
        );
        catalogo.movilidadYOcio.productos.add(
                new Producto("Set de Pesas Ajustables", 159.99, "Hasta 24 kg por mancuerna, ideal para entrenar en casa", 11)
        );
        catalogo.outlet.productos.add(
                new Producto("Televisor LG 55'' OLED (Reacondicionado)", 699.99, "Pantalla OLED 4K, modelo 2022", 12)
        );
        catalogo.outlet.productos.add(
                new Producto("Portátil HP Pavilion (Exposición)", 499.99, "Intel i5, 8GB RAM, SSD 512GB", 13)
        );
        catalogo.outlet.productos.add(
                new Producto("Auriculares JBL Tune 500BT", 29.99, "Bluetooth, ligeros y con buen sonido", 14)
        );
        catalogo.outlet.productos.add(
                new Producto("Monitor Samsung 24''", 99.99, "Full HD, panel IPS, ligero desgaste estético", 15)
        );


    }

    public void mostrarMenu(User currentUser) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 5) {
            System.out.println("===== MENÚ DE LA TIENDA =====");
            System.out.println("1. Tecnología y Electrónica");
            System.out.println("2. Hogar");
            System.out.println("3. Movilidad y Ocio");
            System.out.println("4. Outlet");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Has seleccionado: " + catalogo.tecnologiaYElectronica.nombre);
                    mostrarProductos(catalogo.tecnologiaYElectronica, currentUser);
                    mostrarCuentas(currentUser);
                    break;

                case 2:
                    System.out.println("Has seleccionado: " + catalogo.hogar.nombre);
                    mostrarProductos(catalogo.hogar,currentUser);
                    mostrarCuentas(currentUser);
                    break;

                case 3:
                    System.out.println("Has seleccionado: " + catalogo.movilidadYOcio.nombre);
                    mostrarProductos(catalogo.movilidadYOcio, currentUser);
                    mostrarCuentas(currentUser);
                    break;

                case 4:
                    System.out.println("Has seleccionado: " + catalogo.outlet.nombre);
                    mostrarProductos(catalogo.outlet, currentUser);
                    mostrarCuentas(currentUser);
                    break;

                case 5:
                    System.out.println("Saliendo del programa...");
                    currentUser.accountMenu(currentUser,registros);
                default:
                    System.out.println("");

            }
        }
    }
    public void mostrarCuentas(User currentUser) {
        for (int i = 0; i < BankAccount.bankAccounts.size(); i++) {
            if (currentUser.userid.equals(BankAccount.bankAccounts.get(i).userid)) {
                System.out.println((i+1) + ". " + BankAccount.bankAccounts.get(i).IBAN);

            }
        }
    }
    public void mostrarProductos(Categoria categoria, User currentUser) {

        if (categoria.productos.isEmpty()) {
            System.out.println("No hay productos en esta categoría.");
            return;
        }

        System.out.println("Productos disponibles:");
        for (Producto p : categoria.productos) {
            System.out.println(p.numero+"-" + p.nombre + " | " + p.precio + "€ | " + p.descripcion);
        }

        System.out.println("Dime cual quieres por el numero");
        Scanner sc = new Scanner(System.in);
        int elegido=sc.nextInt();
        Producto opcionProduct = null;
        for (Producto p : categoria.productos) {
            if (p.numero == elegido) {
                opcionProduct = p;
                System.out.println("Productos elegido: " + p.nombre);
            }
        }
        for (int i = 0; i < BankAccount.bankAccounts.size(); i++) {
            if (currentUser.userid.equals(BankAccount.bankAccounts.get(i).userid)) {
                System.out.println((i+1) + ". " + BankAccount.bankAccounts.get(i).IBAN);
            }
        }
        System.out.println("Escribe el IBAN: ");
        Scanner sc2 = new Scanner(System.in);
        String Elegido = sc2.nextLine();
        BankAccount opcion = null;
        for (int i = 0; i < BankAccount.bankAccounts.size(); i++) {
            if (currentUser.userid.equals(BankAccount.bankAccounts.get(i).userid)) {
                if (Elegido.equals(BankAccount.bankAccounts.get(i).IBAN)) {
                    opcion = BankAccount.bankAccounts.get(i);
                }
            }
        }
        double balanceantes = opcion.balance;
        System.out.println("¿Quieres pagar este producto a plazos? (s/n)");
        String respuesta = sc2.nextLine();

        if (respuesta.equalsIgnoreCase("s")) {
            activarPagoAPlazos(currentUser, opcionProduct, opcion);
            return;
        }
        if (opcion instanceof DebitAccount) {
            pagosDebito(opcion, opcionProduct, balanceantes);
        }
        if (opcion instanceof CreditAccount) {
            CreditAccount creditAccount = (CreditAccount) opcion;
            pagosCredito(creditAccount, opcionProduct, balanceantes);
        }
    }

    public void pagosDebito(BankAccount account , Producto producto, double balanceantes) {
        double sacar= producto.precio;
        if (sacar > account.balance) {
            System.out.println("Insufficient funds");
            return;
        }
        account.balance -= sacar;
        System.out.println("Operation successful");
        int balancedespues= (int) account.balance;
        registro d = new registro(sacar, account.IBAN , balancedespues, balanceantes,  producto.nombre, account.userid, 0);
        registros.add(d);
    }
    public void pagosCredito(CreditAccount credit , Producto producto, double balanceantes) {
        double amount= producto.precio;

        double available = credit.balance - amount;
        if (available < 0) {
            credit.deudaCredit += available*-1;
            if(credit.deudaCredit > credit.maxDeuda){
                System.out.println("No tienes saldo suficiente");
                credit.deudaCredit -= available*-1;
                return;
            }
            credit.balance=0;
            System.out.println("Tienes " + credit.deudaCredit + " de deuda.");
            return;
        }
        credit.balance -= amount;
        System.out.println("Hecho.");
        int balancedespues = (int) credit.balance;
        registro d = new registro(producto.precio, credit.IBAN , balancedespues, balanceantes,  producto.nombre, credit.userid,0);
        registros.add(d);
    }
    public void activarPagoAPlazos(User user, Producto producto, BankAccount opcion) {;
        System.out.println("Precio total: " + producto.precio + "€");
        System.out.println("Se dividirá en 12 pagos mensuales.");
        // Crear registro de deuda
        registro r = new registro(
                producto.precio,     // amount
                opcion.IBAN,             // IBAN
                0,                   // finalmount
                0,                   // beforemount
                "Deuda producto",    // tipo
                user.userid,         // usuario
                (int) producto.precio
        );
        registro.registros.add(r);
    }
}