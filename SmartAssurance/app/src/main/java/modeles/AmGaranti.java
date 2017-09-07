package modeles;

/**
 * Created by LENOVO on 7/30/2017.
 */

public class AmGaranti {
    private Integer id;
    private String libelle;
    private String code;
    private Double limiteMin;

    public AmGaranti() {
    }

    public AmGaranti(Integer id, String libelle, String code, Double limiteMin) {
        this.id = id;
        this.libelle = libelle;
        this.code = code;
        this.limiteMin = limiteMin;
    }

    public Double getLimiteMin() {
        return limiteMin;
    }

    public void setLimiteMin(Double limiteMin) {
        this.limiteMin = limiteMin;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
