package modeles;

/**
 * Created by LENOVO on 7/30/2017.
 */

public class AmGaranti {
    private Integer id;
    private String libelle;
    private String code;

    public AmGaranti() {
    }

    public AmGaranti(Integer id, String libelle, String code) {
        this.id = id;
        this.libelle = libelle;
        this.code = code;
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
