package fr.istic.spring.tp3;

public interface IProvider {
	int getPrice(String item);
	
	void order(String item, int amount);
}
