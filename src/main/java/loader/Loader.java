package loader;

import DAO.MySqlDAO;
import config.Config;
import parser.Parser;

import java.io.IOException;

public class Loader {

    public static void main(String[] args) throws IOException {
        Config c = Config.getInstance();

        Parser parser = new Parser(c.getLinks());
        parser.start();
    }
}
