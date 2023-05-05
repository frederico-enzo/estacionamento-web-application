package br.com.uniamerica.estacionamento.controller;
import br.com.uniamerica.estacionamento.Repository.CondutorRepository;
import br.com.uniamerica.estacionamento.Repository.MovimentacaoRepository;
import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.servece.CondutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/condutor")
public class CondutorController {

    /*
      @Autowired = Usado para acessar outras Classes
      @RequestMapping = Mapeia os metodos da controller / Define o URI para  qual o metodo deve responder
      @GetMapping = é usada para mapear uma solicitação HTTP GET
      ResponseEntity = usado para enviar informações adicionais no cabeçalho ou corpo da resposta
      <?> = usada para indicar que um tipo específico pode ser usado, mas que ainda não é conhecido ou não é relevante em um determinado contexto
      @PathVariable =  é usada para mapear variáveis de caminho de URL em parâmetros de método
      orElse =   é usado para fornecer um valor padrão quando um valor não está presente
      badRequest = é usado para criar uma resposta HTTP 400 (Bad Request)

    */



    @Autowired
    private  CondutorService condutorService;

    @Autowired
    private CondutorRepository condutorRepository;


    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id) {
        final Condutor condutor = this.condutorService.findById(id);

        return ResponseEntity.ok(condutor);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Condutor>> findAll() {
        List<Condutor> condutores = this.condutorService.findAll();
        return ResponseEntity.ok(condutores);
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Condutor>> buscarAtivos() {
        List<Condutor> condutoresAtivos = this.condutorService.findByAtivo();
        return ResponseEntity.ok(condutoresAtivos);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Condutor condutor){
        try{
            this.condutorService.cadastrar(condutor);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error" + e.getCause().getCause().getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") final Long id, @RequestBody final Condutor condutor){
        try{
            final Condutor condutorBanco = this.condutorService.findById(id);

            if(condutorBanco == null || !condutorBanco.getId().equals(condutor.getId()))
            {
                throw new RuntimeException("Não foi possível identificar o registro informado");
            }

            this.condutorService.editar(condutor);
            return ResponseEntity.ok("Registro editado com sucesso");
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error " + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error " + e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        try {
            this.condutorService.delete(id);
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


