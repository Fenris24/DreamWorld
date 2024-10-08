package Game.Manager;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

public class Images
{
    public static BufferedImage loadImage(String Path)
    {
        try
        {
            return ImageIO.read(Objects.requireNonNull(Images.class.getResource(Path)));
        }
        catch (NullPointerException | IOException e)
        {
            e.printStackTrace();
            System.out.println("no image to load :(");
        }
        return null;
    }
    public static BufferedImage mirrorVertically(BufferedImage img)
    {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-img.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        img = op.filter(img, null);
        return img;
    }
    public static BufferedImage mirrorHorizontally(BufferedImage img)
    {
        AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
        tx.translate(-img.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        img = op.filter(img, null);
        return img;
    }
    public static BufferedImage cropImage(BufferedImage im, int x, int y, int w, int h)
    {
        return im.getSubimage(x, y, w, h);
    }
}
