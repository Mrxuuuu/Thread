package spqms;

public class adapter implements JP_110VInterface{

	/**
	 * 定义适配器实现日本电压的接口
	 * 通过依赖注入中国电压接口
	 * 在日本电压的接口实现中调用中国电压接口的方法
	 * 实现适配器转换
	 * 
	 */
	private CN_220VInterface CN_220VInterface;
	
	public adapter(CN_220VInterface CN_220VInterface)
	{
		this.CN_220VInterface=CN_220VInterface;
	}
	
	
	@Override
	public void connect() {
		// TODO Auto-generated method stub
		CN_220VInterface.connect();
	}

	
}
