package tp3;

public interface IAccount {
	String getIBAN();
	
	float getMoneyAmount();
	
	float withdraw(float amount);
	
	void save(float amount);
}
