package com.api.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.api.entities.User;
import com.api.exceptions.CustomNotFoundException;
import com.api.services.UserService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userService;

    // Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    // Get user by FID (Firebase ID)
    @GetMapping("/{fid}")
    public ResponseEntity<User> getByFid(@PathVariable String fid) {
        User user = userService.getById(fid);
        if (user == null) {
            throw new CustomNotFoundException("User not found with FID: " + fid);
        }
        return ResponseEntity.ok(user);
    }

    // Add new user with validation
    @PostMapping
    public ResponseEntity<User> addUser(@Validated @RequestBody User user) {
        User savedUser = userService.addUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // Update existing user by FID with validation
    @PutMapping("/{fid}")
    public ResponseEntity<User> updateUser(@PathVariable String fid, @Validated @RequestBody User user) {
        User updatedUser = userService.updateUser(fid, user);
        if (updatedUser == null) {
            throw new CustomNotFoundException("User not found with FID: " + fid);
        }
        return ResponseEntity.ok(updatedUser);
    }

    // Delete user by FID
    @DeleteMapping("/{fid}")
    public ResponseEntity<String> deleteByFid(@PathVariable String fid) {
        boolean deleted = userService.deleteById(fid);
        if (!deleted) {
            throw new CustomNotFoundException("User not found with FID: " + fid);
        }
        return ResponseEntity.ok("User deleted successfully");
    }

    // Download user image
    @GetMapping("/image/{imageName}")
    public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
        Path path = Paths.get("uploads", imageName);
        if (Files.exists(path)) {
            response.setContentType(Files.probeContentType(path));
            Files.copy(path, response.getOutputStream());
            response.getOutputStream().flush();
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    // Upload user image by FID
    @PostMapping("/{fid}/upload-image")
    public ResponseEntity<String> uploadUserImage(
            @PathVariable String fid,
            @RequestParam("image") MultipartFile imageFile) throws IOException {

        userService.uploadUserImage(fid, imageFile);
        return ResponseEntity.ok("Image uploaded and user updated successfully for FID: " + fid);
    }
}
