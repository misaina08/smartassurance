package modeles;

import java.io.Serializable;

/**
 * @author Misaina
 */
public class Agence extends BaseModele implements Serializable {

    private Integer id;

    private String nom;

    private String ville;

    private String rue;

    private String adresse;

    private Double longitude;

    private Double latitude;

    private String code;
    private Integer codePostal;
    private String tel;
    private String email;

    public Agence() {
    }

    public Agence(Integer id, String nom, String ville, String rue, String adresse, Double longitude, Double latitude, String code, Integer codePostal, String tel, String email) {
        this.id = id;
        this.nom = nom;
        this.ville = ville;
        this.rue = rue;
        this.adresse = adresse;
        this.longitude = longitude;
        this.latitude = latitude;
        this.code = code;
        this.codePostal = codePostal;
        this.tel = tel;
        this.email = email;
    }

    public Integer getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(Integer codePostal) {
        this.codePostal = codePostal;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
