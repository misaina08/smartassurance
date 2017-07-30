package modeles.produit;

import modeles.BaseModele;

/**
 * Created by Misaina on 27/07/2017.
 */

public class Produit extends BaseModele {
    private Integer id;
    private String nom;
    private String code;
    public Produit(){

    }
    public Produit(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
