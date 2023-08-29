/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndt.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ndt.pojo.User;
import com.ndt.repositories.UserRepository;
import java.io.IOException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private UserRepository userRepo;
    
    public boolean authUser(String username, String password) {
        return this.userRepo.authUser(username, password);
    }
    
    public User getUserByUsername(String username) {
        return this.userRepo.getUserByUsername(username);
    }

    public User getUserByRefreshToken(String refreshToken) {
        return this.userRepo.getUserByRefreshToken(refreshToken);
    }

	public void updateRefreshToken(String username, String refreshToken) {
		this.userRepo.updateRefreshToken(username, refreshToken);
	}
    
    public User addUser(User user) throws IOException {
		return this.userRepo.addUser(user);
    }

	public void updateRefreshToken(User user, String refreshToken) {
		user.setRefreshToken(refreshToken);
		userRepo.update(user);
	}
}
