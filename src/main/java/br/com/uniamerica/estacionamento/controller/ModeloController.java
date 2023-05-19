package br.com.uniamerica.estacionamento.controller;
import br.com.uniamerica.estacionamento.entity.*;
import br.com.uniamerica.estacionamento.servece.ModeloService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/modelo")
public class ModeloController {

    @Autowired
    private ModeloService modeloService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id) {
        final Modelo modelo = this.modeloService.findById(id);

        return ResponseEntity.ok(modelo);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Modelo>> findAll() {
        List<Modelo> modelos = this.modeloService.findAll();
        return ResponseEntity.ok(modelos);
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Modelo>> buscarAtivos() {
        List<Modelo> modelos = this.modeloService.findByAtivo();
        return ResponseEntity.ok(modelos);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Modelo modelo){
        try{
            this.modeloService.cadastrar(modelo);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error" + e.getCause().getCause().getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edicao(@PathVariable("id") final Long id, @RequestBody final Modelo modelo){
        try{
            final Modelo modeloBanco = this.modeloService.findById(id);



            this.modeloService.edicao(modelo);
            return ResponseEntity.ok("Registro editado com sucesso");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error " + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            modeloService.deletar(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Entity
    @Audited
    @Table(name = "veiculos", schema = "public")
    @AuditTable(value = "veiculo_audit", schema = "audit")
    public static class Veiculo extends AbstractEntity {

        @Getter
        @Setter
        @Column(name = "placa", length = 10, nullable = false, unique = true)
        private String placa;
        @Getter @Setter
        @Column(name = "cor", length = 20, nullable = false)
        @Enumerated(EnumType.STRING)
        private Cor cor;
        @Getter @Setter
        @ManyToOne
        @JoinColumn(name = "modelo_id", nullable = false)
        private Modelo modeloId;
        @Getter @Setter
        @Column(name = "tipo", length = 20, nullable = false)
        @Enumerated(EnumType.STRING)
        private Tipo tipo;
        @Getter @Setter
        @Column(name = "ano", nullable = false)
        private int ano;
    }
}
   /* {
            "id": 1,
            "cadastro": "2023-05-06T13:34:57.843843",
            "atualizacao": "2023-05-06T13:34:57.843843",
            "ativo": true,
            "marcaId": {
            "id": 1,
            "cadastro": "2023-05-06T13:34:11.595602",
            "atualizacao": "2023-05-06T13:34:11.595602",
            "ativo": true,
            "marca": "Ford"
            },
            "modelo": "Panpa"
            }*/