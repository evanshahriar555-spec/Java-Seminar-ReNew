package org.example.demo.repository;
import org.example.demo.entity.Notebook;
import org.springframework.data.jpa.repository.*;
import java.util.List;
public interface NotebookRepository extends JpaRepository<Notebook,Integer> { @Query("select n from Notebook n join fetch n.processor join fetch n.opsystem order by n.id") List<Notebook> catalogue(); }
