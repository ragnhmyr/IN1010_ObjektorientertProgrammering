class Flytype {

    private String type;
    private String seteinfo;

    public Flytype(String type, String seteinfo) {
        this.type = type;
        this.seteinfo = seteinfo;
    }

    public Flygning opprettFlygning(String nr) {
        // slik at man kan lage flere ulike flygninger
        return new Flygning(nr, seteinfo);

    }
}