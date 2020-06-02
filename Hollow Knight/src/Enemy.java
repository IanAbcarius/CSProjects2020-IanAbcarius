import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;


public class Enemy {
	private int width; 
	private int height;
	private int x;
	private int y;
	boolean alive;
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);
	EProjectile projectiles;
	public Enemy(int xI, int yI, String fileName) {
		// assignment statements for attributes
		alive = true;
		x = xI;
		y = yI;
		width = 50;
		height = 50;
		tx.setToTranslation(x, y);
		loadProjectiles();
		img = getImage(fileName);
	}
	public void loadProjectiles() {
		projectiles = new EProjectile("aspid.png", x, y);
		projectiles.active = true;
	}
	private Image img; 
	public void paint(Graphics g) {
		if(alive) {
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(img, tx, null);
		}
		if(projectiles.active) {
			projectiles.paint(g);
		}
	}
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Enemy.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	
	public void checkPlayer(Player p) {
		if(projectiles.active) {
			projectiles.runCollisionE(p);
		}
	}
	public void reload() {
		projectiles.setX(x);
		projectiles.setY(y);
		projectiles.active = true;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
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
}
