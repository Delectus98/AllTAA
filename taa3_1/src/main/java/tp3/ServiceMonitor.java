package tp3;

import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceMonitor {

	@Before("execution(* tp3.*.*(..)) ")
	public void logServiceAccess(JoinPoint joinPoint) {
		System.out.print(joinPoint.getTarget().getClass().getName());

		System.out.print("#" + joinPoint.getSignature().getName());

		String argsString = " With args: ";
		for (Object a : joinPoint.getArgs()) {
			argsString += a.toString() + " ";
		}
		System.out.println(argsString);
	}

	@Around("execution(* tp3.Bank.transfert(..))")
	public Object verify(ProceedingJoinPoint joinPoint) {
		
		try {

			Bank b = ((Bank) joinPoint.getTarget());
			Object[] args = joinPoint.getArgs();
			// senderId, receiverId, amount
			if (!b.exists((String) args[0])) {
				System.err.println("Client iban does not exists");
			} else if (!b.exists((String) args[1])) {
				System.err.println("Store iban does not exists");
			} else {
				float amount = b.getMoneyAmount((String) args[0]);
				float price = (Float) args[2];
				System.out.println("TRYING TO PAY " + amount + " /" + price);
				if (price > amount || amount <= 0.0 || price <= 0.0) {
					System.err.println("Trying to pay invalid amount");
					return false;
				}

				try {
					System.out.println("Transfering money...");
					return joinPoint.proceed();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
