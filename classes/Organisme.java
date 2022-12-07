import java.util.*;

public class Organisme extends Agent{
	protected String currentTask;
	// Tasks : locateFood, getToFood, rest, [restFindSpot, shareFood, reproduceGatherEnergy, reproduce, locateCa, getToCa, moult]
	protected HashMap<String, Double> data;
	// type : data
	// Collemboles : {"FoodInSystem" : double, "Energy" : double, "TSLR" : double, "Rest" : double, "Age" : double}
	// Isopodes : {"Ca" : double, "FoodInSystem" : double, "Energy" : double, "TSLR" : double, "Rest" : double, "Age" : double}
	// Serpents : {"Ca" : double, "FoodInSystem" : double, "Energy" : double, "Rest" : double, "Age" : double}
	// Rats : {"FoodInSystem" : double, "Energy" : double, "TSLR" : double, "Rest" : double, "Age" : double}

	// targetXLocate, targetYLocate, targetXGather, targetYGather seront ajoutés & enlevés par la fonction de choix d'action et d'action des organismes

	public Organisme(String type) {
		super(type);
		currentTask = "rest";
		data = new HashMap<>();
		data.put("Age", 0.0);
		switch (type) {
			case "Isopode":
				data.put("Ca", 5.0);
			case "Collembole":
				data.put("FoodInSystem", 10.0);
				data.put("Energy", 10.0);
				data.put("TSLR", 0.0);
				data.put("Rest", 0.0);
				break;
			case "Serpent":
				data.put("Ca", 50.0);
				data.put("FoodInSystem", 100.0);
				data.put("Energy", 100.0);
				data.put("Rest", 0.0);
				break;
			case "Rat":
				data.put("FoodInSystem", 20.0);
				data.put("Energy", 20.0);
				data.put("TSLR", 0.0);
				data.put("Rest", 0.0);
				break;
			default:
				break;
		}
	}

	public String nextTask(Terrain t, ArrayList<Organisme> ol) {
		// Assurer la survie de l'agent ; soit répondre aux besoins en énergie & repos

		// Même système pour tous les organismes, valeurs modifiées pour le serpent
		// Économie de l'énergie & de la nourriture
		// 0.03 d'énergie dépensée / frame si agissant ; 0.01 si se reposant      0.003 ; 0.001 pour le serpent
		// 1.5 d'énergie / nourriture ; obtenue a un rythme de 0.15/f si au repos ou 0.06/f si en action
		// Taille du terrain : 20 x 20
		// Distance moyenne depuis un côté : 20
		// Distance moyenne d'un aller-retour : 40
		// Vitesse de déplacement : 3 / s -> .125 / f
		// Energie moyenne consommée par un aller-retour : 40 * 8 * 0.03 = 9.6
		// Energie moyenne obtenue d'un aller-retour : 3 AR = 3 * 9.6 = 28.8
		// Nourriture moyenne obtenue d'un aller-retour : 28.8 / 1.5 = 19.2
		//
		// Condition de survie : tjs > un aller-retour en réserve
		// => Condition de recherche de nourriture : energie + nourriture * 1.5 < 9.6 * 1.5 => energie + 1.5 * nourriture < 14.4
		// Condition d'arrêt de la recherche de nourriture : energie + nourriture * 1.5 > 43.2

		// Repos :
		// en action : dépense de 0.01 repos / frame      0.03 pour le serpent
		// au repos : gain de 0.03 repos / frame          0.015 pour le serpent
		// coût en repos d'un aller-retour : 320 * .01 = 3.2
		// Condition de repos : repos < 4.8
		// Condition d'arrêt du repos : repos > 14.4

		// La priorité d'un besoin vital est 1 - valeur/max
		// Elle sert à déterminer la priorité entre deux actions urgentes
		double potEnergy = data.get("Energy") + data.get("FoodInSystem") * 1.5;

		boolean rest_u = data.get("Rest") < 4.8;
		boolean food_u = potEnergy < 14.4;

		double rest_p = 1 - data.get("Rest") / 14.4;
		double food_p = 1 - potEnergy / 43.2;

		String priority = (rest_u || food_u) ? (food_p >= rest_p ? "food" : "rest") : "other";

		if (priority == "other") {
			boolean actionDone = ((currentTask == "rest" || currentTask == "restFindSpot") && data.get("Rest") > 14.4) ||
										((currentTask == "locateFood" || currentTask == "getToFood") && potEnergy > 43.2);
			if (!actionDone) {
				priority = (currentTask == "rest" || currentTask == "restFindSpot") ? "rest" : "food";
			}
		}

		switch (priority) {
			case "rest":
				return canRest(t.getCase(y, x), ol) ? "rest" : "restFindSpot";
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
						// TODO : reproduction
						break;
				}
				break;
			default:
				break;
		}
		return "";
	}

	public void act(Terrain t) {
		switch (currentTask) {
			default:
				break;
		}
	}

	public boolean canRest(Ressource r, ArrayList<Organisme> ol) {
		return true; // TODO
	}
}
