package DAO;

import entity.Entry;

import java.sql.*;
import java.util.LinkedList;

public class MySqlDAO implements EntryDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/mytestbase?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "testuser";
    private static final String PASSWORD = "testpass";


    @Override
    public void add(LinkedList<Entry> entries) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement prstatement = con.prepareStatement("insert into news (author, name, link, imglink, date) values (?, ?, ?, ?, ?)");
            try {
                Statement stm = con.createStatement();
                for (Entry entry : entries) {
                    String imglink = entry.getImgLink().equals("") ? null : entry.getImgLink();
                    prstatement.setString(1, entry.getAuthor());
                    prstatement.setString(2, entry.getName());
                    prstatement.setString(3, entry.getLink());
                    prstatement.setString(4, entry.getImgLink());
                    prstatement.setString(5, entry.getDate());
                    prstatement.execute();
                }
                stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean alreadyExists(String link) {
        Boolean exist = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement prstatement = con.prepareStatement("select count(link) from news where link=?");
            try {
                Statement stm = con.createStatement();
                prstatement.setString(1, link);
                ResultSet rs = prstatement.executeQuery();
                rs.first();
                exist = rs.getInt(1) > 0;
                stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exist;
    }


}
