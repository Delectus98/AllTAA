package tp3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//Represente une quantité d'item
public class ItemA implements IItem {
	//private static final String ID = "012345689ABCDEF";

	private int count = 50;
	private String id = "itemA";
	private float price = 10.0f;

	@Override
	public String getID() {
		return id;
	}

	@Override
	public int quantity() {
		return count;
	}

	@Override
	public void supply(int amount) {
		count += amount;
	}

	@Override
	public void withdraw(int amount) {
		count -= amount;
	}

	@Override
	public float price() {
		return price;
	}

}
