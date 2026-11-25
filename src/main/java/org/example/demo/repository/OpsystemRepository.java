package org.example.demo.repository;
import org.example.demo.entity.Opsystem;
import org.springframework.data.jpa.repository.*;
import java.util.List;
public interface OpsystemRepository extends JpaRepository<Opsystem,Integer> {  }
