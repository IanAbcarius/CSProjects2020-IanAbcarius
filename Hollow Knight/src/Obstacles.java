import java.awt.Color;
import java.awt.Graphics;

public class Obstacles extends Platform {
	private int type;

	public Obstacles(int r, int c, int w, int h,int x, int y,boolean solid, int type) {
		super(r,c,w,h,x,y,solid);
		this.type = type;
	}

	
	public void setType(int type) {
		this.type = type;
	}

	public void paint(Graphics g){
		if(type == 2) {
			g.setColor(Color.green);
		} else {
			g.setColor(Color.black);
		}
		if(isSolid) {
			g.fillRect(x, y, w, h);
		}
	}
	public int getType() {
		return type;
	}

}
