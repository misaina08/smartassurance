package modeles.automoto;

import modeles.BaseModele;

/**
 * Created by misa on 8/12/2017.
 */

public class AmDommage extends BaseModele {
    private Integer id;
    private String libelle;

    public AmDommage(){}

    public AmDommage(Integer id, String libelle) {
        this.id = id;
        this.libelle = libelle;
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
