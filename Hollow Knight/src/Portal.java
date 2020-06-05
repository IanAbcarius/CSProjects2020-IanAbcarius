
public class Portal extends Platform{
	String p;
	public Portal(int r, int c, int w, int h, int x, int y, String p) {
		super(r, c, w, h, x, y, false);
		this.p = p; 
		// TODO Auto-generated constructor stub
	}
	public String className() {
		return "Portal";
	}

}
