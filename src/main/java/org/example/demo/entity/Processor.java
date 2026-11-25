package org.example.demo.entity;
import jakarta.persistence.*;
@Entity @Table(name="processor") public class Processor {
 @Id private Integer id;
 @Column(nullable=false,length=100) private String manufacturer;
 @Column(nullable=false,length=150) private String type;
 public Integer getId(){return id;}
 public void setId(Integer id){this.id=id;}
 public String getManufacturer(){return manufacturer;}
 public void setManufacturer(String manufacturer){this.manufacturer=manufacturer;}
 public String getType(){return type;}
 public void setType(String type){this.type=type;}
}
