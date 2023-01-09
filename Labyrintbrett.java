import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;

class Labyrintbrett {
    protected JPanel rutenett;
    protected JButton[][] knapper;
    protected Labyrint labyrint;
    protected ArrayList<ArrayList<Tuppel>> utveier; // blir laget nar trykker paa knapp
    protected int antallUtveier = 0; // starter paa null
    protected JPanel tekstpanel;
    protected int tellerverdi = 0; // starter med null
    protected JLabel velkomst = new JLabel("Velg en rute:");
    protected JLabel antall = new JLabel("");
    protected JPanel bytteUtvei = new JPanel(); // JPanel for aa kunne lege paa flere knapper / JLabel inni panelet
    protected JLabel denneUtveiLengde = new JLabel();
    protected JLabel lengstEllerKortest = new JLabel();

    public Labyrintbrett(JPanel rutenett, Labyrint labyrint, JPanel tekstpanel) {
        this.rutenett = rutenett;
        this.labyrint = labyrint;
        this.tekstpanel = tekstpanel;
        StartBrett();

    }

    public void lagKnapper() { // lager labyrintknappene
        knapper = new JButton[labyrint.antRader][labyrint.antKolonner];
        for (int i = 0; i < labyrint.antRader; i++) {
            for (int j = 0; j < labyrint.antKolonner; j++) {
                JButton knapp = labyrint.rutenett[i][j].lagKnapp(); // implementer forskjell i hvit og svart rute for aa
                                                                    // faa hvit/svart bakgrunn
                knapp.addActionListener(new Knappetrykk(i, j, this));
                knapper[i][j] = knapp;
                rutenett.add(knapp);
            }
        }
    }

    public void StartBrett() {
        // provde egentlig aa faa annen rekkefolge paa tekstpanelet ved aa ha
        // BytteUtvei-panelet ca. i midten, men ser ikke ut til aa funke
        // BytteUtvei-panelet kommer nederst.
        tekstpanel.add(velkomst);
        tekstpanel.add(antall);
        tekstpanel.add(bytteUtvei);
        tekstpanel.add(denneUtveiLengde);
        tekstpanel.add(lengstEllerKortest);
        lagKnapper(); // lager selve labyrinten og legger til i rutenettet
    }

    public void tilbakestillKnapper() { // omtrent samme som lagKnapper();
        rutenett.removeAll(); // fjerne knappene som er der fra for og lage nye. Sikkert ikke den mest
                              // effektive maaten aa gjore det paa for forste gang gjores det baade lages og
                              // tilbakestilles knappene
        for (int i = 0; i < labyrint.antRader; i++) {
            for (int j = 0; j < labyrint.antKolonner; j++) {
                JButton knapp = labyrint.rutenett[i][j].lagKnapp(); // implementer forskjell i hvit og svart rute for aa
                                                                    // faa hvit/svart bakgrunn
                knapp.addActionListener(new Knappetrykk(i, j, this));
                knapper[i][j] = knapp;
                rutenett.add(knapp);
            }
        }
    }

    public void fargeleggUtvei(int indeks) {
        tekstpanel.revalidate();
        if (indeks >= 0 && indeks < antallUtveier) { // ellers skjer det ingenting
            int antallElementer = utveier.get(indeks).size();
            int stigning = (200 / antallElementer); // fra 50-250
            if (stigning == 0)
                stigning = 1; // saann at fargen endres og ikke forblir paa null hvis utveien har over 200
                              // elementer
            int start = 50;
            for (Tuppel tuppel : utveier.get(indeks)) {
                JButton knapp = knapper[tuppel.yRad][tuppel.xKol];
                // knapp.setBackground(Color.CYAN);
                // lager gronn labyrint, mork i starten som blir lysere og lysere
                if (start > 255)
                    start = 255; // setter slutten til lyseste farge hvis stien har mer enn 200 elementer
                knapp.setBackground(new Color(0, start, 0));
                start += stigning;
                knapp.setOpaque(true);
            }
            denneUtveiLengde.setText("Denne utveien har lengde: " + antallElementer);
            if (antallElementer == kortesteLengde()) {
                lengstEllerKortest.setText("Dette er korteste utvei");

            } else if (antallElementer == lengsteLengde()) {
                lengstEllerKortest.setText("Dette er lengste utvei");
            } else {
                lengstEllerKortest.setText("");
            }
        }
    }

