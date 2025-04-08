package util.links;

import util.objects.ConnectionPort;

import java.awt.*;

public abstract class BasicLink {
    public ConnectionPort sourcePort;
    public ConnectionPort destinationPort;

    public enum Shape {
        ASSOCIATION,
        GENERALIZATION,
        COMPOSITION,
    }

    public BasicLink(ConnectionPort sourcePort, ConnectionPort destinationPort) {
        this.sourcePort = sourcePort;
        this.destinationPort = destinationPort;
    }

    void _drawLine(Graphics g){
        g.setColor(Color.black);
        g.drawLine((int) sourcePort.x, (int) sourcePort.y, (int) destinationPort.x, (int) destinationPort.y);
    }

    double _getDirection(){
        double dx= destinationPort.x - sourcePort.x;
        double dy= destinationPort.y - sourcePort.y;
        return Math.atan2(dy, dx);
    }

    abstract void _drawHead(Graphics g);

    public abstract void render(Graphics g);
}
