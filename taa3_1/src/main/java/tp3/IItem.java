package tp3;

public interface IItem {
	String getID();
	
	float price();
	
	int quantity();
	
	void supply(int amount);
	
	void withdraw(int amount);
}
