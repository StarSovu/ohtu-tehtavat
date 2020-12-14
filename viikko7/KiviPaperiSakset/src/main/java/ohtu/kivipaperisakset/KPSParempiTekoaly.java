package ohtu.kivipaperisakset;

public class KPSParempiTekoaly extends KiviPaperiSakset {
    private TekoalyParannettu tekoaly = new TekoalyParannettu(20);

    @Override
    protected String toisenSiirto(String ekanSiirto) {
        String tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);
        tekoaly.asetaSiirto(ekanSiirto);
        return tokanSiirto;
    }
}
