package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.Repository.MovimentacaoRepository;
import br.com.uniamerica.estacionamento.entity.Movimentacao;
import br.com.uniamerica.estacionamento.servece.MovimentaçaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/api/movimentacao")
public class MovimentacaoController {

    @Autowired
    private MovimentaçaoService movimentaçaoService;
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable ("id") final  Long id){
        final Movimentacao movimentacao = this.movimentacaoRepository.findById(id).orElse(null);

        return  movimentacao == null
                ? ResponseEntity.badRequest().body("Movimentação não encontrada")
                : ResponseEntity.ok(movimentacao);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> findByAll(){
        final List<Movimentacao> movimentacaos = this.movimentacaoRepository.findAll();
        return ResponseEntity.ok(movimentacaos);
    }

    @GetMapping("/abertos")
    public ResponseEntity<?> findByAbertos(){
        final List<Movimentacao> movimentacaos = this.movimentacaoRepository.findBySaidaIsNull();
        return ResponseEntity.ok(movimentacaos);
    }


    @PostMapping
    public ResponseEntity<?> editar(@RequestParam final Movimentacao movimentacao){
        try{
            this.movimentaçaoService.cadastrar(movimentacao);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error" + e.getCause().getCause().getMessage());
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        try {
            this.movimentaçaoService.delete(id);
            return ResponseEntity.ok("veiculo excluído com sucesso");
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error " + e.getMessage());
        }
    }
}
