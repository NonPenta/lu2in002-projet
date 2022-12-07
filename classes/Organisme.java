import java.util.*;

public class Organisme extends Agent{
	protected String currentTask;
	// Tasks : locateFood, getToFood, rest, [rest_findSpot, shareFood, reproduceGatherEnergy, reproduce, locateCa, getToCa]
	protected HashMap<String, Double> data;
	// type : data
	// Collemboles : {"FoodInSystem" : double, "Energy" : double, "TSLR" : double, "Rest" : double}
	// Isopodes : {"Ca" : double, "FoodInSystem" : double, "Energy" : double, "TSLR" : double, "Rest" : double}
	// Serpents : {"Ca" : double, "FoodInSystem" : double, "Energy" : double, "Rest" : double}
	// Rats : {"FoodInSystem" : double, "Energy" : double, "TSLR" : double, "Rest" : double}
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

	public String nextTask() {
		// Assurer la survie de l'agent ; soit répondre aux besoins en énergie & repos

		// Même système pour tous les organismes, valeurs modifiées pour le serpent
		// Économie de l'énergie & de la nourriture
		// 0.03 d'énergie dépensée / frame si agissant ; 0.01 si se reposant
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
		// Condition d'arrêt de la recherche de nourriture : energie > 43.2 && nourriture > 28.8

		// Repos :
		// en action : dépense de 0.01 repos / frame
		// au repos : gain de 0.03 repos / frame
		// coût en repos d'un aller-retour : 320 * .01 = 3.2
		// Condition de repos : repos < 4.8
		// Condition d'arrêt du repos : repos > 9.6 ou autre besoin vital
		switch (type) {
			case "Isopode":
			case "Collembole":
		}
		return "rest";
	}

	public void act(Terrain t) {
		switch (currentTask) {
			default:
				break;
		}
	}
}
