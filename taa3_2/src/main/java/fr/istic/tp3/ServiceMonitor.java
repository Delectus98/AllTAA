package fr.istic.tp3;

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

	@Before("execution(* fr.istic.rest.tp2.*.*(..)) ")
	public void logServiceAccess(JoinPoint joinPoint) {
		System.out.print(joinPoint.getTarget().getClass().getName());

		System.out.print("#" + joinPoint.getSignature().getName());

		String argsString = " With args: ";
		for (Object a : joinPoint.getArgs()) {
			argsString += a.toString() + " ";
		}
		System.out.println(argsString);
	}

}
