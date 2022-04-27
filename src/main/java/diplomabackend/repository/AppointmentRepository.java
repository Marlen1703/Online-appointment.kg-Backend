package diplomabackend.repository;

import diplomabackend.domain.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    Page <Appointment> findByPolicy(Pageable pageable, String policy);
}
