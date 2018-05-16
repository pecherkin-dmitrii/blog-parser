package entity;

public class Entry {

    private String author;
    private String name;
    private String link;
    private String imgLink;
    private String date;

    public Entry(String author, String name, String link, String imgLink, String date) {
        this.author = author;
        this.name = name;
        this.link = link;
        this.imgLink = imgLink;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public String getImgLink() {
        return imgLink;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }
}
