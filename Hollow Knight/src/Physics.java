public class Physics {
	double gravity;
	double hSlowFactor;
	double vSlowFactor;
	
	public Physics (double g,double h,double v){
		gravity = g;
		hSlowFactor = h;
		vSlowFactor = v;
	}

	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	public double getHSlowFactor() {
		return hSlowFactor;
	}

	public void setHSlowFactor(double hSlowFactor) {
		this.hSlowFactor = hSlowFactor;
	}

	public double getVSlowFactor() {
		return vSlowFactor;
	}

	public void setVSlowFactor(double vSlowFactor) {
		this.vSlowFactor = vSlowFactor;
	}
	public boolean onPlatform(Player p, Room r){
		
		return true;
	}

	public double gethSlowFactor() {
		return hSlowFactor;
	}

	public void sethSlowFactor(double hSlowFactor) {
		this.hSlowFactor = hSlowFactor;
	}

	public double getvSlowFactor() {
		return vSlowFactor;
	}

	public void setvSlowFactor(double vSlowFactor) {
		this.vSlowFactor = vSlowFactor;
	}
}
