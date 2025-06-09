package com.api.services;

import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.api.entities.User;
@Service
public interface UserService {
	
    public List<User> getAllUsers();
    
	public User getById(String userId);
	
	public User addUser(User user);
	
	public User updateUser(String userId,User user);
	
	public Boolean deleteById(String userId);
	
	public User getUserByEmail(String email);
	
	public User loginUser(String email, String password);	
	
	
	void uploadUserImage(String userId, MultipartFile imageFile) throws IOException;
	
	


}
