class TestFly {

    public static void main(String[] args) {
        Flytype sas = new Flytype("SAS", "2:AC*D+F+|3:C*D|4:AC*DF");
        Flygning flygning = sas.opprettFlygning("testflygning");
        Passasjer rag = new Passasjer("Ragnhild", 167);
        Passasjer mat = new Passasjer("Matias", 190);
        Passasjer pappa = new Passasjer("Sveinung", 192);
        Passasjer mamma = new Passasjer("Torunn", 170);

    }

}