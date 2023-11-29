package Fingerprint.Sample;

import java.sql.Date;

public class Sample {
	private int id;
	private String name, des, url;
	private Date createdTime;
	
	
	public Sample() {
		// TODO Auto-generated constructor stub
	}
	
	public Sample(int id, String name, String des, String url, Date createdTime) {
		super();
		this.id = id;
		this.name = name;
		this.des = des;
		this.url = url;
		this.createdTime = createdTime;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	
}
