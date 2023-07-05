package showtime.repository;

import org.springframework.data.jpa.repository.Query;
import showtime.model.Programa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Programa, Integer> {
    @Query(
            value = "select rider_id from aparicion where show_id = :id",
            nativeQuery = true
    )
    List<Integer> findProgramariders(Integer id);
}
