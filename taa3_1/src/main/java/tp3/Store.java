package tp3;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Store implements IStore, ILane, IFastLane, IJustHaveALook{
	@Autowired
	public IBank bank;
	@Autowired
	public IProvider provider;
	@Value("${store-iban}")
	private String storeIban;
	
	private Map<String, Integer> Cart = new HashMap<>();
	
	@Override
	public float getPrice(String article) {
		return provider.getPrice(article);
	}
	@Override
	public boolean isAvailable(String item) {
		return provider.isAvailable(item);
	}
	@Override
	public boolean oneShotOrder(String item, int amount, String iban) {
		boolean available = isAvailable(item);
		boolean provide = provider.order(item, amount);
		if (provide) {
			System.out.println("ONE SHOT " + provide);
			return bank.transfert(iban, storeIban, provider.getPrice(item) * amount);
		}
		return available && provide;
	}
	@Override
	public boolean addItemToCart(String item) {
		boolean available = isAvailable(item);
		if (available)
		{
			/*boolean ordered = provider.order(item, 1);
			if (ordered)
			{*/
				System.out.println("ADDED");
				Cart.put(item, Cart.getOrDefault(item, 0)+1);
				return true;
			//}
		}
		return false;
	}
	@Override
	public boolean pay(String iban) {
		Optional<Float> totalPrice = Cart.entrySet().stream().map((p) -> (float)(provider.getPrice(p.getKey()) * p.getValue())).reduce((a0, a1) -> a0 + a1);
		if (totalPrice.isPresent())
		{
			for (String item : Cart.keySet())
			{
				boolean ordered = provider.order(item, Cart.get(item));
				if (!ordered) {
					System.out.println("Impossible pour le fournisseur car pas assez d'item");
					return false;
				}
			}
			System.out.println("PAYED?"+Cart.toString() + ":" + totalPrice.get());
			Cart.clear();
			return bank.transfert(iban, storeIban, totalPrice.get());
		}

		return false;
	}
}
