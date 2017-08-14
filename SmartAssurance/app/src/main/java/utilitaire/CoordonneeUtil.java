package utilitaire;

import modeles.Agence;
import services.ObjetsStatique;

/**
 * Created by misa on 8/14/2017.
 */

public class CoordonneeUtil {
    public double distance(Coordonnee c1, Coordonnee c2) {
        return Math.sqrt(Math.pow((c2.getLatitude() - c1.getLatitude()), 2) + Math.pow((c2.getLongitude() - c1.getLongitude()), 2));
    }

    public Agence getAgenceProche(Coordonnee currentPos){
        Coordonnee c0 = new Coordonnee(ObjetsStatique.getAgences().get(0).getLatitude(), ObjetsStatique.getAgences().get(0).getLongitude());
        double distance = distance(currentPos, c0);
        Agence res = ObjetsStatique.getAgences().get(0);
        for(Agence a : ObjetsStatique.getAgences()){
            Coordonnee c = new Coordonnee(a.getLatitude(), a.getLongitude());
            double currDist = distance(currentPos, c);
            if(currDist < distance){
                distance = currDist;
                res = a;
            }
        }
        return res;
    }
}
