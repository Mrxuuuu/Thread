package spqms;

public class cook{
   
	private JP_110VInterface jP_110VInterface;
	
	public cook(JP_110VInterface jP_110VInterface){
		this.jP_110VInterface=jP_110VInterface;
	}
	
	public void cook1() {
		jP_110VInterface.connect();
		System.out.println("ทนื๖บรมห...");
	}
	
	public static void main(String[] args) {
		
		CN_220VInterface CN_220VInterface=new CN_220VInterfaceImpl();
		cook cook=new cook(new adapter(CN_220VInterface));
		cook.cook1();
	}
}
