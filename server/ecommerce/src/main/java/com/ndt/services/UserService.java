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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserService implements UserDetailsService {
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
    
    public User addUser(User user) {
		return this.userRepo.addUser(user);
    }

    public User addUser(Map<String, String> params, MultipartFile image) {
		User u = new User();
		u.setUsername(params.get("username"));
		u.setPassword(params.get("password"));
		u.setUserRole(params.get("role"));
		if (!image.isEmpty()) {
			try {
				Map res = this.cloudinary.uploader().upload(image.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
				u.setImageUrl(res.get("secure_url").toString());
			} catch (IOException ex) {
				return null;
			}
		}

		this.userRepo.addUser(u);
		return u;

    }

	public void updateRefreshToken(User user, String refreshToken) {
		user.setRefreshToken(refreshToken);
		userRepo.update(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u = this.userRepo.getUserByUsername(username);
		if (u == null) {
			throw new UsernameNotFoundException("Invalid");
		}
		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority(u.getUserRole()));
		return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(), authorities);
	}
}
