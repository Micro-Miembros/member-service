package co.analisys.gimnasio.controller;

import co.analisys.gimnasio.model.Miembro;
import co.analisys.gimnasio.service.MiembroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gimnasio")
public class MiembroController {
    @Autowired
    private MiembroService miembroService;

    // Endpoint: Registrar nuevo miembro
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Miembro registrado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
        @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    @Operation(summary = "Registrar nuevo miembro", description = "Permite registrar un nuevo miembro en el gimnasio")
    @PostMapping("/miembros")
    public Miembro registrarMiembro(@RequestBody Miembro miembro) {
        return miembroService.registrarMiembro(miembro);
    }

    // Endpoint: Obtener todos los miembros
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista de miembros obtenida exitosamente"),
        @ApiResponse(responseCode = "404", description = "No se encontraron miembros"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
        @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    @Operation(summary = "Obtener todos los miembros", description = "Permite obtener la lista de todos los miembros del gimnasio")
    @GetMapping("/miembros")
    public List<Miembro> obtenerTodosMiembros() {
        return miembroService.obtenerTodosMiembros();
    }

    // Endpoint: Verificar membresía activa
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Membresía activa verificada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Miembro no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor"),
        @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    @Operation(summary = "Verificar membresía activa", description = "Permite verificar si un miembro tiene una membresía activa (true/false)")
    @GetMapping("/miembros/{id}/activa")
    public boolean verificarMembresiaActiva(@PathVariable Long id) {
        return miembroService.membresiaActiva(id);
    }

}