class PrioritetTest {
    
    public static void main(String[] args){
        Prioritetskoe<String> test = new Prioritetskoe<String>();
        String nullte="0";
        String en = "1";
        String to = "2";
        String tre = "3";
        String en2 = "1_2";
        String to2 = "2_2";
        String tre2 = "3_2";
        test.settInn(nullte,0);
        test.settInn(en,1);
        test.settInn(to,2);
        test.settInn(tre,3);
        test.settInn(en2,1);
        test.settInn(to2,2);
        test.settInn(tre2,3);
        System.out.print(test);
        System.out.println("Storrelse: " + test.antall() + " Forventet: 7" );
        String fjernes = test.taUt();
        System.out.println("Storrelse: " + test.antall() + " Forventet: 6" );
        fjernes = test.taUt();
        System.out.println("Storrelse: " + test.antall() + " Forventet: 5" );
        fjernes = test.taUt();
        System.out.println("Storrelse: " + test.antall() + " Forventet: 4" );
        fjernes = test.taUt();
        System.out.println("Storrelse: " + test.antall() + " Forventet: 3" );
        fjernes = test.taUt();
        System.out.println("Storrelse: " + test.antall() + " Forventet: 2" );
        System.out.print(test);
        fjernes = test.taUt();
        System.out.println("Storrelse: " + test.antall() + " Forventet: 1" );
        fjernes = test.taUt();
        System.out.println("Storrelse: " + test.antall() + " Forventet: 0" );

        
    }
}