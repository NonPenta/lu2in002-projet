import java.util.ArrayList;

/**
 * @author BERNARD Victor
 * @author PIRON-PALLISER Maximilien
 */
public class Bundle extends RessourceDynamique{
	private ArrayList<RessourceStatique> res_stat;
	private ArrayList<RessourceDynamique> res_dyn;
	private ArrayList<String> res_types;

	/** 
	* 
	*/
	public Bundle() {
		super("Bundle", 1);
		res_stat = new ArrayList<>();
		res_dyn = new ArrayList<>();
		res_types = new ArrayList<>();
	}

	/**
	 * Permet d'ajouter une nouvelle ressource dans les listes créées,
	 * Ajout dans res_stat en cas de ressource Statique
	 * Ajout dans res_dyn en cas de ressource dynamique
	 * Si ce type de ressource existe déjà dans la liste res_types
	 * 	on ajoute 1 à la quantité de cette ressource. 
	 * @param type
	 * @param quantite
	 */
	public void addRessource(String type, int quantite) {
		boolean c = res_types.contains(type);
		boolean s = isStatic(type);

		if (!c) {
			res_types.add(type);
			if (s) {
				res_stat.add(new RessourceStatique(type, quantite));
			} else {
				res_dyn.add(new RessourceDynamique(type, quantite));
			}
		} else {
			for (Ressource r : (s ? res_stat : res_dyn))
				if (r.type == type)
					r.setQuantite(r.getQuantite() + quantite);
		}
	}

	/**
	 * Permet de, si elle est contenu dans une des listes, retirer la ressource de la liste dans laquelle elle est contenue
	 * @param type
	 * @param quantite
	 */
	public void removeRessource(String type, int quantite) {
		if (!res_types.contains(type)) return;
		boolean s = isStatic(type);
		Ressource r;

		for (int i = 0; i < (s ? res_stat : res_dyn).size(); i++) {
			if ((r = (s ? res_stat : res_dyn).get(i)).type == type) {
				r.setQuantite(r.getQuantite() - quantite);
				if (r.getQuantite() < 0) {
					(s ? res_stat : res_dyn).remove(i);
				}
				return;
			}
		}
	}

	/**
	 * Permet de reccupérer une nouvelle liste contenant les types des ressources contenus dans res_stat et res_dyn à partir de res_type.
	 * @return La liste de tous les types de Ressource contenus dans res_types
	 */
	public ArrayList<String> res_contenues() {
		ArrayList<String> list = new ArrayList<>();
		for (String s : res_types) {
			list.add(s);
		}

		return list;
	}

	/**
	 * Permet d'appliquer un tick sur toutes les ressources dynamiques contenues dans res_dyn
	 */
	public void tick() {
		for (RessourceDynamique r : res_dyn)
			r.tick(this);
	}

	/**
	 * Permet de savoir si une ressource est statique (sinon, dynamique)
	 * @param type
	 * @return Renvoie true si la ressource est statique, false sinon
	 */
	public boolean isStatic(String type) {
		switch (type) {
			case "Graine":
			case "Calcium":
			case "Moisissure":
				return true;
			case "Spore":
			case "Champignon":
			case "Graminee":
			case "MatBio":
				return false;
			default:
				return false;
		}
	}

	/**
	 * Permet de savoir s'il existe dans les listes res_stat(si s est statique) ou res_dyn (si s est dynamique) au moins une ressource de type s
	 * @param s
	 * @return true si un élément d'une des listes est de type s, false sinon
	 */
	public boolean contains(String type) {
		return res_types.contains(type);
	}

	/**
	 * Permet de connaître le nombre de ressource de type s contenues dans les listes res_stat(si s est statique) ou res_dyn (si s est dynamique)
	 * @param s
	 * @return le nombre de ressources de type s contenu dans une des listes
	 */
	public int count(String type) {
		if (!(contains(type))) { return 0; }

		for (Ressource r : (isStatic(type) ? res_stat : res_dyn)){
			if (r.type == type){
				return r.getQuantite();
			}
		}
		return 0;
	}
}
