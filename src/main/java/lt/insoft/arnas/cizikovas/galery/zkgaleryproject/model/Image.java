package lt.insoft.arnas.cizikovas.galery.zkgaleryproject.model;

public class Image {

    private long id;
    private String url;
    private String description;

    public Image() {
    }

    public Image(long id, String url, String description) {
        this.id = id;
        this.url = url;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }
}
