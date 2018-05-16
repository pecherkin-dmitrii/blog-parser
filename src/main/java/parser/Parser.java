package parser;

import DAO.EntryDAO;
import DAO.MySqlDAO;
import entity.Entry;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;

public class Parser {

    private LinkedList<String> links;
    private LinkedList<Entry> entries;
    private EntryDAO db;

    public Parser(LinkedList<String> links) {
        this.links = links;
        entries = new LinkedList<>();
        db = new MySqlDAO();
    }

    public void start() throws IOException {
        for (int i = 0; i < links.size(); i++) {
            createEntries(links.get(i));
        }

        db.add(entries);
        entries.clear();
    }

    private void createEntries(String link) throws IOException {
        Document doc = Jsoup.connect(link).get();

        String author = doc.select("#form-login > input").first().attr("value");

        Elements articles = doc.select("article");

        for (int i = 0; i < articles.size(); i++) {
            String entryName = "";
            String entryLink = "";
            String imgLink = "";
            String date = "";

            Element article = articles.get(i);
            Element aElement = article.select("h1 > a").first();
            Element tags = article.siblingElements().select(".e2-note-tags").first();

            entryLink = aElement.attr("href");
            if (db.alreadyExists(entryLink))
                continue;
            entryName = aElement.text();
            imgLink = article.select("img[src]").first() == null ? "" : article.select("img[src]").first().attr("src");
            date = tags.select(".e2-timestamp").first().attr("title");

            entries.add(new Entry(author, entryName, entryLink, imgLink, date));
        }
    }
}
