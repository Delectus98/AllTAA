package tp3;

public interface IProvider {
	float getPrice(String item);
	
	boolean isAvailable(String item);
	
	IItem get(String item);
	
	boolean order(String item, int amount);
}
