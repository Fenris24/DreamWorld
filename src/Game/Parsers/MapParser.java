package Game.Parsers;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Arrays;

public class MapParser
{
    public static class MapHandler extends DefaultHandler
    {
        public int nrLayers = 5;
        public int mapWidth;
        public int mapHeight;
        private boolean insideLayer = false;
        public StringBuilder layerData = new StringBuilder();
        public int [][][]LayerMatrix;
        int x, y;
        int currentLayer = -1;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
        {
            if (qName.equals("map"))
            {
                mapWidth = Integer.parseInt(attributes.getValue("width"));
                mapHeight = Integer.parseInt(attributes.getValue("height"));
                LayerMatrix = new int[nrLayers][mapHeight + 2][mapWidth + 2];
            }
            else if (qName.equals("layer"))
            {
                currentLayer ++;
            }
            else if (qName.equals("data"))
            {
                insideLayer = true;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException
        {
            if(qName.equals("data"))
            {
                insideLayer = false;
                String[] rows = layerData.toString().split("\n");
                int i = 0;
                if (rows.length > 1)
                {
                    rows = Arrays.copyOfRange(rows, 1, rows.length);
                }
                for (String row : rows)
                {
                    int j = 0;
                    String[] tiles = row.trim().split(",");
                    //System.out.println(row);
                    for (String tile : tiles)
                    {
                        // Process each tile
                        try
                        {
                            int tileValue = Integer.parseInt(tile.trim());
                            LayerMatrix[currentLayer][i][j] = tileValue;
                            //System.out.printf("%-6d", tileValue);
                            j++;
                        }
                        catch (ArrayIndexOutOfBoundsException e)
                        {
                            System.out.println("Error: could not parse tile value as integer - " + currentLayer + " " + i + " " + j );
                            System.out.println(tile);
                        }
                    }
                    i++;
                }

            }
            layerData.setLength(0);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException
        {
            if (insideLayer)
            {
                layerData.append(ch, start, length);
            }
        }
    }

    public static MapHandler GetHandler(String XmlFile) throws SAXException, IOException
    {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser;
        {
            try
            {
                saxParser = factory.newSAXParser();
            }
            catch (ParserConfigurationException | SAXException e)
            {
                throw new RuntimeException(e);
            }
        }

        // Create an XMLReader object
        XMLReader reader = saxParser.getXMLReader();

        // Create a DefaultHandler object
        MapHandler handler = new MapHandler();

        // Set the content handler for the XMLReader
        reader.setContentHandler(handler);

        // Parse the XML file
        InputSource inputSource = new InputSource(XmlFile);
        reader.parse(inputSource);
        return handler;
    }
}
