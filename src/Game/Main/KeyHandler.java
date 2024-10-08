package Game.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
    private static KeyHandler instance;
    public boolean up_On, down_On, left_On, right_On, shift_On, space_On, escape_On;

    public static KeyHandler getInstance()
    {
        if (instance == null)
        {
            instance = new KeyHandler();
        }
        return instance;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_ESCAPE && !escape_On)
        {
            escape_On = true;
        }
        else if(code == KeyEvent.VK_ESCAPE)
        {
            escape_On = false;
        }
        if (code == KeyEvent.VK_SHIFT)
        {
            shift_On = true;
        }
        if (code == KeyEvent.VK_SPACE)
        {
            space_On = true;
        }
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
        {
            up_On = true;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
        {
            left_On = true;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
        {
            down_On = true;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
        {
            right_On = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_SHIFT)
        {
            shift_On = false;
        }
        if (code == KeyEvent.VK_SPACE)
        {
            space_On = false;
        }
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
        {
            up_On = false;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
        {
            left_On = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
        {
            down_On = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
        {
            right_On = false;
        }
    }
}
