import java.util.*;

public class Organisme extends Agent{
	protected String currentTask;
	// Tasks : locateFood, getToFood, rest, [shareFood, reproduceGatherEnergy, reproduce, locateCa, getToCa]
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

	public String nextTask() { return "rest"; }
	public void act(Terrain t) {
		switch (currentTask) {
			default:
				break;
		}
	}
}
