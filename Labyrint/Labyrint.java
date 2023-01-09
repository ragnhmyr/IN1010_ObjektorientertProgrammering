import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

//fungerer med baade sykliske og asykliske labyrinter.
//har implementert metoden kortesteUtvei().
//denne vises i utskrift, men kan fjernes ved aa kommentere vekk linje 71 i denne Labyrint-klassen
//burde implementert metoden med aa vise testutskrifter

class Labyrint {
    protected int antRader;
    protected int antKolonner;

    protected Rute[][] rutenett;
    protected ArrayList<ArrayList<Tuppel>> utveier = new ArrayList<ArrayList<Tuppel>>();

    public Labyrint(File fil) throws FileNotFoundException {
        // FileNotFoundException skal haandteres i neste oblig tror jeg? saan jeg
        // forstod oppgaveteksten
        Scanner sc = new Scanner(fil);
        if (sc.hasNextInt()) {
            antRader = sc.nextInt();
            if (sc.hasNextInt()) {
                antKolonner = sc.nextInt();
            }
        } else {
            System.out.println("Feil i innlesing av fil. Mangler antall rader/kolonner i forste linje av filen");
            sc.close();
            return;
        }
        sc.nextLine(); // for aa hoppe over "\n" som blir igjen etter aa ha faatt de to tallene
        rutenett = new Rute[antRader][antKolonner];
        int radnummer = 0;
        while (sc.hasNextLine()) {
            String rad = sc.nextLine();
            for (int i = 0; i < antKolonner; i++) {
                char tegn = rad.charAt(i);
                if (tegn == '.') { // da er den hvit
                    // hvis den hvite ruten er paa kanten, er det en aapning
                    if (radnummer == 0 || radnummer == antRader - 1 || i == 0 || i == antKolonner - 1) {
                        rutenett[radnummer][i] = new Aapning(this, i, radnummer); // da er det en aapning
                    } else { // ellers er det en vanlig hvit rute
                        rutenett[radnummer][i] = new HvitRute(this, i, radnummer); // da er det en vanlig HvitRute
                    }
                } else if (tegn == '#') { // da er den svart
                    rutenett[radnummer][i] = new SortRute(this, i, radnummer);
                } else {
                    System.out.println("Filen har ikke gyldig tegnsetting av representasjonene til hvit og svart rute");
                }
            }

            radnummer++;
        }
        sc.close(); // lukke scanneren

        // etter å ha lagt inn alle rutene, gaar koden gjennom alle rutene og legger
        // til naboer til hver rute. naboene blir lagret i en array til hver rute:
        for (int i = 0; i < antRader; i++) { // hver rad
            for (int j = 0; j < antKolonner; j++) { // hver kolonne
                rutenett[i][j].leggTilNaboer();
            }
        }

    }

    public ArrayList<ArrayList<Tuppel>> finnUtveiFra(int kol, int rad) {
        Rute denneRuten = rutenett[rad][kol];
        denneRuten.finnUtvei(); // finner eventuell(e) utvei(er) og lagrer i instansvariabelen utveier
        kortesteUtvei(); // maa kjores for labyrinten blir nullstilt
        ArrayList<ArrayList<Tuppel>> funnetUtvei = utveier;
        nullstilleLabyrint(); // lager opprinnelige naboer
        utveier = new ArrayList<ArrayList<Tuppel>>(); // nulstill utveier
        return funnetUtvei;
    }

    @Override
    public String toString() {
        String utskrift = "";
        for (int i = 0; i < rutenett.length; i++) { // hver rad
            for (int j = 0; j < rutenett[0].length; j++) { // hver kolonne
                utskrift += rutenett[i][j].tilTegn();
            }
            utskrift += "\n"; // linjeskift for hver rad
        }
        return utskrift;
    }

    // gjor at alle rutene faar 'tilbake' sine opprinnelige naboer
    // egentlig bare det samme som aa legge til naboer paa nytt
    public void nullstilleLabyrint() {
        for (int i = 0; i < antRader; i++) { // hver rad
            for (int j = 0; j < antKolonner; j++) { // hver kolonne
                rutenett[i][j].leggTilNaboer();
            }
        }
    }

    public void kortesteUtvei() {
        if (utveier.size() == 0) { // da finnes det ingen utvei
            System.out.println("Det finnes ingen utvei fra valgt rute");
        } else {
            int antUtveier = utveier.size();
            System.out.println("Antall utveier funnet: " + utveier.size());
            System.out.print("Korteste utvei er: ");
            ArrayList<Tuppel> korteste = utveier.get(0); // sett den forste verdien til korteste
            int kortesteLengde = korteste.size();
            // gaa deretter gjennom for aa sjekke om noen utveier er kortere
            for (int i = 0; i < antUtveier; i++) {
                if (utveier.get(i).size() < kortesteLengde) {
                    korteste = utveier.get(i);
                    kortesteLengde = utveier.get(i).size();
                }
            }
            skrivUtEnUtvei(korteste);
            System.out.println("");
            System.out.println("Korteste utvei har lengde: " + kortesteLengde);
        }

    }

    public void skrivUtAlleUtveier() {
        for (ArrayList<Tuppel> lis : utveier) {
            for (Tuppel t : lis)
                System.out.println(t);
            System.out.println();
        }
    }

    public void skrivUtEnUtvei(ArrayList<Tuppel> utvei) {
        for (Tuppel koord : utvei) {
            System.out.print(koord + " ");
        }
    }
}
