package Game.Fight;

import Game.Animations.Animation;
import Game.Main.MouseHandler;
import Game.Manager.AssetManager;
import Game.Manager.Const;

import javax.swing.*;
import java.io.IOException;
import java.security.cert.CertificateEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletionService;

public class SpellMenu
{
    static SpellMenu instance;
    Spell []slot;
    int []menuSlots;
    Map <Integer, Spell> spells;
    private SpellMenu()
    {
        slot = new Spell[4];
        for(int i = 0; i < 4; ++i)
        {
            slot[i] = null;
        }
        spells = new HashMap<>();
        menuSlots = new int[4];
        Arrays.fill(menuSlots, -1);
        loadSpells();
    }

    //todo specific errors
    public static SpellMenu getInstance()
    {
        if(instance == null)
        {
            instance = new SpellMenu();
        }
        return instance;
    }

    public void update()
    {
        //select spells from menu
        slot[0] = spells.get(menuSlots[0]);
        slot[1] = spells.get(menuSlots[1]);
        slot[2] = spells.get(menuSlots[2]);
        slot[3] = spells.get(menuSlots[3]);
    }
    public Spell []getSlot() {return slot;}
    public int []getMenuSlots() {return menuSlots;}
    public void setMenuSlots(int[] menuSlots) {this.menuSlots = menuSlots;}

    public void loadSpells()
    {
        Animation anim, sec;
        Spell spell, projectile;
        AssetManager asset = AssetManager.getInstance();

        anim = new Animation(asset.getSpell("Fire_Dance"), 15, Const.tileSize * 4, Const.tileSize * 4, 3);
        spell = new Spell("Fire_Dance", "null", 0, 0, 2, Const.tileScale * 2, 10, 8, anim, 50);
        spells.put(0, spell);

        anim = new Animation(asset.getSpell("Divine_Smite"), 11, Const.tileSize * 4, Const.tileSize * 4, 3);
        spell = new Spell("Divine_Smite", "null", 0, -Const.tileSize * 3, 2, Const.tileScale * 2, 25, 12, anim, 50);
        spells.put(1, spell);

        anim = new Animation(asset.getSpell("Vampirism"), 6, Const.tileSize, Const.tileSize, 3);
        spell = new Spell("Vampirism", "life_steal", Const.centerX, Const.centerY + Const.tileSize / 2, 6, 600, anim, 10);
        spells.put(2, spell);

        anim = new Animation(asset.getSpell("Divine_Shield"), 11, Const.tileSize * 2, Const.tileSize * 2, 3);
        spell = new Spell("Divine_Shield", "invincible", Const.centerX - Const.tileSize * 3 / 2, Const.centerY - Const.tileSize, 6, 300, anim, 10);
        spells.put(3, spell);

        anim = new Animation(asset.getSpell("Divine_Blessing"), 11, Const.tileSize * 6, Const.tileSize * 6, 2);
        spell = new Spell("Divine_Blessing", "curing", Const.centerX - Const.tileSize * 9 / 2, 0, 10, 100, anim, 30);
        spells.put(4, spell);

        anim = new Animation(asset.getSpell("Blitz"), 11, Const.tileSize * 2, Const.tileSize * 2, 3);
        spell = new Spell("Blitz", "teleporting", -Const.tileSize * 3, -Const.tileSize * 5, 0, -2,10, 5, anim, 20);
        spells.put(5, spell);

        anim = new Animation(asset.getSpell("Light_Prison"), 12, Const.tileSize * 2, Const.tileSize * 2, 2);
        spell = new Spell("Light_Prison", "lock", -Const.tileSize * 2, -Const.tileSize * 2, 0, -2,0, 6, anim, 10);
        spells.put(6, spell);

        anim = new Animation(asset.getSpell("Poison_Blade"), 6, Const.tileSize, Const.tileSize, 2);
        spell = new Spell("Poison_Blade", "poison", Const.centerX + Const.tileSize / 2, Const.centerY + Const.tileSize, 6, 300, anim, 5);
        spells.put(7, spell);

        sec = new Animation(asset.getSpell("Wind_Slash"), 6, Const.tileSize, Const.tileSize, 3);
        projectile = new Spell("Wind_Slash", "null", Const.centerX, Const.centerY, 10, 1000, 10, 6, sec, 0);
        anim = new Animation(asset.getSpell("Wind_Charge"), 10, Const.tileSize, Const.tileSize, 3);
        spell = new Spell("Wind_Charge", Const.centerX, Const.centerY, 0, 0, 0, 4, anim, projectile, 5);
        spells.put(8, spell);

        anim = new Animation(asset.getSpell("Aegis"), 7, Const.tileSize, Const.tileSize, 3);
        spell = new Spell("Aegis", "defence", Const.centerX, Const.centerY, 6, 420, anim, 8);
        spells.put(9, spell);

        anim = new Animation(asset.getSpell("Full_Might"), 7, Const.tileSize * 2, Const.tileSize * 2, 2);
        spell = new Spell("Warrior's_Might", "power", Const.centerX - Const.tileSize / 2, Const.centerY, 6, 300, anim, 4);
        spells.put(10, spell);

        anim = new Animation(asset.getSpell("Unleashed"), 7, Const.tileSize * 2, Const.tileSize * 2, 2);
        spell = new Spell("Unleashed", "unleashed", Const.centerX - Const.tileSize / 2, Const.centerY, 6, 600, anim, 3);
        spells.put(11, spell);

        anim = new Animation(asset.getSpell("Sea_Dragon"), 19, Const.tileSize * 4, Const.tileSize * 4, 3);
        spell = new Spell("Sea_Dragon", "null", 0, 0, 2, Const.tileScale * 2, 7, 8, anim, 50);
        spells.put(20, spell);

        anim = new Animation(asset.getSpell("Heaven_Rain"), 20, Const.tileSize * 4, Const.tileSize * 4, 2);
        sec = new Animation(asset.getSpell("Rain_Bullets"), 19, Const.tileSize, Const.tileSize, 3);

        projectile = new Spell("Rain_Bullets", "null", -Const.tileSize * 3 / 2, -Const.tileSize * 2, 0, -1, 6, 4, sec, 0);
        spell = new Spell("Heaven_Rain", Const.centerX - Const.tileSize * 5 / 2, 0, 0, 0, 0, 6, anim, projectile, 50);
        spells.put(21, spell);
    }
}
