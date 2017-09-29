package modeles.automoto;

import modeles.BaseModele;

/**
 * Created by misa on 9/20/2017.
 */

public class SinRapportView extends BaseModele {
    private Integer id;

    private String description;
    private Double valeur;
    private Integer idsinistre;
    public SinRapportView() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }

    public Integer getIdsinistre() {
        return idsinistre;
    }

    public void setIdsinistre(Integer idsinistre) {
        this.idsinistre = idsinistre;
    }
}
