package Game.UI;

import Game.Animations.Animation;
import Game.EntityFactory.Player;
import Game.Events.Event;
import Game.Events.Observer;
import Game.Main.KeyHandler;
import Game.Main.MouseHandler;
import Game.Manager.AssetManager;
import Game.Manager.Const;
import Game.Manager.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Menu
{
    //menu
    private int timer = 0;
    private int closeTimer = 60;

    private int []cutRepeat = new int[6];
    public int state = 0;
    public int menuRepeat = 6;
    private int menuSelect = 0;
    private boolean canPress;
    private int direction = 0;
    private int pageRepeat = 4;
    AssetManager a;
    Tile menuBackground;
    Animation openMenu;
    Animation closeMenu;
    Animation []popCutOut;
    Animation pageLeft;
    Animation pageRight;
    BufferedImage selectCutOut;
    BufferedImage []cutoutIcons;
    Player p;
    Event e;
    KeyHandler key;
    MouseHandler mouse;

    //page 1
    BufferedImage veritas;
    BufferedImage []statsIcons;
    BufferedImage []expBar;
    BufferedImage skillTree;
    BufferedImage []stIcons;
    Animation selectSpell;
    Page_1 p1;

    public Menu()
    {
        p1 = new Page_1();
        p = Observer.getInstance().player;
        e = p.getEvent();
        e.inGameMenu = true;
        a = AssetManager.getInstance();
        key = KeyHandler.getInstance();
        mouse = MouseHandler.getInstance();
        menuBackground = a.getMenu("Menu_Background")[0];
        openMenu = new Animation(a.getMenu("Menu_Open"), 5, 16 * Const.tileSize, Const.screenHeight, Const.menuScale);
        closeMenu = new Animation(a.getMenu("Menu_Close"), 5, 16 * Const.tileSize, Const.screenHeight, Const.menuScale);
        popCutOut = new Animation[6];
        selectCutOut = a.getMenu("Menu_CutOut")[4].getImage();
        pageLeft = new Animation(a.getMenu("Page_Left"), 8, 16 * Const.tileSize, Const.screenHeight, Const.menuScale);
        pageRight = new Animation(a.getMenu("Page_Right"), 8, 16 * Const.tileSize, Const.screenHeight, Const.menuScale);
        cutRepeatReset();
        cutoutIcons = new BufferedImage[6];
        for(int i = 0; i < 6; ++i)
        {
            cutoutIcons[i] = a.getMenu("Cutout_Icons")[i].getImage();
            popCutOut[i] = new Animation(a.getMenu("Menu_CutOut"), 4, Const.tileSize, Const.tileSize, 1);
        }
    }
    public void update()
    {
        if(state == 1 && timer < closeTimer)
        {
            p.getPlayerAnimation().resetCurrent_state();
            timer++;
            key.escape_On = false;
        }
        if(state == 1 && key.escape_On && timer == closeTimer)
        {
            p1.reset();
            menuSelect = 0;
            menuRepeat = 6;
            state = 2;
            key.escape_On = false;
        }
        if(state == 0 && key.escape_On)
        {
            timer = 0;
            pageRepeat = 4;
            state = 1;
            key.escape_On = false;
        }
        if((!key.down_On && !key.up_On) && (!key.right_On && !key.left_On))
        {
            pageRepeat = 4;
            canPress = true;
        }
        if(((key.up_On && !key.down_On) || (key.left_On && !key.right_On)) && canPress && menuRepeat == -1)
        {
            if(menuSelect > 0)
            {
                direction = 1;
                canPress = false;
                menuSelect--;
            }
        }
        if(((key.down_On && !key.up_On) || (key.right_On && !key.left_On)) && canPress && menuRepeat == -1)
        {
            if(menuSelect < 5)
            {
                direction = 2;
                canPress = false;
                menuSelect++;
            }
        }
        p1.update();
    }
    public void draw(Graphics2D g2d)
    {
        int tileSize = Const.tileSize;
        g2d.setColor(Color.BLACK);
        g2d.setFont(a.getFont().deriveFont(20f));
        g2d.drawImage(menuBackground.getImage(), 0, 0, Const.screenWidth, Const.screenHeight, null);
        if(state == 2 && closeMenu.draw(g2d, tileSize * 4 + tileSize / 2, 0, true, menuRepeat, 1))
        {
            cutRepeatReset();
            state = 0;
        }
        if(state == 1 && openMenu.draw(g2d, tileSize * 4 + tileSize / 2, 0, true, menuRepeat, 1))
        {
            menuRepeat = -1;
            if((menuSelect == 0 && direction == 0) || (menuSelect == 1 && direction == 2))
            {
                p1.draw(g2d);
            }
            if(canPress && direction == 1 && pageRight.draw(g2d, tileSize * 4 + tileSize / 2 + 5, 0, true, pageRepeat, 1))
            {
                direction = 0;
                pageRepeat = -1;
            }
            if(canPress && direction == 2 && pageLeft.draw(g2d, tileSize * 4 + tileSize / 2, 0, true, pageRepeat, 1))
            {
                direction = 0;
                pageRepeat = -1;
            }
            if(popCutOut[0].draw(g2d, tileSize * 19, tileSize * 4, true, cutRepeat[0], 1))
            {
                g2d.drawImage(cutoutIcons[0], tileSize * 19 + 10, tileSize * 4, tileSize, tileSize, null);
                cutRepeat[0] = -1;
                if(popCutOut[1].draw(g2d, tileSize * 19, tileSize * 4 + tileSize, true, cutRepeat[1], 1))
                {
                    g2d.drawImage(cutoutIcons[1], tileSize * 19 + 10, tileSize * 4 + tileSize, tileSize, tileSize, null);
                    cutRepeat[1] = -1;
                    if(popCutOut[2].draw(g2d, tileSize * 19, tileSize * 4 + 2 * tileSize, true, cutRepeat[2], 1))
                    {
                        g2d.drawImage(cutoutIcons[2], tileSize * 19 + 10, tileSize * 4 + 2 * tileSize, tileSize, tileSize, null);
                        cutRepeat[2] = -1;
                        if(popCutOut[3].draw(g2d, tileSize * 19, tileSize * 4 + 3 * tileSize, true, cutRepeat[3], 1))
                        {
                            g2d.drawImage(cutoutIcons[3], tileSize * 19 + 10, tileSize * 4 + 3 * tileSize, tileSize, tileSize, null);
                            cutRepeat[3] = -1;
                            if(popCutOut[4].draw(g2d, tileSize * 19, tileSize * 4 + 4 * tileSize, true, cutRepeat[4], 1))
                            {
                                g2d.drawImage(cutoutIcons[4], tileSize * 19 + 10, tileSize * 4 + 4 * tileSize, tileSize, tileSize, null);
                                cutRepeat[4] = -1;
                                if(popCutOut[5].draw(g2d, tileSize * 19, tileSize * 4 + 5 * tileSize, true, cutRepeat[5], 1))
                                {
                                    g2d.drawImage(cutoutIcons[5], tileSize * 19 + 10, tileSize * 4 + 5 * tileSize, tileSize, tileSize, null);
                                    cutRepeat[5] = -1;
                                }
                            }
                        }
                    }
                }
            }
            g2d.drawImage(selectCutOut, tileSize * 19, tileSize * 4 + menuSelect * tileSize, tileSize, tileSize, null);
            g2d.drawImage(cutoutIcons[menuSelect], tileSize * 19 + 15, tileSize * 4 + menuSelect * tileSize, tileSize, tileSize, null);
        }
    }
    private void cutRepeatReset()
    {
        for(int i = 0; i < 6; ++i)
        {
            cutRepeat[i] = 6;
        }
    }
}
