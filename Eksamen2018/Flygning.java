class Flygning {

    private String flightNo;
    private String seteinfo;
    private Seterad[] seterader;

    public Flygning(String nr, String tekst) {
        ;
        flightNo = nr;
        seteinfo = tekst;
        String[] startSeterad = seteinfo.split("\\|");
        // for (String elem : startSeterad) {
        // System.out.println(elem);
        // }
        // System.out.println(startSeterad.length);
        int antallRader = startSeterad.length;
        seterader = new Seterad[antallRader];
        // lager rader for hver rad
        // inni radene lages selve setene
        for (int i = 0; i < antallRader; i++) {
            seterader[i] = new Seterad(startSeterad[i]);
        }

    }

    public boolean book(Passasjer pas) {

        return false;

    }

    public boolean book(Passasjer pas, boolean foretrekkerVindu, boolean foretrekkerMidtgang) {

        return false;

    }

    public boolean book(Passasjer pas1, Passasjer pas2) {

        return false;
    }

    public void skriv() {

    }

}