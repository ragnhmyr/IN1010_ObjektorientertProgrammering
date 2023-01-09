import java.util.ArrayList;
import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;

public class HvitRute extends Rute {

    public HvitRute(Labyrint labyrint, int kolonne, int rad) {
        super(labyrint, kolonne, rad);
    }

    @Override
    public char tilTegn() {
        return '.';
    }

    @Override
    public void gaa(ArrayList<Tuppel> sti) {
        ArrayList<Tuppel> nySti = new ArrayList<>(sti); // kopierer stien
        if (!hentBesok()) { // hvis siten ikke har vaert innom ruta enda
            nySti.add(new Tuppel(this)); // lager tuppel av ruta og legger den i den nye stien
            besok(); // da er den besokt
            super.gaa(nySti); // ga videre i ny sti
            fjernBesok(); // fjern besok saann at den kan besokes i senere sti
        }
        // ellers gaar den bare ut for da har ruta vaert besokt og da skal vi ikke gaa
        // inn i den igjen
    }

    @Override
    public String toString() {
        return "Hvit " + super.toString();
    }

    @Override
    public JButton lagKnapp() {
        JButton hvit = new JButton();
        hvit.setBackground(Color.WHITE);
        hvit.setOpaque(true);
        return hvit;
    }

}
