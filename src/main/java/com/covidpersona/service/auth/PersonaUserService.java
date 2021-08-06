package com.covidpersona.service.auth;

import com.covidpersona.entity.PersonaUser;
import com.covidpersona.repository.UserRepository;
import com.sun.media.sound.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonaUserService {

    @Autowired
    private UserRepository userRepository;

    public long RegisterPersonaUser(String username,String password,String role) throws InvalidDataException {
        if (username.isEmpty() || username == null){
            throw new InvalidDataException("username is required");
        }
        if (password.isEmpty() || password == null){
            throw new InvalidDataException("password is required");
        }
        PersonaUser oldUser = userRepository.finddaoUserByUserName(username);
        if (oldUser != null){
            throw new InvalidDataException("username already exists");
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        PersonaUser personaUser = new PersonaUser();
        personaUser.setUsername(username);
        personaUser.setPassword(passwordEncoder.encode(password));
        personaUser.setRole(role);

        return userRepository.save(personaUser).getId();
    }
}
