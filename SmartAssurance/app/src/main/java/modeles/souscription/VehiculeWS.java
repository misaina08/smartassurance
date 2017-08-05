package modeles.souscription;

import java.util.Date;
import java.util.List;

import modeles.BaseModele;

/**
 * Created by LENOVO on 7/30/2017.
 */

public class VehiculeWS extends BaseModele {
    private String noImm;
    private String marque;
    private String type;
    private String noSerie;
    private Date dateCirculation;
    private Integer puissance;
    private String se;
    private Integer nbRoues;
    private Integer nbPlaces;
    private Integer idClient;
    private List<SaisiGaranti> garanties;
//    private Integer idSouscription;

    public VehiculeWS() {
    }

//    public Integer getIdSouscription() {
//        return idSouscription;
//    }
//
//    public void setIdSouscription(Integer idSouscription) {
//        this.idSouscription = idSouscription;
//    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getNoImm() {
        return noImm;
    }

    public void setNoImm(String noImm) {
        this.noImm = noImm;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNoSerie() {
        return noSerie;
    }

    public void setNoSerie(String noSerie) {
        this.noSerie = noSerie;
    }

    public Date getDateCirculation() {
        return dateCirculation;
    }

    public void setDateCirculation(Date dateCirculation) {
        this.dateCirculation = dateCirculation;
    }

    public Integer getPuissance() {
        return puissance;
    }

    public void setPuissance(Integer puissance) {
        this.puissance = puissance;
    }

    public String getSe() {
        return se;
    }

    public void setSe(String se) {
        this.se = se;
    }

    public Integer getNbRoues() {
        return nbRoues;
    }

    public void setNbRoues(Integer nbRoues) {
        this.nbRoues = nbRoues;
    }

    public Integer getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(Integer nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public List<SaisiGaranti> getGaranties() {
        return garanties;
    }

    public void setGaranties(List<SaisiGaranti> garanties) {
        this.garanties = garanties;
    }
}
