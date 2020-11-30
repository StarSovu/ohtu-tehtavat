package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Erotus extends Komento {
    public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        int vahennettava = Integer.parseInt(this.syotekentta.getText());
        this.sovellus.miinus(vahennettava);
        int erotus = this.sovellus.tulos();
        
        this.syotekentta.setText("");
        this.tuloskentta.setText("" + erotus);
        
        this.undo.disableProperty().set(false);
        this.nollaa.disableProperty().set(erotus == 0);
    }  
}