package br.com.uniamerica.estacionamento.controller;
import br.com.uniamerica.estacionamento.Repository.CondutorRepository;
import br.com.uniamerica.estacionamento.Repository.MovimentacaoRepository;
import br.com.uniamerica.estacionamento.entity.Condutor;
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
    private CondutorRepository condutorRepository;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id) {
        final Condutor condutor = this.condutorRepository.findById(id).orElse(null);

        return condutor == null
                ? ResponseEntity.badRequest().body("Nenhum valor encontrado.")
                : ResponseEntity.ok(condutor);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> findAll() {
        final List<Condutor> condutor = this.condutorRepository.findAll();

        return ResponseEntity.ok(condutor);
    }

    @GetMapping("/ativos")
    public ResponseEntity<?> findByAtivo(){
        final List<Condutor> condutor = this.condutorRepository.findByAtivo(true);

        return ResponseEntity.ok(condutor);
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Condutor condutor){
        try{
            this.condutorRepository.save(condutor);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error" + e.getCause().getCause().getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") final Long id, @RequestBody final Condutor condutor){
        try{
            final Condutor condutorBanco = this.condutorRepository.findById(id).orElse(null);

            if(condutorBanco == null || !condutorBanco.getId().equals(condutor.getId()))
            {
                throw new RuntimeException("Não foi possível identificar o registro informado");
            }

            this.condutorRepository.save(condutor);
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
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        try {
            Optional<Condutor> optionalCondutor = condutorRepository.findById(id);
            if (optionalCondutor.isPresent()) {
                Condutor condutor = optionalCondutor.get();
                if (movimentacaoRepository.existsByCondutor(condutor)) {
                    condutor.setAtivo(true);
                    condutorRepository.save(condutor);
                } else {
                    condutorRepository.delete(condutor);
                }
                return ResponseEntity.ok("Condutor excluído com sucesso");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Erro ao excluir condutor: " + e.getMessage());
        }
    }

}
      /*{
        "nome": "João da ",
        "cpf": "123.123.789-00",
        "telefone": "(11) 1233-9999",
        "cadastro": "2023-04-27T00:00:00",
        "ativo": true
        }*/