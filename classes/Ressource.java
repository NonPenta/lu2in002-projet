public class Ressource {
	private static int nbRessourcesCreees = 0;

	public final int ident;

	public final String type;

	private int quantite;

	private int x;

	private int y;

	public Ressource(String type, int quantite) {
		this.type = type;
		this.quantite = quantite;
		this.ident = nbRessourcesCreees++;
		this.x = -1;
		this.y = -1;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getQuantite() {
		return this.quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public void setPosition(int lig, int col) {
		this.x = lig;
		this.y = col;
	}

	public void initialisePosition() {
		this.x = -1;
		this.y = -1;
	}

	public String toString() {
		String sortie = String.valueOf(this.type) + "[id:" + this.ident + " quantite: " + this.quantite + "] ";
		if (this.x == -1 || this.y == -1) {
			sortie = String.valueOf(sortie) + " n'est pas sur le terrain.";
		} else {
			sortie = String.valueOf(sortie) + " en position (" + this.x + ", " + this.y + ")";
		} 
		return sortie;
	}

	public void removeRessource(String type, int quantite) { return; }
	public void addRessource(String type, int quantite) { return; }
	public boolean contains(String type) { return type == this.type; }
	public int count(String type) { return 0; }
	public int take(String type) { int tmp = quantite; quantite = 0; return tmp; }
}
