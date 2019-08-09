package ch.supsi.webapp.web.repository;

import ch.supsi.webapp.web.model.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuoloRepository extends JpaRepository<Ruolo,String> {
    /*
    // query JPA con annotazione, giusto per tenerlo a mente
    @Query("SELECT b FROM BlogPost b where b.category = :value")
    List<BlogPost> list(@Param(value = "value") String category);
     */
}