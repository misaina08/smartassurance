package modeles;

import java.util.Date;

/**
 * Created by misa on 9/16/2017.
 */

public class Actualite extends BaseModele {
    private Integer id;
    private String titre;
    private String contenu;
    private String resumes;
    private String photo;
    private Date daty;
    private String heure;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getResumes() {
        return resumes;
    }

    public void setResumes(String resumes) {
        this.resumes = resumes;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }
}
