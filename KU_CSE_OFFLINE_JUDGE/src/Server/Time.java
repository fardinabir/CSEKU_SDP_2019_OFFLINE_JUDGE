package Server;

import java.util.Calendar;
import java.util.Date;

public class Time {
	public static Date ds,dc;
	public static void set()
	{
		Calendar cal = Calendar.getInstance();
		ds = cal.getTime();
	}
	public static int Minutes()
	{
		Calendar cal = Calendar.getInstance();
		dc = cal.getTime();
		int x;
		try {
			x = ((dc.getHours()*60)+dc.getMinutes())-((ds.getHours()*60)+ds.getMinutes());
		}
		catch(Exception e)
		{
			x = 10;
		}
		return x;
	}

}
