
package ohtu.intjoukkosovellus;

import static java.lang.Integer.min;

public class IntJoukko {

    public final static int KAPASITEETTI = 5,
                            OLETUSKASVATUS = 5;
    private int kasvatuskoko;
    private int[] ljono;
    private int alkioidenLkm;

    public IntJoukko() {
        joukonMuodostus(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        joukonMuodostus(kapasiteetti, OLETUSKASVATUS);

    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        joukonMuodostus(kapasiteetti, kasvatuskoko);

    }
    
    private void joukonMuodostus(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            return;
        }
        
        ljono = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            ljono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm == ljono.length) {
                ljono = taulukonEriPituinenKopio(ljono, alkioidenLkm + kasvatuskoko);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        int kohta = etsiKohta(luku);
        return kohta != -1;
    }

    public boolean poista(int luku) {
        int kohta = etsiKohta(luku);
        if (kohta != -1) {
            for (int j = kohta; j < alkioidenLkm - 1; j++) {
                ljono[j] = ljono[j + 1];
            }
            alkioidenLkm--;
            return true;
        }

        return false;
    }
    
    private int etsiKohta(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                return i;
            }
        }
        
        return -1;
    }
    
    private int[] taulukonEriPituinenKopio(int[] taulukko, int pituus) {
        int[] kopio = new int[pituus];
        for (int i = 0; i < min(pituus, taulukko.length); i++) {
            kopio[i] = taulukko[i];
        }
        
        return kopio;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += ljono[i];
                tuotos += ", ";
            }
            tuotos += ljono[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = taulukonEriPituinenKopio(ljono, alkioidenLkm);
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko joukko1, IntJoukko joukko2) {
        IntJoukko yhdiste = new IntJoukko();
        yhdiste = lisaaJoukkoonJoukko(yhdiste, joukko1);
        yhdiste = lisaaJoukkoonJoukko(yhdiste, joukko2);
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko joukko1, IntJoukko joukko2) {
        IntJoukko leikkaus = yhdiste(joukko1, joukko2);
        leikkaus = poistaJoukostaJoukko(leikkaus, erotus(joukko1,joukko2));
        leikkaus = poistaJoukostaJoukko(leikkaus, erotus(joukko2,joukko1));
        return leikkaus;

    }
    
    public static IntJoukko erotus (IntJoukko joukko1, IntJoukko joukko2) {
        IntJoukko erotus = new IntJoukko();
        erotus = lisaaJoukkoonJoukko(erotus, joukko1);
        erotus = poistaJoukostaJoukko(erotus, joukko2);
        return erotus;
    }
    
    private static IntJoukko lisaaJoukkoonJoukko(IntJoukko joukko, IntJoukko lisattava) {
        int[] lisattavaTaulu = lisattava.toIntArray();
        for (int i = 0; i < lisattavaTaulu.length; i++) {
            joukko.lisaa(lisattavaTaulu[i]);
        }
        return joukko;
    }
    
    private static IntJoukko poistaJoukostaJoukko(IntJoukko joukko, IntJoukko poistettava) {
        int[] poistettavaTaulu = poistettava.toIntArray();
        for (int i = 0; i < poistettavaTaulu.length; i++) {
            joukko.poista(poistettavaTaulu[i]);
        }
        return joukko;
    }
        
}
