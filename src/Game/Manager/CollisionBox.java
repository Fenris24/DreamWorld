package Game.Manager;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class CollisionBox
{
    private int []x;
    private int []y;
    private int inv;
    private int scale;
    private int width;
    private int height;
    private String name;
    Polygon form;
    public CollisionBox(String name)
    {
        this.name = name;
    }
    //Constructor for rectangles
    public CollisionBox(String name, int inv, int scale, int x1, int y1, int x2, int y2)
    {
        this.name = name;
        this.inv = inv;
        this.scale = scale;
        x = new int[4];
        y = new int[4];
        x[3] = x1 * scale;
        y[3] = (inv - y1) * scale;
        x[1] = x2 * scale;
        y[1] = (inv - y2) * scale;
        x[0] = x[3];
        y[0] = y[1];
        x[2] = x[1];
        y[2] = y[3];
        width = x[2] - x[3];
        height = y[3] - y[0];
        form = new Polygon(x, y, 4);
    }
    //Constructor for triangle
    public CollisionBox(String name, int inv, int scale, int x1, int y1, int x2, int y2, int x3, int y3)
    {
        this.name = name;
        this.inv = inv;
        this.scale = scale;
        x = new int[3];
        y = new int[3];
        x[0] = x1 * scale;
        y[0] = (inv - y1) * scale;
        x[1] = x2 * scale;
        y[1] = (inv - y2) * scale;
        x[2] = x3 * scale;
        y[2] = (inv - y3) * scale;
        form = new Polygon(x, y, 3);
    }
    public void drawCollision(Graphics2D g2d, int x, int y)
    {
        if(!name.equals("null"))
        {
            g2d.setColor(Color.RED);
            AffineTransform transform = new AffineTransform();
            transform.translate(x, y);
            Shape movedForm = transform.createTransformedShape(form);
            if(name.equals("rectangle"))
            {
                g2d.draw(movedForm);
            }
            else if(name.equals("triangle"))
            {
                g2d.draw(movedForm);
            }
            drawCorners(g2d, x, y);
        }
    }
    public void drawCorners(Graphics2D g2d, int x, int y)
    {
        Ellipse2D a = new Ellipse2D.Double((double) getTrueX(0, x) - 5, (double) getTrueY(0, y) - 5, 10, 10);
        g2d.setColor(Color.BLUE);
        g2d.draw(a);
        Ellipse2D b = new Ellipse2D.Double((double) getTrueX(1, x) - 5, (double) getTrueY(1, y) - 5, 10, 10);
        g2d.setColor(Color.GREEN);
        g2d.draw(b);
        Ellipse2D c = new Ellipse2D.Double((double) getTrueX(2, x) - 5, (double) getTrueY(2, y) - 5, 10, 10);
        g2d.setColor(Color.YELLOW);
        g2d.draw(c);
        if(this.name.equals("rectangle"))
        {
            Ellipse2D d = new Ellipse2D.Double((double) getTrueX(3, x) - 5, (double) getTrueY(3, y) - 5, 10, 10);
            g2d.setColor(Color.CYAN);
            g2d.draw(d);
        }
    }
    public Shape getShapeInSpace(int x, int y)
    {
        if(!name.equals("null"))
        {
            AffineTransform transform = new AffineTransform();
            transform.translate(x, y);
            return transform.createTransformedShape(form);
        }
        return null;
    }
    public CollisionBox getMirVerBox()
    {
        if(name.equals("rectangle"))
        {
            return new CollisionBox(name, inv, scale, inv - (x[3] / scale), inv - (y[3] / scale), inv - (x[1] / scale), inv - (y[1] / scale));
        }
        else if(name.equals("triangle"))
        {
            return new CollisionBox(name, inv, scale, inv - (x[0] / scale), inv - (y[0] / scale), inv - (x[1] / scale), inv - (y[1] / scale), inv - (x[2] / scale), inv - (y[2] / scale));
        }
        return null;
    }
    public String getName()
    {
        return name;
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public int getTrueX(int index, int worldX)
    {
        return worldX + x[index];
    }
    public int getTrueY(int index, int worldY)
    {
        return worldY + y[index];
    }

    public int[] getX()
    {
        return x;
    }
    public int[] getY()
    {
        return y;
    }
    public void setX(int[] x)
    {
        this.x = x;
    }

    public void setY(int[] y)
    {
        this.y = y;
    }

    public Polygon getForm()
    {
        return form;
    }
    public void setForm(Polygon form)
    {
        this.form = form;
    }
}
