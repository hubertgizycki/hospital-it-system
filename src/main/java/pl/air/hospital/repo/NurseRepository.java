package pl.air.hospital.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import pl.air.hospital.model.Nurse;

public interface NurseRepository extends JpaRepository<Nurse, Long> {

}
