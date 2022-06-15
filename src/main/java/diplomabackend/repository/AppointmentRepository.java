package diplomabackend.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import diplomabackend.enums.StatusEnum;
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
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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

    @Query(value = "SELECT * \n" +
            "FROM appointment\n" +
            "WHERE to_char(time, 'YYYY-MM-DD') LIKE :time and attending_doctor_id=:id",nativeQuery = true)
    List<Appointment> findAllByIdAndBookTime(@Param("time")String time, @Param("id")Long id);


    @Override
    Page<Appointment> findAll(Predicate predicate,Pageable pageable);

    Page<Appointment>findAllByConsumer_UsernameAndStatus(String username,Pageable pageable,StatusEnum statusEnum);

    List<Appointment> findAll(Predicate predicate);

}
