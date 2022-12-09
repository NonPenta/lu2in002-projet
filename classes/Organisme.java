import java.util.*;

/**
 * @author BERNARD Victor
 * @author PIRON-PALLISER Maximilien
 */
public class Organisme extends Agent{
	protected String currentTask;
	// Tasks : locateFood, getToFood, rest, [restFindSpot, shareFood, reproduceGatherEnergy, reproduce, locateCa, getToCa, moult]
	protected HashMap<String, Double> data;
	// type : data
	// Collemboles : {"FoodInSystem" : double, "Energy" : double, "TSLR" : double, "Rest" : double, "Age" : double, "foodGiver" : double}
	// Isopodes : {"Ca" : double, "FoodInSystem" : double, "Energy" : double, "TSLR" : double, "Rest" : double, "Age" : double, "foodGiver" : double}
	// Serpents : {"Ca" : double, "FoodInSystem" : double, "Energy" : double, "Rest" : double, "Age" : double}
	// Rats : {"FoodInSystem" : double, "Energy" : double, "TSLR" : double, "Rest" : double, "Age" : double, "foodGiver" : double}

	// targetXLocate[Food,Ca], targetYLocate[Food,Ca], targetXGather[Food,Ca], targetYGather[Food,Ca] seront ajoutés & enlevés par la fonction de choix d'action et d'action des organismes
	// idem pour reproduceTimer et moultTimer

	private final double δe = -.01 / ((type == "Serpent") ? 10 : 1);
	private final double Δe = -.03 / ((type == "Serpent") ? 10 : 1);
	// 0.03 d'énergie dépensée / frame si agissant ; 0.01 si se reposant      0.003 ; 0.001 pour le serpent
	private final double δf = .15;
	private final double Δf = .06;
	// 1.5 d'énergie / nourriture ; obtenue a un rythme de 0.15/f si au repos ou 0.06/f si en action
	private final double δr = .03 / ((type == "Serpent") ? 2 : 1);
	private final double Δr = -.01 * ((type == "Serpent") ? 3 : 1);
	// 0.03 repos obtenu / frame si se reposant ; 0.01 dépensé si agissant  0.015 ; 0.03 pour le serpent

	/**
	 * 
	 * @param type
	 */
	public Organisme(String type) {
		super(type);
		currentTask = "rest";
		data = new HashMap<>();
		data.put("Age", 0.0);
		switch (type) {
			case "Isopode":
				data.put("Ca", 0.0);
			case "Collembole":
				data.put("foodGiver", Math.random() - 0.5);
				data.put("FoodInSystem", 10.0);
				data.put("Energy", 10.0);
				data.put("TSLR", 0.0);
				data.put("Rest", 0.0);
				break;	
			case "Serpent":
				data.put("Ca", 0.0);
				data.put("FoodInSystem", 100.0);
				data.put("Energy", 100.0);
				data.put("Rest", 0.0);
				break;
			case "Rat":
				data.put("foodGiver", Math.random() - 0.5);
				data.put("FoodInSystem", 20.0);
				data.put("Energy", 20.0);
				data.put("TSLR", 0.0);
				data.put("Rest", 0.0);
				break;
			default:
				break;
		}
	}
	
