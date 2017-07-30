package services;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 7/28/2017.
 */

public class ObjetsStatique {
    private static List<String> rtOptions;
    private static List<String> rtTypes;
    private static List<String> rtCotisations;

    public void init(){
        rtOptions = new ArrayList<String>();
        rtTypes = new ArrayList<String>();
        rtCotisations = new ArrayList<String>();
        rtOptions.add("Annuelle");
        rtOptions.add("Semestrielle");
        rtOptions.add("Trimestrielle");
        rtOptions.add("Capitalisée");

        rtTypes.add("110% Retraite complémentaire");
        rtTypes.add("200% Retraite minute");

        rtCotisations.add("Annuelle");
        rtCotisations.add("Semestrielle");
        rtCotisations.add("Trimestrielle");
        rtCotisations.add("Mensuelle");
        rtCotisations.add("Libre");
    }

    public static List<String> getRtOptions() {
        return rtOptions;
    }

    public static void setRtOptions(List<String> rtOptions) {
        ObjetsStatique.rtOptions = rtOptions;
    }

    public static List<String> getRtTypes() {
        return rtTypes;
    }

    public static void setRtTypes(List<String> rtTypes) {
        ObjetsStatique.rtTypes = rtTypes;
    }

    public static List<String> getRtCotisations() {
        return rtCotisations;
    }

    public static void setRtCotisations(List<String> rtCotisations) {
        ObjetsStatique.rtCotisations = rtCotisations;
    }
}
