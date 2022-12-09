import java.util.*;
/**
 * @author BERNARD Victor
 * @author PIRON-PALLISER Maximilien
 */

public class Agent {
	protected double x, y;
	public final String type;

	/**
	 * 
	 * @param type
	 */
	public Agent(String type) {
		this.type = type;
		x = -1;
		y = -1;
	}

	/**
	 * 
	 * @param _x
	 * @param _y
	 * @return La distance entre deux points donnés en paramètres
	 */

	public double distance(int _x, int _y) {
		double x_ = (double) _x;
		double y_ = (double) _y;
		double dx, dy;
		return (dx = x - x_) * (dx < 0 ? -1 : 1) + (dy = y - y_) * (dy < 0 ? -1 : 1);
	}

	/**
	 * 
	 * @param nx
	 * @param ny
	 */
	public void seDeplacer(int nx, int ny) {
		x = nx; y = ny;
	}
}
