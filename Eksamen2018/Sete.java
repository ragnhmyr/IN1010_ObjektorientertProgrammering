class Sete implements MidtgangSete {

    protected int radNr;
    protected char seteNr;
    protected Passasjer passasjer = null; // ingen som har booket sete naar det opprettes
    protected boolean hoyreForMidtgang = false;
    protected boolean venstreForMidtgang = false;
    protected boolean vedVindu = false;

    public Sete(int radNr, char seteNr) {
        this.radNr = radNr;
        this.seteNr = seteNr;

    }

    public boolean passerForLangeBen() {
        return false; // hvis det er GodBenPlassSete settes denne til True;
    }

    public boolean erVindussete() {
        return vedVindu;
    }

    public boolean erVedMidtgang() {
        return hoyreForMidtgang || venstreForMidtgang;
    }

    @Override
    public String toString() {
        String utskrift = "" + radNr + seteNr;
        if (this.erVedMidtgang()) {
            utskrift += " midt ";
        }
        if (this.vedVindu) {
            utskrift += " vindu ";
        }
        return utskrift;
    }

}