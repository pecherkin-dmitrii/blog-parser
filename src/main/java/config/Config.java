package config;

import java.io.IOException;
import java.util.LinkedList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Config {

    private static Config config;
    private LinkedList<String> links = new LinkedList<>();

    private Config(LinkedList<String> links) {
        this.links = links;
    }

    public static Config getInstance() {
        if (config == null) {
            LinkedList<String> linksTemp = new LinkedList<>();
            try {
                DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

                Document document = documentBuilder.parse("src/main/resources/links.xml");

                NodeList linkNodes = document.getElementsByTagName("link");
                for (int i = 0; i < linkNodes.getLength(); i++) {
                    linksTemp.add(linkNodes.item(i).getTextContent());
                }
            }
            catch (ParserConfigurationException e) {
                System.out.println(e);
            }
            catch (SAXException e) {
                System.out.println(e);
            }
            catch (IOException e) {
                System.out.println(e);
            }

            config = new Config(linksTemp);
        }
        return config;
    }

    public LinkedList<String> getLinks() {
        return links;
    }
}
