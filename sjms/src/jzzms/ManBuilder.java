package jzzms;

public class ManBuilder implements BuiderMan {

	//构造角色实例
	Person person=new Person();
	@Override
	public void buildHead() {
		// TODO Auto-generated method stub
		person.setHead("巨人头");
	}

	@Override
	public void buildbody() {
		// TODO Auto-generated method stub
		person.setBody("巨人身");
	}

	@Override
	public void buildfootAndhand() {
		// TODO Auto-generated method stub
		person.setFootAndhand("巨人身和头");
	}

	@Override
	public Person buildMan() {
		return person;
		// TODO Auto-generated method stub
    
	}
	
	

}
