package org.example.demo.entity;
import jakarta.persistence.*;
@Entity @Table(name="contact_messages") public class ContactMessage {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=100) private String name;
 @Column(nullable=false,length=254) private String email;
 @Column(nullable=false,length=2000) private String message;
 @Column(nullable=false) private java.time.LocalDateTime sentAt;
 public Long getId(){return id;}
 public void setId(Long id){this.id=id;}
 public String getName(){return name;}
 public void setName(String name){this.name=name;}
 public String getEmail(){return email;}
 public void setEmail(String email){this.email=email;}
 public String getMessage(){return message;}
 public void setMessage(String message){this.message=message;}
 public java.time.LocalDateTime getSentAt(){return sentAt;}
 public void setSentAt(java.time.LocalDateTime sentAt){this.sentAt=sentAt;}
}
