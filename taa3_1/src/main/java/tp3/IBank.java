package tp3;

public interface IBank {
	boolean exists(String iban);
	
	float getMoneyAmount(String iban);
	
	boolean transfert(String account, String beneficiary, float amount);
}
