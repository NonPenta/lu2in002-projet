import java.util.*;

public class Agent {
	protected int x, y;
	public final String type;
	protected String currentTask;
	protected HashMap<String, Integer> data;

	public Agent(String type) {
		this.type = type;
		x = -1;
		y = -1;
		currentTask = "";
		data = new HashMap<>();
	}
	public int distance(int x_, int y_) {
		int dx, dy;
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
