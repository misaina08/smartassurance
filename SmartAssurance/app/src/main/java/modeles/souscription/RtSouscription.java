package modeles.souscription;

/**
 * Created by LENOVO on 7/28/2017.
 */

public class RtSouscription extends Souscription {
    private Integer idCotisation;
    private Integer idType;
    private Integer idOption;
    private String beneficiare;
    private Integer ageRetraite;

    public RtSouscription() {

    }

    public RtSouscription(Integer idCotisation, Integer idType, Integer idOption, String beneficiare, Integer ageRetraite) {
        this.idCotisation = idCotisation;
        this.idType = idType;
        this.idOption = idOption;
        this.beneficiare = beneficiare;
        this.ageRetraite = ageRetraite;
    }

    public Integer getIdCotisation() {
        return idCotisation;
    }

    public void setIdCotisation(Integer idCotisation) {
        this.idCotisation = idCotisation;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public Integer getIdOption() {
        return idOption;
    }

    public void setIdOption(Integer idOption) {
        this.idOption = idOption;
    }

    public String getBeneficiare() {
        return beneficiare;
    }

    public void setBeneficiare(String beneficiare) {
        this.beneficiare = beneficiare;
    }

    public Integer getAgeRetraite() {
        return ageRetraite;
    }

    public void setAgeRetraite(Integer ageRetraite) {
        this.ageRetraite = ageRetraite;
    }
}
