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
import pl.air.hospital.repo.DepartmentRepository;

@Controller
@RequestMapping(value = "/departments")
public class DepartmentController {

	@Autowired
	private DepartmentRepository depRepo;
	
	/// READ ///

	@GetMapping(value = "/{id}")
	public String displayOne(@PathVariable Long id, Model model) {
		Optional<Department> opt = depRepo.findById(id);
		if (opt.isPresent()) {
			Department department = opt.get();
			model.addAttribute("department", department);
		} else {
			throw new NoDataFoundException("Nie znaleziono działu o podanym id = " + id);
		}
		return "department";
	}

	@GetMapping()
	public String displayAll(Model model) {
		List<Department> all = depRepo.findAll();
		model.addAttribute("departments", all);
		return "departments";
	}
		
	/// CREATE ///

	@GetMapping(value = "/form")
	public String displayForm(Model model) {
		Department department = new Department();
		model.addAttribute("department", department);
		return "department-form";
	}
	
	/// UPDATE ///
	
	@GetMapping(value = "/form/{id}")
	public String displayForm(@PathVariable Long id, Model model) {
		Optional<Department> opt = depRepo.findById(id);
		if (opt.isPresent()) {
			Department department = opt.get();
			model.addAttribute("department", department);
		} else {
			throw new NoDataFoundException("Nie znaleziono działu o podanym id = " + id);
		}
		return "department-form";
	}
	
	/// SAVE ///
	
	@PostMapping(value = "/save")
	public String saveOne(@Valid Department department, Errors errors) {
		if(errors.hasErrors()) {
			return "department-form";
		}
		depRepo.save(department);
		return "redirect:/departments";
	}

}
