package threadtest;

public class finalint  {
	
	private final static  int i=0;
	
	static {
		System.out.println(i);
	}
	
	{
		System.out.println("<>");
	}
	
	public static void main(String[] args) {
		System.out.println("hello");
	}
}
