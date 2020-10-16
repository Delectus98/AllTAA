package tp3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Client implements IRun, IClient {
	@Autowired
	private IJustHaveALook s0;
	@Autowired
	private IFastLane s1;
	@Autowired
	private ILane s2;
	@Value("${iban}")
	private String iban;
	
	// client behaviour
	@Override
	public void run() {
		String itemID = "itemA";
		if (s0.isAvailable(itemID))
		{
			float price = s0.getPrice(itemID);
			
			if (price < 50.0f)
			{
				if (s1.oneShotOrder(itemID, 10, iban))
				{
					System.out.println("SUCESS BUYING " + itemID);
				}
				else
				{
					System.out.println("FAIL BUYING " + itemID);
				}
			}
			
			boolean state = 
					s2.addItemToCart(itemID) &&
					s2.addItemToCart(itemID) &&
					s2.addItemToCart(itemID);
			if (!state)
			{
				System.out.println("WARNING MISSING ITEM " + itemID);
			}
			else if (s2.pay(iban))
			{
				System.out.println("SUCESS BUYING " + itemID);
			}
		}
		
	}

}
