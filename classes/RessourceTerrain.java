/**
 * @author BERNARD Victor
 * @author PIRON-PALLISER Maximilien
 */

public class RessourceTerrain extends Ressource {
	Bundle b;
	// Charbon
	// Substrat
	// Décors

	/**
	 * 
	 * @param type
	 */
	public RessourceTerrain(String type) {
		super(type,0);
	}

	/**
	 * 
	 * @param t
	 */
	public void tick(Terrain t) {
		b.tick(t);
	}
}