	/**
	 * 
	 * @param t
	 * @param ol
	 * @return
	 */
	public String nextTask(Terrain t, ArrayList<Organisme> ol) {
		// Actions ne pouvant être stoppées que par la mort ou leur terminaison : reproduction et mue

		// Assurer la survie de l'agent ; soit répondre aux besoins en énergie & repos

		// Même système pour tous les organismes, valeurs modifiées pour le serpent
		// Économie de l'énergie & de la nourriture
		// 0.03 d'énergie dépensée / frame si agissant ; 0.01 si se reposant      0.003 ; 0.001 pour le serpent
		// 1.5 d'énergie / nourriture ; obtenue a un rythme de 0.15/f si au repos ou 0.06/f si en action
		// Taille du terrain : 20 x 20
		// Distance moyenne depuis un côté : 20
		// Distance moyenne d'un aller-retour : 40
		// Vitesse de déplacement : 3 / s -> .125 / f
		//
		// => Condition de recherche de nourriture : energie + nourriture * 1.5 < 9.6 * 1.5 => energie + 1.5 * nourriture < 14.4
		// Condition d'arrêt de la recherche de nourriture : energie + nourriture * 1.5 > 43.2

		// Repos :
		// en action : dépense de 0.01 repos / frame      0.03 pour le serpent
		// au repos : gain de 0.03 repos / frame          0.015 pour le serpent
		// Condition de repos : repos < 4.8
		// Condition d'arrêt du repos : repos > 14.4

		// La priorité d'un besoin vital est 1 - valeur/max
		// Elle sert à déterminer la priorité entre deux actions urgentes

		if (data.containsKey("reproduceTimeElapsed")) return "reproduce";
		if (data.containsKey("moultTimeElapsed")) return "moult";

		double potEnergy = data.get("Energy") + data.get("FoodInSystem") * 1.5;

		boolean rest_u = data.get("Rest") < 4.8;
		boolean food_u = potEnergy < 14.4;

		double rest_p = 1 - data.get("Rest") / 14.4;
		double food_p = 1 - potEnergy / 43.2;

		String priority = (rest_u || food_u) ? (food_p >= rest_p ? "food" : "rest") : "other";

		if (priority == "other") {
			boolean actionDone = ((currentTask != "rest" && currentTask != "restFindSpot") || data.get("Rest") > 14.4) &&
										((currentTask != "locateFood" || currentTask != "getToFood") || potEnergy > 43.2);
			if (!actionDone) {
				priority = (currentTask == "rest" || currentTask == "restFindSpot") ? "rest" : "food";
			}
		}

		switch (priority) {
			case "rest":
				return canRest(t.getCase((int) y, (int) x), ol) ? "rest" : "restFindSpot";
			case "food":
				if (!(data.containsKey("targetXGatherFood"))) {
					return "locateFood";
				} else {
					return "getToFood";
				}
			case "other":
				switch (type) {
					case "Serpent":
					case "Isopode":
						if (data.get("Age") < 720 || type == "Serpent") { // Test redondant, mais solution plus élégante que la précédente
							boolean canMoult = data.get("Ca") > data.get("Age") / 72;
							return canMoult ? "moult" : (data.containsKey("targetXGatherCa") ? "getToCa" : "locateCa");
						}
					case "Rat":
					case "Collembole":
						if (data.containsKey("TSLR")) {
							if (data.get("TSLR") < 720) {
								return data.get("foodGiver") >  0 ? "shareFood" : (potEnergy > 64.8 ? "reproduce" : "reproduceGatherEnergy");
							}
						}
						break;
				}
				break;
			default:
				break;
		}
		return "rest";
	}

