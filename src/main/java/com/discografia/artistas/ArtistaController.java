package com.discografia.artistas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ArtistaController {

    @Autowired
    private IArtistaRepository artistaRepository;

    @PostMapping(value = "/artista", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Artista> HandleInsertArtistaRequest(@RequestBody Artista artista) {
        return ResponseEntity.ok(artistaRepository.save(artista));
    }

    @GetMapping(value = "/artistas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Artista>> HandleGetAristasRequest() {
        return ResponseEntity.ok(artistaRepository.findAll());
    }

    @GetMapping(value = "/artista/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> HandleGetArtistaRequest(@PathVariable String id) {
        return artistaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/artista/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> HandleUpdateArtistaRequest(@PathVariable String id, @RequestBody Artista artista) {
        if (!artistaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        artista._id = id;
        return ResponseEntity.ok(artistaRepository.save(artista));
    }

    @DeleteMapping(value = "/artista/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> HandleDeleteArtistaRequest(@PathVariable String id) {
        if (!artistaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        artistaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}