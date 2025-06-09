package com.api.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import com.api.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @Column(unique = true, nullable = false)
    private String fid;  // Primary key from Firebase

    
    private String name;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$", message = "Email must be a Gmail address")
    @Column(unique = true, nullable = true)
    private String email;

    @Column(name = "image_url", nullable = true)
    private String imageUrl;

    @Column(nullable = true)
    private LocalDate dateOfBirth;

    @Column(nullable = true)
    private LocalTime timeOfBirth;

    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be exactly 10 digits")
    private String contactNumber;

    @NotNull(message = "Field Gender is required")
    private String gender;

    @Embedded
    @Column(nullable = true)
    private UserBirthLocation birthLocation;  // Optional embedded

    private double walletBalance = 0.0;

    // Getters and setters

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    // No setter for id because it's auto-generated

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalTime getTimeOfBirth() {
        return timeOfBirth;
    }

    public void setTimeOfBirth(LocalTime timeOfBirth) {
        this.timeOfBirth = timeOfBirth;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public UserBirthLocation getBirthLocation() {
        return birthLocation;
    }

    public void setBirthLocation(UserBirthLocation birthLocation) {
        this.birthLocation = birthLocation;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }
}
