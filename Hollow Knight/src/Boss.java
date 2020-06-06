import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class Boss {
	private int width; 
	private int height;
	private int x;
	private long time;
	private int y;
	private boolean alive;
	private int health;
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);
	int startTime;
	ArrayList<EProjectile> projectiles;
	public Boss(int xI, int yI, String fileName, Player p) {
		// assignment statements for attributes
		alive = true;
		x = xI;
		y = yI;
		width = 160;
		health = 500; //edit health
		height = 160;
		tx.setToTranslation(x-50, y-20);
		projectiles = new ArrayList<EProjectile>();
		img = getImage(fileName);
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public void loadProjectiles(Player p) {
		time = System.currentTimeMillis();
		projectiles.add(new EProjectile("projectile.png", x+90, y+90));
	}
	private Image img; 
	public void paint(Graphics g, Player p) {
		//System.out.println(health + " health");
		if(projectiles.size()<3 &&(System.currentTimeMillis() - time) >= 2000) { //add more projectiles by increasing size
			//System.out.println(time);
			loadProjectiles(p);
			//System.out.println(System.currentTimeMillis() + " hi");
		}
		if(alive) {
			Graphics2D g2 = (Graphics2D) g;
			//g.fillRect(x,y,width,height);
			g2.drawImage(img, tx, null);
		}
		for(int i = 0; i<projectiles.size(); i++) {
			if(projectiles.get(i).active) {
				projectiles.get(i).paint(g); 
			} else if(alive) {
				reload(p, i);
			}
		}
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
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
		for(int i = 0; i<projectiles.size(); i++) {
			if(projectiles.get(i).active) {
				projectiles.get(i).runCollisionE(p);
			}
		}
		
	}
	public void checkPlatforms(Platform[][] grid) {
		for(int i = 0; i<projectiles.size(); i++) {
			if(projectiles.get(i).active) {
				projectiles.get(i).collidedPlatform(grid);
			}
		}
	}
	public void reload(Player p, int i) {
		projectiles.get(i).setX(x+90);
		projectiles.get(i).setY(y+90);
		projectiles.get(i).aim(p, true);
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
