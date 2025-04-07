package util.objects;

import java.awt.*;

public class ConnectionPort{
    static final int WIDTH =4;
    int x,y;
    public ConnectionPort(int x, int y){
        this.x=x;
        this.y=y;
    }

    public void render(Graphics g){
        g.setColor(new Color(0, 0, 0,255));
        g.fillRect(x-WIDTH/2,y-WIDTH/2,WIDTH,WIDTH);
    }
}
