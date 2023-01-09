import java.util.ArrayList;
import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;

public class SortRute extends Rute {

    public SortRute(Labyrint labyrint, int kolonne, int rad) {
        super(labyrint, kolonne, rad);
    }

    @Override
    public char tilTegn() {
        return '#';
    }

    @Override
    public void gaa(ArrayList<Tuppel> sti) {
        // skal ikke gaa videre hvis man kommer til en svart rute
        // fjerner derfor alle naboene til den svarte ruta
        fjernAlleNaboer(); // da stoppes denne ruta
        // hadde kanskje egentlig ikke trengt aa fjerne alle naboer
    }

    @Override
    public String toString() {
        return "Sort " + super.toString();
    }

    @Override
    public JButton lagKnapp() {
        JButton svart = new JButton();
        svart.setBackground(Color.BLACK);
        svart.setOpaque(true);
        return svart;
    }
}
