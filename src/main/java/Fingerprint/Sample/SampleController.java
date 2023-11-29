package Fingerprint.Sample;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import Fingerprint.User.UserController;



@Controller
public class SampleController {
	private SampleDAO sampleDAO = new SampleDAO();
	
	@GetMapping("samples")
	public String getBooks(Model model) throws IOException {
		if(UserController.isLoggin) {
			List<Sample> samples =  sampleDAO.selectAllSamples();
			model.addAttribute("samples",samples);
			model.addAttribute("user", UserController.user);
			return "samples";
		}
		return "redirect:/login";
	}
	
	@GetMapping("/sample/{sampleid}")
	public String getItem(Model model, @PathVariable String sampleid) throws NumberFormatException {
		if(UserController.isLoggin) {
			model.addAttribute("sampleid", sampleid);
			Sample sample = sampleDAO.selectSample(Integer.valueOf(sampleid));
			model.addAttribute("sample", sample);
			model.addAttribute("user", UserController.user);
			return "sample-detail";
		}
		return "redirect:/login";
	}
	
	@GetMapping("/sample/search/{name}")
	public String searchItem(Model model, @PathVariable String name) {
		if(UserController.isLoggin) {
			List<Sample> samples =  sampleDAO.searchSample(name);
			model.addAttribute("samples",samples);
			model.addAttribute("user", UserController.user);
			return "samples";
		}
		return "redirect:login";
	}
	
	@PostMapping("sample/save/{sampleid}")
	public String addItem(Sample sample, @PathVariable String sampleid) {
		return sampleDAO.addSample(sample);
	}
	
	@PutMapping("sample/save/{sampleid}")
	public String updateItem(Sample sample, @PathVariable String sampleid) {
		sample.setId(Integer.valueOf(sampleid));
		return sampleDAO.updateSample(sample);
	}
	
	@DeleteMapping("sample/{sampleid}")
	public String deleteItem(Sample sample, @PathVariable String sampleid) {
		return sampleDAO.deleteSample(Integer.valueOf(sampleid));
	}
}
