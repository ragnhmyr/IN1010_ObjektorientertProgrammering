import java.util.ArrayList;

public class Aapning extends HvitRute {

    public Aapning(Labyrint labyrint, int kolonne, int rad) {
        super(labyrint, kolonne, rad);
    }

    @Override
    public String toString() {
        return "Aapning " + super.toString();
    }

    @Override
    public void gaa(ArrayList<Tuppel> sti) {
        // fjerner alle naboene til aapningen slik at man ikke skal gaa videre
        // fjernAlleNaboer();
        ArrayList<Tuppel> nySti = new ArrayList<>(sti);
        nySti.add(new Tuppel(this)); // legger til aapningen i stien slik at aapningen kommer med
        minLabyrint.utveier.add(nySti); // legger til denne stien som en utvei i labyrinten
        super.gaa(sti); // tilfelle man begynner labyrinten i aapningen maa man gaa videre til
                        // eventuelle naboer
    }

}
