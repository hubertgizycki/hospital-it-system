package pl.air.hospital.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import pl.air.hospital.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
 
	
}
