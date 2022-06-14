package diplomabackend.repository;

import diplomabackend.domain.WorkHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkHourRepository extends JpaRepository<WorkHour,Long> {

    @Query(value = "SELECT * \n" +
            "FROM work_hour\n" +
            "WHERE to_char(book_time, 'YYYY-MM-DD') LIKE :bookTime and doctor_id=:id",nativeQuery = true)
    List<WorkHour> findAllByIdAndBookTime(@Param("bookTime")String bookTime,@Param("id")Long id);
}
