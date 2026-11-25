package org.example.demo.controller;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record NotebookForm(@NotBlank @Size(max=100) String manufacturer,@NotBlank @Size(max=150) String type,@NotNull @DecimalMin("1.0") @DecimalMax("99.9") @Digits(integer=2,fraction=1) BigDecimal display,@NotNull @Min(1) Integer memory,@NotNull @Min(1) Integer harddisk,@NotBlank @Size(max=200) String videocontroller,@NotNull @Min(0) Integer price,@NotNull @Min(1) Integer processorid,@NotNull @Min(1) Integer opsystemid,@NotNull @Min(0) Integer pieces){}
