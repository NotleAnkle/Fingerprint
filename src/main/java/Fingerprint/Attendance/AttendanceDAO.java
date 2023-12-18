package Fingerprint.Attendance;

import Fingerprint.DAO;

import java.sql.PreparedStatement;

public class AttendanceDAO extends DAO {
    public AttendanceDAO() {

    }

    public int saveAttendanceUser(String id, Attendance data) {
        String sql = "INSERT INTO `finger`.`attendances` (`user_id`, `check_in`) VALUES (?, ?, ?);";
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
