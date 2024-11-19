package com.control.acess.Config;

import com.control.acess.Models.Usuario;
import com.control.acess.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Usuario user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        return new UserDetailsImpl(user);
    }

}