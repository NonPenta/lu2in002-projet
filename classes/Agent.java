import java.util.*;

public abstract class Agent {
	protected double x, y;
	public final String type;

	protected Agent(String type) {
		this.type = type;
		x = -1;
		y = -1;
	}
	public double distance(int _x, int _y) {
		double x_ = (double) _x;
		double y_ = (double) _y;
		double dx, dy;
		return (dx = x - x_) * (dx < 0 ? -1 : 1) + (dy = y - y_) * (dy < 0 ? -1 : 1);
	}

	public void seDeplacer(int nx, int ny) {
		x = nx; y = ny;
	}
}
