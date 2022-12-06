public class RessourceTerrain extends Ressource {
	Bundle b;
	// Charbon
	// Substrat
	// Décors
	public RessourceTerrain(String type) {
		super(type,0);
	}

	public void tick(Terrain t) {
		b.tick(t);
	}
}
