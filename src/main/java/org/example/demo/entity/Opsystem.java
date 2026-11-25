package org.example.demo.entity;
import jakarta.persistence.*;
@Entity @Table(name="opsystem") public class Opsystem {
 @Id private Integer id;
 @Column(nullable=false,length=150) private String osname;
 public Integer getId(){return id;}
 public void setId(Integer id){this.id=id;}
 public String getOsname(){return osname;}
 public void setOsname(String osname){this.osname=osname;}
}
