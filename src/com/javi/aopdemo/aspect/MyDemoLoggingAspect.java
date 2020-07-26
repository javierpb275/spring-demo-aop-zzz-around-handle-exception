package com.javi.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Around;

import com.javi.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Around("execution(*com.javi.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(
			ProceedingJoinPoint theProceedingJoinPoint)  throws Throwable {
		
		
		// print out method we are advising on
		String method = theProceedingJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>>> Executing @Around advice on method: " + method);
		
		// get begin timestamp
		long begin = System.currentTimeMillis();
		
		// execute method
		Object result = theProceedingJoinPoint.proceed();//theProceedingJoinPonit: Handle to target method. .proceed(): execute the target method
		
		// get end timestamp
		long end = System.currentTimeMillis();
		
		// compute duration and display it
		long duration = end - begin;
		myLogger.info("\n=====>>>> Duration: " + duration / 1000.0 + " seconds");
		 	
			return result;
			
		}
	
	
	/*
	// After advice:
	
	@After("execution(*com.javi.aopdemo.dao.AccountDAO.findAccounts(..))")//@After will run for success or failure (finally)
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
		
		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>>> Executing @After (finally) advice on method: " + method);
		
	}
	
	// AfterThrowing advice:
	@AfterThrowing(
			pointcut="execution(*com.javi.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing="theExc")//parameter name for exception
	public void afterThrowingFindAccountsAdvice(
			JoinPoint theJoinPoint, Throwable theExc) {
		
		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>>> Executing @AfterThrowing advice on method: " + method);
		
		// log the exception
		myLogger.info("\n=====>>>> The exception is: " + theExc);
		
	}
	
	// AfterReturning advice:
	
	@AfterReturning(
			pointcut="execution(*com.javi.aopdemo.dao.AccountDAO.findAccounts(..))", //match on AccountDAO.findAccounts(..)
			returning="result")//parameter name for return value
	public void afterReturningFindAccountsAdvice(
			JoinPoint theJoinPoint, List<Account> result) {
		
		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>>> Executing @AfterReturning advice on method: " + method);
		
		// print out the results of the method call
		myLogger.info("\n=====>>>> result is: " + result);
		
		// let's post-process the data ... let's modify it:
		
		// convert the account names to uppercase
		convertAccountNamesToUpperCase(result);
		
		// print out the result
		myLogger.info("\n=====>>>> result is: " + result);
	}
	*/
	
	private void convertAccountNamesToUpperCase(List<Account> result) {
		
		// loop through accounts
		
		for (Account tempAccount : result) {
			
			// get uppercase version of name
			String theUpperName = tempAccount.getName().toUpperCase();
			
			// update the name on the account
			tempAccount.setName(theUpperName);
			
		}
		
		
	}


	@Before("com.javi.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")//added fully qualified class name
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		myLogger.info("\n=====>>>> Executing @Before advice on method");
		
		// display the method signature
		MethodSignature methodSig = (MethodSignature)  theJoinPoint.getSignature();
		
		myLogger.info("Method: " + methodSig);
		
		// display method arguments
		
		// get args
		Object[] args = theJoinPoint.getArgs();
		
		// loop thru args
		for (Object tempArg : args) {
			myLogger.info(tempArg.toString());
			
			if (tempArg instanceof Account) {
				
				// downcast and print Account specific stuff
				Account theAccount = (Account) tempArg;
				
				myLogger.info("account name: " + theAccount.getName());
				myLogger.info("account level: " + theAccount.getLevel());
				
			}
			
		}
		
		
	}
	

}
