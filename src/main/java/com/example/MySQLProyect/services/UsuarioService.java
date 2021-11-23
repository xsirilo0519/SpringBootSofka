package com.example.MySQLProyect.services;

import com.example.MySQLProyect.models.UsuarioModel;
import com.example.MySQLProyect.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return (ArrayList<UsuarioModel>) usuarioRepository.findAll();
    }

    public UsuarioModel guardarUsuario (UsuarioModel usuario){
        return usuarioRepository.save(usuario);
    }

    public Optional<UsuarioModel> obtenerPorId(Long id){
        return usuarioRepository.findById(id);
    }

    public ArrayList<UsuarioModel> obtenerPorPrioridad(Integer prioridad){
        return usuarioRepository.findByPrioridad(prioridad);
    }

    public boolean eliminarUsuario(Long id){
        try{
            usuarioRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public ArrayList<UsuarioModel> obtenerPorPrioridadYNombre(Integer prioridad,String nombre){
        return usuarioRepository.findByPrioridadAndNombre(prioridad,nombre);
    }

    public UsuarioModel actualizarEmailById(Long id,String email){
        Optional<UsuarioModel> usuario=null;
        try {
            usuario = usuarioRepository.findById(id);
            usuario.get().setEmail(email);
            return usuarioRepository.save(usuario.get());
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<UsuarioModel> listaMaximaPrioridad(){
        ArrayList<UsuarioModel> listaUsuarios=(ArrayList<UsuarioModel>) usuarioRepository.findAll();
        return (ArrayList<UsuarioModel>) listaUsuarios.stream().filter(x->x.getPrioridad()>3).collect(Collectors.toList());
    }
}
