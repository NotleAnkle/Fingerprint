package Fingerprint.Data;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import Fingerprint.User.UserController;



@Controller
@CrossOrigin
public class DataController {
	private DataDAO dataDAO = new DataDAO();
	
	@PostMapping("/getnextid")
	public ResponseEntity<Integer> getNextId() {
	    int result = dataDAO.getNextId();
	    System.out.println(result);
	    return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/check")
	public String check(Model model) throws IOException {
		return "check";
	}
	
	@GetMapping("/data")
	public String home(Model model) throws IOException {
		model.addAttribute("user", UserController.user);
		return "home";
	}
	
	@GetMapping("/data/{id}")
	public String getItem(Model model, @PathVariable String id) throws NumberFormatException {
		model.addAttribute("id", id);

		Data data = dataDAO.selectData(Integer.valueOf(id));
		model.addAttribute("data", data);

		return "data";
	}
	
	@PostMapping("data/save/{dataid}")
	public String addItem(Data data, @PathVariable String dataid) {
		return dataDAO.addData(data);
	}
	
	@PutMapping("data/save/{dataid}")
	public String updateItem(Data data, @PathVariable String dataid) {
		data.setId(Integer.valueOf(dataid));
		return dataDAO.updateData(data);
	}
	
	@DeleteMapping("data/{dataid}")
	public String deleteItem(Data data, @PathVariable String dataid) {
		return dataDAO.deleteData(Integer.valueOf(dataid));
	}
}
