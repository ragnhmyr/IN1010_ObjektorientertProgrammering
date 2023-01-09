import java.awt.*;
// import java.awt.event.*;
import javax.swing.*;

// import java.util.Scanner;
// import java.io.File; //for fillesing
// import java.io.FileNotFoundException;
// import java.util.ArrayList;

class LabyrintGUI {
    protected int b = 500, h = 500; // storrelse paa labyrinten
    protected int b2 = 400, h2 = 500; // storrelse paa tekstflaten

    protected JPanel gridPanel;
    protected JPanel tekstPanel;
    protected JPanel startPanel;
    protected Labyrint labyrint;
    protected int tellerverdi = 0;
    protected Labyrintbrett labyrintbrett;

    public LabyrintGUI(Labyrint labyrint) {
        this.labyrint = labyrint;
        JFrame vindu = new JFrame("Labyrint");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startPanel = new JPanel();
        startPanel.setLayout(new BorderLayout());// for aa fikse posisjon
        vindu.add(startPanel);

        tekstPanel = new JPanel();
        tekstPanel.setPreferredSize(new Dimension(b2, h2));

        tekstPanel.setLayout(new GridLayout(5, 1));

        startPanel.add(tekstPanel, BorderLayout.WEST); // legg til til venstre

        gridPanel = new JPanel();
        gridPanel.setPreferredSize(new Dimension(b, h));
        gridPanel.setLayout(new GridLayout(labyrint.antRader, labyrint.antKolonner));

        startPanel.add(gridPanel, BorderLayout.EAST); // legg til hoyre
        labyrintbrett = new Labyrintbrett(gridPanel, this.labyrint, tekstPanel);

        vindu.pack();
        vindu.setVisible(true);

    }

}
