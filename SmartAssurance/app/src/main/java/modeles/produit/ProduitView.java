package modeles.produit;

import modeles.BaseModele;

/**
 * Created by Misaina on 27/07/2017.
 */

public class ProduitView extends BaseModele {
    private Integer id;
    private String nom;

    public ProduitView(){

    }
    public ProduitView(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
