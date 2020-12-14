package ohtu.kivipaperisakset;

public class KPSPelaajaVsPelaaja extends KiviPaperiSakset {
    @Override
    protected String toisenSiirto(String ekanSiirto) {
        System.out.println("Toisen pelaajan siirto: ");
        return scanner.nextLine();  
    }
}