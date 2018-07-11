package cz.maly.dp_be.repository;

import cz.maly.dp_be.model.Downtime;
import cz.maly.dp_be.model.TypeDowntime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeDowntimeRepository extends JpaRepository<TypeDowntime, Long> {

}
