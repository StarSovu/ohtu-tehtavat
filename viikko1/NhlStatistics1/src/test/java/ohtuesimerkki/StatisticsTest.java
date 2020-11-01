/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sovu
 */
public class StatisticsTest {
 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }  
    
    @Test
    public void findPlayer() {
        Player player = new Player("Semenko", "EDM", 4, 12);
        
        // tutkitaan, palautetaanko haluttu pelaaja
        assertEquals(player.toString(), stats.search("Semenko").toString());
    }
    
    @Test
    public void incorrectPlayer() {
        // palautetaan null, kun etsittävää pelaajaa ei ole listalla
        assertEquals(null, stats.search("I"));
    }
    
    @Test
    public void correctPlayerList() {
        ArrayList<Player> playersOfEDM = new ArrayList<>();
        playersOfEDM.add(new Player("Semenko", "EDM", 4, 12));;
        playersOfEDM.add(new Player("Kurri",   "EDM", 37, 53));
        playersOfEDM.add(new Player("Gretzky", "EDM", 35, 89));
        
        //tarkistaa, antaako komento tosiaankin yllä määritellyt joukkueen EDM pelaajat
        assertEquals(playersOfEDM.toString(), stats.team("EDM").toString());
    }
    
    @Test
    public void correctTopScorers() {
        ArrayList<Player> top3 = new ArrayList<>();
        top3.add(new Player("Gretzky", "EDM", 35, 89));
        top3.add(new Player("Lemieux", "PIT", 45, 54));
        top3.add(new Player("Yzerman", "DET", 42, 56));
        
        //tarkistaa, ovatko top 3 pelaajaa oikein (alkuperäinen ohjelma antaa 3 pelaajaa syötteellä 2)
        assertEquals(top3.toString(), stats.topScorers(2).toString());
    }
    
}

