package Game.Manager;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Collection;

public class Tile implements Serializable
{
    private BufferedImage image;
    private CollisionBox box;

    public Tile(BufferedImage image)
    {
        this.image = image;
        this.box = new CollisionBox("null");
    }
    public Tile(BufferedImage image, CollisionBox box)
    {
        this.image = image;
        this.box = box;
    }

    public BufferedImage getImage()
    {
        return image;
    }

    public CollisionBox getBox()
    {
        return box;
    }
}
