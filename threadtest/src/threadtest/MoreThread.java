package threadtest;

public class MoreThread{

	public static void main(String[] args) {
		ProducerAndCustomer pac=new ProducerAndCustomer();
		pac.producer("Producer A");
		pac.producer("Producer B");
		pac.producer("Producer C");
		pac.customer("Customer 1");
		pac.customer("Customer 2");
		pac.customer("Customer 3");
		pac.customer("Customer 4");
	}
}
