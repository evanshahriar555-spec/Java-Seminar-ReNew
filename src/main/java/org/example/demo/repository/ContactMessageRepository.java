package org.example.demo.repository;
import org.example.demo.entity.ContactMessage;
import org.springframework.data.jpa.repository.*;
import java.util.List;
public interface ContactMessageRepository extends JpaRepository<ContactMessage,Long> { List<ContactMessage> findAllByOrderBySentAtDescIdDesc(); }
