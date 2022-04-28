package diplomabackend.repository;

import diplomabackend.domain.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository <Doctor,Long> {

    Optional <Doctor> findById(Long id);

    Page <Doctor> findAll(Pageable pageable);
}
