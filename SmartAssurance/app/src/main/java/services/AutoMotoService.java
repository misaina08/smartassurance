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
        res.add(new AmGaranti(1, "Responsabilité civile", "RC", new Double(2000)));
        res.add(new AmGaranti(2, "Tièrce complète", "TC", new Double(0)));
        res.add(new AmGaranti(3, "Tièrce collision", "TCL", new Double(10.2)));
        res.add(new AmGaranti(4, "Tièrce collision aménagée", "TCA", new Double(2000)));
        res.add(new AmGaranti(5, "Perte total uniquement", "PTU", new Double(2000)));
        res.add(new AmGaranti(6, "Incendie et explosions", "C", new Double(2000)));
        res.add(new AmGaranti(7, "Vol", "D", new Double(2000)));
        res.add(new AmGaranti(8, "Bris de glaces", "BG", new Double(2000)));
        res.add(new AmGaranti(9, "Grèves - Emeutes et mouvements populaire", "G", new Double(2000)));
        res.add(new AmGaranti(10, "Privation de jouissance", "PJ", new Double(2000)));
        res.add(new AmGaranti(11, "Protection juridique", "PJ", new Double(2000)));
        res.add(new AmGaranti(12, "Indemnisation directe et recours", "I", new Double(2000)));
        res.add(new AmGaranti(13, "Décès", "DC", new Double(2000)));
        res.add(new AmGaranti(14, "Infirmité Permanente", "IP", new Double(2000)));
        res.add(new AmGaranti(15, "Frais de traitement", "FT", new Double(2000)));
        return res;
    }


}
