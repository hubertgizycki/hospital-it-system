package pl.air.hospital.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.air.hospital.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
