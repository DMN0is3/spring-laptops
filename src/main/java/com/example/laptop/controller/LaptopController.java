package com.example.laptop.controller;

import com.example.laptop.entity.Laptop;
import com.example.laptop.repository.LaptopRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    // Crear LOG
    private final Logger log = LoggerFactory.getLogger(LaptopController.class);

    // Atributo
    private LaptopRepository laptopRepository;

    // Constructor
    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    /**
     * METODO findAll()<br>
     * http://localhost:8080/api/laptops
     */
    @GetMapping("/api/laptops")
    @ApiOperation("Mostrar todos los laptops.")
    public List<Laptop> findAll() {

        log.info("REST Request - Método findAll()");
        return laptopRepository.findAll();
    }

    /**
     * METODO findOneById()<br>
     * http://localhost:8081/api/laptops/{id}
     * @param id
     */
    @GetMapping("/api/laptops/{id}")
    @ApiOperation("Buscar el laptop por {id}.")
    public ResponseEntity<Laptop> findOneById(@ApiParam("Clave Primaria (Long)") @PathVariable Long id) {

        log.info("REST Request - Método findOneById()");

        Optional<Laptop> laptopOpt = laptopRepository.findById(id);

        if(laptopOpt.isPresent())
            return ResponseEntity.ok(laptopOpt.get());
        else
            return ResponseEntity.notFound().build();
    }

    /**
     * METODO create()<br>
     * Introducir laptop en formato JSON <br>
     * http://localhost:8080/api/laptops <br>
     * <pre> EJEMPLO:
     *     {
     *         "marca": "DELL",
     *         "modelo": "Inspiron",
     *         "pulgadas": 15,
     *         "procesador": "I3",
     *         "ram": 8,
     *         "hdd": 256,
     *         "peso": 1.85,
     *         "precio": 499,
     *         "disponible": true
     *     }
     * </pre>
     * @param laptop
     */
    @PostMapping("/api/laptops")
    @ApiOperation("Crear un laptop (JSON) para almacenar.")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop){

        log.info("REST Request - Método create()");

        if(laptop.getId() != null){
            log.warn("Intento de crear un laptop con 'id'");
            return ResponseEntity.badRequest().build();
        }

        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    /**
     * METODO update()<br>
     * Introducir laptop en formato JSON (ejemplo en METODO create())<br>
     * http://localhost:8080/api/laptops
     * @param laptop
     */
    @PutMapping("/api/laptops")
    @ApiOperation("Actualizar características de un laptop.")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){

        log.info("REST Request - Método update()");

        if(laptop.getId() == null ){
            log.warn("Intento de actualización de un laptop inexistente");
            return ResponseEntity.badRequest().build();
        } else if (!laptopRepository.existsById(laptop.getId())){
            log.warn("Intento de actualización de un laptop inexistente");
            return ResponseEntity.notFound().build();
        }

        Laptop result = laptopRepository.save(laptop);

        return ResponseEntity.ok(result);
    }

    /**
     * METODO delete(id)<br>
     * http://localhost:8080/api/laptops/{id}
     * @param id
     */
    @DeleteMapping("/api/laptops/{id}")
    @ApiOperation("Borrar un laptop por su {id}")
    public ResponseEntity<Laptop> delete(@ApiParam("Clave Primaria (Long)") @PathVariable Long id){

        log.info("REST Request - Método delete(id)");

        if(!laptopRepository.existsById(id)){
            log.warn("Intento de borrar un laptop inexistente");
            return ResponseEntity.notFound().build();
        }

        laptopRepository.deleteById(id);
        log.info("Elementos en BBDD: " + laptopRepository.count());

        return ResponseEntity.noContent().build();
    }

    /**
     * METODO deleteAll()<br>
     * http://localhost:8080/api/laptops
     */
    @ApiIgnore
    @DeleteMapping("/api/laptops")
    @ApiOperation("¡ BORRADO DE TODOS LOS ELEMENTOS ! ")
    public ResponseEntity<Laptop> deleteAll(){

        log.info("REST Request - Método deleteAll()");

        laptopRepository.deleteAll();
        log.info("Elementos en BBDD: " + laptopRepository.count());

        return ResponseEntity.noContent().build();
    }

}
