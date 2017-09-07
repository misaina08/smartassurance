/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.model;

import java.util.List;

import modeles.BaseModele;

/**
 *
 * @author LENOVO
 */
public class Chat extends BaseModele{
    private List<String> motscles;
    private String libelle;
    private String action;
    private String param;
    private String typesparams;

    public List<String> getMotscles() {
        return motscles;
    }

    public void setMotscles(List<String> motscles) {
        this.motscles = motscles;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getTypesparams() {
        return typesparams;
    }

    public void setTypesparams(String typesparams) {
        this.typesparams = typesparams;
    }
}
