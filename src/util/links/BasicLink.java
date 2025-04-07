package util.links;

import util.objects.BasicObject;

import java.awt.*;

public abstract class BasicLink {
    public BasicObject SourceObject;
    public BasicObject destinationObject;

    public enum Shape {
        ASSOCIATION,
        GENERALIZATION,
        COMPOSITION,
    }

    public BasicLink(BasicObject SourceObject, BasicObject DestinationObject) {
        this.SourceObject = SourceObject;
        this.destinationObject = DestinationObject;
    }

    void _drawLine(Graphics g){
        g.setColor(Color.black);
        g.drawLine((int) SourceObject.x, (int) SourceObject.y, (int) destinationObject.x, (int) destinationObject.y);
    }

    public abstract void render(Graphics g);
}
