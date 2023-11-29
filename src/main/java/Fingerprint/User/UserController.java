package Fingerprint.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class UserController {
    private UserDAO userDAO = new UserDAO();
    static public User user = new User();
    static public boolean isLoggin = true;
    
    @GetMapping("/login")
    public String getLogin(Model model) {
    	model.addAttribute("message", "");
    	return "login";
    }

    @PostMapping("/login")
    public String checkLogin(@RequestParam String username, @RequestParam String password, Model model) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
            if(userDAO.checkLogin(user)) {
            	model.addAttribute("message", "");
            	this.user = user;
            	isLoggin = true;
                return "redirect:/samples";   
            } else {
                model.addAttribute("message", "Tên người dùng hoặc mật khẩu không chính xác");
                return "login"; 
            }
    }
    @GetMapping("/logout")
    public String logOut() {
    	isLoggin = false;
    	user = new User();
    	return "redirect:/samples";
    }
        
//	@PostMapping("/register")
//	public String addUser(@RequestBody User user) {
//		return userDAO.addUser(user);
//	}
//	
//	@GetMapping("/user/{id}")
//	public User getUser(@PathVariable int id) {
//		return userDAO.selectUser(id);
//	}
}
