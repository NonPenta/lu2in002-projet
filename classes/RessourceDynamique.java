/**
 * @author BERNARD Victor
 * @author PIRON-PALLISER Maximilien
 */
public class RessourceDynamique extends Ressource {
	// Champignons, Graminees, MatBio
	private int age;
	private int nProduit; // Graines & Spores

	/**
	 * 
	 * @param type
	 * @param quantite
	 */
	public RessourceDynamique(String type, int quantite){
		super(type,quantite);
		age = 0;
	}

	/**
	 * Permet de connaître l'âge d'une ressource dynamique
	 * @return l'age de cette ressource
	 */
	public int getAge() { return age; }

	/**
	 * Permet de fait vieillir la ressource dynamique puis :
	 * - Dans le cas d'un champignon, s'il a 5 ans, on ajoute 1 nProduit (ici spore)
	 * - Dans le cas d'une MatBio, on la retire du bundle et on crée une moisissure
	 * - Dans le cas d'un graminee, s'il a 3 ans, on ajoute 1 à nProduit (ici graine)
	 * @param t
	 * @param b
	 */
	public void tick(Terrain t, Bundle b) {
		age++;
		switch (type) {
		case "Champignon":
			if (age % 5 == 0) nProduit++;
			break;
		case "MatBio":
			b.removeRessource(type, 1);
			b.addRessource("Moisissure", 1);
			break;
		case "Graminee":
			if (age % 3 == 0) nProduit++;
		}
	}
}
