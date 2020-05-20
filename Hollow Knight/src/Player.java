import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;

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
	int stamina;
	boolean isClimbing;
	Rectangle hB;
	
	public Player(int x, int y, int w, int h, int stamina) {
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
		sH=900;
		sW=1300;
		hB = new Rectangle(x,y,h,w);
	}
	
	public void setCords(){
		r = 36*y/(sH);
		c = x/(sW/52);
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
		hB = new Rectangle(x-1,y-1,h+2,w+2);
		g.drawRect(x, y, w, h);
		for(int i =0; i<4 ;i++){
			if(grid[r+i][c].isSolid()) {
			if(grid[r+i][c].getY()>=(y+h)) {
				y=grid[r+i][c].getY()-h;
				return true;
				
			}}
			
			
					
		}


System.out.println("works");
			return false;
}
			
			
			
			
			
			
			
			
		

	
	public void paint(Graphics g) {
		hB = new Rectangle(x,y,h,w);
		g.setColor(Color.green);
		g.fillRect(x, y, w, h);

		
	}
	

}
