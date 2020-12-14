package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class KiviPaperiSakset {
    protected static final Scanner scanner = new Scanner(System.in);
    
    public void pelaa() {
        Tuomari tuomari = new Tuomari();
        
        String ekanSiirto = ensimmaisenSiirto();
        String tokanSiirto = toisenSiirto(ekanSiirto);
        
        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();
            
            ekanSiirto = ensimmaisenSiirto();
            tokanSiirto = toisenSiirto(ekanSiirto);
        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }
    
    protected String ensimmaisenSiirto() {
        System.out.println("Ensimmäisen pelaajan siirto: ");
        return scanner.nextLine();
    }

    abstract protected String toisenSiirto(String ekanSiirto);
    
    protected static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
    
    public static KiviPaperiSakset kaksinpeli() {
        return new KPSPelaajaVsPelaaja();
    }
    
    public static KiviPaperiSakset yksinpeli() {
        return new KPSTekoaly();
    }
    
    public static KiviPaperiSakset pahaYksinpeli() {
        return new KPSParempiTekoaly();
    }
    
}