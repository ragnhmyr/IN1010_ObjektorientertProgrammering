class GodBenPlassSete extends Sete {

    public GodBenPlassSete(int radNr, char seteNr) {
        super(radNr, seteNr);
    }

    @Override //
    public boolean passerForLangeBen() {
        return true;
    }

    @Override
    public String toString() {
        String utskrift = super.toString();
        utskrift += " benplass ";
        return utskrift;
    }

}