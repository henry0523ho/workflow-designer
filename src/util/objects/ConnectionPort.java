package util.objects;

import java.awt.*;

public class ConnectionPort{
    static final int WIDTH =8;
    public int x,y;
    public ConnectionPort(int x, int y){
        this.x=x;
        this.y=y;
    }

    public void render(Graphics g){
        g.setColor(new Color(0, 0, 0,255));
        g.fillRect(x-WIDTH/2,y-WIDTH/2,WIDTH,WIDTH);
    }
    public void move(int dx, int dy){
        x+=dx;
        y+=dy;
    }

    public boolean inside(int detectX, int detectY){
        if(detectX>=x-WIDTH/2 && detectX<=x+WIDTH/2 && detectY>=y-WIDTH/2 && detectY<=y+WIDTH/2){
            return true;
        }
        return false;
    }
}
