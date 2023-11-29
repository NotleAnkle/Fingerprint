package Fingerprint.Data;

public class Data {
	private int id, age;
	private String name, des;
	
	public Data() {
		// TODO Auto-generated constructor stub
	}
	
	public Data(int id, int age, String name, String des) {
		super();
		this.id = id;
		this.age = age;
		this.name = name;
		this.des = des;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	
	
	
}
