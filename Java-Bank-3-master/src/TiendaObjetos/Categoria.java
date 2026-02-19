package TiendaObjetos;

import java.util.ArrayList;

public class Categoria {

    public String nombre;
    public ArrayList<Producto> productos;

    public Categoria(String nombre) {
        this.nombre = nombre;
        this.productos = new ArrayList<>();
    }
}
