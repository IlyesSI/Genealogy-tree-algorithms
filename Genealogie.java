package common;

import java.util.*;

public class Genealogie {
    HashMap< Integer, Individu > map;
    int id_cur ;

    public Genealogie(){
        this.map = new HashMap<Integer, Individu >();
        this.id_cur = 0 ;
    }

    private int genererID(){
        id_cur = map.size()+1;
        return id_cur;
    }

    public void trouverFaine(Individu indv, int ID_indiv) {
        int p = indv.pere;
        int m = indv.mere;
        if (p != -1 | m != -1) {
            if(p != -1){
                if (map.get(p).faine == -1) {
                    map.get(p).faine = ID_indiv;
                }
                else if (map.get(p).faine != -1) {
                    if (indv.naissance.annee < map.get(map.get(p).faine).naissance.annee) {
                        map.get(p).faine = ID_indiv;
                    } else if (indv.naissance.annee == map.get(map.get(p).faine).naissance.annee && indv.naissance.mois < map.get(map.get(p).faine).naissance.mois) {
                        map.get(p).faine = ID_indiv;
                    } else if (indv.naissance.annee == map.get(map.get(p).faine).naissance.annee && indv.naissance.mois == map.get(map.get(p).faine).naissance.mois && indv.naissance.jour < map.get(map.get(p).faine).naissance.jour) {
                        map.get(p).faine = ID_indiv;
                    }
                }
            }
            if(m != -1){
                if (map.get(m).faine == -1) {
                    map.get(m).faine = ID_indiv;
                }
                else if (map.get(m).faine != -1) {
                    if (indv.naissance.annee < map.get(map.get(m).faine).naissance.annee) {
                        map.get(m).faine = ID_indiv;
                    } else if (indv.naissance.annee == map.get(map.get(m).faine).naissance.annee && indv.naissance.mois < map.get(map.get(m).faine).naissance.mois) {
                        map.get(m).faine = ID_indiv;
                    } else if (indv.naissance.annee == map.get(map.get(m).faine).naissance.annee && indv.naissance.mois == map.get(map.get(m).faine).naissance.mois && indv.naissance.jour < map.get(map.get(m).faine).naissance.jour) {
                        map.get(m).faine = ID_indiv;
                    }
                }
            }
        }
    }


    public void trouverCadet(Individu indv, int ID_indiv){
        int i;
        int longueur = map.size();
        int ID_Cadet = 0;
        HashMap<Integer, Integer> sousMapFaimmile = new HashMap<Integer, Integer>();

        for(i=1; i<=longueur; i++){
            if(map.get(i).pere == map.get(ID_indiv).pere | map.get(i).mere == map.get(ID_indiv).mere){

                //1 comparer les dates
                String Date_annee = String.valueOf(map.get(i).naissance.annee);
                String Date_mois = null;
                String Date_jour = null;
                if(map.get(i).naissance.mois>=10){
                    Date_mois = String.valueOf(map.get(i).naissance.mois);
                }
                else if (map.get(i).naissance.mois<10){
                    Date_mois = "0" + String.valueOf(map.get(i).naissance.mois);
                }
                if(map.get(i).naissance.jour>=10){
                    Date_jour = String.valueOf(map.get(i).naissance.jour);
                }
                else if (map.get(i).naissance.jour<10){
                    Date_jour = "0" + String.valueOf(map.get(i).naissance.jour);
                }
                String Date_string = Date_annee + Date_mois + Date_jour;
                int Date_int = Integer.parseInt(Date_string);
                sousMapFaimmile.put(Date_int, i);
            }
        }
        ArrayList<Integer> sousMapFaimmile_ArrayList = new ArrayList<Integer>(sousMapFaimmile.keySet());
        Collections.sort(sousMapFaimmile_ArrayList);

        if(sousMapFaimmile_ArrayList.size()>1){
            int Date_Cadet = sousMapFaimmile_ArrayList.get(1);
            ID_Cadet = sousMapFaimmile.get(Date_Cadet);
        }

        for(int j=0; j< sousMapFaimmile_ArrayList.size(); j++){
            int Date_membreFamille = sousMapFaimmile_ArrayList.get(j);
            int ID_membreFamille = sousMapFaimmile.get(Date_membreFamille);
            map.get(ID_membreFamille).cadet = ID_Cadet;
        }
    }


    public void adj(String s, int p, int m, Date n, Date d){
        Individu individu = new Individu(s,n,d,p,m,-1,-1);
        int ID_individu = genererID();
        this.map.put(ID_individu,individu);
        trouverFaine(individu, ID_individu);
        trouverCadet(individu, ID_individu);
    }

    public void printMap(){
        System.out.println(this.map); // pour tester la mÃ©thode
    }
    
    public Individu getIndividu(int x){ //teste accÃ¨s Ã  un individu
        Individu indv = map.get(x);
        return indv;
    }

    public boolean freres_soeurs(int x, int y){
        if (map.get(x).pere != -1 && map.get(y).pere != -1 && map.get(x).pere == map.get(y).pere && x != y){
            return true;
        }
        else if (map.get(x).mere != -1 && map.get(y).mere != -1 && map.get(x).mere == map.get(y).mere && x != y){
            return true;
        }
        return false;
    }

