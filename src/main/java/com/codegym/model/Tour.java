package com.codegym.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;

import java.util.UUID;
@Component
@Entity
@Table(name = "tour")
public class Tour implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;
    private String destination;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    private Long idUser;

    public Tour() {
    }

    //    public Tour() {
//        this.code = generateUniqueCode();
//    }

    public Tour(Long id, String code, String destination, Double price, Type type, Long idUser) {
        this.id = id;
        this.code = code;
        this.destination = destination;
        this.price = price;
        this.type = type;
        this.idUser = idUser;
    }

//    private String generateUniqueCode() {
//        return "CG" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
//    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }
    public Long getIdUser() { return idUser; }
    public void setIdUser(Long idUser) { this.idUser = idUser; }

    @Override
    public boolean supports(Class<?> clazz) {
        return Tour.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Tour tour = (Tour) target;

        ValidationUtils.rejectIfEmpty(errors, "code", "code.empty", "Code cannot be empty");
        ValidationUtils.rejectIfEmpty(errors, "destination", "destination.empty", "Destination cannot be empty");
        ValidationUtils.rejectIfEmpty(errors, "price", "price.empty", "Price cannot be empty");
        ValidationUtils.rejectIfEmpty(errors, "type", "type.empty", "Type cannot be empty");
        ValidationUtils.rejectIfEmpty(errors, "idUser", "idUser.empty", "User ID cannot be empty");

        if (tour.getCode() != null && (!tour.getCode().startsWith("CG") || tour.getCode().length() != 8)) {
            errors.rejectValue("code", "code.format", "Code must start with 'CG' and be exactly 8 characters long");
        }

        if (tour.getPrice() != null && tour.getPrice() < 0) {
            errors.rejectValue("price", "price.invalid", "Price must be non-negative");
        }
    }
}
