package modeles.automoto;

import modeles.BaseModele;

/**
 * Created by misa on 9/7/2017.
 */

public class AmUsage extends BaseModele {
    private Integer id;
    private String libelle;
    private Double mt;

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

    public Double getMt() {
        return mt;
    }

    public void setMt(Double mt) {
        this.mt = mt;
    }
}
