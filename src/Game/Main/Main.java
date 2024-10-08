package Game.Main;

import org.xml.sax.SAXException;

import javax.swing.JFrame;
import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException, SAXException
    {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("game");
        GamePanel game_Panel = new GamePanel();
        window.add(game_Panel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        System.out.println(window.getWidth() + " " + window.getHeight());
        game_Panel.startGameThread();
    }
}