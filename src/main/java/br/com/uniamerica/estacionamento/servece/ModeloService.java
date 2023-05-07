package br.com.uniamerica.estacionamento.servece;

import br.com.uniamerica.estacionamento.Repository.ModeloRepository;
import br.com.uniamerica.estacionamento.Repository.VeiculoRepository;
import br.com.uniamerica.estacionamento.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;
    @Autowired
    private VeiculoRepository veiculoRepository;

    public Modelo findById(Long id) {
        Optional<Modelo> bancoModelo = this.modeloRepository.findById(id);
        if (bancoModelo.isPresent()) {
            return bancoModelo.get();
        } else {
            throw new RuntimeException("Condutor não encontrado");
        }
    }

    public List<Modelo> findAll() {

        return this.modeloRepository.findAll();
    }

    public List<Modelo> findByAtivo(){

        return this.modeloRepository.findByAtivo(true);
    }

    @Transactional
    public Modelo cadastrar(Modelo modelo) {
        Optional<Modelo> optionalModelo = modeloRepository.findByModeloAndMarcaId(modelo.getModelo(), modelo.getMarcaId());
        String nome = modelo.getModelo();
        if (nome == null || !nome.matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("O nome do modelo deve conter apenas letras ");
        }
        if (optionalModelo.isPresent()) {
            throw new IllegalArgumentException("Já existe um modelo cadastrado com este nome para esta marca.");
        }
        return modeloRepository.save(modelo);
    }
    @Transactional
    public Modelo edicao(Modelo modelo) {
        Optional<Modelo> optionalModelo = modeloRepository.findByModeloAndMarcaId(modelo.getModelo(), modelo.getMarcaId());
        if (optionalModelo.isPresent()) {
            throw new IllegalArgumentException("Já existe um modelo cadastrado com este nome para esta marca.");
        }
        return modeloRepository.save(modelo);
    }


    @Transactional
    public void deletar(Long id){
        Modelo modelo = modeloRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Modelo não encontrado"));

        List<Veiculo> veiculosComModelo = veiculoRepository.findByModeloId(modelo);

        if (!veiculosComModelo.isEmpty()) {
            throw new IllegalStateException("Não é possível excluir este modelo pois existem veículos cadastrados com ele.");
        } else {
            modeloRepository.delete(modelo);
        }
    }
}