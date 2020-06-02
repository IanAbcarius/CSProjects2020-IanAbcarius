import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class Enemy {
	private int width; 
	private int height;
	private int x;
	private int y;
	boolean alive;
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);
	int startTime;
	EProjectile projectiles;
	public Enemy(int xI, int yI, String fileName, Player p) {
		// assignment statements for attributes
		alive = true;
		x = xI;
		y = yI;
		width = 50;
		height = 50;
		tx.setToTranslation(x, y);
		loadProjectiles(p);
		img = getImage(fileName);
	}
	public void loadProjectiles(Player p) {
		projectiles = new EProjectile("aspid.png", x, y);
		projectiles.active = true;
		projectiles.aim(p);
	}
	private Image img; 
	public void paint(Graphics g, Player p) {
		if(alive) {
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(img, tx, null);
		}
		if(projectiles.active) {
			projectiles.paint(g);
		} else if(alive) {
			reload(p);
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
	public void checkPlatforms(Platform[][] grid) {
		if(projectiles.active) {
			projectiles.collidedPlatform(grid);
		}
	}
	public void reload(Player p) {
		projectiles.setX(x);
		projectiles.setY(y);
		projectiles.aim(p);
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
