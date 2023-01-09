class Tuppel {
    protected int xKol;
    protected int yRad;

    // lager tuppel fra en rute
    public Tuppel(Rute rute) {
        xKol = rute.minKol;
        yRad = rute.minRad;
    }

    @Override
    public String toString() {
        return "(" + xKol + "," + yRad + ")"; // for aa faa paa formen (x,y)
    }

    // sammenlignRute() brukes ikke i programmet allikevel
    // var et forste forsok paa aa haandtere sykliske labyrinter
    public boolean sammenlignRute(Rute rute) { // kunne heller kanskje laget en CompareTo metode
        if (xKol == rute.minKol && yRad == rute.minRad) {
            return true; // begge maa stemme for at de skal vaere like
        }
        return false;
    }
}
