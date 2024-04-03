package common;


public class Main {
    public static void main(String[] args) {
        Genealogie g = new Genealogie();
        g.adj("Arthur", -1, -1, new Date(9, 11, 1879), new Date(12, 11, 1934 ));
        g.adj("Marcel", 1, -1, new Date(1, 7, 1928), new Date(21, 9, 2004 ));
        g.adj("Clothilde", -1, -1, new Date(30, 8, 1929), new Date(26, 4, 2005 ));
        g.adj("Jeanne", 2, 3, new Date(13,4, 1948 ), new Date());
        g.adj("Henri", -1,-1, new Date(2,8, 1947), new Date());
        g.adj("Aline", -1,-1, new Date(7,9, 1943 ), new Date());
        g.adj("Pierre", -1,-1, new Date(26,6, 1941), new Date());
        g.adj("Julien", 7, 6, new Date(13,8, 1965), new Date());
        g.adj("Alex", 7, 6, new Date(18,4, 1969), new Date());
        g.adj("Sophie", 5, 4, new Date(9,11, 1972), new Date());
        g.adj("Clementine", 5, 4, new Date(12,10, 1973), new Date());
        g.adj("Marion", 5, 4, new Date(5,5, 1976), new Date());
        g.adj("Christian", -1, -1, new Date(13,2, 1971), new Date());
        g.adj("Thomas", 9, 10, new Date(18,10, 2012), new Date());
        g.adj("Cloe", 9, 10, new Date(21,6, 2002), new Date());
        g.adj("Hugo", 9, 10, new Date(12,5, 2005), new Date());
        g.adj("Isabelle", 13, 12, new Date(28,4, 2003), new Date());
    
        
        System.out.println("Est-ce que Sophie et Marion sont soeurs? -> " +g.freres_soeurs(10, 12));
        
        System.out.println("Est-ce que Hugo et Isabelle sont cousins? -> " +g.cousins(16, 17));
        
        System.out.println("Frères et soeurs de Hugo -> " +g.affiche_freres_soeurs(16));
        
        System.out.println("Est-ce que Arthur est un ancêtre de Thomas? -> " +g.ancetre(1, 14));
        
        System.out.println("Quel est l'ancêtre connu le plus ancien de Cloe? -> " +g.plus_ancien(15));
        
        //g.affiche_parente(14); //Thomas = index 14
    }
}
