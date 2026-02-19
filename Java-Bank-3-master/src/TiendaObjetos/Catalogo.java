package TiendaObjetos;

public class Catalogo {

    public Categoria tecnologiaYElectronica;
    public Categoria hogar;
    public Categoria movilidadYOcio;
    public Categoria outlet;

    public Catalogo() {
        tecnologiaYElectronica = new Categoria("Tecnología y Electrónica");
        hogar = new Categoria("Hogar");
        movilidadYOcio = new Categoria("Movilidad y Ocio");
        outlet = new Categoria("Outlet");
    }
}
