package modeles.retraite;

import java.util.Date;

import modeles.BaseModele;

/**
 * Created by LENOVO on 8/6/2017.
 */

public class RtDepot extends BaseModele {
    private Integer id;
    private Integer idRtContrat;
    private Double valeur;
    private Date daty;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdRtContrat() {
        return idRtContrat;
    }

    public void setIdRtContrat(Integer idRtContrat) {
        this.idRtContrat = idRtContrat;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }
}
