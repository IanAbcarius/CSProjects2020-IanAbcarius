import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.net.URL;

public class Player {
	int x;
	int y;
	int w;
	int h;
	int r;
	int c;
	int sH;
	int sW;
	double xV;
	double yV;
	double xA;
	double yA;
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);
	boolean jump;
	boolean jump2;
	int stamina;
	boolean isClimbing;
	Rectangle hB;
	private Image img; // image of the frog
	
	public Player(int x, int y, int w, int h, int stamina, String fileName) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.xV = 0;
		this.yV = 0;
		this.xA = 0;
		this.yA = 0;
		this.stamina = stamina;
		this.isClimbing = false;
		tx.scale(1, 1);
		sH=900;
		sW=1300;
		hB = new Rectangle(x,y,h,w);
		img = getImage(fileName);
		jump = true;
		jump2 = true;
	}
	
	public void setCords(){
		r = 36*y/(sH);
		c = x/(sW/52);
		tx.setToTranslation(x, y);
	}
	
	public void move(Platform[][] grid,Graphics g){
		setCords();
		xV += xA;
		yV += yA;
		hB = new Rectangle(x,y,h,w);
		if(onPlatform(grid, g)){
			yV = 0;
		}
		if(xV>10) {
			xV=10;
		}
		if(yV>10) {
			yV=10;
		}
		x += xV;
		y+= yV;
		setCords();
	}
	
	public void updateKinematics(Physics p,Platform[][] grid){
		yA=p.getGravity();
		if(Math.abs(xV)>12){
		xV = xV*p.getHSlowFactor();
		}
		
	}

	public boolean onPlatform(Platform[][] grid, Graphics g){
		for(int i =0; i<4 ;i++){
			if(grid[r+i][c].isSolid()) {
			if(grid[r+i][c].getY()>=(y+h)) {
				y=grid[r+i][c].getY()-h;
				tx.setToTranslation(x, y);
				jump = false;
				jump2 = false;
				System.out.println("on platform");
				return true;
				
			}}	
		}
		return false;
	}		
		
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Player.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	
	public void paint(Graphics g) {
		hB = new Rectangle(x,y,h,w);
		g.setColor(Color.green);
		g.fillRect(x, y, w, h);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
		
	}
	

}
