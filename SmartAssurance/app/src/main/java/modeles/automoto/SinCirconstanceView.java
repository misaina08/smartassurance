package modeles.automoto;

import modeles.BaseModele;

/**
 * Created by misa on 8/13/2017.
 */

public class SinCirconstanceView extends BaseModele {
    private Integer id;
    private Integer etape;
    private String description;
    private Integer idsinistre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEtape() {
        return etape;
    }

    public void setEtape(Integer etape) {
        this.etape = etape;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIdsinistre() {
        return idsinistre;
    }

    public void setIdsinistre(Integer idsinistre) {
        this.idsinistre = idsinistre;
    }
}
