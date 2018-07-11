package cz.maly.dp_be.repository;

import cz.maly.dp_be.model.TypeOfApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOfApplicationRepository extends JpaRepository<TypeOfApplication, Long> {

}
