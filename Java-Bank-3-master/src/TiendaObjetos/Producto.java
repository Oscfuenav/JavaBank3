package TiendaObjetos;

public class Producto {

    public int numero;
    public String nombre;
    public double precio;
    public String descripcion;

    public Producto(String nombre, double precio, String descripcion, int numero) {
        this.numero=numero;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }
}