    public void leggTilTekstKnapper() {
        antall.setText("Antall utveier: " + antallUtveier + "          ");
        JButton minke = new JButton("<");
        JButton oke = new JButton(">");
        JLabel denneUtvei = new JLabel("" + tellerverdi);

        tekstpanel.add(antall);
        bytteUtvei.add(new JLabel("Utvei som vises: "));
        bytteUtvei.add(minke);
        bytteUtvei.add(denneUtvei);
        bytteUtvei.add(oke);
        tekstpanel.add(bytteUtvei);

        // OekTeller fra Teller-eksempel i forelesning
        class OekTeller implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                tellerverdi++;
                if (tellerverdi > antallUtveier - 1) {
                    tellerverdi = antallUtveier - 1;
                    denneUtvei.setText("" + tellerverdi);
                } else {
                    denneUtvei.setText("" + tellerverdi);
                }
                tilbakestillKnapper();
                fargeleggUtvei(tellerverdi);
            }
        }
        oke.addActionListener(new OekTeller());
        class MinkTeller implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                tellerverdi--;
                if (tellerverdi < 0) {
                    //
                    tellerverdi = 0;
                    denneUtvei.setText("" + tellerverdi);
                } else {
                    denneUtvei.setText("" + tellerverdi);
                }
                tilbakestillKnapper();
                fargeleggUtvei(tellerverdi);
            }
        }
        minke.addActionListener(new MinkTeller());

    }

    public int kortesteLengde() {
        if (utveier.size() == 0) { // da finnes det ingen utvei
            return 0;
        } else {
            int antUtveier = utveier.size();
            ArrayList<Tuppel> korteste = utveier.get(0); // sett den forste verdien til korteste
            int kortesteLengde = korteste.size();
            // gaa deretter gjennom for aa sjekke om noen utveier er kortere
            for (int i = 0; i < antUtveier; i++) {
                if (utveier.get(i).size() < kortesteLengde) {
                    korteste = utveier.get(i);
                    kortesteLengde = utveier.get(i).size();
                }
            }
            return kortesteLengde;
        }
    }

    public int lengsteLengde() {
        if (utveier.size() == 0) { // da finnes det ingen utvei
            return 0;
        } else {
            int antUtveier = utveier.size();
            ArrayList<Tuppel> lengste = utveier.get(0); // sett den forste verdien til korteste
            int lengsteLengde = lengste.size();
            // gaa deretter gjennom for aa sjekke om noen utveier er kortere
            for (int i = 0; i < antUtveier; i++) {
                if (utveier.get(i).size() > lengsteLengde) {
                    lengste = utveier.get(i);
                    lengsteLengde = utveier.get(i).size();
                }
            }
            return lengsteLengde;
        }
    }
}

class Knappetrykk implements ActionListener {
    int rad, kol;
    Labyrintbrett labyrintbrett;

    public Knappetrykk(int i, int j, Labyrintbrett labyrintbrett) {
        rad = i;
        kol = j;
        this.labyrintbrett = labyrintbrett;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        labyrintbrett.tilbakestillKnapper(); // tilfelle man trykker paa ny rute, sann at den gamle stien blir borte
        // labyrintbrett.rutenett.revalidate();
        labyrintbrett.velkomst.setText(""); // tar bort velkomsten hvis man trykker paa en rute
        labyrintbrett.bytteUtvei.removeAll(); // fjerner knappen for aa endre utvei, saann at oppdatert kan bli lagt til
        System.out.println("Trykket paa rad: " + rad + " kolonne: " + kol);
        ArrayList<ArrayList<Tuppel>> funnetUtveier = labyrintbrett.labyrint.finnUtveiFra(kol, rad);
        labyrintbrett.utveier = funnetUtveier;
        labyrintbrett.antallUtveier = funnetUtveier.size(); // kolonne oppgitt
        if (labyrintbrett.antallUtveier > 0) { // hvis det fins noen utveier blir forste utvei fargelagt og tekstknapper
                                               // lagt til, ellers skjer ingenting
            labyrintbrett.tellerverdi = 0; // reset teller
            labyrintbrett.fargeleggUtvei(0); // starter med aa tegne opp den forste, kunne ogsa brukt tellerverdi i
                                             // stedenfor 0
            labyrintbrett.leggTilTekstKnapper();
        } else {
            labyrintbrett.velkomst.setText("Finnes ingen utveier fra denne ruten. Prov en annen:");
        }
    }

}