package pl.edu.pw.mini.owady;

import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Apis {

    private ArrayList<Pszczola> pszczoly;

    public int iloscPszczolWUlu() {return pszczoly.size();}
    public ArrayList<Pszczola> getPszczoly() {return pszczoly;}
    public void infoOUlu() {for (Pszczola p : pszczoly) System.out.println(p);}

    //--------TODO----------
    public static abstract class Pszczola implements Runnable{
        private String imie;
        private int silaAtaku;
        private int wiek;

        public int getWiek(){
            return wiek;
        }
        public int getSilaAtaku(){
            return silaAtaku;
        }
        public String getImie(){
            return imie;
        }
        public void setSilaAtaku(int sila){
            this.silaAtaku = sila;
        }
        public Pszczola(String imie, int wiek) {
            this.imie = imie;
            this.wiek = wiek;
        }
    }
    public static class KrolowaMatka extends Pszczola{
        private int iloscJaj;

        public void zaplodnienie(){
            iloscJaj += 1000;
        }

        @Override
        public void run() {
            System.out.println("Glod godowy...");
        }

        public KrolowaMatka(String imie, int wiek) {
            super(imie, wiek);
            iloscJaj = 0;
            setSilaAtaku(100);
        }
        public int getIloscJaj(){
            return iloscJaj;
        }
        @Override
        public String toString() {
            return "Królowa " + getImie() + " (atak: " + getSilaAtaku() + "), żyję " + getWiek() + " dni i będę matką dla " + getIloscJaj() + " mlodych pszczolek";
        }
    }
//    truten nie moze byc static zeby pozniej moc sie odwolac do listy pszczoly ktora nie jest static
    public class Truten extends Pszczola{
        private Random random = new Random();
        private boolean przydatny;

        public void zaplodnienie(KrolowaMatka krolowa){
            if (przydatny) {
                przydatny = false;
                krolowa.zaplodnienie();
                System.out.println("Można umierać...");
            }
        }

        @Override
        public void run() {
            if(random.nextBoolean()){
                for(Pszczola pszczola : pszczoly){
                    if (pszczola instanceof KrolowaMatka){
                        ((KrolowaMatka) pszczola).zaplodnienie();
                        break;
                    }
                }
            }
        }

    public Truten(String imie, int wiek) {
        super(imie, wiek);
        setSilaAtaku(0);
        przydatny = true;
    }

    @Override
    public String toString() {
        return "Truten " + getImie() + " (atak: " + getSilaAtaku() + "), żyję " + getWiek() + " dni";
    }
}
    public static class Robotnica extends Pszczola{
        private Random random = new Random();
        private int iloscWyprodukowanegoMiodu;
        public void zbierajNektar(int ile_miodu){
            iloscWyprodukowanegoMiodu += ile_miodu;
        }
        public int getIloscWyprodukowanegoMiodu(){
            return iloscWyprodukowanegoMiodu;
        }
        @Override
        public void run() {
            zbierajNektar(random.nextInt(21));
        }

        public Robotnica(String imie, int wiek) {
            super(imie, wiek);
            iloscWyprodukowanegoMiodu = 0;
            setSilaAtaku(50);
        }

        @Override
        public String toString() {
            return "Robotnica " + getImie() + " (atak: " + getSilaAtaku() + "), żyję " + getWiek() + " dni i zrobilam " + getIloscWyprodukowanegoMiodu() + " baryłek miodu";
        }
    }
    private static class PorownanieWieku implements Comparator<Pszczola>{
        @Override
        public int compare(Pszczola o1, Pszczola o2) {
            return Integer.compare(o1.getWiek(), o2.getWiek());
        }
    }
    public Comparator<Pszczola> getPorownanieWieku(){
        return new PorownanieWieku();
    }

    public Apis() {
        this.pszczoly = new ArrayList<>();
        dodajPszczole(new KrolowaMatka("Imie_krolowa_1",4));
        dodajPszczole(new Truten("Imie_truten_1",3));
        dodajPszczole(new Truten("Imie_truten_2",4));
        dodajPszczole(new Truten("Imie_truten_3",3));
        dodajPszczole(new Robotnica("Imie_robotnica_1",4));
        dodajPszczole(new Robotnica("Imie_robotnica_2",4));
        dodajPszczole(new Robotnica("Imie_robotnica_3",4));
    }
    public void zyciePszczol(){
        int licznik_trutni = 0;
        int licznik_robotnic = 0;
        KrolowaMatka krolowa = null;
        for (Pszczola pszczola : pszczoly){
            if (pszczola instanceof KrolowaMatka){
                krolowa = (KrolowaMatka) pszczola;
                break;
            }
        }
        for (Pszczola pszczola : pszczoly){
            if (licznik_trutni < 2){
                if(pszczola instanceof Truten){
                    ((Truten) pszczola).zaplodnienie(krolowa);
                }
            }
            if (licznik_robotnic < 3){
                if (pszczola instanceof Robotnica){
                    ((Robotnica) pszczola).zbierajNektar(5);
                }
            }
        }
    }
    public void sortujWgSilyIImienia(){
        Collections.sort(pszczoly, new PorownanieWieku() {
            @Override
            public int compare(Pszczola o1, Pszczola o2) {
                if (o1.getSilaAtaku() != o2.getSilaAtaku()){
                    return Integer.compare(o1.getSilaAtaku(),o2.getSilaAtaku());
                }
                return o1.getImie().compareTo(o2.getImie());
            }
        });
    }
    public void dodajZolnierza(String imie_1, int wiek_1){
        dodajPszczole(new Pszczola(imie_1, wiek_1) {
            @Override
            public String toString() {
                return "Żołnierz " + getImie() + " (atak: " + silaAtaku + "), żyję " + getWiek() + " dni i potrafię użądlić";
            }
            protected int silaAtaku = 99;

            @Override
            public void run() {
                System.out.println("Walka to moje życie");
            }
        });
    }
    public void dodajPszczole(Pszczola pszczola){
        pszczoly.add(pszczola);
    }
    public void watkiPszczol(){
        for (Pszczola pszczola : pszczoly){
            Thread thread = new Thread(pszczola);
            thread.start();
        }
    }
}
