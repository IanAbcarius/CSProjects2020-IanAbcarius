import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Slash {
	private int w; 
	private int h;
	private Image img; 
	private int x;
	private int y;
	private int side;
	private boolean attacking;
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);
	public Slash(String fileName, int side) {
		this.side = side;
		tx.setToTranslation(x, y);
		img = getImage(fileName);
		attacking = false;
	}
	public void paint(Graphics g) {
		if(attacking) {
			tx.setToTranslation(x, y);
			Graphics2D g2 = (Graphics2D) g;
			//g.fillRect(x, y, w, h);
			g2.drawImage(img, tx, null);
		}
		
	}
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = NPC.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	public void position(Player p) {
		attacking = true;
		switch(side) {
			case 1:
				System.out.println("coolz");
				x = p.x - 40;
				y = p.y + 5;
				w = 40;
				h = p.h-15;
				break;
			case 2:
				x = p.x + p.w;
				y = p.y + 5;
				w = 40;
				h = p.h-15;
				break;
			case 3:
				x = p.x - 5;
				y = p.y - 40;
				w = p.w+10;
				h = 40;
				break;
		}
	}
	public void setAttack(boolean attack) {
		attacking = attack;
	}
}
