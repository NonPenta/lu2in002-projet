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
	 * Comme on ne peut pas modifier les classes Ressource ni Terrain,
	 * on ne peut exécuter de fonction tick() pour faire avancer les ressources dans le temps,
	 * donc on redéfinit une fonction de Ressource inutile pour cette classe fille afin de pouvoir l'exécuter
	 * */
	public void initialisePosition() {
		tick();
	}

	/**
	 * Idem ; Cette fonction sert donc en réalité à effectuer diverses actions sur le bundle, et à obtenir diverses informations dessus
	 * @param lig
	 * @param col
	 */
	public void setPosition(int lig, int col) {
		int mode = lig;
		int val = col;
		if (mode < 20) return; // Ceci correspondrait à un appel "normal" de cette fonction ; que nous ne voulons pas pour cette classe
		switch (mode) {
			case 20: // équivalent à contains.
		}
	}

	/** Ajoute une ressource au Bundle de cette ressource
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
