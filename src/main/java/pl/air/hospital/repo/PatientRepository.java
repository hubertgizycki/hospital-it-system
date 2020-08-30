package pl.air.hospital.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.air.hospital.model.Doctor;
import pl.air.hospital.model.Nurse;
import pl.air.hospital.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

	
	@Query(
			"SELECT COUNT(*)                " +
			"FROM Patient                  " +
			"WHERE doctor = :doctor "
	)
	long countByDoctor(Doctor doctor);
	

	@Query(
			"SELECT COUNT(*)                " +
			"FROM Patient                  " +
			"WHERE nurse = :nurse "
	)
	long countByNurse(Nurse nurse);
	
	
}
