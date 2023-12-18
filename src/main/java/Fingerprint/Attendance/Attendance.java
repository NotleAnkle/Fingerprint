package Fingerprint.Attendance;

public class Attendance {
    private int id;
    private String user_id, check_in;

    public Attendance(int id, String check_in, String user_id) {
        this.id = id;
        this.check_in = check_in;
        this.user_id = user_id;
    }

    public Attendance() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCheck_in() {
        return check_in;
    }

    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}