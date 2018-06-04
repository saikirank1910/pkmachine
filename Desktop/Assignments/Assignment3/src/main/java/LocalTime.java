import org.joda.time.DateTime;
import org.joda.time.JodaTimePermission;

public class LocalTime {
	public static void main(String[] args) {
	    DateTime dt = new DateTime();
	    System.out.print(dt.getHourOfDay());
	    System.out.println(":" +dt.getMinuteOfHour());
	}
}
