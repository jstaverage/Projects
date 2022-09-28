package classes;

public class Test {
	private static String convertTime(int inputTime)
	{
		String result = "";
		int secs = 0;
		int mins = 0;
		int hsecs = 0;
		
		mins = (int) inputTime/6000;
		inputTime -= (mins*6000);
		
		secs = (int) inputTime/100;
		inputTime -= (secs * 100);
		
		hsecs = inputTime;
		
		if(mins < 10)
		{
			result += "0" + String.valueOf(mins) + ":";
		}
		else
		{
			result += String.valueOf(mins) + ":";
		}
		if(secs < 10)
		{
			result += "0" + String.valueOf(secs) + ".";
		}
		else
		{
			result += String.valueOf(secs) + ".";
		}
		if(hsecs < 10)
		{
			result += "0" + String.valueOf(hsecs);
		}
		else
		{
			result += String.valueOf(hsecs);
		}
		return result;
	}//end method
	public static void main(String[] args) {
		
		System.out.println(convertTime(5755));

	}

}
