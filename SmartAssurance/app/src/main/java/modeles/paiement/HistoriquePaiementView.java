package modeles.paiement;

import java.util.Date;

import modeles.BaseModele;

/**
 * Created by LENOVO on 8/8/2017.
 */

public class HistoriquePaiementView extends BaseModele {
    private Integer id;
    private String motif;
    private Double mt;
    private Integer idSouscription;
    private Integer idMoyenPaiementClient;
    private Integer idProduit;
    private String nopolice;
    private Integer duree;
    private Double primeNet;
    private Double primeTotal;
    private Date daty;
    private String produit;
    private String numeroCompte;
    private String nomComplet;
    private String moyenPaiement;
    private Date dateOperation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Double getMt() {
        return mt;
    }

    public void setMt(Double mt) {
        this.mt = mt;
    }

    public Integer getIdSouscription() {
        return idSouscription;
    }

    public void setIdSouscription(Integer idSouscription) {
        this.idSouscription = idSouscription;
    }

    public Integer getIdMoyenPaiementClient() {
        return idMoyenPaiementClient;
    }

    public void setIdMoyenPaiementClient(Integer idMoyenPaiementClient) {
        this.idMoyenPaiementClient = idMoyenPaiementClient;
    }

    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public String getNopolice() {
        return nopolice;
    }

    public void setNopolice(String nopolice) {
        this.nopolice = nopolice;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public Double getPrimeNet() {
        return primeNet;
    }

    public void setPrimeNet(Double primeNet) {
        this.primeNet = primeNet;
    }

    public Double getPrimeTotal() {
        return primeTotal;
    }

    public void setPrimeTotal(Double primeTotal) {
        this.primeTotal = primeTotal;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
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

    public String getMoyenPaiement() {
        return moyenPaiement;
    }

    public void setMoyenPaiement(String moyenPaiement) {
        this.moyenPaiement = moyenPaiement;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }
}