    public boolean cousins(int x, int y){
        if (freres_soeurs(x,y ) | x==y){
            return false;
        }
        else if (map.get(x).pere != -1 && map.get(y).pere != -1 && freres_soeurs(map.get(x).pere, map.get(y).pere)){
                return true;
        }
        else if (map.get(x).mere != -1 && map.get(y).mere != -1 && freres_soeurs(map.get(x).mere, map.get(y).mere)){
                return true;
        }
        else if (map.get(x).pere != -1 && map.get(y).mere != -1 && freres_soeurs(map.get(x).pere, map.get(y).mere)){
                return true;
        }
        else if (map.get(x).mere != -1 && map.get(y).pere != -1 && freres_soeurs(map.get(x).mere, map.get(y).pere)){
                return true;
        }
        return false;
    }

    public ArrayList<String> affiche_freres_soeurs(int x){
        int i;
        ArrayList<String> nom_freres_soeurs = new ArrayList<>();
        for (i=1; i<= map.size(); i++){
            if(freres_soeurs(x,i)){
                nom_freres_soeurs.add(map.get(i).nom);
            }
        }
        return nom_freres_soeurs;
    }

    public ArrayList<String> affiche_enfants(int x){
        int i;
        ArrayList<String> nom_enfants = new ArrayList<>();
        for (i=1; i<= map.size(); i++){
            if(map.get(i).pere==x | map.get(i).mere==x){
                nom_enfants.add(map.get(i).nom);
            }
        }
        return nom_enfants;
    }

    public ArrayList<String> affiche_cousins(int x){
        int i;
        ArrayList<String> nom_cousins = new ArrayList<>();
        for (i=1; i<= map.size(); i++){
            if(cousins(i,x)){
                nom_cousins.add(map.get(i).nom);
            }
        }
        return nom_cousins;
    }

    public ArrayList<String> affiche_oncles(int x){
    	ArrayList<String> oncles = new ArrayList<>();
        ArrayList<String> oncles_pere = affiche_freres_soeurs(map.get(x).pere);
        ArrayList<String> oncles_mere = affiche_freres_soeurs(map.get(x).mere);
        oncles.addAll(oncles_mere);
        oncles.addAll(oncles_pere);
        return oncles;
    }
    
    public boolean ancetre(int x, int y) {
    	if (x == y) { 																												//si x et y égaux, x pas ancetre de y
    		return false;
    	}
    	
    	if (map.get(y).pere == -1 && map.get(y).mere == -1) {																		//si pas de parents chez y, pas d'ancêtre connu
    		return false;
    	}
    	try {
    		return (x == map.get(y).pere || ancetre(x, map.get(y).pere)) || (x == map.get(y).mere || ancetre(x, map.get(y).mere));
    	}
    	catch (NullPointerException npe){
    		return false;
    	}
    }
    
    public boolean verifAge(int x, int y) {															//verifie si x est plus agé que y, si c'est le cas return true
    	if (map.get(x).naissance.annee < map.get(y).naissance.annee) {
    		return true;
    	}
    	else if (map.get(x).naissance.annee == map.get(y).naissance.annee) {
    		if (map.get(x).naissance.mois < map.get(y).naissance.mois) {return true;}
    		else if (map.get(x).naissance.mois == map.get(y).naissance.mois) {
    			if (map.get(x).naissance.jour < map.get(y).naissance.jour) {return true;}
    			else if (map.get(x).naissance.jour == map.get(y).naissance.jour) {return false;}
    			else return false;
    		}
    		else return false;
    	}
    	else {
    		return false;
    	}
    }
    
    public int plus_ancien(int x) {
    	if (map.get(x).pere == -1 && map.get(x).mere == -1) {				//si pas de parents chez x, c'est lui-même l'ancetre commun
    		return x;
    	}
    	
    	if (map.get(x).mere == -1) {
    		return plus_ancien(map.get(x).pere);
    	}
    	if (map.get(x).pere == -1) {
    		return plus_ancien(map.get(x).mere);
    	}
    	
    	int mere_ancienne = plus_ancien(map.get(x).mere);
    	int pere_ancien = plus_ancien(map.get(x).pere);
    	if (verifAge(mere_ancienne, pere_ancien) == true) {
    		return mere_ancienne;
    	}
    	else {
    		return pere_ancien;
    	}
    }
    
    static int compteur = 1;
    public void affiche_parente(int x) {
    	ArrayList<String> gen = new ArrayList<>();
        if((map.get(x).pere != -1) && (map.get(x).mere != -1)){
            Individu pere = getIndividu(map.get(x).pere);
            Individu mere = getIndividu(map.get(x).mere);
            gen.add(pere.nom);
            gen.add(mere.nom);
        }

    	ArrayList<String> oncles = affiche_oncles(x);
    	gen.addAll(oncles);
    	System.out.println("- "+compteur+":");
    	for (String nom : gen) {
    		System.out.println(" "+nom);
    	}
    	compteur++;
    	
    	if (map.get(x).mere == -1) {
    		affiche_parente(map.get(x).pere);
    	}
    	if (map.get(x).pere == -1) {
    		affiche_parente(map.get(x).mere);
    	}
    	else {
    		affiche_parente(map.get(x).mere);
    		affiche_parente(map.get(x).pere);
    	}
    }
}
