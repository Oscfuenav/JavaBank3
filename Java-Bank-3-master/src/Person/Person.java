package Person;

import java.io.Serializable;

public abstract class Person implements Serializable {
    public int id = 0;
    public String name="", birthDate="", password="";
    public boolean active=true;



    public Person(String name, String password, String birthDate, int id) {
        this.name = name;
        this.password = password;
        this.birthDate = birthDate;
        this.id = id;
        this.active = true;
    }

    abstract boolean checkPassword(String password);

    abstract boolean checkDate(String date);

    public abstract void accountMenu();

}
