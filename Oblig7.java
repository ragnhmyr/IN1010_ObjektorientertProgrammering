
//import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;
import java.io.File; //for fillesing
import java.io.FileNotFoundException;

public class Oblig7 {

    public static void main(String[] args) {
        // Kode for aa hente fil er delvis hentet fra Quiz.java gjennomgaatt i
        // forelesning

        JFileChooser velger = new JFileChooser();
        int resultat = velger.showOpenDialog(null);
        if (resultat != JFileChooser.APPROVE_OPTION)
            System.exit(1);
        File f = velger.getSelectedFile(); // Scanner leser = null;
        Labyrint labyrint = null;
        try {
            labyrint = new Labyrint(f);
        } catch (FileNotFoundException e) {
            System.out.printf("FEIL: Fant ikke valgt fil");
            System.exit(1);
        }

        /*
         * for testing Labyrint labyrint = null; File fil = new File("labyrinter/4.in");
         * try { labyrint = new Labyrint(fil); } catch (FileNotFoundException e) {
         * System.out.printf("FEIL: Fant ikke valgt fil"); System.exit(1); }
         * System.out.print(labyrint);
         */

        LabyrintGUI gui = new LabyrintGUI(labyrint); // setter i gang labyrintGUI
    }
}
