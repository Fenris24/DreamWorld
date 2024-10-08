package Game.Main;
import Game.Manager.Const;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
public class MouseHandler extends JPanel implements MouseListener, MouseMotionListener
{
    private static MouseHandler instance;
    public int mouseX, mouseY;
    public boolean leftClick, rightClick;

    public static MouseHandler getInstance()
    {
        if (instance == null)
        {
            instance = new MouseHandler();
        }
        return instance;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
    }
    @Override
    public void mousePressed(MouseEvent e)
    {
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            leftClick = true;
        }
        if(e.getButton() == MouseEvent.BUTTON3)
        {
            rightClick = true;
        }
    }
    @Override
    public void mouseReleased(MouseEvent e)
    {
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            leftClick = false;
        }
        if(e.getButton() == MouseEvent.BUTTON3)
        {
            rightClick = false;
        }
    }
    @Override
    public void mouseEntered(MouseEvent e){}
    @Override
    public void mouseExited(MouseEvent e){}
    @Override
    public void mouseMoved(MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();
    }
    @Override
    public void mouseDragged(MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();
    }
}