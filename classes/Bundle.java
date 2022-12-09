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
	 * 
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
	 * 
	 * @param type
	 * @param quantite
	 */
	public void removeRessource(String type, int quantite) {
		if (res_types.contains(type)) return;
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
	 * 
	 * @return
	 */
	public ArrayList<String> res_contenues() {
		ArrayList<String> list = new ArrayList<>();
		for (String s : res_types) {
			list.add(s);
		}

		return list;
	}

	/**
	 * 
	 * @param t
	 */
	public void tick(Terrain t) {
		for (RessourceDynamique r : res_dyn)
			r.tick(t, this);
	}

	/**
	 * 
	 * @param type
	 * @return
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
}
