package Game.UI;

import Game.Animations.Animation;
import Game.EntityFactory.Player;
import Game.Events.Event;
import Game.Events.Observer;
import Game.Fight.SpellMenu;
import Game.Main.MouseHandler;
import Game.Manager.AssetManager;
import Game.Manager.Const;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Page_1
{
    Player p;
    BufferedImage veritas;
    BufferedImage []statsIcons;
    BufferedImage []expBar;
    MouseHandler mouse;
    BufferedImage skillTree;
    BufferedImage []stIcons;
    Animation selectSpell;
    BufferedImage []spellIcons;
    BufferedImage textCutout;
    int slotSelect = -1;
    int spellSelect = -1;
    int []slotX = new int[4];
    int slotY = Const.tileSize * 10 + Const.tileSize / 8;
    public int []spellOnSlot;
    int []iX = new int[12];
    int []iY = new int[12];
    int coverW = Const.tileSize + Const.tileSize * 2 / 7;
    int coverH = Const.tileSize + Const.tileSize / 6;
    public Page_1()
    {
        p = Observer.getInstance().player;
        mouse = MouseHandler.getInstance();
        AssetManager a = AssetManager.getInstance();
        stIcons = new BufferedImage[3];
        expBar = new BufferedImage[6];
        statsIcons = new BufferedImage[8];
        skillTree = a.getMenu("Skill_Tree")[0].getImage();

        textCutout = a.getMenu("Text_Cutout")[0].getImage();
        for(int i = 0; i < 3; ++i)
        {
            stIcons[i] = a.getMenu("ST_Icons")[i].getImage();
        }
        for(int i = 0; i < 6; ++i)
        {
            expBar[i] = a.getBar("Exp_Bar")[i].getImage();
        }
        for(int i = 0; i < 8; ++i)
        {
            statsIcons[i] = a.getMenu("Stats_Icons")[i].getImage();
        }
        spellIcons = new BufferedImage[12];
        spellIcons[0] = a.getSpellIcon("Fire_Dance");
        spellIcons[1] = a.getSpellIcon("Divine_Smite");
        spellIcons[2] = a.getSpellIcon("Vampirism");
        spellIcons[3] = a.getSpellIcon("Divine_Shield");
        spellIcons[4] = a.getSpellIcon("Divine_Blessing");
        spellIcons[5] = a.getSpellIcon("Blitz");
        spellIcons[6] = a.getSpellIcon("Light_Prison");
        spellIcons[7] = a.getSpellIcon("Poison_Blade");
        spellIcons[8] = a.getSpellIcon("Wind_Slash");
        spellIcons[9] = a.getSpellIcon("Aegis");
        spellIcons[10] = a.getSpellIcon("Warrior's_Might");
        spellIcons[11] = a.getSpellIcon("Guard_Breaker");
        spellOnSlot = SpellMenu.getInstance().getMenuSlots();

        veritas = a.getMenu("Veritas_Pic")[0].getImage();
        selectSpell = new Animation(a.getMenu("Select_Spell"), 2, Const.tileSize, Const.tileSize, 1);
    }
    public void update()
    {
        if(mouse.leftClick)
        {
            for(int i = 0; i < 4; ++i)
            {
                if(mouse.mouseX > slotX[i] && mouse.mouseX < slotX[i] + Const.tileSize &&
                        mouse.mouseY - 10 > slotY && mouse.mouseY < slotY + Const.tileSize + Const.tileSize / 3)
                {
                    if(slotSelect == i)
                    {
                        spellOnSlot[i] = -1;
                    }
                    slotSelect = i;
                }
            }
            for(int i = 0; i < 12; ++i)
            {
                if(mouse.mouseX > iX[i]  && mouse.mouseX < iX[i] + Const.tileSize - 8 &&
                        mouse.mouseY > iY[i] + 8 && mouse.mouseY < iY[i] + Const.tileSize)
                {
                    spellSelect = i;
                }
            }
            mouse.leftClick = false;
        }
        SpellMenu.getInstance().setMenuSlots(spellOnSlot);
    }
    public void reset()
    {
        slotSelect = -1;
        spellSelect = -1;
    }
    public void draw(Graphics2D g2d)
    {
        int tileSize = Const.tileSize;
        //left page
        g2d.drawString("Lvl: " + p.getStats().getLVL(), tileSize * 10 + tileSize / 2, tileSize * 4 - tileSize / 2);
        g2d.drawImage(veritas, tileSize * 6, tileSize * 3, tileSize * 6, tileSize * 5, null);
        g2d.drawString("Name:  " + Const.playerName + "        Gold    : " + p.getStats().getGold(), tileSize * 6 + tileSize / 4, tileSize * 8 + tileSize / 2);
        g2d.drawString("HP    : " + p.getStats().getHP() + "/" + p.getStats().getTrueHP(), tileSize * 6 + tileSize / 6, tileSize * 9);
        g2d.drawString("AD    : " + p.getStats().getAD(), tileSize * 9 + tileSize / 4, tileSize * 9);
        g2d.drawString("MP    : " + p.getStats().getMP() + "/" + p.getStats().getTrueMP(), tileSize * 6 + tileSize / 6, tileSize * 9 + tileSize / 2);
        g2d.drawString("MS    : " + p.getStats().getMS(), tileSize * 9 + tileSize / 4, tileSize * 9 + tileSize / 2);
        g2d.drawString("AS    : " + p.getStats().getAS(), tileSize * 6 + tileSize / 6, tileSize * 10);
        g2d.drawString("AC    : " + p.getStats().getAC(), tileSize * 9 + tileSize / 4, tileSize * 10);
        g2d.drawString("EXP for next lvl:  " + p.getStats().getEXP() + "/" + p.getStats().getEXPCap(), tileSize * 6 + tileSize / 6, tileSize * 11 - tileSize / 4);

        g2d.drawImage(statsIcons[0], tileSize * 7 - tileSize / 6, tileSize * 8 + tileSize / 4, tileSize, tileSize, null);//HP icon
        g2d.drawImage(statsIcons[1], tileSize * 10 + tileSize / 4, tileSize * 8 - tileSize / 4, tileSize, tileSize, null);//Gold icon
        g2d.drawImage(statsIcons[3], tileSize * 10 - tileSize / 6, tileSize * 8 + tileSize / 4, tileSize, tileSize, null);//AD icon
        g2d.drawImage(statsIcons[4], tileSize * 7 - tileSize / 4, tileSize * 9 + tileSize / 4, tileSize, tileSize, null);//AS icon
        g2d.drawImage(statsIcons[5], tileSize * 7 - tileSize / 6, tileSize * 9 - tileSize / 4, tileSize, tileSize, null);//MP icon
        g2d.drawImage(statsIcons[6], tileSize * 10 - tileSize / 6, tileSize * 9 - tileSize / 4, tileSize, tileSize, null);//MS icon
        g2d.drawImage(statsIcons[7], tileSize * 10 - tileSize / 6, tileSize * 9 + tileSize / 4, tileSize, tileSize, null);//MS icon
        int i;
        g2d.drawImage(expBar[3], tileSize * 7 - tileSize / 4, tileSize * 10 + tileSize / 10, tileSize * 2, tileSize * 2, null);
        for(i = 1; i < 10; ++i)
        {
            g2d.drawImage(expBar[4], tileSize * 7 - tileSize / 4 + i * 18 + 4, tileSize * 10 + tileSize / 10, tileSize * 2, tileSize * 2, null);
        }
        g2d.drawImage(expBar[5], tileSize * 7 - tileSize / 4 + i * 18 + 4, tileSize * 10 + tileSize / 10, tileSize * 2, tileSize * 2, null);
        g2d.drawImage(statsIcons[2], tileSize * 11 - tileSize / 8, tileSize * 10 - tileSize / 10, tileSize * 2, tileSize * 2, null);

        //right page
        g2d.drawImage(skillTree, tileSize * 12, tileSize * 3, tileSize * 8, tileSize * 8 + tileSize / 2, null);
        g2d.drawString("Skill Points: " + 10, tileSize * 15 - tileSize / 4, tileSize * 3 + tileSize / 2);
        iX[0] = tileSize * 13 + tileSize / 3 + 1;
        iY[0] = tileSize * 4 - tileSize / 2 + tileSize / 24;
        g2d.drawImage(stIcons[1], iX[0], iY[0], coverW, coverH, null);
        iX[1] = tileSize * 17 + tileSize / 2 + tileSize / 12;
        iY[1] = tileSize * 4 - tileSize / 2 + tileSize / 24;
        g2d.drawImage(stIcons[1], iX[1], iY[1], coverW, coverH, null);
        iX[2] = tileSize * 13 + tileSize / 2 - 1;
        iY[2] = tileSize * 5 + tileSize / 6 - 1;
        g2d.drawImage(stIcons[1], iX[2], iY[2], coverW, coverH, null);
        iX[3] = tileSize * 17 + tileSize / 2 - 1;
        iY[3] = tileSize * 5 + tileSize / 6 - 1;
        g2d.drawImage(stIcons[1], iX[3], iY[3], coverW, coverH, null);
        iX[4] = tileSize * 15 + tileSize / 2 - 1;
        iY[4] = tileSize * 5 + tileSize / 4;
        g2d.drawImage(stIcons[1], iX[4], iY[4], coverW, coverH, null);
        iX[5] = tileSize * 14 + tileSize / 3;
        iY[5] = tileSize * 6 + tileSize / 2 + tileSize / 24;
        g2d.drawImage(stIcons[1], iX[5], iY[5], coverW, coverH, null);
        iX[6] = tileSize * 16 + tileSize / 2 + tileSize / 9;
        iY[6] = tileSize * 6 + tileSize / 2 + tileSize / 24;
        g2d.drawImage(stIcons[1], iX[6], iY[6], coverW, coverH, null);
        iX[7] = tileSize * 14 + tileSize / 16;
        iY[7] = tileSize * 8 + 6;
        g2d.drawImage(stIcons[1], iX[7], iY[7], coverW, coverH, null);
        iX[8] = tileSize * 17 - tileSize / 9;
        iY[8] = tileSize * 8 + 6;
        g2d.drawImage(stIcons[1], iX[8], iY[8], coverW, coverH, null);
        iX[9] = tileSize * 15 + tileSize / 2 - 1;
        iY[9] = tileSize * 9 + tileSize / 5 + 1;
        g2d.drawImage(stIcons[1], iX[9], iY[9], coverW, coverH, null);
        iX[10] = tileSize * 13 + tileSize / 3;
        iY[10] = tileSize * 9 + 20;
        g2d.drawImage(stIcons[1], iX[10], iY[10], coverW, coverH, null);
        iX[11] = tileSize * 17 + tileSize / 2 + tileSize / 8;
        iY[11] = tileSize * 9 + tileSize / 3 + tileSize / 12;
        g2d.drawImage(stIcons[1], iX[11], iY[11], coverW, coverH, null);


        slotX[0] = tileSize * 14 - 18;
        slotX[1] = tileSize * 15 - 8;
        slotX[2] = tileSize * 16 + 2;
        slotX[3] = tileSize * 17 + 12;
        g2d.drawImage(stIcons[0], slotX[0], slotY, tileSize + tileSize / 2, tileSize + tileSize / 2, null);
        g2d.drawImage(stIcons[0], slotX[1], slotY, tileSize + tileSize / 2, tileSize + tileSize / 2, null);
        g2d.drawImage(stIcons[0], slotX[2], slotY, tileSize + tileSize / 2, tileSize + tileSize / 2, null);
        g2d.drawImage(stIcons[0], slotX[3], slotY, tileSize + tileSize / 2, tileSize + tileSize / 2, null);

        if(slotSelect != -1)
        {
            selectSpell.draw(g2d, slotX[slotSelect] + 5, slotY + 12, true, 12, 1);
        }
        if(spellSelect != -1)
        {
            selectSpell.draw(g2d, iX[spellSelect], iY[spellSelect] + 5, true, 12, 1);
        }
        if(spellSelect != -1 && slotSelect != -1)
        {
            spellOnSlot[slotSelect] = spellSelect;
            spellSelect = -1;
        }
        if(spellOnSlot[0] != -1)
        {
            g2d.drawImage(spellIcons[spellOnSlot[0]], slotX[0] + 7, slotY + 19, tileSize + tileSize / 2, tileSize + tileSize / 2, null);
        }
        if(spellOnSlot[1] != -1)
        {
            g2d.drawImage(spellIcons[spellOnSlot[1]], slotX[1] + 7, slotY + 19, tileSize + tileSize / 2, tileSize + tileSize / 2, null);
        }
        if(spellOnSlot[2] != -1)
        {
            g2d.drawImage(spellIcons[spellOnSlot[2]], slotX[2] + 7, slotY + 19, tileSize + tileSize / 2, tileSize + tileSize / 2, null);
        }
        if(spellOnSlot[3] != -1)
        {
            g2d.drawImage(spellIcons[spellOnSlot[3]], slotX[3] + 7, slotY + 19, tileSize + tileSize / 2, tileSize + tileSize / 2, null);
        }
        SpellMenu s = SpellMenu.getInstance();
        s.setMenuSlots(spellOnSlot);
    }
}
