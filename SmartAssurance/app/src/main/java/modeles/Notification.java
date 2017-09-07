package modeles;

/**
 * Created by misa on 9/6/2017.
 */

public class Notification {
    private String titre;
    private String contenu;

    private String target;

    public Notification(String titre, String contenu, String target) {
        this.titre = titre;
        this.contenu = contenu;
        this.target = target;
    }

    public Notification() {
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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
