class Prioritetskoe<T> {

    protected Node start;

    //lager en indre node-klasse inne i klassen for aa lenke objektene sammen
    protected class Node{
        protected T data;
        protected Node neste;
        protected int prio;

        protected Node(T objekt,int prio){
            data=objekt;
            this.prio=prio;
        }

        //lagt til for test
        @Override
        public String toString(){
            return data.toString();
        }
    }

    public void settInn(T inn, int prio){
        Node skalSettesInn=new Node(inn,prio);
        if (start==null){ //hvis listen er tom
            start = skalSettesInn;
        }
        else {
            Node skalSammenlignes = start;
            while (skalSammenlignes.prio<prio && skalSammenlignes.neste!=null){
                skalSammenlignes=skalSammenlignes.neste;
            }
            //da har vi funnet en med lik proritet, ellers finnes ikke lik prioritet og vi setter den inn
            Node gammelNeste=skalSammenlignes.neste;
            skalSammenlignes.neste=skalSettesInn;
            skalSettesInn.neste=gammelNeste;
            }
    }

    public T taUt(){
        Node somFjernes=start;
        if (somFjernes.neste!=null){
            start=somFjernes.neste;
        }
        else {
            start=null;
        }
        return somFjernes.data;
    }

    public int antall(){
        int storrelse=0;
        Node somTelles = start;
        while (somTelles!=null){
            somTelles=somTelles.neste;
            storrelse++;
        }
        return storrelse;
    }

    //lagt til for test
    @Override
    public String toString(){
        String utskrift="";
        Node skalSkrivesUt=start;
        while (skalSkrivesUt!=null){
            utskrift+=skalSkrivesUt.toString() + "\n";
            skalSkrivesUt=skalSkrivesUt.neste;
        }
        return utskrift;
    }
    
}

//LF 

class PrioritetskoLF <T> {
    private class Node {
        T data;
        int prioritet;
        Node neste = null;
        Node (T inn, int prio) {
            data = inn;  prioritet = prio;
        }
    }
    private Node forste = null;
    private int ant = 0; // Antall noder i listen (>= 0)

    public void settInn(T inn, int prio) {
        Node ny = new Node(inn, prio);   
        ant++;
        if (forste == null) forste = ny;
        else if (ny.prioritet <= forste.prioritet) {
            ny.neste = forste;  forste = ny;

        } 
        else {
            Node p  = forste;
            // Invariant: p.prioritet < ny.prioritet
        while (p.neste != null && p.neste.prioritet<ny.prioritet) p = p.neste; ny.neste = p.neste;  
        // NB! Fungerer også om p er sist i listen.
        p.neste = ny;
    }
}

public  T taUt() {
    if (forste == null) return null;
    T ut = forste.data;
    forste = forste.neste;  
    ant--;
    return ut;
}

public int antall() {
    return ant;
}



}