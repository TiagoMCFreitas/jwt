package com.control.acess.Service;

import com.control.acess.Config.JwtTokenService;
import com.control.acess.Config.SecurityConfiguration;
import com.control.acess.Config.UserDetailsImpl;
import com.control.acess.DTOS.LoginDto;
import com.control.acess.DTOS.RecoveryToken;
import com.control.acess.Enums.RoleName;
import com.control.acess.Models.Roles;
import com.control.acess.Models.Usuario;
import com.control.acess.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.OffsetScrollPositionHandlerMethodArgumentResolverSupport;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired

    private JwtTokenService jwtTokenService;

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;
    @Autowired
    private OffsetScrollPositionHandlerMethodArgumentResolverSupport offsetScrollPositionHandlerMethodArgumentResolverSupport;

    // Método responsável por autenticar um usuário e retornar um token JWT
    public RecoveryToken authenticateUser(LoginDto loginUserDto) {
        // Cria um objeto de autenticação com o email e a senha do usuário
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

        // Autentica o usuário com as credenciais fornecidas
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // Obtém o objeto UserDetails do usuário autenticado
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Gera um token JWT para o usuário autenticado
        return new RecoveryToken(jwtTokenService.generateToken(userDetails));
    }

    // Método responsável por criar um usuário
    public void createUser(Usuario usuario) {
        // Cria um novo usuário com os dados fornecidos
        Roles roles = new Roles();
        usuario.setPassword(securityConfiguration.passwordEncoder().encode(usuario.getPassword()));



        roles.setCargo(RoleName.valueOf(usuario.getCargo().toString()));
        usuario.setCargo(RoleName.valueOf(usuario.getCargo().toString()));
        // Salva o novo usuário no banco de dados
        userRepository.save(usuario);
}}
