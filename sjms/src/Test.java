import gcmo.car;
import gcmo.carFactory;

public class Test {

	public static void main(String[] args) {
		carFactory car=new carFactory();
		gcmo.car car2 = car.createCar("±¼³Û");
		System.out.println(car2);
		
	}
}
