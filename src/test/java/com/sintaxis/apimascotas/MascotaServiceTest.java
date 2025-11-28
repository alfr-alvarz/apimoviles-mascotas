package com.sintaxis.apimascotas;

import com.sintaxis.apimascotas.model.Mascota;
import com.sintaxis.apimascotas.model.Usuario;
import com.sintaxis.apimascotas.repository.MascotaRepository;
import com.sintaxis.apimascotas.repository.UsuarioRepository;
import com.sintaxis.apimascotas.service.MascotaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MascotaServiceTest {

    @Mock // Simulamos el repositorio 
    private MascotaRepository mascotaRepository;

    @Mock // Simulación el repo de usuarios (necesario para asignar dueño)
    private UsuarioRepository usuarioRepository;

    @InjectMocks // Inyectamos los mocks en el servicio real
    private MascotaService mascotaService;

    // PRUEBA 1: Verificar que trae la lista de mascotas
    @Test
    void obtenerTodas_DeberiaRetornarLista() {
        // 1. PREPARAR (Datos falsos)
        Mascota m1 = new Mascota(); m1.setNombre("Firu");
        Mascota m2 = new Mascota(); m2.setNombre("Michi");
        
        // Cuando el repo busque todas, devolvemos nuestra lista falsa
        when(mascotaRepository.findAll()).thenReturn(Arrays.asList(m1, m2));

        // 2. EJECUTAR
        List<Mascota> resultado = mascotaService.obtenerTodas();

        // 3. VERIFICAR
        assertNotNull(resultado);
        assertEquals(2, resultado.size()); // Esperamos 2 mascotas
        System.out.println("✅ Test Mascota 1: Lista obtenida correctamente.");
    }

    // PRUEBA 2: Verificar guardar mascota con dueño
    @Test
    void guardar_DeberiaAsignarUsuarioYGuardar() {
        // 1. PREPARAR
        Long idUsuario = 1L;
        Mascota nuevaMascota = new Mascota();
        nuevaMascota.setNombre("Rex");
        
        // Simulamos que el usuario existe en la BD
        Usuario usuarioMock = new Usuario();
        usuarioMock.setId(idUsuario);
        when(usuarioRepository.findById(idUsuario)).thenReturn(Optional.of(usuarioMock));

        // Simulamos el guardado de la mascota
        when(mascotaRepository.save(any(Mascota.class))).thenReturn(nuevaMascota);

        // 2. EJECUTAR (Llamamos al método guardar del servicio)
        Mascota resultado = mascotaService.guardar(nuevaMascota, idUsuario);

        // 3. VERIFICAR
        assertNotNull(resultado);
        verify(usuarioRepository, times(1)).findById(idUsuario); // ¿Buscó al dueño?
        verify(mascotaRepository, times(1)).save(any(Mascota.class)); // ¿Guardó la mascota?
        
        System.out.println("✅ Test Mascota 2: Mascota guardada con dueño exitosamente.");
    }
}