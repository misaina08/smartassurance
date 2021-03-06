package services;

import java.util.ArrayList;
import java.util.List;

import async.ListProduitAsync;
import modeles.Agence;
import modeles.AmGaranti;
import modeles.automoto.AmDommage;
import modeles.automoto.SinCategorie;
import modeles.produit.Produit;

/**
 * Created by LENOVO on 7/28/2017.
 */

public class ObjetsStatique {
    private static List<String> rtOptions;
    private static List<String> rtTypes;
    private static List<String> rtCotisations;
    private static List<String> usagesVehicule;
    private static List<SinCategorie> sinCategories;
    private static List<AmDommage> amDommages;
    private static List<Produit> produits;
    private static List<Agence> agences;
    private static List<AmGaranti> garanties;

    private static boolean estSurGuichet = false;

    public void init() {
        ListProduitAsync listProduitAsync = new ListProduitAsync();
        listProduitAsync.execute();
        AutoMotoService autoMotoService = new AutoMotoService();
        garanties = autoMotoService.getGaranties();

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

        sinCategories = new ArrayList<SinCategorie>();
        sinCategories.add(new SinCategorie(1, "Accident"));
        sinCategories.add(new SinCategorie(2, "Vol/incendie"));
        sinCategories.add(new SinCategorie(3, "autres"));

        amDommages = new ArrayList<AmDommage>();
        amDommages.add(new AmDommage(1, "Pare-chocs "));
        amDommages.add(new AmDommage(2, "Capot "));
        amDommages.add(new AmDommage(3, "Peinture "));
        amDommages.add(new AmDommage(4, "Pneu "));
        amDommages.add(new AmDommage(5,"Toit"));
        amDommages.add(new AmDommage(6,"Pare-brise"));
        amDommages.add(new AmDommage(7,"Coffre"));
        amDommages.add(new AmDommage(8,"Feu arrière"));
        amDommages.add(new AmDommage(9,"Aile arrière"));
        amDommages.add(new AmDommage(10,"Glace de  custode"));
        amDommages.add(new AmDommage(11,"Montant du toit"));
        amDommages.add(new AmDommage(12,"Calandre"));
        amDommages.add(new AmDommage(13,"Enjoliveur de roue"));
        amDommages.add(new AmDommage(14,"Roue"));
        amDommages.add(new AmDommage(15,"Aile avant"));
        amDommages.add(new AmDommage(16,"Retroviseur"));
        amDommages.add(new AmDommage(17,"Essuie glace"));
        amDommages.add(new AmDommage(18,"Capot"));
        amDommages.add(new AmDommage(19,"Clignotant"));
        amDommages.add(new AmDommage(20,"Glace"));
        amDommages.add(new AmDommage(21,"Poigné de porte"));
        amDommages.add(new AmDommage(22,"Porte"));
        amDommages.add(new AmDommage(23,"Phare"));
        amDommages.add(new AmDommage(24,"Toit ouvrant"));


//        produits = new ArrayList<Produit>();
//        produits.add(new Produit(1, "Assurance vie"));
//        produits.add(new Produit(2, "Assurance auto-moto"));
//        produits.add(new Produit(3, "Assurance retraite"));

        usagesVehicule = new ArrayList<String>();
        usagesVehicule.add("Particulier");
        usagesVehicule.add("Entreprise");

        agences = new ArrayList<Agence>();
        agences.add(new Agence(1, "Antsahavola", "Antananarivo", "Antsahavola", "Antsahavola", new Double(47.522285), new Double(-18.908629), "aa", 101, "033890890", "antsahavola@gmail.com"));
        agences.add(new Agence(2, "Anosizato", "Antananarivo", "Anosizato", "Anosizato", new Double(47.496558), new Double(-18.938178), "aa", 101, "033890890", "antsahavola@gmail.com"));
        agences.add(new Agence(3, "Ampefiloha", "Antananarivo", "Ampefiloha", "Ampefiloha", new Double(47.517867), new Double(-18.912766), "aa", 101, "033890890", "antsahavola@gmail.com"));

    }

    public static List<AmDommage> getAmDommages() {
        return amDommages;
    }

    public static void setAmDommages(List<AmDommage> amDommages) {
        ObjetsStatique.amDommages = amDommages;
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

    public static List<SinCategorie> getSinCategories() {
        return sinCategories;
    }

    public static void setSinCategories(List<SinCategorie> sinCategories) {
        ObjetsStatique.sinCategories = sinCategories;
    }

    public static List<Produit> getProduits() {
        return produits;
    }

    public static void setProduits(List<Produit> produits) {
        ObjetsStatique.produits = produits;
    }

    public static List<Agence> getAgences() {
        return agences;
    }

    public static void setAgences(List<Agence> agences) {
        ObjetsStatique.agences = agences;
    }

    public static boolean isEstSurGuichet() {
        return estSurGuichet;
    }

    public static void setEstSurGuichet(boolean estSurGuichet) {
        ObjetsStatique.estSurGuichet = estSurGuichet;
    }

    public static List<String> getUsagesVehicule() {
        return usagesVehicule;
    }

    public static void setUsagesVehicule(List<String> usagesVehicule) {
        ObjetsStatique.usagesVehicule = usagesVehicule;
    }

    public static List<AmGaranti> getGaranties() {
        return garanties;
    }

    public static void setGaranties(List<AmGaranti> garanties) {
        ObjetsStatique.garanties = garanties;
    }
}
