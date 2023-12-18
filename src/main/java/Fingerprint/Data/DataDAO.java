package Fingerprint.Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.web.bind.annotation.GetMapping;

import Fingerprint.DAO;

public class DataDAO extends DAO {
    public DataDAO() {
        // TODO Auto-generated constructor stub
    }

    public int getNextId() {
        String sql = "SELECT AUTO_INCREMENT\r\n"
                + "FROM information_schema.TABLES\r\n"
                + "WHERE TABLE_SCHEMA = 'finger'\r\n"
                + "  AND TABLE_NAME = 'tbldata';";
        int reseult = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                // Nếu có dữ liệu, thì mới đọc giá trị
                reseult = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reseult;
    }

    public String addData(Data data) {
        String sql = "INSERT INTO `finger`.`tbldata` (`name`, `age`, `des`) VALUES (?, ?, ?);";
        int reseult = 0;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, data.getName());
            ps.setInt(2, data.getAge());
            ps.setString(3, data.getDes());
            reseult = ps.executeUpdate();
            return "redirect:/data";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    public Data selectData(int id) {
        Data data = new Data();
        String sql = "select * from tbldata where id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                data.setId(id);
                data.setName(result.getString("name"));
                data.setAge(result.getInt("age"));
                data.setDes(result.getString("des"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public String updateData(Data data) {
        String sql = "UPDATE `finger`.`tbldata` SET `name` = ?, `age` = ?, `des` = ? WHERE (`id` = ?);";

        int reseult = 0;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, data.getName());
            ps.setInt(2, data.getAge());
            ps.setString(3, data.getDes());
            ps.setInt(4, data.getId());
            reseult = ps.executeUpdate();
            return "redirect:/data";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    public String deleteData(int samplecode) {
        String sql = "DELETE FROM `finger`.`tblsample` WHERE (`id` = ?);";
        try {
            PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setInt(1, samplecode);
            int result = ps.executeUpdate();

            ps.close();
            return "redirect:/samples";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error"; // tạo trang Error
    }
}
