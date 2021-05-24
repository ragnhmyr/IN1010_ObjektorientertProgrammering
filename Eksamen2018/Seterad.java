class Seterad {

    private int radNummer;
    private Sete[] seter;
    // protected int antSeterVenstreMidtgang;
    // protected int antSeterHoyreMidtgang;
    // protected boolean harMidtgang=false;

    public Seterad(String info) {
        String[] finneNr = info.split(":");
        radNummer = Integer.parseInt(finneNr[0]);

        String seteString = finneNr[1];
        String kunSeter = "";
        String seterOgMidtgang = "";
        for (int i = 0; i < seteString.length(); i++) {
            char tegn = seteString.charAt(i);
            if (tegn != '*' && tegn != '+') {
                kunSeter += tegn;
            }
            if (tegn != '+') {
                seterOgMidtgang += tegn;
            }
        }
        seter = new Sete[kunSeter.length()];
        // for (int j = 0; j < seter.length; j++) {
        // seter[j] = new Sete(radNummer, kunSeter.charAt(j));
        // }
        // System.out.println(kunSeter);

        // lage seter og godBenPlassSete
        int seteIndeks = 0;
        for (int i = 0; i < seteString.length(); i++) {
            char tegn = seteString.charAt(i);
            if (kunSeter.contains("" + tegn)) { // da skal det bli et sete
                if (i < seteString.length() - 1 && seteString.charAt(i + 1) == '+') {
                    seter[seteIndeks] = new GodBenPlassSete(radNummer, tegn);
                } else {
                    seter[seteIndeks] = new Sete(radNummer, tegn);
                }
                seteIndeks++;
            }
        }

        // if (kunSeter.contains("A")) {
        // System.out.println(kunSeter + "inneholder A");
        // }

        // markere seter ved midtgangen
        String[] deleOppRaden = seterOgMidtgang.split("\\*");
        // for (String elem : deleOppRaden) {
        // System.out.println(elem);
        // }
        if (deleOppRaden.length > 1) {// da fins det en midtgang
            // antar for enkelhetsskyld at det maks er én midtgang
            int venstreIndeks = deleOppRaden[0].length() - 1;
            int hoyreIndeks = venstreIndeks + 1;
            seter[venstreIndeks].venstreForMidtgang = true;
            seter[hoyreIndeks].hoyreForMidtgang = true;

        }

        // markere setene ved vinduet
        seter[0].vedVindu = seter[seter.length - 1].vedVindu = true;

        // for test: Skriv ut sete
        for (Sete sete : seter) {
            System.out.print(sete.toString() + " ");
        }
        System.out.println("\n");

    }

}