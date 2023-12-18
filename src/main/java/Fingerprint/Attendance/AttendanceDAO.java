package Fingerprint.Attendance;

import Fingerprint.DAO;
import Fingerprint.Data.Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO extends DAO {
    public AttendanceDAO() {

    }
    
    public List<Attendance> selectAllData() {
        List<Attendance> datas = new ArrayList<>();
        String sql = "select * from attendances";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
            	Attendance data = new Attendance();
                data.setId(result.getInt("id"));
                data.setUser_id(result.getInt("user_id"));
                data.setName(result.getString("name"));
                data.setCheck_in(result.getString("check_in"));
                data.setCheck_out(result.getString("check_out"));
                datas.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datas;
    }
    

    public int saveAttendanceUser(String id, Attendance data) {
        String sql = "INSERT INTO `finger`.`attendances` (`user_id`, `check_in`) VALUES (?, ?);";
        int result = 0;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, data.getCheck_in());
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
