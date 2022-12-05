import java.util.ArrayList;

public class Bundle extends RessourceDynamique{
	private ArrayList<Ressource> ressources;
	
	public Bundle() {
		super("Bundle", 1);
		ressources = new ArrayList<Ressource>();
	}

	public void addRessource(String type, int quantite) {
		for (Ressource r : ressources) {
			if (r.type == type) {
				r.setQuantite(r.getQuantite() + quantite);
				return;
			}
		}

		switch (type) {
			case "Graine":
			case "Calcium":
			case "Moisissure":
				ressources.add(new RessourceStatique(type, quantite));
				break;
			case "Spore":
			case "Champignon":
				ressources.add(new Consommable(type, quantite));
				break;
			case "Graminee":
			case "MatBio":
				ressources.add(new NonConsommable(type, quantite));
				break;
			default:
				break;
		}
	}

	public void removeRessource(String type, int quantite) {
		Ressource r;
		for (int i = 0; i < ressources.size(); i++) {
			if ((r = ressources.get(i)).type == type) {
				r.setQuantite(r.getQuantite() - quantite);
				if (r.getQuantite() < 0) {
					ressources.remove(i);
				}
				return;
			}
		}
	}

	public ArrayList<Ressource> getList(){
		return ressources;
	}
}
