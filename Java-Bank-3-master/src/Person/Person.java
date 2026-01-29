package Person;

import java.io.Serializable;

/**
 * Clase abstracta que representa a una persona dentro del sistema bancario.
 *
 * <p>Es la clase base para:
 * <ul>
 *     <li>{@link User}</li>
 *     <li>{@link Employee}</li>
 *     <li>{@link Gerente}</li>
 * </ul>
 * </p>
 *
 * <p>Contiene atributos comunes como:
 * <ul>
 *     <li>ID</li>
 *     <li>Nombre</li>
 *     <li>Fecha de nacimiento</li>
 *     <li>Contraseña</li>
 *     <li>Estado activo/bloqueado</li>
 * </ul>
 * </p>
 *
 * <p>Define métodos abstractos que deben implementar las clases hijas:
 * <ul>
 *     <li>Validación de contraseña</li>
 *     <li>Validación de fecha</li>
 *     <li>Menú de cuenta</li>
 * </ul>
 * </p>
 */
public abstract class Person implements Serializable {

    /** Identificador único de la persona. */

    /** Nombre completo de la persona. */
    public String name = "";

    /** Fecha de nacimiento en formato dd/mm/yyyy. */
    public String birthDate = "";

    /** Contraseña de acceso al sistema. */
    public String password = "";

    /** Estado de la cuenta (true = activa, false = bloqueada). */
    public boolean active = true;

    /**
     * Constructor de la clase Person.
     *
     * @param name nombre completo
     * @param password contraseña
     * @param birthDate fecha de nacimiento
     */
    public Person(String name, String password, String birthDate) {
        this.name = name;
        this.password = password;
        this.birthDate = birthDate;
        this.active = true;
    }

    /**
     * Valida una contraseña según las reglas definidas por la clase hija.
     *
     * @param password contraseña a validar
     * @return true si es válida, false en caso contrario
     */
    abstract boolean checkPassword(String password);

    /**
     * Valida una fecha según las reglas definidas por la clase hija.
     *
     * @param date fecha a validar
     * @return true si es válida, false en caso contrario
     */
    abstract boolean checkDate(String date);

    /**
     * Muestra el menú correspondiente al tipo de persona (usuario, empleado o gerente).
     */
    public abstract void accountMenu(User currentUser);
}
