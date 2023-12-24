package Fingerprint.Attendance;

import Fingerprint.DAO;

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

    public int getNextId() {
        String query = "SELECT MAX(id) FROM attendances;";
        int newId = 0;
        try (
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet resultSet = ps.executeQuery()
        ) {
            if (resultSet.next()) {
                newId = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newId;
    }

    public String saveAttendanceUser(Attendance data) {
        String sql = "INSERT INTO `finger`.`attendances` (`id`, `user_id`, `name`, `check_in`, `check_out`) VALUES (?, ?, ?, ?, ?);";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, getNextId() + 1);
            ps.setInt(2, data.getUser_id());
            ps.setString(3, data.getName());
            ps.setString(4, data.getCheck_in());
            ps.setString(5, data.getCheck_out());
            ps.executeUpdate();
            return "redirect:/attendance";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "error";
    }

    public String updateAttendanceUser(int id, String checkout) {
        String sql = "UPDATE `finger`.`attendances` SET `check_out` = ? WHERE (`user_id` = ?);";
        boolean result = false;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, checkout);
            ps.setInt(2, id);
            ps.executeUpdate();
            return "redirect:/attendance";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "error";
    }
}
