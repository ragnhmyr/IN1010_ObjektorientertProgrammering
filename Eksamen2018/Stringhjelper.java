class Stringhjelper {

    static int inneholderLF(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        for (int is = 0; is <= sLen - tLen; is++) {
            boolean eq = true;
            for (int it = 0; it < tLen; it++) {
                if (s.charAt(is + it) != t.charAt(it)) {
                    eq = false;
                    break;
                }
            }
            if (eq)
                return is;
        }
        return -1;
    }

    static int inneholder(String s, String t) {
        // ville egentlig gjort baede s og t om til sma/store bokstaver, men antar at de
        // er like
        int tLengde = t.length();
        int sLengde = s.length();
        if (tLengde > sLengde) {
            return -1; // da gaar det ikke an
        }
        int indeks = 0;
        while (indeks < sLengde) {
            if (s.charAt(indeks) == (t.charAt(0))) { // da stemmer forste bokstav
                int antStemmer = 1;
                int startIndeks = indeks;
                for (int i = 1; i < tLengde; i++) {
                    // gaar gjennom de neste bokstavene i t
                    // indeks = indeks + i; // slik at den hopper ut av while-lokka hvis
                    // indeks>sLengde
                    int nyIndeks = startIndeks + i;
                    if (nyIndeks < sLengde && s.charAt(nyIndeks) == (t.charAt(i))) {
                        antStemmer++;
                    }
                }

                if (antStemmer == tLengde) {
                    return startIndeks;
                }
            }

            indeks++;
        }

        return -1;
    }

    public static void main(String[] args) {
        // System.out.println(Stringhjelper.inneholder("stein", "ei"));
        // System.out.println(Stringhjelper.inneholder("stein", "sen"));
        // System.out.println(Stringhjelper.inneholder("stein", "langtord"));
        // System.out.println(Stringhjelper.inneholder("stein", "steinord"));
        // System.out.println(Stringhjelper.inneholder("stein", "n"));
        // System.out.println(Stringhjelper.inneholder("stein", "stein"));

        System.out.println(Stringhjelper.inneholderLF("stein", "ei"));
        System.out.println(Stringhjelper.inneholderLF("stein", "sen"));
        System.out.println(Stringhjelper.inneholderLF("stein", "langtord"));
        System.out.println(Stringhjelper.inneholderLF("stein", "steinord"));
        System.out.println(Stringhjelper.inneholderLF("stein", "n"));
        System.out.println(Stringhjelper.inneholderLF("stein", "stein"));

    }

}
