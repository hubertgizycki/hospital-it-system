package pl.air.hospital.web;

import java.util.List;
import java.util.Optional;

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
import pl.air.hospital.model.Nurse;
import pl.air.hospital.model.Patient;
import pl.air.hospital.model.Room;
import pl.air.hospital.repo.DepartmentRepository;
import pl.air.hospital.repo.DoctorRepository;
import pl.air.hospital.repo.NurseRepository;
import pl.air.hospital.repo.PatientRepository;
import pl.air.hospital.repo.RoomRepository;

@Controller
@RequestMapping(value = "/patients")
public class PatientController {

	@Autowired
	private PatientRepository patRepo;
	@Autowired
	private DoctorRepository docRepo;
	@Autowired
	private NurseRepository nurRepo;
	@Autowired
	private DepartmentRepository depRepo;
	@Autowired
	private RoomRepository roomRepo;

	/// READ ///

	@GetMapping(value = "/{id}")
	public String displayOne(@PathVariable Long id, Model model) {
		Optional<Patient> opt = patRepo.findById(id);
		if (opt.isPresent()) {
			Patient patient = opt.get();
			model.addAttribute("patient", patient);
		} else {
			throw new NoDataFoundException("Nie znaleziono pacjenta o podanym id = " + id);
		}
		return "patient";
	}

	@GetMapping()
	public String displayAll(Model model) {
		List<Patient> all = patRepo.findAll();
		model.addAttribute("patients", all);
		return "patients";
	}

	/// CREATE ///

	@GetMapping(value = "/form")
	public String displayForm(Model model) {
		Patient patient = new Patient();
		model.addAttribute("patient", patient);
		addPatientDataToModel(model);

		return "patient-form";
	}

	private void addPatientDataToModel(Model model) {
		List<Department> departments = depRepo.findAll();
		model.addAttribute("departments", departments);
		List<Doctor> doctors = docRepo.findAll();
		model.addAttribute("doctors", doctors);
		List<Nurse> nurses = nurRepo.findAll();
		model.addAttribute("nurses", nurses);
		List<Room> rooms = roomRepo.findAll();
		model.addAttribute("rooms", rooms);
	}

	/// UPDATE ///

	@GetMapping(value = "/form/{id}")
	public String displayForm(@PathVariable Long id, Model model) {
		Optional<Patient> opt = patRepo.findById(id);
		if (opt.isPresent()) {
			Patient patient = opt.get();
			model.addAttribute("patient", patient);
			addPatientDataToModel(model);
		} else {
			throw new NoDataFoundException("Nie znaleziono pacjenta o podanym id = " + id);
		}
		return "patient-form";
	}

	/// SAVE ///

	@PostMapping(value = "/save")
	public String saveOne(@Valid Patient patient, Errors errors, Model model) {
		if (errors.hasErrors()) {
			addPatientDataToModel(model);
			return "patient-form";
		} else {
			patRepo.save(patient);
		}
		return "redirect:/patients";
	}
	
	/// DELETE ///
	@GetMapping(value = "/delete/{id}")
	public String deleteOne(@PathVariable Long id) {
		if(!patRepo.existsById(id)) {
			throw new NoDataFoundException("Nie znaleziono pacjenta o podanym id = " + id);
		}
		patRepo.deleteById(id);
		
		return "redirect:/patients";
	}

}
