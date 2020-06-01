public class Obstacles extends Platform {
	private int type;

	public Obstacles(int r, int c, int w, int h,int x, int y,boolean solid, int type) {
		super(r,c,w,h,x,y,solid);
		this.type = type;
	}

	
	public void setType(int type) {
		this.type = type;
	}


	public int getType() {
		return type;
	}

}
