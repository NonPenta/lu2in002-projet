public class Consommable extends RessourceDynamique {
	private int nSpores = 0;
	// Champignons
	// Spore
	public Consommable(String type, int quantite) {
		super(type, quantite);
	}
	public void tick(Terrain t) {
		super.tick(t);
		if (type == "Spore") {
			if (getAge() > 10) {
				Consommable c = new Consommable("Champignon", 1);
				t.setCase(getY(), getX(), c);
			}
		} else if (type == "Champignon") {
			if (getAge() % 5 == 0) { nSpores++; }
		}
	}
}
