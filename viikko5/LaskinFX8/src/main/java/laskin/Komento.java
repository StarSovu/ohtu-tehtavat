package laskin;


import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Komento {
    protected TextField tuloskentta;
    protected TextField syotekentta;
    protected Button nollaa;
    protected Button undo;
    protected Sovelluslogiikka sovellus;
    protected int edellinenTulos;
    
    public Komento(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
    }

    public abstract void suorita();
    
    public void peru() {
        this.sovellus.nollaa();
        this.sovellus.plus(edellinenTulos);
        
        this.tuloskentta.setText("" + this.edellinenTulos);
        this.asetaNapit();
        this.undo.disableProperty().set(true);
    }
    
    protected void asetaEdellinenTulos() {
        this.edellinenTulos = Integer.parseInt(this.tuloskentta.getText());
    }
    
    protected void asetaNapit() {
        int tulos = Integer.parseInt(this.tuloskentta.getText());
        this.undo.disableProperty().set(false);
        this.nollaa.disableProperty().set(tulos == 0);
    }
    
}
