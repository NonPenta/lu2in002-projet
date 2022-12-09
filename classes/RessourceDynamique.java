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
	 * - Dans le cas d'un champignon, on ajoute 1 nProduit tous les 5 ticks
	 * - Dans le cas d'une MatBio, on diminue sa quantité dans le bundle et on y ajoute une moisissure
	 * - Dans le cas d'un graminee, on ajoute 1 nProduit tous les 3 ticks
	 * @param b
	 */
	public void tick(Bundle b) {
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
