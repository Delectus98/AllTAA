package tp3;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class Bank implements IBank {
	private List<IAccount> accounts;
	/*@Autowired
	private IDesk desk;*/
	@Value("${iban}")
	private String clientIban;
	@Value("${store-iban}")
	private String storeIban;
	
	@PostConstruct
    private void postRandomConstruct() {
		accounts = new LinkedList<>();
		accounts.add(new Account(500.f, clientIban)); // CLIENT
		accounts.add(new Account(0.f, storeIban)); // STORE
    }
	
	@Override
	public boolean transfert(String account, String beneficiary, float amount) {
		
		Optional<IAccount> o0 = accounts.stream().filter((a0) -> a0.getIBAN().equals(account)).findFirst();
		Optional<IAccount> o1 = accounts.stream().filter((a0) -> a0.getIBAN().equals(beneficiary)).findFirst();
		//if (!o0.isEmpty() && !o1.isEmpty())
		{
			IAccount a0 = o0.get();
			IAccount a1 = o1.get();
			System.out.println(a0.getMoneyAmount() + " - " + amount + ">" + a1.getMoneyAmount() + " + " + amount);

			a0.withdraw(amount);
			a1.save(amount);
			System.out.println(a0.getMoneyAmount() + ">" + a1.getMoneyAmount());
			return true;
		}
		
		//return false;
	}

	@Override
	public boolean exists(String iban) {
		return accounts.stream().filter((a0) -> a0.getIBAN().equals(iban)).findFirst().isPresent();
	}

	@Override
	public float getMoneyAmount(String iban) {
		Optional<IAccount> o0 = accounts.stream().filter((a0) -> a0.getIBAN().equals(iban)).findFirst();
		return o0.get().getMoneyAmount();
	}

}
