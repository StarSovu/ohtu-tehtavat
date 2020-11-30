package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Summa extends Komento {
    public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        this.asetaEdellinenTulos();
        
        int summattava = Integer.parseInt(this.syotekentta.getText());
        this.sovellus.plus(summattava);
        int summa = this.sovellus.tulos();
        
        this.syotekentta.setText("");
        this.tuloskentta.setText("" + summa);
        
        this.asetaNapit();
    }
    
}