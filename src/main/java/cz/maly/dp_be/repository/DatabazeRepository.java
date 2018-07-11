package cz.maly.dp_be.repository;

import cz.maly.dp_be.model.Databaze;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabazeRepository extends JpaRepository<Databaze, Long> {

}
