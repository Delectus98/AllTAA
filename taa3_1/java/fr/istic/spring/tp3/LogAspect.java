package fr.istic.spring.tp3;

@Aspect
public class LogAspect {
    @Before("//PointCut expression")
    //@AfterReturning("//PointCut expression")
    public void log(JoinPoint pjp){
        System.out.println("log ");        
    }
}
