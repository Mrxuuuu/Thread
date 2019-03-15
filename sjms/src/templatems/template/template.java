package templatems.template;

public abstract class template {

	public void process(){
		//首先调用
		first();
		//中间调用
		middle();
		//最后调用
		last();
	}

	private void first() {
		// TODO Auto-generated method stub
		System.out.println("开始");
	}

	private void last() {
		// TODO Auto-generated method stub
		System.out.println("结束");
	}

	abstract void middle();
}
