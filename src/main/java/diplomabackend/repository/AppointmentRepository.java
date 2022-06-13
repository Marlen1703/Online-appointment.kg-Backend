package diplomabackend.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import diplomabackend.StatusEnum;
import diplomabackend.domain.Appointment;
import diplomabackend.domain.QAppointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AppointmentRepository extends JpaRepository<Appointment,Long>,
        QuerydslPredicateExecutor<Appointment>, QuerydslBinderCustomizer<QAppointment> {

    @Override
    default public void customize(
            QuerydslBindings bindings, QAppointment root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        bindings.excluding(root.status);
    }

    Page <Appointment> findByPolicy(Pageable pageable, String policy);
//
@Query(value = "SELECT * FROM appointment a WHERE DATE(time) = CURRENT_DATE and status=0 and attending_doctor_id=:id",nativeQuery = true)
Page<Appointment> findAllTodayAppointments(@Param("id") Long id,Pageable pageable);


    @Override
    Page<Appointment> findAll(Predicate predicate,Pageable pageable);


    List<Appointment> findAll(Predicate predicate);

    Page<Appointment> findAllByStatus(StatusEnum status, Pageable pageable);
}
