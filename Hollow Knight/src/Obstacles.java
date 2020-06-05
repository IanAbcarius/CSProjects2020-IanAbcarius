import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Obstacles extends Platform {
	private int type;
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);
	private Image img; 

	public Obstacles(int r, int c, int w, int h,int x, int y,boolean solid, int type) {
		super(r,c,w,h,x,y,solid);
		this.type = type;
		tx.setToTranslation(x, y);
	}

	
	public void setType(int type) {
		this.type = type;
	}

	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		switch(type) {
			case 2:
				img = getImage("spike not spiegal.png");
				break;
			case 3:
				img = getImage("leftSpike.png");
				break;
			case 4:
				img = getImage("rightSpike.png");
				break;
			case 5:
				img = getImage("downSpike.png");
				break;
			default:
				g.fillRect(x, y, w, h);
				break;
		}
		g2.drawImage(img, tx, null);
		if(isSolid) {
			g.setColor(Color.black);
		}
	}
	public int getType() {
		return type;
	}
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
}
