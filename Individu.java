package common;

public class Individu {
    String nom;
    Date naissance, deces;
    int pere, mere, cadet, faine;

    public Individu(String nom, Date naissance, Date deces, int pere, int mere, int cadet, int faine){
        this.nom = nom;
        this.naissance = naissance;
        this.deces = deces;
        this.pere = pere;
        this.mere = mere;
        this.cadet = cadet;
        this.faine = faine;
    }
}
