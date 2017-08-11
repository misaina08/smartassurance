package modeles.automoto;

import modeles.BaseModele;

/**
 * Created by LENOVO on 8/10/2017.
 */

public class SinCategorie extends BaseModele {
    private Integer id;
    private String libelle;

    public SinCategorie(){}

    public SinCategorie(Integer id, String lib){
        this.id = id;
        this.libelle = lib;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String toString(){
        return libelle;
    }
}