	/**
	 * 
	 * @param t
	 * @param casesLibres
	 * @param ol
	 */
	public void act(Terrain t, ArrayList<Organisme> ol) {
		switch (currentTask) {
			case "locateFood":
				int d;
				// si : target
				if (data.contains("targetXLocateFood")) {
				// 	-> avancer vers la target
						x += 0.125 * (data.get("targetXLocateFood") - x > 0 ? 1 : -1);
						y += 0.125 * (data.get("targetYLocateFood") - y > 0 ? 1 : -1);
						d = (int) distance(data.get("targetXLocateFood"), data.get("targetYLocateFood"));
						// Si target atteinte : nouvelle target
						if (d == 0) {
							data.put("targetXLocateFood", (int) (x + (Math.random * 10 - 5) + 20) % 20);
							data.put("targetYLocateFood", (int) (y + (Math.random * 10 - 5) + 20) % 20);
						}
					}
				// si : pas target
				} else {
				// 	-> créer target
					data.put("targetXLocateFood", (int) (x + (Math.random * 10 - 5) + 20) % 20);
					data.put("targetYLocateFood", (int) (y + (Math.random * 10 - 5) + 20) % 20);
					d = 39;
				}
				// 	-> check tout autour de soi ; rayon de 5 cases
				for (int i = (int) x - 5; i <= (int) x + 5; i++) {
					for (int j = (int) y - 5; j <= (int) y + 5; i++) {
						if ((t.getCase(j, i).count("Champignons") > 5 || t.getCase(j, i).count("Moisissure") > 0) && distance(i, j) < d) {
							if (data.containsKey("targetXLocateFood")) {
								data.remove("targetXLocateFood");
								data.remove("targetYLocateFood");
							}
							data.put("targetXGatherFood", i);
							data.put("targetYGatherFood", j);
							d = (int) distance(i, j);
						}
					}
				}
				break;
			case "getToFood":
				x += 0.125 * (data.get("targetXGatherFood") - x > 0 ? 1 : -1);
				y += 0.125 * (data.get("targetYGatherFood") - y > 0 ? 1 : -1);
				d = (int) distance(data.get("targetXGatherFood"), data.get("targetYGatherFood"));
				for (int i = (int) x - 5; i <= (int) x + 5; i++) {
					for (int j = (int) y - 5; j <= (int) y + 5; i++) {
						if ((t.getCase(j, i).count("Champignons") > 5 || t.getCase(j, i).count("Moisissure") > 0) && distance(i, j) < d) {
							data.put("targetXGatherFood", i);
							data.put("targetYGatherFood", j);
							d = (int) distance(i, j);
						}
					}
				}
				break;
			case "reproduceGatherEnergy":
			case "rest":
				data.merge("Energy", (data.get("FoodInSystem") > 0.1 ? δf : 0.0 ) - δe, (v, δ) -> v + δ); // MàJ stat energie
				data.merge("FoodInSystem", (data.get("FoodInSystem") > 0.1 ? - 0.1 : 0.0 ), (v, δ) -> v + δ); // MàJ stat nourriture
				data.merge("Rest", δr, (v, δ) -> v + δ); // MàJ stat repos
				break;
			case "restFindSpot":
				break;
			case "shareFood":
				for (Organisme o : ol) {
					if (o.currentTask == "reproduceGatherEnergy" && o.data.get("FoodInSystem") * 1.5 + o.data.get("Energy") < 64.8) {
						o.data.merge("FoodInSystem", 9.6, (v, d) -> v + d);
						data.merge("FoodInSystem", -9.6, (v, d) -> v + d);
						break;
					}
				}
				break;
			case "reproduce":
				if (!data.containsKey("reproduceTimeElapsed")) {
					data.put("reproduceTimeElapsed", 0.0);
				} else if (data.get("reproduceTimeElapsed") < 480) { data.merge("reproduceTimeElapsed", 1.0, (v, d) -> v + d);
				} else {
					data.remove("reproduceTimeElapsed");
					Organisme no = new Organisme(type);
					no.seDeplacer((int) x, (int) y);
					ol.add(no);
				}
				data.merge("Energy", (data.get("FoodInSystem") > .1 ? δf : 0.0 ) - Δe, (v, Δ) -> v + Δ); // MàJ stat energie
				data.merge("FoodInSystem", (data.get("FoodInSystem") > 0.04 ? - 0.1 : 0.0 ), (v, Δ) -> v + Δ); // MàJ stat nourriture
				data.merge("Rest", Δr, (v, Δ) -> v + Δ); // MàJ stat repos
				break;
			case "locateCa":
				int d;
				// si : target
				if (data.contains("targetXLocateCa")) {
				// 	-> avancer vers la target
						x += 0.125 * (data.get("targetXLocateCa") - x > 0 ? 1 : -1);
						y += 0.125 * (data.get("targetYLocateCa") - y > 0 ? 1 : -1);
						d = (int) distance(data.get("targetXLocateCa"), data.get("targetYLocateCa"));
						// Si target atteinte : nouvelle target
						if (d == 0) {
							data.put("targetXLocateCa", (int) (x + (Math.random * 10 - 5) + 20) % 20);
							data.put("targetYLocateCa", (int) (y + (Math.random * 10 - 5) + 20) % 20);
						}
					}
				// si : pas target
				} else {
				// 	-> créer target
					data.put("targetXLocateCa", (int) (x + (Math.random * 10 - 5) + 20) % 20);
					data.put("targetYLocateCa", (int) (y + (Math.random * 10 - 5) + 20) % 20);
					d = 39;
				}
				// 	-> check tout autour de soi ; rayon de 5 cases
				for (int i = (int) x - 5; i <= (int) x + 5; i++) {
					for (int j = (int) y - 5; j <= (int) y + 5; i++) {
						if (t.getCase(j, i).count("Ca") > 0 && distance(i, j) < d) {
							if (data.containsKey("targetXLocateCa")) {
								data.remove("targetXLocateCa");
								data.remove("targetYLocateCa");
							}
							data.put("targetXGatherCa", i);
							data.put("targetYGatherCa", j);
							d = (int) distance(i, j);
						}
					}
				}
				break;
				break; // TODO
			case "getToCa":
				break; // TODO
			case "moult":
				break; // TODO
			default:
				break;
		}
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param ol
	 * @return
	 */
	public boolean caseLibre(int x, int y, ArrayList<Organisme> ol) {
		int n = 0;
		for (Organisme o : ol) {
			if (o.distance(x, y) < 1) {
				if (o.currentTask == "rest" || o.currentTask == "moult" || o.currentTask == "reproduce") {
					if (o.type == "Collembole" || o.type == "Isopode") {
						n++;
					} else { return false; }
				}
			}
		}
		return n <= 5;
	}

	/**
	 * 
	 * @param r
	 * @param ol
	 * @return
	 */
	public boolean canRest(Ressource r, ArrayList<Organisme> ol) {
		switch (type) {
			case "Collembole":
				if (r.type != "Charbon") { return false; }
			case "Isopode":
				if (r.type != "Decor" && type == "Isopode") { return false; }
			default:
				return caseLibre(r.getX(), r.getY(), ol);
		}
	}
}
