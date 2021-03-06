package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;
    
    @Before
    public void setUp() {
        // luodaan ensin mock-oliot
        pankki = mock(Pankki.class);
        
        viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);
        
        varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        // sitten testattava kauppa 
        k = new Kauppa(varasto, pankki, viite);    
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilinsiirtoKutsutaanOikeillaParametreilla() {
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5));
    }
    
    @Test
    public void ostoksenPaatyttyaKahdellaEriTuotteellaMetodiaTilintsiirtoKutsutaanOikeillaParametreilla() {
        // määritellään tuote 2 (kakku)
        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kakku", 20));

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan molempia tuotteita 1
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(25));
    }
    
    @Test
    public void ostoksenPaatyttyaKahdellaSamallaTuotteellaMetodiaTilintsiirtoKutsutaanOikeillaParametreilla() {
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa kaksi kertaa
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(10));
    }
    
    @Test
    public void ostoksenPaatyttyaRiittavallaJaLoppuneellaTuotteellaMetodiaTilintsiirtoKutsutaanOikeillaParametreilla() {
        // määritellään tuote 2 (kakku) joka on loppunut
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kakku", 20));

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan molempia tuotteita 1
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5));
    }
    
    @Test
    public void metodiAloitaAsiointitNollaaEdellisenOstoksenTiedot() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        
        k.aloitaAsiointi();
        k.tilimaksu("pekka", "12345");
        
        // varmistus, että pankin metodia on kutsuttu summalla 0
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), eq(0));
    }
    
    @Test
    public void metodiTilisiirtoPyytaaUudenViitenumeronJokaKerta() {
        when(viite.uusi())
            .thenReturn(1)
            .thenReturn(2)
            .thenReturn(3);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        // varmistetaan, että nyt käytössä ensimmäisenä pyydetty viite
        verify(pankki).tilisiirto(anyString(), eq(1), anyString(), anyString(), anyInt());
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        // ... toisena pyydetty viite
        verify(pankki).tilisiirto(anyString(), eq(2), anyString(), anyString(), anyInt());
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        // ... ja kolmantena pyydetty viite        
        verify(pankki).tilisiirto(anyString(), eq(3), anyString(), anyString(), anyInt());  
    }
    
    @Test
    public void koristaPoistettuaTuotettaEiOsteta() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.poistaKorista(1);
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), eq(0));
    }

}

