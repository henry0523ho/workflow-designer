package util.links;

import util.objects.ConnectionPort;

import java.awt.*;

public class AssociationLink extends BasicLink {
    public AssociationLink(ConnectionPort sourcePort, ConnectionPort destinationPort) {
        super(sourcePort, destinationPort);
    }

    @Override
    void _drawHead(Graphics g) {
        double direction = _getDirection();
        int headSize = 20;
        int x1 = (int) (destinationPort.x - headSize * Math.cos(direction + Math.PI / 6));
        int y1 = (int) (destinationPort.y - headSize * Math.sin(direction + Math.PI / 6));
        int x2 = (int) (destinationPort.x - headSize * Math.cos(direction - Math.PI / 6));
        int y2 = (int) (destinationPort.y - headSize * Math.sin(direction - Math.PI / 6));
        g.setColor(Color.black);
        g.drawLine((int) destinationPort.x, (int) destinationPort.y, x1, y1);
        g.drawLine((int) destinationPort.x, (int) destinationPort.y, x2, y2);
    }

    @Override
    public void render(Graphics g) {
        _drawLine(g);
        _drawHead(g);
    }
}
