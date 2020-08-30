package pl.air.hospital.web;
import java.util.List;
import java.util.Optional;

import javax.swing.*;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.air.hospital.exception.NoDataFoundException;
import pl.air.hospital.model.Department;
import pl.air.hospital.model.Doctor;
import pl.air.hospital.repo.DepartmentRepository;
import pl.air.hospital.repo.DoctorRepository;
import pl.air.hospital.repo.PatientRepository;

@Controller
@RequestMapping(value = "/doctors")
public class DoctorController {

	@Autowired
	private DoctorRepository docRepo;

	@Autowired
	private DepartmentRepository depRepo;
	
	@Autowired
	private PatientRepository patRepo;

	/// READ ///

	@GetMapping(value = "/{id}")
	public String displayOne(@PathVariable Long id, Model model) {
		Optional<Doctor> opt = docRepo.findById(id);
		if (opt.isPresent()) {
			Doctor doctor = opt.get();
			model.addAttribute("doctor", doctor);
		} else {
			throw new NoDataFoundException("Nie znaleziono lekarza o podanym id = " + id);
		}
		return "doctor";
	}

	@GetMapping()
	public String displayAll(Model model) {
		List<Doctor> all = docRepo.findAll();
		model.addAttribute("doctors", all);
		return "doctors";
	}

	/// CREATE ///

	@GetMapping(value = "/form")
	public String displayForm(Model model) {
		Doctor doctor = new Doctor();
		model.addAttribute("doctor", doctor);
		addDoctorDataToModel(model);

		return "doctor-form";
	}

	private void addDoctorDataToModel(Model model) {
		List<Department> departments = depRepo.findAll();
		model.addAttribute("departments", departments);
	}

	/// UPDATE ///

	@GetMapping(value = "/form/{id}")
	public String displayForm(@PathVariable Long id, Model model) {
		Optional<Doctor> opt = docRepo.findById(id);
		if (opt.isPresent()) {
			Doctor doctor = opt.get();
			model.addAttribute("doctor", doctor);
			addDoctorDataToModel(model);
		} else {
			throw new NoDataFoundException("Nie znaleziono lekarza o podanym id = " + id);
		}
		return "doctor-form";
	}

	/// SAVE ///

	@PostMapping(value = "/save")
	public String saveOne(@Valid Doctor doctor, Errors errors, Model model) {
		if (errors.hasErrors()) {
			addDoctorDataToModel(model);
			return "doctor-form";
		} else {
			docRepo.save(doctor);
		}
		return "redirect:/doctors";
	}

	/// DELETE ///

	@GetMapping(value = "/delete/{id}")
	public String deleteOne(@PathVariable Long id) {
		if (!docRepo.existsById(id)) {
			throw new NoDataFoundException("Nie znaleziono lekarza o podanym id = " + id);
		}
		Doctor doctor = Doctor.builder()
				.id(id)
				.build();
		long patCount = patRepo.countByDoctor(doctor);
		
		if(patCount == 0) { 
		docRepo.deleteById(id);
		}
		else
			messageBox("Ostrzeżenie","Nie można usunąć danego lekarza, ponieważ ma pacjentów");

		return "redirect:/doctors";
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
