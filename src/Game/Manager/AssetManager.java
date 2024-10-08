package Game.Manager;

import Game.Parsers.CollisionParser;
import Game.Parsers.MapParser;
import org.xml.sax.SAXException;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static Game.Manager.Images.*;

public class AssetManager
{
    private static AssetManager instance = null;
    private Font font;
    private Map<String, Tile[]> tileAssets;
    private Map<String, MapParser.MapHandler> mapAssets;
    private Map<String, Tile[]> entityAsset;
    private Map<String, Tile[]> menuAsset;
    private Map<String, Tile[]> barAsset;
    private Map<String, Tile[]> vfxAsset;
    private Map<String, Tile[]> iconAsset;
    private Map<String, BufferedImage> spellIcon;
    private Map<String, Tile[]> spellsAsset;
    private AssetManager() throws IOException
    {
        tileAssets = new HashMap<>();
        mapAssets = new HashMap<>();
        entityAsset = new HashMap<>();
        menuAsset = new HashMap<>();
        barAsset = new HashMap<>();
        vfxAsset = new HashMap<>();
        iconAsset = new HashMap<>();
        spellIcon = new HashMap<>();
        spellsAsset = new HashMap<>();
        loadAssets();
    }
    public static AssetManager getInstance()
    {
        if(instance == null)
        {
            try
            {
                instance = new AssetManager();
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }
    public Font getFont(){ return font;}
    public MapParser.MapHandler getMap(String name)
    {
        return mapAssets.get(name);
    }
    public Tile[] getTiles(String name)
    {
        return tileAssets.get(name);
    }
    public Tile[] getEntity(String name)
    {
        return entityAsset.get(name);
    }
    public Tile[] getMenu(String name){ return menuAsset.get(name);}
    public Tile[] getBar(String name){ return barAsset.get(name);}
    public Tile[] getVfx(String name){ return vfxAsset.get(name);}
    public BufferedImage getSpellIcon(String name){ return spellIcon.get(name);}
    public Tile[] getSpell(String name){ return spellsAsset.get(name);}
    public void loadAssets() throws IOException
    {
        //Font
        loadFont("DungeonFont.ttf");
        //Assets for the first map
        int inv = 15; // to swap mirror coordinates on X axis for collisions
        Tile[] asset = loadTileset("/Ancient Forest/AncientForest.png", 16, 16, "Ancient Forest Shapes.txt", inv, Const.tileScale);
        System.out.println("Loaded AncientForest.png");
        tileAssets.put("Ancient Forest", asset);

        asset = loadTileset("/Ancient Forest/Surplus Trees Scaled.png", 49, 50, "null", inv, Const.tileScale);
        System.out.println("Loaded Surplus Trees Scaled.png");
        tileAssets.put("Trees", asset);

        asset = loadTileset("/Ancient Forest/gentle tree wall scaled.png", 32 , 48, "null", inv, Const.tileScale);
        System.out.println("Loaded gentle tree wall scaled.png");
        tileAssets.put("TreeWall", asset);

        MapParser.MapHandler parser = loadMap("AncientForest.xml");
        System.out.println("Loaded AncientForest.xml");
        mapAssets.put("Ancient Forest", parser);

        //Assets for second map
        asset = loadTileset("/DarkDimension/DarkDimension.png", 21, 29, "Dark Dimension Shapes.txt", inv, Const.tileScale);
        tileAssets.put("Dark Dimension", asset);

        parser = loadMap("DarkDimension.xml");
        mapAssets.put("Dark Dimension", parser);

        //Assets for boss room
        asset = loadTileset("/BossRoom/Dungeon tileset.png", 12, 24, "BossRoom Shapes.txt", inv, Const.tileScale);
        tileAssets.put("BossRoom", asset);

        parser = loadMap("BossRoom.xml");
        mapAssets.put("BossRoom", parser);

        //Assets for player
        //Animation anim = null;
        inv = 47;
        asset = loadAnimation("/Veritas/Sword/Idle_Sword/Idle_Sword.png", 4, 1, 1, "null", inv, Const.playerScale);
        //anim = new Animation(asset, 4, Const.tileSize, Const.tileSize, 3);
        entityAsset.put("Idle_Sword", asset);

        asset = loadAnimation("/Veritas/Sword/Walk_Sword/Walk_Sword.png", 6, 1, 1, "null", inv, Const.playerScale);
        entityAsset.put("Walk_Sword", asset);

        asset = loadAnimation("/Veritas/Sword/Run_Sword/Run_Sword.png", 6, 1, 1, "null", inv, Const.playerScale);
        entityAsset.put("Run_Sword", asset);

        asset = loadAnimation("/Veritas/Sword/Hurt_Sword/Hurt_Sword.png", 4, 1, 1, "null", inv, Const.playerScale);
        entityAsset.put("Hurt_Sword", asset);

        asset = loadAnimation("/Veritas/Sword/Combo_Sword_1/Sword_Combo1_Attack1.png", 5, 1, 1, "./Resources/Veritas/Sword/Combo_Sword_1/Sword_Combo1_Attack1.txt", inv, Const.playerScale);
        entityAsset.put("Combo1_Attack1_Sword", asset);

        asset = loadAnimation("/Veritas/Sword/Combo_Sword_1/Sword_Combo1_Attack2.png", 6, 1, 1, "./Resources/Veritas/Sword/Combo_Sword_1/Sword_Combo1_Attack2.txt", inv, Const.playerScale);
        entityAsset.put("Combo1_Attack2_Sword", asset);

        asset = loadAnimation("/Veritas/Sword/Combo_Sword_1/Sword_Combo1_Attack3.png", 6, 1, 1, "./Resources/Veritas/Sword/Combo_Sword_1/Sword_Combo1_Attack3.txt", inv, Const.playerScale);
        entityAsset.put("Combo1_Attack3_Sword", asset);

        asset = loadAnimation("/Veritas/Sword/Combo_Sword_2/Sword_Combo2_Attack1.png", 6, 1, 1, "./Resources/Veritas/Sword/Combo_Sword_2/Sword_Combo2_Attack1.txt", inv, Const.playerScale);
        entityAsset.put("Combo2_Attack1_Sword", asset);

        asset = loadAnimation("/Veritas/Sword/Combo_Sword_2/Sword_Combo2_Attack2.png", 4, 1, 1, "./Resources/Veritas/Sword/Combo_Sword_2/Sword_Combo2_Attack2.txt", inv, Const.playerScale);
        entityAsset.put("Combo2_Attack2_Sword", asset);

        asset = loadAnimation("/Veritas/Sword/Combo_Sword_2/Sword_Combo2_Attack3.png", 4, 1, 1, "./Resources/Veritas/Sword/Combo_Sword_2/Sword_Combo2_Attack3.txt", inv, Const.playerScale);
        entityAsset.put("Combo2_Attack3_Sword", asset);

        asset = loadAnimation("/Veritas/Sword/Combo_Sword_2/Sword_Combo2_Attack4.png", 4, 1, 1, "./Resources/Veritas/Sword/Combo_Sword_2/Sword_Combo2_Attack4.txt", inv, Const.playerScale);
        entityAsset.put("Combo2_Attack4_Sword", asset);
        System.out.println("Loaded Veritas");

        //Assets for goblin
        //CollisionBox goblinHitBox = new CollisionBox("rectangle", Const.tileSize, 14, 20, 20, 30);
        asset = loadAnimation("/Goblin/Goblin_Idle.png", 4, 1, 1, "null", inv, Const.goblinScale);
        entityAsset.put("Goblin_Idle", asset);

        asset = loadAnimation("/Goblin/Goblin_Run.png", 8, 1, 1, "null", inv, Const.goblinScale);
        entityAsset.put("Goblin_Run", asset);

        asset = loadAnimation("/Goblin/Goblin_Hit.png", 4, 1, 1, "null", inv, Const.goblinScale);
        entityAsset.put("Goblin_Hit", asset);

        asset = loadAnimation("/Goblin/Goblin_Death.png", 4 , 1, 1, "null", inv, Const.goblinScale);
        entityAsset.put("Goblin_Death", asset);

        asset = loadAnimation("/Goblin/Goblin_Attack.png", 8, 2, 1, "./Resources/Goblin/Goblin_Attack.txt", inv * 2, Const.goblinScale);
        entityAsset.put("Goblin_Attack", asset);
        System.out.println("Loaded Goblin");

        //Assets for darkling
        inv = 95;
        asset = loadAnimation("/Darkling/Darkling_Idle.png", 9, 2, 2, "null", inv, 3);
        entityAsset.put("Darkling_Idle", asset);

        asset = loadAnimation("/Darkling/Darkling_Run.png", 6, 2, 2, "null", inv, 3);
        entityAsset.put("Darkling_Run", asset);

        asset = loadAnimation("/Darkling/Darkling_Hit.png", 5, 2, 2, "null", inv, 3);
        entityAsset.put("Darkling_Hit", asset);

        asset = loadAnimation("/Darkling/Darkling_Death.png", 23, 2, 2, "null", inv, 3);
        entityAsset.put("Darkling_Death", asset);

        asset = loadAnimation("/Darkling/Darkling_Attack.png", 12, 2, 2, "./Resources/Darkling/Darkling_Attack.txt", inv, 3);
        entityAsset.put("Darkling_Attack", asset);
        System.out.println("Loaded Darkling");

        //Assets for boss
        inv = 191;
        asset = loadAnimation("/Boss/Boss_Idle.png", 6, 4, 4, "null", inv, 4);
        entityAsset.put("Boss_Idle", asset);

        asset = loadAnimation("/Boss/Boss_Attack.png", 11, 4, 4, "./Resources/Boss/Boss_Attack.txt", inv, 4);
        entityAsset.put("Boss_Attack", asset);

        asset = loadAnimation("/Boss/Boss_Death.png", 5, 4, 4, "null", inv, 4);
        entityAsset.put("Boss_Death", asset);

        //Assets for mouse menu
        inv = 95;
        asset = loadAnimation("/Mouse/Abilities_Menu/Mouse circle.png", 4, 2, 2, "null", inv, Const.mouseMenuScale);
        menuAsset.put("Mouse_Menu", asset);

        asset = loadAnimation("/Mouse/Abilities_Menu/Mouse_Q1.png", 4, 2, 2, "null", inv, Const.mouseMenuScale);
        menuAsset.put("Mouse_Q1", asset);

        asset = loadAnimation("/Mouse/Abilities_Menu/Mouse_Q2.png", 4, 2, 2, "null", inv, Const.mouseMenuScale);
        menuAsset.put("Mouse_Q2", asset);

        asset = loadAnimation("/Mouse/Abilities_Menu/Mouse_Q3.png", 4, 2, 2, "null", inv, Const.mouseMenuScale);
        menuAsset.put("Mouse_Q3", asset);

        asset = loadAnimation("/Mouse/Abilities_Menu/Mouse_Q4.png", 4, 2, 2, "null", inv, Const.mouseMenuScale);
        menuAsset.put("Mouse_Q4", asset);

        //Assets for main menu
        asset = loadSingleImage("/Menu/Book/Background.png", "null", inv, Const.menuScale);
        menuAsset.put("Menu_Background", asset);

        asset = loadAnimation("/Menu/Book/Book_Open.png", 5, 16, 13, "null", inv, Const.menuScale);
        menuAsset.put("Menu_Open", asset);

        asset = loadAnimation("/Menu/Book/Book_Close.png", 5, 16, 13, "null", inv, Const.menuScale);
        menuAsset.put("Menu_Close", asset);

        asset = loadAnimation("/Menu/Book/Page_Left.png", 8, 16, 13, "null", inv, Const.menuScale);
        menuAsset.put("Page_Left", asset);

        asset = loadAnimation("/Menu/Book/Page_Right.png", 8, 16, 13, "null", inv, Const.menuScale);
        menuAsset.put("Page_Right", asset);

        inv = 15;
        asset = loadAnimation("/Menu/Book/Cut_Out.png", 5, 1, 1, "null", inv, 1);
        menuAsset.put("Menu_CutOut", asset);

        asset = loadAnimation("/Menu/Book/Cutout_Icons.png", 6, 1, 1, "null", inv, 1);
        menuAsset.put("Cutout_Icons", asset);

        asset = loadAnimation("/Menu/Book/Stats_Icons.png", 8, 1, 1, "null", inv, 1);
        menuAsset.put("Stats_Icons", asset);

        asset = loadSingleImage("/Menu/Book/Text_Cutout.png", "null", inv, 1);
        menuAsset.put("Text_Cutout", asset);

        //skill tree
        asset = loadSingleImage("/Icons/Sword_Skill_Tree.png", "null", inv, 1);
        menuAsset.put("Skill_Tree", asset);

        asset = loadAnimation("/Icons/Things_For_Menu.png", 3, 1, 1, "null", inv,1);
        menuAsset.put("ST_Icons", asset);

        asset = loadAnimation("/Icons/Select_Spell.png", 2, 1, 1, "null", inv,1);
        menuAsset.put("Select_Spell", asset);

        //bars assets
        asset = loadAnimation("/Bars/Health_Bar.png", 4, 1, 1, "null", inv, Const.barScale);
        barAsset.put("Health_Bar", asset);

        asset = loadAnimation("/Bars/Exp_Bar.png", 6, 1, 1, "null", inv, Const.barScale);
        barAsset.put("Exp_Bar", asset);

        asset = loadAnimation("/Bars/Mana_Bar.png", 6, 1, 1, "null", inv, Const.barScale);
        barAsset.put("Mana_Bar", asset);

        //characters pictures
        asset = loadSingleImage("/Veritas/Veritas.png", "null", inv, 1);
        menuAsset.put("Veritas_Pic", asset);

        //vfx
        asset = loadAnimation("/Vfx/Hit_1.png", 4, 1, 1, "null", inv, Const.playerScale);
        vfxAsset.put("Hit_1", asset);

        asset = loadAnimation("/Vfx/Hit_2.png", 4, 1, 1, "null", inv, Const.playerScale);
        vfxAsset.put("Hit_2", asset);

        asset = loadAnimation("/Vfx/Hit_3.png", 4, 1, 1, "null", inv, Const.playerScale);
        vfxAsset.put("Hit_3", asset);

        asset = loadAnimation("/Vfx/Hit_4.png", 4, 1, 1, "null", inv, Const.playerScale);
        vfxAsset.put("Hit_4", asset);

        asset = loadAnimation("/Vfx/Hit_51.png", 4, 2, 2, "null", inv, Const.playerScale);
        vfxAsset.put("Hit_51", asset);
        asset = loadAnimation("/Vfx/Hit_52.png", 4, 2, 2, "null", inv, Const.playerScale);
        vfxAsset.put("Hit_52", asset);
        asset = loadAnimation("/Vfx/Hit_53.png", 4, 2, 2, "null", inv, Const.playerScale);
        vfxAsset.put("Hit_53", asset);
        asset = loadAnimation("/Vfx/Hit_54.png", 4, 2, 2, "null", inv, Const.playerScale);
        vfxAsset.put("Hit_54", asset);



        //spells
        loadSpellIcons();

        asset = loadAnimation("/Spells/Vampirism.png", 6, 1, 1, "null", inv, 1);
        spellsAsset.put("Vampirism", asset);

        asset = loadAnimation("/Spells/Divine_Shield.png", 11, 2, 2, "null", inv, 1);
        spellsAsset.put("Divine_Shield", asset);

        asset = loadAnimation("/Spells/Invincible.png", 60, 2, 2, "null", inv, 1);
        spellsAsset.put("Invincible", asset);

        asset = loadAnimation("/Spells/Divine_Blessing.png", 12, 6, 6, "null", inv, 1);
        spellsAsset.put("Divine_Blessing", asset);

        asset = loadAnimation("/Spells/Healing.png", 12, 2, 2, "null", inv, 1);
        spellsAsset.put("Healing", asset);

        inv = 95;
        asset = loadAnimation("/Spells/Blitz.png", 11, 2, 2, "./Resources/Spells/Blitz.txt", inv, 3);
        spellsAsset.put("Blitz", asset);

        asset = loadAnimation("/Spells/Light_Prison.png", 12, 2, 2, "./Resources/Spells/Light_Prison.txt", inv, 2);
        spellsAsset.put("Light_Prison", asset);

        asset = loadAnimation("/Spells/Light_Lock.png", 28, 2, 2, "null", inv, 2);
        spellsAsset.put("Light_Lock", asset);

        asset = loadAnimation("/Spells/Poison_Blade.png", 6, 1, 1, "null", inv, 1);
        spellsAsset.put("Poison_Blade", asset);

        asset = loadAnimation("/Spells/Poison_Hit.png", 5, 1, 1, "null", inv, 1);
        spellsAsset.put("Poison_Hit", asset);

        inv = 47;
        asset = loadAnimation("/Spells/Wind_Charge.png", 10, 1, 1, "null", inv, 3);
        spellsAsset.put("Wind_Charge", asset);

        asset = loadAnimation("/Spells/Wind_Slash.png", 6, 1, 1, "./Resources/Spells/Wind_Slash.txt", inv, 3);
        spellsAsset.put("Wind_Slash", asset);

        asset = loadAnimation("/Spells/Aegis.png", 7, 1, 1, "null", inv, 1);
        spellsAsset.put("Aegis", asset);

        asset = loadAnimation("/Spells/Full_Might.png", 9, 2, 2, "null", inv, 1);
        spellsAsset.put("Full_Might", asset);

        asset = loadAnimation("/Spells/Unleashed.png", 7, 2, 2, "null", inv, 1);
        spellsAsset.put("Unleashed", asset);

        inv = 191;
        asset = loadAnimation("/Spells/Divine_Smite.png", 11, 4, 4, "./Resources/Spells/Divine_Smite.txt", inv, 3);
        spellsAsset.put("Divine_Smite", asset);

        asset = loadAnimation("/Spells/Sea_Dragon's_Wrath.png", 19, 4, 4, "./Resources/Spells/Sea_Dragon.txt", inv, 3);
        spellsAsset.put("Sea_Dragon", asset);

        asset = loadAnimation("/Spells/Fire_Dance.png", 15, 4, 4, "./Resources/Spells/Fire_Dance.txt", inv, 3);
        spellsAsset.put("Fire_Dance", asset);

        asset = loadAnimation("/Spells/Heaven's_Rain.png", 20, 4, 4, "null", inv, 2);
        spellsAsset.put("Heaven_Rain", asset);

        inv = 47;
        asset = loadAnimation("/Spells/Rain_Bullets.png", 19, 1, 1, "./Resources/Spells/Rain_Bullets.txt", inv, 3);
        spellsAsset.put("Rain_Bullets", asset);

    }
    public Tile[] loadSingleImage(String path, String colPath, int inv, int scale) throws IOException
    {
        CollisionBox[] v = null;
        if(!colPath.equals("null"))
        {
            v = CollisionParser.IndexCollisions(new CollisionBox[1], colPath, inv, scale);
        }
        BufferedImage img = loadImage(path);
        Tile []tile = new Tile[1];

        if(v != null)
        {
            tile[0] = new Tile(img, v[0]);
        }
        else
        {
            tile[0] = new Tile(img, new CollisionBox("null"));
        }
        return tile;
    }
    public Tile[] loadAnimation(String path, int length, int scaleX, int scaleY, String colPath, int inv, int scale) throws IOException
    {
        CollisionBox[] v = null;
        if(!colPath.equals("null"))
        {
            v = CollisionParser.IndexCollisions(new CollisionBox[length], colPath, inv, scale);
        }
        BufferedImage tileSet = loadImage(path);
        Tile[] tiles = new Tile[length];
        for (int i = 0; i < length; ++i)
        {
            BufferedImage img = cropImage(tileSet, i * Const.tileSize * scaleX, 0, Const.tileSize * scaleX, Const.tileSize * scaleY);
            if(v != null)
            {
                tiles[i] = new Tile(img, v[i]);
            }
            else
            {
                tiles[i] = new Tile(img, new CollisionBox("null"));
            }
        }
        return tiles;
    }
    public MapParser.MapHandler loadMap(String mapFilePath)
    {
        MapParser.MapHandler handler = null;
        try
        {
            handler = MapParser.GetHandler(mapFilePath);
        }
        catch (IOException | SAXException e)
        {
            System.out.println("no file for the map");
        }
        return handler;
    }
    private Tile[] loadTileset(String path, int sizeX, int sizeY, String colPath, int inv, int scale) throws IOException
    {
        CollisionBox[] v = null;
        if(!colPath.equals("null"))
        {
            v = CollisionParser.IndexCollisions(new CollisionBox[sizeX * sizeY], colPath, inv, scale);
        }
        BufferedImage tileSet = loadImage(path);
        Tile[] tiles = new Tile[sizeX * sizeY];
        for (int i = 0; i < sizeX; ++i)
        {
            for(int j = 0; j < sizeY; ++j)
            {
                BufferedImage img = cropImage(tileSet, j * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
                if(!colPath.equals("null"))
                {
                    tiles[i * sizeY + j] = new Tile(img, v[i * sizeY + j]);
                }
                else
                {
                    tiles[i * sizeY + j] = new Tile(img, new CollisionBox("null"));
                }
            }
        }
        return tiles;
    }
    public void loadFont(String file)
    {
        try
        {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(file)));
        }
        catch (IOException | FontFormatException e)
        {
            e.printStackTrace();
        }
        font = new Font(file.substring(0, file.length() - 4), Font.PLAIN, 32);
    }
    public void loadSpellIcons() throws IOException
    {
        int i = 0, j = 0;
        BufferedImage img;
        BufferedImage asset;
        asset = loadImage("/Icons/Spell_Sheet.png");

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("Poison_Blade", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("Toxicity", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("1__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("2__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("3__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("4__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("Aegis", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("5__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("6__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("Berserk", img);
        i++;
        j = 0;
        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("Vampirism", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("7__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("8__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("9__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("10__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("Blitz", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("11__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("12__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("13__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("14__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("Divine_Blessing", img);
        i++;
        j = 0;
        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("15__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("Heal", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("16__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("Divine_Smite", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("Excalibur", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("17__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("Light_Prison", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("Heaven's_Rain", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("18__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("19__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("20++", img);
        i++;
        j = 0;
        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("21__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("22__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("23++", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("24__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("25__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("26__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("27__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("Meteor", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("Fire_Dance", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("28__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("Piercing_Fire", img);
        i++;
        j = 0;
        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("Lightning_Bolt", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("29__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("30__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("31__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("32__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("33__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("34++", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("35++", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("36++", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("37++", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("38__", img);
        i++;
        j = 0;
        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("39__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("40__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("Wind_Slash", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("41__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("42__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("Fire_Breath", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("43__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("44__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("45__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("46++", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("47++", img);
        i++;
        j = 0;
        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("48++", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("49++", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("50++", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("51__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("52__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("Guard_Breaker", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("53++", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("54__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("55__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("56__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("57__", img);
        i++;
        j = 0;
        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("58__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("59__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("60__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("61__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("62__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("63__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("64__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("65__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("66__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("67__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("68__", img);
        i++;
        j = 0;
        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("69__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("70++", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("71__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("72__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("73__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("74__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("Warrior's_Might", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("76__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("77__", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("Divine_Shield", img);

        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("78++", img);
        i++;
        j = 0;
        img = cropImage(asset, (j++) * Const.tileSize, i * Const.tileSize, Const.tileSize, Const.tileSize);
        spellIcon.put("79++", img);
    }
}
