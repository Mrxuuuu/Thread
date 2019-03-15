package gcmo;

import gcmo.impl.bcCar;
import gcmo.impl.bmCar;

public class carFactory {

	public  car createCar(String name) {
		car car=null;
		if(name!=null)
		{
			if(name.equals("奔驰"))
			{
				car=new bcCar();
				System.out.println("生产了奔驰");
			}
			if(name.equals("宝马"))
			{
				car= new bmCar();
				System.out.println("生产了宝马");
			}
		}
		else {
			return null;
		}
		return car;
	}
}
