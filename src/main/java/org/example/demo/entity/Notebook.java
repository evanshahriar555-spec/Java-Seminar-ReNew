package org.example.demo.entity;
import jakarta.persistence.*;
@Entity @Table(name="notebook") public class Notebook {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Integer id;
 @Column(nullable=false,length=100) private String manufacturer;
 @Column(nullable=false,length=150) private String type;
 @Column(nullable=false,precision=4,scale=1) private java.math.BigDecimal display;
 @Column(nullable=false) private Integer memory;
 @Column(nullable=false) private Integer harddisk;
 @Column(nullable=false,length=200) private String videocontroller;
 @Column(nullable=false) private Integer price;
 @ManyToOne(optional=false) @JoinColumn(name="processorid") private Processor processor;
 @ManyToOne(optional=false) @JoinColumn(name="opsystemid") private Opsystem opsystem;
 @Column(nullable=false) private Integer pieces;
 public Integer getId(){return id;}
 public void setId(Integer id){this.id=id;}
 public String getManufacturer(){return manufacturer;}
 public void setManufacturer(String manufacturer){this.manufacturer=manufacturer;}
 public String getType(){return type;}
 public void setType(String type){this.type=type;}
 public java.math.BigDecimal getDisplay(){return display;}
 public void setDisplay(java.math.BigDecimal display){this.display=display;}
 public Integer getMemory(){return memory;}
 public void setMemory(Integer memory){this.memory=memory;}
 public Integer getHarddisk(){return harddisk;}
 public void setHarddisk(Integer harddisk){this.harddisk=harddisk;}
 public String getVideocontroller(){return videocontroller;}
 public void setVideocontroller(String videocontroller){this.videocontroller=videocontroller;}
 public Integer getPrice(){return price;}
 public void setPrice(Integer price){this.price=price;}
 public Processor getProcessor(){return processor;}
 public void setProcessor(Processor processor){this.processor=processor;}
 public Opsystem getOpsystem(){return opsystem;}
 public void setOpsystem(Opsystem opsystem){this.opsystem=opsystem;}
 public Integer getPieces(){return pieces;}
 public void setPieces(Integer pieces){this.pieces=pieces;}
}
