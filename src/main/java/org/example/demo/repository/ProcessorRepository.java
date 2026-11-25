package org.example.demo.repository;
import org.example.demo.entity.Processor;
import org.springframework.data.jpa.repository.*;
import java.util.List;
public interface ProcessorRepository extends JpaRepository<Processor,Integer> {  }
