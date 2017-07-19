package gf.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Lyonnel Dzotang on 18/07/2017.
 */
public class DateTest {

    public static void main(String[] args) {
        Calendar debut = new GregorianCalendar(2017, 5, 1);
        Calendar fin = new GregorianCalendar(2017, 9, 4);

        ArrayList<Integer> seance = new ArrayList<>();
        int pointeur = debut.get(Calendar.DAY_OF_MONTH);
        int finPointeur = fin.get(Calendar.DAY_OF_MONTH);
        System.out.println("pointeur debut: " + pointeur);
        System.out.println("pointeur fin: " + finPointeur);
        while (pointeur != finPointeur) {
            seance.add(debut.get(Calendar.DAY_OF_MONTH));
            debut.add(Calendar.DAY_OF_MONTH, 7);
            pointeur = debut.get(Calendar.DAY_OF_MONTH);
        }

        System.out.println("Valeurs: "+ seance.toString());
        System.out.println("Nombre: "+ seance.size());
    }
}
