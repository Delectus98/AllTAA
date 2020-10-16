package fr.istic.spring.tp3;

public interface IJustHaveALook extends IStore {
	int getPrice(String article);
	
	boolean isAvailable(String item);
}
