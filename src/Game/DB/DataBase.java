package Game.DB;
import Game.Main.GamePanel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class DataBase
{
    GamePanel gp;

    public DataBase(GamePanel gp) {
        this.gp = gp;
    }
    public static void insertInDataBase(String nume_fisier, String nume_tabela, int a) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + nume_fisier + ".db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String sql = "INSERT INTO " + nume_tabela + "(score) " + "VALUES (" + a + ");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }
    public static int getFromDataBase(String nume_fisier, String nume_tabela) {
        Connection c = null;
        Statement stmt = null;
        int value = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + nume_fisier + ".db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM " + nume_tabela + ";");
            while (rs.next()) {
                value = rs.getInt("score");
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return value;
    }
}