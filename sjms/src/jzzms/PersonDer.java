package jzzms;

public class PersonDer {

	Person PerConstruct(BuiderMan per){
		//调用方法  构造角色不同部位，最后拼接返回
		per.buildbody();
		per.buildfootAndhand();
		per.buildHead();
		return per.buildMan();	
	}
	
	public static void main(String[] args) {
		PersonDer personDer=new PersonDer();
		//调用构造角色方法
		Person person = personDer.PerConstruct(new ManBuilder());
		System.out.println(person.getHead());
		System.out.println(person.getBody());
		System.out.println(person.getFootAndhand());
		
	}
}
