public class NonConsommable extends RessourceDynamique{
	private int nGraines;
	// Graminées
	// Mat. bio. en décomposition
	public NonConsommable(String type, int quantite) { super(type, quantite); }
	public void tick(Terrain t) {
		super.tick(t);
		if (type == "MatBio") {
			Bundle b = new Bundle();
			b.addRessource(type, getQuantite() - 1);
			b.addRessource("Moisissure", 1);
			t.setCase(getY(), getX(), b);
		} else if (type == "Graminee") {
			if (getAge() % 3 == 0) { nGraines++; }
		}
	}
	public void tick(Terrain t, Bundle b) {
		super.tick(t);
		if (type == "MatBio") {
			b.removeRessource(type, 1);
			b.addRessource("Moisissure", 1);
		} else if (type == "Graminee") {
			if (getAge() % 3 == 0) { nGraines++; }
		}
	}
}
