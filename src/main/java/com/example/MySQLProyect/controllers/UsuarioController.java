package com.example.MySQLProyect.controllers;

import com.example.MySQLProyect.models.UsuarioModel;
import com.example.MySQLProyect.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/list")
    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }

    @PostMapping("/create")
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario) {
        return usuarioService.guardarUsuario(usuario);
    }

    @GetMapping(path ="/{id}")
    public Optional<UsuarioModel> obtenerUsuarioPorId(@PathVariable("id") Long id){
        return usuarioService.obtenerPorId(id);
    }

    @GetMapping("/query")
    public ArrayList<UsuarioModel> obtenerUsuariosPorPrioridad(@RequestParam("prioridad") Integer prioridad){
        return usuarioService.obtenerPorPrioridad(prioridad);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<String> eliminarPorId(@PathVariable("id") Long id){
        if(usuarioService.eliminarUsuario(id))
            return new ResponseEntity<String>("Usuario eliminado", HttpStatus.OK);
        return new ResponseEntity<String>("Usuario no encontrado", HttpStatus.BAD_REQUEST);
    }
    //Busqueda por prioridad y nombre
    @GetMapping(path = "Buscar/{prioridad}/{nombre}")
    public ArrayList<UsuarioModel> obtenerUsuariosPorPrioridad(@PathVariable("prioridad")Integer prioridad,@PathVariable("nombre")String nombre){
        return usuarioService.obtenerPorPrioridadYNombre(prioridad,nombre);
    }
}
