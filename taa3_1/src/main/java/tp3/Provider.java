package tp3;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class Provider implements IProvider {
	private List<IItem> items;
	
	@PostConstruct
    private void postRandomConstruct() {
		items = new LinkedList<>();
		items.add(new ItemA()); // CLIENT
    }
	
	@Override
	public float getPrice(String item) {
		Optional<IItem> o1 = items.stream().filter((i0) -> i0.getID().equals(item)).findFirst();
		
		if (!o1.isEmpty())
		{
			IItem i1 = o1.get();
			return i1.price();
		}
		//exception
		return 0;
	}

	@Override
	public boolean order(String item, int amount) {
		Optional<IItem> o1 = items.stream().filter((i0) -> i0.getID().equals(item)).findFirst();
		
		if (!o1.isEmpty())
		{
			IItem i1 = o1.get();
			if (i1.quantity() >= amount)
			{
				i1.withdraw(amount);
				
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isAvailable(String item) {
		Optional<IItem> o1 = items.stream().filter((i0) -> i0.getID().equals(item)).findFirst();
		return o1.isPresent();
	}

	@Override
	public IItem get(String item) {
		Optional<IItem> o1 = items.stream().filter((i0) -> i0.getID().equals(item)).findFirst();
		return o1.get();
	}
	
}
