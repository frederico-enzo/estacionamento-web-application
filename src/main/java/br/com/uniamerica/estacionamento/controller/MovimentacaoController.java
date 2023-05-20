package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.Repository.MovimentacaoRepository;
import br.com.uniamerica.estacionamento.entity.Configuracao;
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
    private MovimentaçaoService movimentacaoService;
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
    public ResponseEntity<?> cadastrar(@RequestBody final Movimentacao movimentacao) {
        try {
            this.movimentacaoService.cadastrar(movimentacao);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        }
    }

    @PutMapping("/finalizar/{id}")
    public ResponseEntity<?> finalizar(@PathVariable("id") final Long id, @RequestBody final Movimentacao movimentacaos){
        try{
            final Movimentacao movimentacao = this.movimentacaoRepository.findById(id).orElse(null);
            this.movimentacaoService.update(movimentacaos);
            return ResponseEntity.ok("Data e Hora\n " +
                    "entrada:" + movimentacao.getEntrada() + "\n" +
                    "saída:" + movimentacao.getSaida() + "\n" +
                    "Condutor:" + movimentacao.getCondutor().getNome() + "\n" +
                    "Veiculo:" + movimentacao.getVeiculo().getPlaca() + "\n" +
                    "Quantidade de Horas:" + movimentacao.getTempo() + "\n" +
                    "Quantidade de Horas Desconto:" + movimentacao.getTempoDesconto() + "\n" +
                    "Valor a Pagar:" + movimentacao.getValorTotal() + "\n" +
                    "Valor de Desconto");
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
            this.movimentacaoService.delete(id);
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
