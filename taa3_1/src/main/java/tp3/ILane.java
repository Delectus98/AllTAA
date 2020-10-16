package tp3;

public interface ILane extends IStore {
	boolean addItemToCart(String item);
	
	boolean pay(String iban);
}
