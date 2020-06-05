import java.awt.Graphics;

public class Platform {
	protected int r;
	protected int c;
	protected int w;
	protected int h;
	protected int x;
	protected int y;
	protected boolean isSolid;

	public Platform(int r, int c, int w, int h, int x, int y,boolean solid) {
		super();
		this.r = r;
		this.c = c;
		this.w = w;
		this.h = h;
		this.x = x;
		this.y = y;
		this.isSolid = solid;
	}

	public String className() {
		return "Platform";
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	public void paint(Graphics g){
		if(isSolid) {
		g.fillRect(x, y, w, h);
		}
	}
	
	public int getType() {
		return 1;
	}



	public boolean isSolid() {
		return isSolid;
	}



	public void setSolid(boolean isSolid) {
		this.isSolid = isSolid;
	}
}
