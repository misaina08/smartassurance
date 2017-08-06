package modeles.retraite;

import java.util.Date;

import modeles.BaseModele;

/**
 * Created by LENOVO on 8/6/2017.
 */

public class RtContratView extends BaseModele {
    private Integer id;

    private Integer idSouscripteur;


    private Integer idPeriodiciteCotisation;

    private Integer idTypeRt;

    private Integer idSouscription;

    private Integer idOptionRt;

    private Date dateRt;

    private String beneficiaires;

    private Integer ageRetraite;

    private Date dateSouscription;

    private Integer valide;

    private String noPolice;

    private String nomSouscripteur;

    private String prenomSouscripteur;

    private String adresseSouscripteur;

    private String professionSouscripteur;

    private Date naissanceSouscripteur;

    private String periodicite;

    private String typeRt;

    private Integer tauxType;

    private String optionRetraite;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdSouscripteur() {
        return idSouscripteur;
    }

    public void setIdSouscripteur(Integer idSouscripteur) {
        this.idSouscripteur = idSouscripteur;
    }

    public Integer getIdPeriodiciteCotisation() {
        return idPeriodiciteCotisation;
    }

    public void setIdPeriodiciteCotisation(Integer idPeriodiciteCotisation) {
        this.idPeriodiciteCotisation = idPeriodiciteCotisation;
    }

    public Integer getIdTypeRt() {
        return idTypeRt;
    }

    public void setIdTypeRt(Integer idTypeRt) {
        this.idTypeRt = idTypeRt;
    }

    public Integer getIdSouscription() {
        return idSouscription;
    }

    public void setIdSouscription(Integer idSouscription) {
        this.idSouscription = idSouscription;
    }

    public Integer getIdOptionRt() {
        return idOptionRt;
    }

    public void setIdOptionRt(Integer idOptionRt) {
        this.idOptionRt = idOptionRt;
    }

    public Date getDateRt() {
        return dateRt;
    }

    public void setDateRt(Date dateRt) {
        this.dateRt = dateRt;
    }

    public String getBeneficiaires() {
        return beneficiaires;
    }

    public void setBeneficiaires(String beneficiaires) {
        this.beneficiaires = beneficiaires;
    }

    public Integer getAgeRetraite() {
        return ageRetraite;
    }

    public void setAgeRetraite(Integer ageRetraite) {
        this.ageRetraite = ageRetraite;
    }

    public Date getDateSouscription() {
        return dateSouscription;
    }

    public void setDateSouscription(Date dateSouscription) {
        this.dateSouscription = dateSouscription;
    }

    public Integer getValide() {
        return valide;
    }

    public void setValide(Integer valide) {
        this.valide = valide;
    }

    public String getNoPolice() {
        return noPolice;
    }

    public void setNoPolice(String noPolice) {
        this.noPolice = noPolice;
    }

    public String getNomSouscripteur() {
        return nomSouscripteur;
    }

    public void setNomSouscripteur(String nomSouscripteur) {
        this.nomSouscripteur = nomSouscripteur;
    }

    public String getPrenomSouscripteur() {
        return prenomSouscripteur;
    }

    public void setPrenomSouscripteur(String prenomSouscripteur) {
        this.prenomSouscripteur = prenomSouscripteur;
    }

    public String getAdresseSouscripteur() {
        return adresseSouscripteur;
    }

    public void setAdresseSouscripteur(String adresseSouscripteur) {
        this.adresseSouscripteur = adresseSouscripteur;
    }

    public String getProfessionSouscripteur() {
        return professionSouscripteur;
    }

    public void setProfessionSouscripteur(String professionSouscripteur) {
        this.professionSouscripteur = professionSouscripteur;
    }

    public Date getNaissanceSouscripteur() {
        return naissanceSouscripteur;
    }

    public void setNaissanceSouscripteur(Date naissanceSouscripteur) {
        this.naissanceSouscripteur = naissanceSouscripteur;
    }

    public String getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(String periodicite) {
        this.periodicite = periodicite;
    }

    public String getTypeRt() {
        return typeRt;
    }

    public void setTypeRt(String typeRt) {
        this.typeRt = typeRt;
    }

    public Integer getTauxType() {
        return tauxType;
    }

    public void setTauxType(Integer tauxType) {
        this.tauxType = tauxType;
    }

    public String getOptionRetraite() {
        return optionRetraite;
    }

    public void setOptionRetraite(String optionRetraite) {
        this.optionRetraite = optionRetraite;
    }
}
