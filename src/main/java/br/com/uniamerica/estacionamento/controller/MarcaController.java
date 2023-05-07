package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.Repository.MarcaRepository;
import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.servece.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/marca")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;


    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id) {
        final Marca marca = this.marcaService.findById(id);

        return ResponseEntity.ok(marca);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> findAll() {
        final List<Marca> marcas = this.marcaService.findAll();
        return ResponseEntity.ok(marcas);
    }

    @GetMapping("/ativos")
    public ResponseEntity<?> findByAtivo(){
        final List<Marca> marcas = this.marcaService.findByAtivo();
        return ResponseEntity.ok(marcas);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Marca marcas){
        try{
            this.marcaService.cadastrar(marcas);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error" + e.getCause().getCause().getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") final Long id, @RequestBody final Marca marcas){
        try{
            final Marca marcaBanco = this.marcaService.findById(id);
            if(marcaBanco == null || !marcaBanco.getId().equals(marcas.getId()))
            {
                throw new RuntimeException("Não foi possível identificar o registro informado");
            }
            this.marcaService.update(marcas);
            return ResponseEntity.ok("Registro editado com sucesso");
        }   catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
        }   catch (DataIntegrityViolationException e){
                return ResponseEntity.internalServerError().body("Error" + e.getCause().getCause().getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        try {
            this.marcaService.delete(id);
            return ResponseEntity.ok("Condutor excluído com sucesso");
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error " + e.getMessage());
        }
    }
}













