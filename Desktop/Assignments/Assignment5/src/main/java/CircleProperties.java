import com.prokarma.Circle;
import java.util.*;
public class CircleProperties{
	public static void main(String args[]){
		System.out.println("Enter the radius to find area and circumference:");
		Scanner read = new Scanner(System.in);
		double radius = read.nextDouble();
		
		Circle get = new Circle(radius);
		Double areaOfCircle,circumferenceOfCircle;
		areaOfCircle = get.getArea();
		circumferenceOfCircle = get.getCircumference();
		System.out.println("Area of circle is: "+areaOfCircle);
		System.out.println("Circumference Of Circle is: "+circumferenceOfCircle);
	}
}