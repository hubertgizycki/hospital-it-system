package pl.air.hospital.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.air.hospital.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
