package modeles.produit;

import modeles.BaseModele;

/**
 * Created by Misaina on 27/07/2017.
 */

public class Produit extends BaseModele {
    private Integer id;
    private String intitule;
    private String code;
    private String description;
    private String procedures;
    private String piecesNecessaires;

    public Produit() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProcedures() {
        return procedures;
    }

    public void setProcedures(String procedures) {
        this.procedures = procedures;
    }

    public String getPiecesNecessaires() {
        return piecesNecessaires;
    }

    public void setPiecesNecessaires(String piecesNecessaires) {
        this.piecesNecessaires = piecesNecessaires;
    }
}
