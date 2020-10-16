package tp3;

public interface IJustHaveALook extends IStore {
	float getPrice(String article);
	
	boolean isAvailable(String item);
}
