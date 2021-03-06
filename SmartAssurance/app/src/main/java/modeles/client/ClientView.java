/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeles.client;

import java.util.Date;

import modeles.BaseModele;

/**
 * @author Misaina
 */
public class ClientView extends BaseModele {

    private Integer id;

    private Integer clTypeId;

    private Integer agenceId;

    private String nom;

    private String prenom;

    private String adresse;

    private String profession;
    private String codesecret;
    private Date dateNaissance;
    private String lieuNaissance;
    private String cin;
    private String tel;
    private String email;
    private String mdp;
    private String noclient;
    private Date dateSouscription;
    private String cltype;
    private String agence;

    public ClientView() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClTypeId() {
        return clTypeId;
    }

    public void setClTypeId(Integer clTypeId) {
        this.clTypeId = clTypeId;
    }

    public Integer getAgenceId() {
        return agenceId;
    }

    public void setAgenceId(Integer agenceId) {
        this.agenceId = agenceId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
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

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNoclient() {
        return noclient;
    }

    public void setNoclient(String noclient) {
        this.noclient = noclient;
    }

    public Date getDateSouscription() {
        return dateSouscription;
    }

    public void setDateSouscription(Date dateSouscription) {
        this.dateSouscription = dateSouscription;
    }


    public String getCltype() {
        return cltype;
    }

    public void setCltype(String cltype) {
        this.cltype = cltype;
    }

    public String getAgence() {
        return agence;
    }

    public void setAgence(String agence) {
        this.agence = agence;
    }

    public String getCodesecret() {
        return codesecret;
    }

    public void setCodesecret(String codesecret) {
        this.codesecret = codesecret;
    }

}
