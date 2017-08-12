package modeles.automoto;

import modeles.BaseModele;

/**
 * Created by misa on 8/12/2017.
 */

public class DommagesSinistreView extends BaseModele {
    private Integer id;
    private Integer amSinistreId;
    private Integer amDommageId;
    private String dommage;

    public DommagesSinistreView(){}

    public DommagesSinistreView(Integer id, Integer amSinistreId, Integer amDommageId, String dommage) {
        this.id = id;
        this.amSinistreId = amSinistreId;
        this.amDommageId = amDommageId;
        this.dommage = dommage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmSinistreId() {
        return amSinistreId;
    }

    public void setAmSinistreId(Integer amSinistreId) {
        this.amSinistreId = amSinistreId;
    }

    public Integer getAmDommageId() {
        return amDommageId;
    }

    public void setAmDommageId(Integer amDommageId) {
        this.amDommageId = amDommageId;
    }

    public String getDommage() {
        return dommage;
    }

    public void setDommage(String dommage) {
        this.dommage = dommage;
    }
}
