//AuthenticationService.java
package com.consultorio.consultorio.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.consultorio.consultorio.config.JwtServiceGenerator;
import com.consultorio.consultorio.dto.LoginDTO;
import com.consultorio.consultorio.dto.UserDTO;
import com.consultorio.consultorio.entity.User;
import com.consultorio.consultorio.repository.LoginRepository;

@Service
public class LoginService {
	
	@Autowired
	private LoginRepository repository;
	@Autowired
	private JwtServiceGenerator jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserDTO logar(LoginDTO loginDTO) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginDTO.getUsername(),
						loginDTO.getPassword()
						)
				);
		User user = repository.findByUsername(loginDTO.getUsername()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		
		return toUserDTO2(user, jwtToken);
	}

   
	public UserDTO include(User user) {
        Assert.notNull(user.getUsername(), "Username não informado!");
        Assert.notNull(user.getPassword(), "Password não informado!");
        Assert.notNull(user.getRole(), "Role não informada!");
        
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());        
        userDTO.setRole(user.getRole());
        userDTO.setToken(user.getPassword());

        repository.save(user);

        return userDTO;
    }
	
    public List<UserDTO> getAllUsers() {
        return repository.findAll().stream()
                .map(this::toUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return toUserDTO(user);
    }

    public UserDTO updateUser(Long id, User userDetails) {
        User user = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(userDetails.getUsername());
        user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        user.setRole(userDetails.getRole());

        repository.save(user);

        return toUserDTO(user);
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
    
    
    private UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setRole(user.getRole());
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }
    
	private UserDTO toUserDTO2(User user, String token) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setRole(user.getRole());
		userDTO.setToken(token);
		userDTO.setUsername(user.getUsername());
		return userDTO;
	}
}
