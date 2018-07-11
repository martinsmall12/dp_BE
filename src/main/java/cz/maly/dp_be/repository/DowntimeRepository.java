package cz.maly.dp_be.repository;

import cz.maly.dp_be.model.Downtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DowntimeRepository extends JpaRepository<Downtime, Long> {

}
