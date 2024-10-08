package Game.Animations;

import Game.Manager.CollisionBox;
import Game.Manager.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import static Game.Manager.Images.mirrorHorizontally;
import static Game.Manager.Images.mirrorVertically;

public class Animation
{
    private Tile[] frames;
    protected final int totalFrames;
    protected final int width;
    protected final int height;
    protected final int scale;
    private float currentFrame = 0;
    private Tile processedFrame;
    public Animation(Tile[] frames, int totalFrames, int width, int height, int scale)
    {
        this.totalFrames = totalFrames;
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.frames = frames;
    }
    public boolean draw(Graphics2D g2d, int x, int y, boolean active, int repeat, int poz)
    {
        int f = (int)currentFrame % totalFrames;
        if(active)
        {
            if(repeat == -1)
            {
                g2d.drawImage(frames[totalFrames - 1].getImage(), x, y, width * scale, height * scale, null);
                return true;
            }
            else
            {
                BufferedImage img = null;
                CollisionBox col = null;
                switch(poz)
                {
                    case 1:
                        img = frames[f].getImage();
                        col = frames[f].getBox();
                        break;
                    case 2:
                        img = mirrorVertically(frames[f].getImage());
                        col = frames[f].getBox().getMirVerBox();
                        break;
                    case 3:
                        img = mirrorHorizontally(frames[f].getImage());
                        break;
                    case 4:
                        img = mirrorHorizontally(mirrorVertically(frames[f].getImage()));
                        break;
                }
                g2d.drawImage(img, x, y, width * scale, height * scale, null);
                if(col != null)
                {
                    processedFrame = new Tile(img, col);
                    processedFrame.getBox().drawCollision(g2d, x, y);
                }
                else
                {
                    processedFrame = new Tile(img, new CollisionBox("null"));
                }
                currentFrame += (1. / repeat);
                if((int)currentFrame == totalFrames)
                {
                    currentFrame = 0;
                    return true;
                }
            }
        }
        return false;
    }
    public CollisionBox getBox()
    {
        if(processedFrame != null)
        {
            return processedFrame.getBox();
        }
        return null;
    }
}