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
	int ix;
	int iy;
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
	int deathTime;
	int stamina;
	boolean isClimbing;
	boolean isDying;
	Rectangle hB;
	private Image img; // image of the frog
	
	public Player(int x, int y, int ix, int iy, int w, int h, int stamina, String fileName) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.ix = ix;
		this.iy=iy;
		this.xV = 0;
		this.yV = 0;
		this.xA = 0;
		this.yA = 0;
		this.stamina = stamina;
		this.isClimbing = false;
		this.isDying = false;
		tx.scale(1, 1);
		sH=900;
		sW=1300;
		hB = new Rectangle(x,y,h,w);
		img = getImage(fileName);
		jump = true;
		jump2 = true;
		deathTime = 0;
	}
	
	public void setCords(){
		r = 36*y/(sH);
		c = x/(sW/52);
		tx.setToTranslation(x, y);
	}
	
	public void move(Platform[][] grid){
		setCords();
		xV += xA;
		yV += yA;
		inPlatform(grid);

		hB = new Rectangle(x,y,h,w);
		if(onPlatform(grid)){
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
		inPlatform(grid);
		if(onPlatform(grid)){
			yV = 0;
		}
	}
	
	public void updateKinematics(Physics p,Platform[][] grid){
		yA=p.getGravity();
		if(Math.abs(xV)>12){
		xV = xV*p.getHSlowFactor();
		}
		
	}

	public boolean onPlatform(Platform[][] grid){
		boolean g1 =false;
		boolean g2 =false;
		for(int i =0; i<4;i++){
			if(grid[r+i-1][c].isSolid()) {
			if(grid[r+i-1][c].getY()>=(y+h)) {
//				y=grid[r+i][c].getY()-h;
//				tx.setToTranslation(x, y);
				if(grid[r+i-1][c].getType()==2) {
					isDying = true;
					deathTime = (int) System.currentTimeMillis();
				}
				g1 = true;
				continue;
			}	
			}
			}
		for(int i =0; i<4;i++){
			if(grid[r+i-1][c+1].isSolid()) {
			if(grid[r+i-1][c+1].getY()>=(y+h)) {
//				y=grid[r+i][c+1].getY()-h;
//				tx.setToTranslation(x, y);
				if(grid[r+i-1][c].getType()==2) {
					isDying = true;
					System.out.println("yer");
				}
				g2 = true;
				continue;
			}}}
		if(g1||g2) {
			jump = false;
			jump2 = false;
//			System.out.println("on platform");
			System.out.println(isDying);
			return true;
		}
		
		
		
		return false;
	}		
	private void inPlatform(Platform[][] grid) {
		if(grid[r+1][c].isSolid||grid[r][c].isSolid||grid[r+1][c+1].isSolid||grid[r][c+1].isSolid) {
			yV=0;
			y-=26;
			setCords();
			inPlatform(grid);
		}
			
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
	
	
	public void deathCheck() {
		if(isDying&&System.currentTimeMillis()-deathTime>2000) {
			x = ix;
			y = iy;
			isDying = false;
			setCords();
		}
	}
	
	
	public void paint(Graphics g) {
		hB = new Rectangle(x,y,h,w);
		g.setColor(Color.green);
		g.fillRect(x, y, w, h);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
		
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

	public int getsH() {
		return sH;
	}

	public void setsH(int sH) {
		this.sH = sH;
	}

	public int getsW() {
		return sW;
	}

	public void setsW(int sW) {
		this.sW = sW;
	}

	public double getxV() {
		return xV;
	}

	public void setxV(double xV) {
		this.xV = xV;
	}

	public double getyV() {
		return yV;
	}

	public void setyV(double yV) {
		this.yV = yV;
	}

	public double getxA() {
		return xA;
	}

	public void setxA(double xA) {
		this.xA = xA;
	}

	public double getyA() {
		return yA;
	}

	public void setyA(double yA) {
		this.yA = yA;
	}

	public AffineTransform getTx() {
		return tx;
	}

	public void setTx(AffineTransform tx) {
		this.tx = tx;
	}

	public boolean isJump() {
		return jump;
	}

	public void setJump(boolean jump) {
		this.jump = jump;
	}

	public boolean isJump2() {
		return jump2;
	}

	public void setJump2(boolean jump2) {
		this.jump2 = jump2;
	}

	public int getDeathTime() {
		return deathTime;
	}

	public void setDeathTime(int deathTime) {
		this.deathTime = deathTime;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public boolean isClimbing() {
		return isClimbing;
	}

	public void setClimbing(boolean isClimbing) {
		this.isClimbing = isClimbing;
	}

	public boolean isDying() {
		return isDying;
	}

	public void setDying(boolean isDying) {
		this.isDying = isDying;
	}

	public Rectangle gethB() {
		return hB;
	}

	public void sethB(Rectangle hB) {
		this.hB = hB;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}
	

}
