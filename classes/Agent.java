import java.util.*;

public class Agent {
	protected double x, y;
	public final String type;

	public Agent(String type) {
		this.type = type;
		x = -1;
		y = -1;
	}
	public double distance(int _x, int _y) {
		double x_ = (float) x;
		double y_ = (float) y;
		double dx, dy;
		return (dx = x - x_) * (dx < 0 ? -1 : 1) + (dy = y - y_) * (dy < 0 ? -1 : 1);
	}

	public int seDeplacer(int nx, int ny, ArrayList<Agent> al) {
		for (Agent a : al) {
			if (a.distance(nx, ny) == 0) {
				return 1;
			}
		}

		x = nx; y = ny;
		return 0;
	}
}
