package modeles.automoto;

import modeles.BaseModele;

/**
 * Created by misa on 8/13/2017.
 */

public class PhotosSinistreView extends BaseModele {
    private Integer id;
    private Integer idsinistre;
    private String photo;

    public PhotosSinistreView() {
    }

    public PhotosSinistreView(Integer id, Integer idsinistre, String photo) {
        this.id = id;
        this.idsinistre = idsinistre;
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdsinistre() {
        return idsinistre;
    }

    public void setIdsinistre(Integer idsinistre) {
        this.idsinistre = idsinistre;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
