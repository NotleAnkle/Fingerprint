package Fingerprint.Sample;

import java.io.Console;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Fingerprint.DAO;


public class SampleDAO extends DAO {
	 public SampleDAO() {
		 super();
		// TODO Auto-generated constructor stub
	}
	 public List<Sample> searchSample(String name){
			String sql = "SELECT * from `sample`.`tblsample` WHERE name LIKE ?";
			ResultSet resultSet = null;
			List<Sample> samples = new ArrayList<Sample>();
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1,"%"+name+"%");
				resultSet = ps.executeQuery();
				while(resultSet.next()) {
					int id = resultSet.getInt("id");
					String sname = resultSet.getString("name");
					String url = resultSet.getString("url");
					String des = resultSet.getString("des");
					Date createdTime = resultSet.getDate("createdTime");
					samples.add(new Sample(id, sname, des, url, createdTime));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return samples;
	}
	 public String addSample(Sample sample) {
			String sql = "INSERT INTO `sample`.`tblsample` (`name`,`url`, `des`, `createdTime`) VALUES (?, ?, ?, ?);";
			int reseult = 0;
			LocalDate locald = LocalDate.now();
			Date date = Date.valueOf(locald); // Magic happens here!

			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, sample.getName());
				ps.setString(2, sample.getUrl());
				ps.setString(3, sample.getDes());
				ps.setDate(4, date);
				reseult = ps.executeUpdate();
				return "redirect:/samples";
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "error";
	}
	
	 public List<Sample> selectAllSamples(){
			List<Sample> samples = new ArrayList<Sample>();
			String sql = "select * from tblsample";
			try{
				Statement statement = con.createStatement();
				ResultSet resultSet = statement.executeQuery(sql);
				while(resultSet.next()) {
					int id = resultSet.getInt("id");
					String name = resultSet.getString("name");
					String url = resultSet.getString("url");
					String des = resultSet.getString("des");
					Date createdTime = resultSet.getDate("createdTime");
					samples.add(new Sample(id, name, des, url, createdTime));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return samples;
		}
		public Sample selectSample(int samplecode) {
			Sample sample = new Sample();
			String sql = "select * from tblsample where id = ?";
			try{
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1,samplecode);
				ResultSet result = ps.executeQuery();
				while (result.next()) {
					sample.setId(samplecode);
					sample.setName(result.getString("name"));
					sample.setUrl(result.getString("url"));
					sample.setDes(result.getString("des"));
					sample.setCreatedTime(result.getDate("createdTime"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return sample;
		}
		
		public String updateSample(Sample sample) {
			String sql = "UPDATE `sample`.`tblsample` SET `name` = ?, `url` = ?, `des` = ?, `createdTime` = ? WHERE (`id` = ?);";

			int reseult = 0;
			
			LocalDate locald = LocalDate.now();
			Date date = Date.valueOf(locald); // Magic happens here!
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, sample.getName());
				ps.setString(2, sample.getUrl());
				ps.setString(3, sample.getDes());
				ps.setDate(4, date);
				ps.setInt(5, sample.getId());
				reseult = ps.executeUpdate();
				return "redirect:/samples";
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "error";
		}
		public String deleteSample(int samplecode) {
			String sql = "DELETE FROM `sample`.`tblsample` WHERE (`id` = ?);";
			try {
				PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
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
