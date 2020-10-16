package tp3;

public interface IFastLane extends IStore {
	boolean oneShotOrder(String item, int amount, String iban);
}
