import java.util.ArrayList;
// import java.awt.*;
// import java.awt.event.*;
import javax.swing.*;

abstract class Rute {
    protected int minKol;
    protected int minRad;
    protected Labyrint minLabyrint;
    protected boolean hattBesok;
    protected Rute[] naboer = new Rute[4]; // nabo nord,syd,vest,ost. initialiserer de til null i starten
    // provde forst aa lagre utveiene her, men da lagres den bare i den ruta som
    // aapningen er i
    // og ikke i 'startruta'. Synes det derfor ble enklest aa legge til utvei
    // direkte til labyrinten fra en aapning
    // protected ArrayList<ArrayList<Tuppel>> utveier = new
    // ArrayList<ArrayList<Tuppel>>(); // har forst ikke funnet utvei

    public Rute(Labyrint labyrint, int kolonne, int rad) {
        minKol = kolonne;
        minRad = rad;
        minLabyrint = labyrint;
        hattBesok = false; // har ikke vaert besokt naar ruta lages
    }

    abstract public char tilTegn(); // Overrides i svart og hvit

    public void leggTilNaboer() {
        int nordNabo = minRad - 1;
        int sydNabo = minRad + 1;
        int vestNabo = minKol - 1;
        int ostNabo = minKol + 1;
        if (nordNabo >= 0) { // da har den en nord-nabo, ellers er den null
            naboer[0] = minLabyrint.rutenett[nordNabo][minKol];
        }
        if (sydNabo < minLabyrint.antRader) { // da har den en syd-nabo
            naboer[1] = minLabyrint.rutenett[sydNabo][minKol];
        }
        if (vestNabo >= 0) { // da har den en vestnabo
            naboer[2] = minLabyrint.rutenett[minRad][vestNabo];
        }
        if (ostNabo < minLabyrint.antKolonner) { // da har den en ost-nabo
            naboer[3] = minLabyrint.rutenett[minRad][ostNabo];
        }
    }

    // fjerner ruta som skal fjernes fra nabo-lista til denne ruta
    public void fjernNabo(Rute skalFjernes) {
        for (int i = 0; i < 4; i++) { // iterere gjennom alle naboene og sjekker om en av naboene er ruta
            if (naboer[i] != null && naboer[i].equals(skalFjernes)) {
                naboer[i] = null; // fjerner ruta fra nabolista, dvs. setter nabo til null
            }
        }
    }

    public void fjernAlleNaboer() {
        for (int i = 0; i < 4; i++) {
            naboer[i] = null; // setter alle naboene til null
        }
    }

    // brukt i test for aa sjekke om naboene ble riktige
    public void skrivUtNaboer() {
        System.out.println("Naboer til: " + toString());
        for (Rute rute : naboer) {
            System.out.println(rute);
        }
    }

    public void gaa(ArrayList<Tuppel> sti) {
        for (Rute nabo : naboer) {
            if (nabo != null) {
                // fjerner ruta den gaar fra i nabolista til den nye ruta saan at den ikke gaar
                // tilbake der den kom fra
                nabo.fjernNabo(this);
                nabo.gaa(sti); // gaa til naboen
                nabo.leggTilNaboer(); // legg til naboer igjen saann at man kan gaa alle naboene sine paa nye stier
            }
        }
    }

    public void besok() {
        hattBesok = true;
    }

    public void fjernBesok() {
        hattBesok = false;
    }

    public boolean hentBesok() {
        return hattBesok;
    }

    public void finnUtvei() {
        ArrayList<Tuppel> sti = new ArrayList<Tuppel>();
        // lager ny, tom sti til gaa-metoden
        gaa(sti);
    }

    @Override
    public String toString() {
        return "Rute (kol,rad): (" + minKol + "," + minRad + ")";
    }

    abstract public JButton lagKnapp(); // abstrakt rute som overrides i HvitRute, SvartRute

}
