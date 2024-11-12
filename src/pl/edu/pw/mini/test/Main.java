package pl.edu.pw.mini.test;

import pl.edu.pw.mini.owady.Apis;

import java.util.Collections;

public class Main {

    /*
     * Klasa Main - 1.5p
     * Klasa Apis - 7.0p
     * Przejrzysty/czytelny kod - 0.5p
     */
    public static void main(String[] args) {

        Apis ul = new Apis();
        System.out.println("W ulu jest: " + ul.iloscPszczolWUlu() + " pszczół");
        ul.infoOUlu();
        //--------TODO-------------------------
        // Dodanie pszczół do listy
        //a)
        ul.dodajPszczole(ul.new Truten("truten", 4));
        ul.dodajPszczole(new Apis.Robotnica("robotnica", 2));
        ul.dodajPszczole(new Apis.KrolowaMatka("krolowa", 5));
        //-------------------------------------
        ul.zyciePszczol();
        System.out.println("\nW ulu jest: " + ul.iloscPszczolWUlu() + " pszczół");
        ul.infoOUlu();
        System.out.println("\nPszczoły posortowane wg siły i imienia:");
        ul.sortujWgSilyIImienia();
        ul.infoOUlu();
        System.out.println("\nPszczoły posortowane wg wieku:");
        //--------TODO-------------------------
        //Sortowanie listy pszczół za pomocą komparatora
        //b)
        Collections.sort(ul.getPszczoly(), ul.getPorownanieWieku());

        //-------------------------------------
        ul.infoOUlu();
        System.out.println("\nŻołnierz:");
        //--------TODO-------------------------
        //Dodanie żołnierza
        //c)
        ul.dodajZolnierza("zolnierz", 4);

        //-------------------------------------
        System.out.println((ul.getPszczoly().get(ul.getPszczoly().size() - 1)));
        System.out.println("\nWątki pszczół:");
        ul.watkiPszczol();
    }

}
