public class RessourceDynamique extends Ressource {
	// Champignons, Graminees, MatBio
	private int age;
	private int nProduit; // Graines & Spores

	public RessourceDynamique(String type, int quantite){
		super(type,quantite);
		age = 0;
	}

	public int getAge() { return age; }

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
