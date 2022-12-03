public class RessourceDynamique extends Ressource {
	private int age;
	public RessourceDynamique(String type, int quantite){
		super(type,quantite);
		age = 0;
	}

	public int getAge() { return age; }

	public void tick(Terrain t) { age++; } // mise à jour de la ressource (passage d'une étape)
}
