package modeles.automoto;

import modeles.BaseModele;

/**
 * Created by LENOVO on 8/5/2017.
 */

public class GarantiVehiculeView extends BaseModele {
    private Integer id;
    private Integer idSouscription;
    private String libelle;
    private String code;
    private Double mtLimite;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdSouscription() {
        return idSouscription;
    }

    public void setIdSouscription(Integer idSouscription) {
        this.idSouscription = idSouscription;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getMtLimite() {
        return mtLimite;
    }

    public void setMtLimite(Double mtLimite) {
        this.mtLimite = mtLimite;
    }
}
