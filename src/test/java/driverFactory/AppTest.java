package driverFactory;

import org.testng.annotations.Test;

public class AppTest {
	@Test
	public void startTest()
	{
		try
		{
			DriverScript ds=new DriverScript();
			ds.erp_Account();
		}
		catch(Throwable t)
		{
			System.out.println((t.getMessage()));
		}
	}

}
