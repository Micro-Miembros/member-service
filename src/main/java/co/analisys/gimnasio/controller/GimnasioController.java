package co.analisys.gimnasio.controller;

import co.analisys.gimnasio.model.Miembro;
import co.analisys.gimnasio.service.MiembroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gimnasio")
public class GimnasioController {
    @Autowired
    private MiembroService miembroService;

    @PostMapping("/miembros")
    public Miembro registrarMiembro(@RequestBody Miembro miembro) {
        return miembroService.registrarMiembro(miembro);
    }

    @GetMapping("/miembros")
    public List<Miembro> obtenerTodosMiembros() {
        return miembroService.obtenerTodosMiembros();
    }

}