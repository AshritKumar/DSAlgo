package practice;

public class DateCalcUtil {
	
	static int months[] = {0, 31,28, 31, 30, 31, 30, 31, 31, 30, 31, 30 ,31};
	
	public static int calcDateDiff(int[] date1, int[] date2) {
		int d1 = date1[0];
		int m1 = date1[1];
		int y1 = date1[2];
		
		int d2 = date2[0];
		int m2 = date2[1];
		int y2 = date2[2];
		
		int daysTillMnt1End = getDaysTillMonthEnd(m1, d1, y1);
		//System.out.println("Mont 1 end days "+ daysTillMnt1End);
		if(y1 == y2) {
			if(m1 == m2)
				return d2-d1;
			int daysTillEndMonth2 = getDaysTillEndMonth(m1, m2 ,y2)+d2;
			return daysTillMnt1End + daysTillEndMonth2;
		}
		// get days till year1 November from month after m1
		int daysTillYr1End = getDaysTillEndMonth(m1, 13, y1 );
		//System.out.println("Days till yr1 end "+daysTillYr1End);
		//get days b/w y1 and y2
		int daysTillYr2 = getDaysBtwYears(y1, y2);
		//System.out.println("Days b/w y1 and y2 "+daysTillYr2);
		// get days from jan 1st y2 to m2
		int daysTillM2 = getDaysTillEndMonth(0, m2, y2)+d2;
		//System.out.println("days till m2 = "+daysTillM2);
		
		return daysTillMnt1End + daysTillYr1End + daysTillYr2 + daysTillM2;
	}
	
	private static int getDaysBtwYears(int y1, int y2) {
		int days = 0;
		for(int i=y1+1; i<y2; i++) {
			days += isLeapYear(i) ? 366 :365 ;
		}
		return days;
	}

	private static int getDaysTillEndMonth(int m1,int m2, int y2) {
		int days = 0;
		for(int i=m1+1; i<m2; i++) {
			days += getDaysInMonth(i , y2);
		}
		return days;
	}

	private static int getDaysInMonth(int mnt, int yr) {
		if(isLeapYear(yr) && mnt == 2)
			return 29;
		return months[mnt];
	}

	private static int getDaysTillMonthEnd(int mnt, int day, int yr) {
		// exclude current day
		int days = months[mnt] - day;
		if(isLeapYear(yr) && mnt==2) {
			days++;
		}
		return days;
	}

	private static boolean isLeapYear(int yr) {
		if(yr % 400 == 0)
			return true;
		else if(yr % 100 == 0)
			return false;
		else if(yr % 4 == 0)
			return true;			
		return false;
	}
	
	public static int calculateDateDiff(int[] date1, int[] date2) {
		if(date1[2] > date2[2] || (date1[2] == date2[2] && date1[1] > date2[1]) || (date1[2] == date2[2] && date1[1] == date2[1] && date1[0] > date2[0]))
			return calcDateDiff(date2, date1);
		return calcDateDiff(date1, date2);
	}

	public static void main(String[] args) {
		String dt1 = "13-6-2018";
		String dt2 = "12-6-2000";
		String dt11 [] = dt1.split("-");
		String dt22[] = dt2.split("-");
		int[] date1 = new int[3];
		int[] date2 = new int[3];
		for(int i=0; i<3; i++) {
			date1[i] = Integer.parseInt(dt11[i].trim());
			date2[i] = Integer.parseInt(dt22[i].trim());
		}
		
		int days = calculateDateDiff(date1, date2);
		System.out.println(days);
	}

}
