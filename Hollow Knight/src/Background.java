import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;


public class Background {
	private int width; 
	private int height;
	private int x;
	private int y;
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);
	public Background(String fileName) {
		// assignment statements for attributes
		x = 0;
		y = 0;
		width = 50;
		height = 50;
		tx.setToTranslation(x, y);
		img = getImage(fileName);
	}
	private Image img; 
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		//g.fillRect(x, y, width, height);
		g2.drawImage(img, tx, null);	
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
}
