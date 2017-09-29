package services;

import java.util.ArrayList;
import java.util.List;

import modeles.AmGaranti;

/**
 * Created by LENOVO on 7/30/2017.
 */

public class AutoMotoService {

    public List<AmGaranti> getGaranties() {
        List<AmGaranti> res = new ArrayList<AmGaranti>();
        res.add(new AmGaranti(1,"Responsabilité civile","RC",new Double(200000)));
        res.add(new AmGaranti(2,"Tièrce complète","B",new Double(0)));
        res.add(new AmGaranti(3,"Tierce collision","B'",new Double(1000)));
        res.add(new AmGaranti(4,"Tierce collision ammenagée","B''",new Double(30000)));
        res.add(new AmGaranti(5,"Perte total uniquement","",new Double(12000)));
        res.add(new AmGaranti(6,"Incendie et explosions","",new Double(4000)));
        res.add(new AmGaranti(7,"Vol","",new Double(13000)));
        res.add(new AmGaranti(8,"Bris de glaces","",new Double(40000)));
        res.add(new AmGaranti(9,"Grèves - Emeutes et mouvements populaire","",new Double(14000)));
        res.add(new AmGaranti(10,"Privation de jouissance","",new Double(22000)));
        res.add(new AmGaranti(11,"Protection juridique","",new Double(10400)));
        res.add(new AmGaranti(12,"Indemnisation directe et recours","",new Double(9000)));
        res.add(new AmGaranti(13,"Décès","",new Double(300000)));
        res.add(new AmGaranti(14,"Infirmité Permanente","",new Double(200000)));
        res.add(new AmGaranti(15,"Frais de traitement","",new Double(12000)));
        return res;
    }


}
