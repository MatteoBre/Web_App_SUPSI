package ch.supsi.webapp.web.repository;

import ch.supsi.webapp.web.model.Blogpost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogpostRepository extends JpaRepository<Blogpost,Integer> {
    @Override
    boolean existsById(Integer integer);

    List<Blogpost> findByCategoria(String categoria);
}
