package Game.Manager;

import java.security.PublicKey;
import java.util.List;
import java.util.Vector;

public final class Const
{
    //Other constants
    public static String playerName = "Veritas";
    public static int barScale = 3;
    //General values
    public static int screenWidth = 1200;
    public static int screenHeight = 700;
    public static final int tileSize = 48;
    public static final int centerX = screenWidth / 2 - tileSize;
    public static final int centerY = screenHeight / 2 - 2 * tileSize;
    public static final int tileScale = 3;
    public static final String[] entities = {"goblin"};

    //Entity scales
    public static final int playerScale = 3;
    public static final int goblinScale = 2;

    //Menu scales
    public static final int mouseMenuScale = 2;
    public static final int menuScale = 1;
    public static final int bookScale = 2;

    //Speeds
    public static final int slowSpeed = 4;
    public static final int normalSpeed = 8;
    public static final int fastSpeed = 12;

    //Vision
    public static final int goblinVisionX = 480;
    public static final int goblinVisionY = 240;

    public static final int darklingVisionX = 520;
    public static final int darklingVisionY = 300;

}
