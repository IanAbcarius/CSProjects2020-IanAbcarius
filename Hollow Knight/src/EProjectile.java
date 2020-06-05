import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

/* class to represent a Ship object in a game */
public class EProjectile {

	private int x, y;
	public boolean active;
	private int width, height;
	private int r;
	private int c;
	private long startTime;
	private Image img;
	private int sH, sW;
	private double vx, vy;
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

	// constructor
	public EProjectile(String fileName, int xI, int yI) {
		x = xI;
		y = yI;
		sH = 900;
		sW = 1300;
		vx = 0;
		vy = 0;
		width = 25;
		height = 25;

		// do not touch
		img = getImage(fileName);
		update();
	}

	public void setVx(int vx) {
		this.vx = vx;
		update();
	}

	public void setVy(int vy) {
		this.vy = vy;
		update();
	}

	// draw the affinetransform
	public void paint(Graphics g) {
		//g.fillRect(x, y, 25, 25);
		Graphics2D g2 = (Graphics2D) g;
		// g.fillRect(x+2, y, 20, 22);
		g2.drawImage(img, tx, null);
		update();
	}

	public void aim(Player p, boolean boss) {
		if (System.currentTimeMillis() - startTime >= 1000 && !boss) {
			active = true;
			double deltaX = p.x - x;
			double deltaY = p.y - y;
			double direction = Math.atan2(deltaY, deltaX);
			vx = (10 * Math.cos(direction));
			vy = (10 * Math.sin(direction));
		} else if(System.currentTimeMillis() - startTime >= 1000) {
			active = true;
			double deltaX = p.x - x;
			double deltaY = p.y - y;
			double direction = Math.atan2(deltaY, deltaX);
			double rand = Math.random()*Math.PI/3-Math.PI/6;
			vx = (10 * Math.cos(direction+rand));
			vy = (10 * Math.sin(direction+rand));
		}
	}

	private void update() {
		x += (int) vx;
		y += (int) vy;
		r = 36 * y / (sH);
		c = x / (sW / 52);
		tx.setToTranslation(x, y);
		if (x > 1300 || x < 0 || y < 0 || y > 900) {
			startTime = System.currentTimeMillis();
			active = false;
		}
	}

	// converts image to make it drawable in paint
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = EProjectile.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
		update();
	}

	public void collidedPlatform(Platform[][] grid) {
		if (grid[r + 1][c].isSolid || grid[r + 1][c + 1].isSolid || grid[r][c].isSolid || grid[r][c + 1].isSolid) {
			startTime = System.currentTimeMillis();
			active = false;
			//System.out.println("hit");
		}
	}

	public void setY(int y) {
		this.y = y;
		update();
	}

	public double getVx() {
		return vx;
	}

	public double getVy() {
		return vy;
	}

	public boolean checkCollisionE(Player p) {
		Rectangle projectile = new Rectangle(x + 2, y, 20, 22);
		Rectangle player = new Rectangle(p.getX(), p.getY(), p.getW(), p.getH());
		return projectile.intersects(player);
	}

	public void runCollisionE(Player p) {
		if (checkCollisionE(p)) {
			p.isDying = true;
			// p.setLives(p.getLives()-1);
			startTime = System.currentTimeMillis();
			active = false;
		}
	}
}
