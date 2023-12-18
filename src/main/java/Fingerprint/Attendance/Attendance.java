package Fingerprint.Attendance;

import java.sql.Date;

public class Attendance {
    private int id, user_id;
    private String check_in, check_out, name;
    
	public Attendance() {
		// TODO Auto-generated constructor stub
	}

	public Attendance(int id, int user_id, String check_in, String check_out, String name) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.check_in = check_in;
		this.check_out = check_out;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getCheck_in() {
		return check_in;
	}

	public void setCheck_in(String check_in) {
		this.check_in = check_in;
	}

	public String getCheck_out() {
		return check_out;
	}

	public void setCheck_out(String check_out) {
		this.check_out = check_out;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}