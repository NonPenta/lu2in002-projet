/**
 * @author BERNARD Victor
 * @author PIRON-PALLISER Maximilien
 */

public class RessourceTerrain extends Ressource {
	private Bundle b;
	// Charbon
	// Substrat
	// Décors

	/**
	 * @param type
	 */
	public RessourceTerrain(String type) {
		super(type,0);
		b = new Bundle();
	}

	/**
	 * Applique tick au bundle b 
	 */
	public void tick() {
		b.tick();
	}

	/**
	 * 
	 * @param type
	 * @param quantite
	 */
	public void addRessource(String type, int quantite) {
		b.addRessource(type, quantite);
	}

	/**
	 * Permet de, si elle est contenu dans une des listes de b, 
	 * retirer la ressource de la liste dans laquelle elle est contenue
	 * @param type
	 * @param quantite
	 */
	public void removeRessource(String type, int quantite) {
		b.removeRessource(type, quantite);
	}
}
