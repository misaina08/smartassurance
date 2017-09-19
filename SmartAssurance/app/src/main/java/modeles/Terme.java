package modeles;

/**
 * Created by misa on 9/19/2017.
 */

public class Terme extends BaseModele {
    private Integer id;

    private String terme;

    private String explication;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTerme() {
        return terme;
    }

    public void setTerme(String terme) {
        this.terme = terme;
    }

    public String getExplication() {
        return explication;
    }

    public void setExplication(String explication) {
        this.explication = explication;
    }
}
