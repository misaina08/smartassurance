package services;

import java.util.ArrayList;
import java.util.List;

import modeles.AmGaranti;

/**
 * Created by LENOVO on 7/30/2017.
 */

public class AutoMotoService {

    public List<AmGaranti> getGaranties(){
        List<AmGaranti> res = new ArrayList<AmGaranti>();
        res.add(new AmGaranti(1, "Responsabilité civile", "RC"));
        res.add(new AmGaranti(2, "Tièrce complète", "TC"));
        res.add(new AmGaranti(3, "Tièrce collision", "TCL"));
        res.add(new AmGaranti(4, "Tièrce collision aménagée", "TCA"));
        res.add(new AmGaranti(5, "Perte total uniquement", "PTU"));
        res.add(new AmGaranti(6, "Incendie et explosions", "C"));
        res.add(new AmGaranti(7, "Vol", "D"));
        res.add(new AmGaranti(8, "Bris de glaces", "BG"));
        res.add(new AmGaranti(9, "Grèves - Emeutes et mouvements populaire", "G"));
        res.add(new AmGaranti(10, "Privation de jouissance", "PJ"));
        res.add(new AmGaranti(11, "Protection juridique", "PJ"));
        res.add(new AmGaranti(12, "Indemnisation directe et recours", "I"));
        res.add(new AmGaranti(13, "Décès", "DC"));
        res.add(new AmGaranti(14, "Infirmité Permanente", "IP"));
        res.add(new AmGaranti(15, "Frais de traitement", "FT"));
        return res;
    }


}
