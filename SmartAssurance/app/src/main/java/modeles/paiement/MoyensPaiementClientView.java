package modeles.paiement;

import java.util.Date;

import modeles.BaseModele;

/**
 * Created by LENOVO on 8/8/2017.
 */

public class MoyensPaiementClientView extends BaseModele{
    private Integer id;

    private String numeroCompte;

    private String nomComplet;

    private Date dateExpiration;

    private Integer idClient;

    private Integer idMoyenPaiement;

    private String libelle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public Integer getIdMoyenPaiement() {
        return idMoyenPaiement;
    }

    public void setIdMoyenPaiement(Integer idMoyenPaiement) {
        this.idMoyenPaiement = idMoyenPaiement;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
