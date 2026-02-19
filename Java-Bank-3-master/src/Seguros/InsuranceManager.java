package Seguros;
import Person.User;

import java.time.LocalDate;

import static Person.User.users;
import static Seguros.CarInsurance.comprobarYCobrarCar;
import static Seguros.HealthInsurance.comprobarYCobrarHealth;
import static Seguros.HomeInsurance.comprobarYCobrarHome;
import static Seguros.LifeInsurance.comprobarYCobrarLife;

public class InsuranceManager {

    public static void renovarSeguros() {

        for (User user : users) {


            for (LifeInsurance life : LifeInsurance.lifeInsurance) {
                if (life.userid.equals(user.userid)) {
                    comprobarYCobrarLife(life);
                }
            }


            for (CarInsurance car : CarInsurance.carInsurance) {
                if (car.userid.equals(user.userid)) {
                    comprobarYCobrarCar(car);
                }
            }


            for (HomeInsurance home : HomeInsurance.homeInsurance) {
                if (home.userid.equals(user.userid)) {
                    comprobarYCobrarHome(home);
                }
            }


            for (HealthInsurance health : HealthInsurance.healthInsurance) {
                if (health.userid.equals(user.userid)) {
                    comprobarYCobrarHealth(health);
                }
            }
        }
    }
}
