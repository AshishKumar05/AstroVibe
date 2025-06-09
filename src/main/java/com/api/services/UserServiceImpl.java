package com.api.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.entities.User;
import com.api.exceptions.CustomNotFoundException;
import com.api.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getById(String fid) {
        return userRepo.findById(fid)
                .orElseThrow(() -> new CustomNotFoundException("User not found with ID: " + fid));
    }

    @Override
    public User addUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User updateUser(String fid, User user) {
        User existingUser = userRepo.findById(fid)
                .orElseThrow(() -> new CustomNotFoundException("User not found with ID: " + fid));

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setDateOfBirth(user.getDateOfBirth());
        existingUser.setTimeOfBirth(user.getTimeOfBirth());
        existingUser.setContactNumber(user.getContactNumber());
        existingUser.setGender(user.getGender());
        existingUser.setBirthLocation(user.getBirthLocation());
        existingUser.setWalletBalance(user.getWalletBalance());
        existingUser.setImageUrl(user.getImageUrl());  // update image URL if provided

        return userRepo.save(existingUser);
    }

    @Override
    public String deleteById(String fid) {
        if (userRepo.existsById(fid)) {
            userRepo.deleteById(fid);
            return "Successfully deleted user with ID: " + fid;
        } else {
            throw new CustomNotFoundException("User not found with ID: " + fid);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new CustomNotFoundException("User not found with email: " + email));
    }

    // loginUser and changePassword removed because password is not part of User entity anymore

    @Override
    public void uploadUserImage(String fid, MultipartFile imageFile) throws IOException {
        User user = userRepo.findById(fid)
                .orElseThrow(() -> new CustomNotFoundException("User not found with ID: " + fid));

        String uploadDir = "uploads/";
        Files.createDirectories(Paths.get(uploadDir));

        String fileName = imageFile.getOriginalFilename();
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("Invalid file name");
        }

        Path filePath = Paths.get(uploadDir).resolve(fileName);
        Files.write(filePath, imageFile.getBytes());

        // Update imageUrl to point to the saved file path or URL
        user.setImageUrl(fileName);  // or "uploads/" + fileName if you want full path
        userRepo.save(user);
    }

	@Override
	public User loginUser(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}
}
