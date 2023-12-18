package Fingerprint.Attendance;

import Fingerprint.Data.Data;
import Fingerprint.Data.DataDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
public class AttendanceController {
    private AttendanceDAO attendanceDAO = new AttendanceDAO();
    private DataDAO dataDAO = new DataDAO();

    @GetMapping("/attendance")
    public String getUser() {
        return "attendance-table";
    }

    @PostMapping("/attendances/{id}")
    public ResponseEntity<Data> getAttendanceUser(Model model, @PathVariable String id) {
        Data data = dataDAO.selectData(Integer.parseInt(id));
        model.addAttribute("data", data);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("attendances/save/{id}")
    public int saveAttendanceUser(Attendance data, @PathVariable String id) {
        return attendanceDAO.saveAttendanceUser(id, data);
    }
}
