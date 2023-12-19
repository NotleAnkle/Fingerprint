package Fingerprint.Attendance;

import Fingerprint.Data.Data;
import Fingerprint.Data.DataDAO;

import java.io.IOException;

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
    public String home(Model model) throws IOException {
        model.addAttribute("datas", attendanceDAO.selectAllData());
        return "attendance-table";
    }

    @PostMapping("/attendances/{id}")
    public ResponseEntity<Data> getAttendanceUser(Model model, @PathVariable String id) {
        Data data = dataDAO.selectData(Integer.valueOf(id));
        model.addAttribute("data", data);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("/attendances/save")
    public String saveAttendanceUser(@RequestBody Attendance attendanceUser) {
        return attendanceDAO.saveAttendanceUser(attendanceUser);
    }

    @PatchMapping("/attendances/update/{id}")
    public String updateAttendanceUser(@PathVariable String id, @RequestBody String checkout) {
        return attendanceDAO.updateAttendanceUser(Integer.valueOf(id), checkout);
    }

}
