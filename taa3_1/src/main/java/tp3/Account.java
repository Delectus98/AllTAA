package tp3;

import org.springframework.beans.factory.annotation.Value;

public class Account implements IAccount {
	private float cash;
	private String iban;
	
	public Account(float cash, String iban)
	{
		System.out.println(cash);
		this.iban = iban;
		this.cash = cash;
	}
	
	@Override
	public String getIBAN() {
		return iban;
	}

	@Override
	public float getMoneyAmount() {
		return cash;
	}

	@Override
	public float withdraw(float amount) {
		return cash -= amount;
	}

	@Override
	public void save(float amount) {
		cash += amount;
	}

}
