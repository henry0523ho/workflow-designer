package util.links;

import util.objects.ConnectionPort;

import java.awt.*;

public class CompositionLink extends BasicLink {
    public CompositionLink(ConnectionPort sourcePort, ConnectionPort destinationPort) {
        super(sourcePort, destinationPort);
    }

    @Override
    void _drawHead(Graphics g) {
        double direction = _getDirection();
        int headSize = 20;
        int x1 = (int) (destinationPort.x - headSize * Math.cos(direction + Math.PI / 8) / 2);
        int y1 = (int) (destinationPort.y - headSize * Math.sin(direction + Math.PI / 8) / 2);
        int x2 = (int) (destinationPort.x - headSize * Math.cos(direction - Math.PI / 8) / 2);
        int y2 = (int) (destinationPort.y - headSize * Math.sin(direction - Math.PI / 8) / 2);
        int x3 = (int) (destinationPort.x - headSize * Math.cos(direction));
        int y3 = (int) (destinationPort.y - headSize * Math.sin(direction));
        g.setColor(Color.white);
        g.fillPolygon(new int[]{x1, x3, x2, (int) destinationPort.x}, new int[]{y1, y3, y2, (int) destinationPort.y}, 4);
        g.setColor(Color.black);
        g.drawLine((int) destinationPort.x, (int) destinationPort.y, x1, y1);
        g.drawLine((int) destinationPort.x, (int) destinationPort.y, x2, y2);
        g.drawLine(x3, y3, x1, y1);
        g.drawLine(x3, y3, x2, y2);
    }

    @Override
    public void render(Graphics g) {
        _drawLine(g);
        _drawHead(g);
    }
}