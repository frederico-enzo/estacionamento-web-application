package br.com.uniamerica.estacionamento.servece;

import br.com.uniamerica.estacionamento.Repository.MarcaRepository;
import br.com.uniamerica.estacionamento.Repository.ModeloRepository;
import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.entity.Modelo;
import br.com.uniamerica.estacionamento.entity.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private ModeloRepository modeloRepository;

    public Marca findById(Long id){
        Optional<Marca> bancoMarca = this.marcaRepository.findById(id);
        if (bancoMarca.isPresent()){
            return bancoMarca.get();
        }else
            throw new IllegalArgumentException("Indentificar não encontrado");
    }

    public List<Marca> findAll(){
        return marcaRepository.findAll();
    }

    public List<Marca> findByAtivo(){
        return marcaRepository.findByAtivo(true);
    }

    @Transactional
    public Marca cadastrar (Marca marca){
        Optional<Marca> bancoMarca = this.marcaRepository.findById(marca.getId());
        String nome = marca.getMarca();

        if (bancoMarca.isPresent()){
            throw new IllegalArgumentException("Essa marca já está cadastrada");}
        if (nome == null || !nome.matches("[a-zA-Z\\s]+")){
            throw new IllegalArgumentException("Já existe um marca cadastrado com este nome.");
        }
        return marcaRepository.save(marca);
    }

    @Transactional
    public Marca update (Marca marca){
        Optional<Marca> bancoMarca = this.marcaRepository.findById(marca.getId());
        String nome = marca.getMarca();

        if (bancoMarca.isPresent()){
            throw new IllegalArgumentException("Essa marca já está cadastrada");}
        if (nome == null || !nome.matches("[a-zA-Z\\s]+")){
            throw new IllegalArgumentException("Já existe um marca cadastrado com este nome.");
        }
        return marcaRepository.save(marca);
    }

    @Transactional
    public void delete(Long id) {
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Marca não encontrada"));

        List<Modelo> modelosComEssaMarca = this.modeloRepository.findByMarcaId(id);

        if (!modelosComEssaMarca.isEmpty()) {
            throw new IllegalStateException("Não é possível excluir esta marca pois existem modelos cadastrados com ela.");
        } else {
            marcaRepository.delete(marca);
        }
    }











}
