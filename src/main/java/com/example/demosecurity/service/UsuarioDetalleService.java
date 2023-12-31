package com.example.demosecurity.service;

import com.example.demosecurity.model.bd.Rol;
import com.example.demosecurity.model.bd.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UsuarioDetalleService implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.buscarUsuarioPorNomusuario(username);
        return usuarioPorAutenticacion(usuario,
                obtenerAutorizacionUsuario(usuario.getRoles()));
    }
    private List<GrantedAuthority> obtenerAutorizacionUsuario(Set<Rol> listRoles){
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        for (Rol rol : listRoles) {
            roles.add(new SimpleGrantedAuthority(rol.getNomrol()));
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }
    private UserDetails usuarioPorAutenticacion(Usuario usuario,
                                                List<GrantedAuthority> authorityList){
        return new User(usuario.getNomusuario(), usuario.getPassword(),
                usuario.getActivo(),
                true,
                true,
                true, authorityList);
    }


}
