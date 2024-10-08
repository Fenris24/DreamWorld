package Game.Main;

import Game.Animations.Animation;
import Game.Animations.PlayerAnimations;
import Game.EntityFactory.*;
import Game.Events.Observer;
import Game.Manager.AssetManager;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import Game.DB.DataBase;

import Game.Manager.Const;
import Game.MapFactory.*;
import Game.Parsers.MapParser;
import Game.UI.UI;
import org.xml.sax.SAXException;
import java.awt.image.BufferStrategy;
import java.util.Random;

import Game.UI.Menu;

import static Game.DB.DataBase.insertInDataBase;


public class GamePanel extends Canvas implements Runnable
{
    final int original_TileSize = 16;
    final int scale = 3;
    public final int tile_Size = scale * original_TileSize;
    final int screen_Rows = 14;
    final int screen_Col = 25;
    public int screen_Width = tile_Size * screen_Col;    //*1152*
    public int screen_Height = tile_Size * screen_Rows;  //*672*
    Thread game_Thread;
    int FPS = 60;
    AssetManager assets;
    KeyHandler keyH;
    MouseHandler mouseH;
    Stats s = new Stats(100, 100, 8, 20, 30, 99);//Hp Mp Ms Ad As Ac
    Player player;
    Goblin []goblin;
    Darkling []darkling;
    Boss boss;
    Menu menu;
    UI ui;
    AncientForest ancientForest;
    DarkDimension darkDimension;
    BossRoom bossRoom;
    Observer observer;
    int drawFinalCount = 0;
    int drawCount = 0;
    long timer;
    long endTimer = 0;
    int scoreTotal;
    int score = 1000000;
    DataBase db;

    boolean rep = true;
    public GamePanel() throws IOException, SAXException
    {
        this.setPreferredSize(new Dimension(screen_Width, screen_Height));
        this.setBackground(Color.black);

        keyH = KeyHandler.getInstance();
        mouseH = MouseHandler.getInstance();
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.addMouseMotionListener(mouseH);
        this.setFocusable(true);
        db = new DataBase(this);

        observer = Observer.getInstance();
        assets = AssetManager.getInstance();

        MapParser.MapHandler handle = assets.getMap("Ancient Forest");
        AncientForestFactory aff = new AncientForestFactory();
        ancientForest = aff.createMap(handle);

        handle = assets.getMap("Dark Dimension");
        DarkDimensionFactory ddf = new DarkDimensionFactory();
        darkDimension = ddf.createMap(handle);

        handle = assets.getMap("BossRoom");
        BossRoomFactory br = new BossRoomFactory();
        bossRoom = br.createMap(handle);

        observer.currentMap = "Ancient Forest";

        PlayerFactory pF = new PlayerFactory();
        player = pF.createEntity("Veritas", s, 0);
        GoblinFactory gF = new GoblinFactory();
        goblin = new Goblin[10];
        for(int i = 0; i < 10; ++i)
        {
            Random random = new Random();
            int nr = random.nextInt(500);
            goblin[i] = gF.createEntity("Goblin",  new Stats(50, 10, 4, 5, 120, 0), nr);
        }
        DarklingFactory dF = new DarklingFactory();
        darkling = new Darkling[10];
        for(int i = 0; i < 10; ++i)
        {
            Random random = new Random();
            int nr = random.nextInt(500);
            darkling[i] = dF.createEntity("Darkling", new Stats(100, 0, 15, 10, 40, 0), nr);
        }
        boss = new Boss("Boss", new Stats(1, 1, 1, 10, 90, 0), 0);
        menu = new Menu();
        ui = new UI();
    }

    public void startGameThread()
    {
        game_Thread = new Thread(this);
        game_Thread.start();
    }

    @Override
    public void run()
    {
        double drawInterval = 1000000000.0/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while(game_Thread != null)
        {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if(delta >= 1)
            {
                update();
                render();
                delta--;
                drawCount++;
            }
        }

    }

    public void update()
    {
        if(menu.state == 0)
        {
            player.update();
            for(int i = 0; i < 10; ++i)
            {
                if(observer.nrEnt == 10 && observer.currentLvl == 1)
                {
                    endTimer++;
                    if(endTimer >= 300)
                    {
                        player.setWorldX(Const.tileSize * 40);
                        player.setWorldY(Const.tileSize * 30);
                        observer.currentMap = "Dark Dimension";
                        observer.currentLvl++;
                        endTimer = 0;
                    }
                }
                if(observer.nrEnt == 0 && observer.currentLvl == 2)
                {
                    endTimer++;
                    if(endTimer >= 300)
                    {
                        player.setWorldX(Const.tileSize * 20);
                        player.setWorldY(Const.tileSize * 20);
                        observer.currentMap = "BossRoom";
                        observer.currentLvl++;
                    }
                }
                if(!goblin[i].getEvent().isDead && observer.currentLvl == 1)
                {
                    goblin[i].update();
                }
                if(!darkling[i].getEvent().isDead && observer.currentLvl == 2)
                {
                    darkling[i].update();
                }
                if(!boss.getEvent().isDead && observer.currentLvl == 3)
                {
                    boss.update();
                }
            }
        }
        menu.update();
        if(boss.getEvent().isDead)
        {
            scoreTotal = score;
            insertInDataBase("DataBaseGame", "DataBaseTable", scoreTotal);
            System.exit(0);
        }
    }

    public void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }
        Graphics2D g2d = (Graphics2D)bs.getDrawGraphics();

        if(menu.state == 0)
        {
            if(observer.currentLvl == 1)
            {
                score--;
                ancientForest.draw1(g2d, player.getWorldX(), player.getWorldY());
            }
            if(observer.currentLvl == 2)
            {
                score--;
                darkDimension.draw(g2d, player.getWorldX(), player.getWorldY());
            }
            if(observer.currentLvl == 3)
            {
                score--;
                bossRoom.draw(g2d, player.getWorldX(), player.getWorldY());
            }
            player.draw(g2d);
            if(observer.currentLvl == 1)
            {
                for(int i = 0; i < 10; ++i)
                {
                    goblin[i].draw(g2d);
                }
            }
            if(observer.currentLvl == 2)
            {
                for(int i = 0; i < 10; ++i)
                {
                    darkling[i].draw(g2d);
                }
            }
            if(observer.currentLvl == 3)
            {
                boss.draw(g2d);
            }
            if(observer.currentLvl == 1)
            {
                ancientForest.draw2(g2d, player.getWorldX(), player.getWorldY());
            }

            ui.draw(g2d);
            PlayerAnimations a = player.getPlayerAnimation();
            a.drawMouse(g2d, player.getEvent());
        }
        else
        {
            menu.draw(g2d);
        }
        //draw fps
        g2d.setFont(assets.getFont());
        g2d.setColor(Color.WHITE);
        g2d.drawString("fps: " + drawFinalCount, 1100, 40);
        if(timer >= 1000000000)
        {
            drawFinalCount = drawCount;
            drawCount = 0;
            timer = 0;
        }
        g2d.dispose();
        bs.show();
    }
}
