
public class Portal extends Platform {
	String p;
	int num;
	private int mR;
	private int mC;
	private int nX;
	private int nY;

	public Portal(int r, int c, int w, int h, int x, int y, String p) {
		super(r, c, w, h, x, y, false);
		this.p = p;
		num = p.charAt(0);

		if (num==97) {
			mR = 4;
			mC = 1;
			nX = 130;
			nY = 675;
		}
		else if(num == 98) {
			mR = 4;
			mC = 0;
			nX = 1200;
			nY = 470;
		}
		else if(num == 99) {
			mR = 4;
			mC = 2;
			nX = 100;
			nY = 725;
		}
		else if(num == 100) {
			mR = 4;
			mC = 1;
			nX = 1200;
			nY = 725;
		}
		else if(num == 101) {
			mR = 3;
			mC = 1;
			nX = 500;
			nY = 800;
		}
		else if(num == 102) {
			mR = 4;
			mC = 1;
			nX = 550;
			nY = 150;
		}
		else if(num == 103) {
			mR = 3;
			mC = 2;
			nX = 125;
			nY = 575;
		}
		else if(num == 104) {
			mR = 4;
			mC = 2;
			nX = 575;
			nY = 125;
		}
		else if(num == 105) {
			mR = 3;
			mC = 1;
			nX = 1200;
			nY = 800;
		}
		else if(num == 106) {
			mR = 2;
			mC = 2;
			nX = 450;
			nY = 800;
		}
		else if(num == 107) {
			mR = 3;
			mC = 2;
			nX = 600;
			nY = 100;
		}
		else if(num == 108) {
			mR = 1;
			mC = 2;
			nX = 125;
			nY = 800;
		}
		else if(num == 109) {
			mR = 2;
			mC = 2;
			nX = 650;
			nY = 125;
		}
		
		
		
		
		
		
	

		// TODO Auto-generated constructor stub
	}

	public String className() {
		return "Portal";
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public int getmR() {
		return mR;
	}

	public void setmR(int mR) {
		this.mR = mR;
	}

	public int getmC() {
		return mC;
	}

	public void setmC(int mC) {
		this.mC = mC;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getnX() {
		return nX;
	}

	public void setnX(int nX) {
		this.nX = nX;
	}

	public int getnY() {
		return nY;
	}

	public void setnY(int nY) {
		this.nY = nY;
	}

}
