package pl.air.hospital.web;

import java.util.List;
import java.util.Optional;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.air.hospital.exception.NoDataFoundException;
import pl.air.hospital.model.Nurse;
import pl.air.hospital.repo.NurseRepository;
import pl.air.hospital.repo.PatientRepository;

@Controller
@RequestMapping(value = "/nurses")
public class NurseController {

	@Autowired
	private NurseRepository nurRepo;

	@Autowired
	private PatientRepository patRepo;

	/// READ ///

	@GetMapping(value = "/{id}")
	public String displayOne(@PathVariable Long id, Model model) {
		Optional<Nurse> opt = nurRepo.findById(id);
		if (opt.isPresent()) {
			Nurse nurse = opt.get();
			model.addAttribute("nurse", nurse);
		} else {
			throw new NoDataFoundException("Nie znaleziono pielgniarki o podanym id = " + id);
		}
		return "nurse";
	}

	@GetMapping()
	public String displayAll(Model model) {
		List<Nurse> all = nurRepo.findAll();
		model.addAttribute("nurses", all);
		return "nurses";
	}
   
	/// DELETE ///

		@GetMapping(value = "/delete/{id}")
		public String deleteOne(@PathVariable Long id) {
			if (!nurRepo.existsById(id)) {
				throw new NoDataFoundException("Nie znaleziono pielęgniarki o podanym id = " + id);
			}
			Nurse nurse = Nurse.builder()
					.id(id)
					.build();
			long patCount = patRepo.countByNurse(nurse);
			
			if(patCount == 0) { 
			patRepo.deleteById(id);
			}
			else
				messageBox("Ostrzeżenie","Nie można usunąć pielęgniarki, ponieważ opiekuje się pacjentami");

			return "redirect:/nurses";
		}

		public void messageBox(String title, String message) {
			setupHeadlessMode();
			JFrame frame;
			frame = new JFrame(title);
			
			JLabel label = new JLabel(message, SwingConstants.CENTER);

			frame.add(label);
			frame.setSize(400, 300);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		}
		
		    public static void setupHeadlessMode() {
		        System.setProperty("java.awt.headless", "false");
		    }
}
