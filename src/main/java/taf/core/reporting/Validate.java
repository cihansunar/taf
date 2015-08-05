package taf.core.reporting;

import taf.core.exceptions.ExpectedValueDifferentException;
import taf.core.exceptions.ExpectedValueIsNotChanged;

public class Validate 
{
	public static void isTrue(boolean condition, String message) throws ExpectedValueDifferentException
	{
		if(condition)
			TestReporter.log(LogType.SUCCESS, message);
		else
		{
			TestReporter.log(LogType.FAILURE, message);
			throw new ExpectedValueDifferentException(message);
		}
	}
	public static void isFalse(boolean condition, String message) throws ExpectedValueDifferentException
	{
		if(!condition)
			TestReporter.log(LogType.SUCCESS, message);
		else
		{
			TestReporter.log(LogType.FAILURE, message);
			throw new ExpectedValueDifferentException(message);
		}
	}
	
	public static void equals(String expectedValue, String currentValue, String message) throws ExpectedValueDifferentException
	{
		System.out.println(currentValue);
		if(expectedValue.equals(currentValue))
			TestReporter.log(LogType.SUCCESS, message);
		else
		{
			TestReporter.log(LogType.FAILURE, message);
			throw new ExpectedValueDifferentException(message);
		}
	}
	
	public static void notEqual(String expectedValue, String currentValue, String message) throws ExpectedValueIsNotChanged
	{
		if(!expectedValue.equals(currentValue))
			TestReporter.log(LogType.SUCCESS, message);
		else
		{
			TestReporter.log(LogType.FAILURE, message);
			throw new ExpectedValueIsNotChanged(message);
		}
	}

}
