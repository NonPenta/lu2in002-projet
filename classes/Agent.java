import java.util.*;
/**
 * @author BERNARD Victor
 * @author PIRON-PALLISER Maximilien
 */

public abstract class Agent {
	protected double x, y;
	public final String type;

	/**
	 * 
	 * @param type
	 */
	protected Agent(String type) {
		this.type = type;
		x = -1;
		y = -1;
	}

	/**
	 * Permet de connaître la distance entre deux points
	 * @param _x
	 * @param _y
	 * @return la distance après calcul
	 */

	public double distance(int _x, int _y) {
		double x_ = (double) _x;
		double y_ = (double) _y;
		double dx, dy;
		return (dx = x - x_) * (dx < 0 ? -1 : 1) + (dy = y - y_) * (dy < 0 ? -1 : 1);
	}

	/**
	 * Permet à un Agent de se déplacer vers un point(nx,ny)
	 * @param nx
	 * @param ny
	 */
	abstract public void seDeplacer(int nx, int ny);
}
