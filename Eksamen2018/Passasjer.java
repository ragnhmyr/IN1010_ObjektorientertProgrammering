class Passasjer {

    protected String navn;
    protected double hoyde;

    public Passasjer(String navn, double hoyde) {
        this.navn = navn;
        this.hoyde = hoyde;
    }

    public boolean harLangeBen() {
        // if (hoyde >= 190) {
        // return true;
        // }
        // return false;
        return hoyde >= 190;
    }

}