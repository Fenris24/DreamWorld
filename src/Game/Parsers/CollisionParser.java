package Game.Parsers;

import Game.Manager.CollisionBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CollisionParser
{
    public static CollisionBox[] IndexCollisions(CollisionBox[] v, String name, int inv, int scale) throws FileNotFoundException
    {
        File inputFile = new File(name);
        Scanner scanner = new Scanner(inputFile);
        int i = 0;
        while(scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            String[] tokens = line.split("\\s+");
            String shapeType = tokens[0];
            switch(shapeType)
            {
                case "rectangle":
                case "square":
                    int rectX1 = Integer.parseInt(tokens[1]);
                    int rectY1 = Integer.parseInt(tokens[2]);
                    int rectX2 = Integer.parseInt(tokens[3]);
                    int rectY2 = Integer.parseInt(tokens[4]);
                    v[i] = new CollisionBox("rectangle", inv, scale, rectX1, rectY1, rectX2, rectY2);
                    break;
                case "triangle":
                    int triX1 = Integer.parseInt(tokens[1]);
                    int triY1 = Integer.parseInt(tokens[2]);
                    int triX2 = Integer.parseInt(tokens[3]);
                    int triY2 = Integer.parseInt(tokens[4]);
                    int triX3 = Integer.parseInt(tokens[5]);
                    int triY3 = Integer.parseInt(tokens[6]);
                    v[i] = new CollisionBox("triangle", inv, scale, triX1, triY1, triX2, triY2, triX3, triY3);
                    break;
                case "null":
                    v[i] = new CollisionBox("null");
                    break;
            }
            i++;
        }
        scanner.close();
        return v;
    }
}
