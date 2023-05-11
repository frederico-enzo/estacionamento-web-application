package br.com.uniamerica.estacionamento.servece;

import br.com.uniamerica.estacionamento.Repository.MarcaRepository;
import br.com.uniamerica.estacionamento.Repository.ModeloRepository;
import br.com.uniamerica.estacionamento.entity.Condutor;
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
    
    @Transactional
    public Marca cadastrar(Marca marca){
        
        Optional<Marca> verificacao = this.marcaRepository.findById(marca.getId());
        
        if (verificacao.isPresent()){
            throw new IllegalArgumentException("Essa Marca já está cadastrada");
        } else if (marca.getNome() == null || !marca.getNome().matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("O nome da marca deve conter somente letras");
        }

        return this.marcaRepository.save(marca);
    }

    @Transactional
    public Marca update (Marca marca){

        Optional<Marca> nomeJaCadastrado = marcaRepository.findByNome(marca.getNome());
        Optional<Marca> verificacao = this.marcaRepository.findById(marca.getId());

        if (verificacao.isPresent() && !nomeJaCadastrado.get().getId().equals(marca.getId())){
            throw new IllegalArgumentException("Essa Marca já está cadastrada");
        } else if (marca.getNome() == null || !marca.getNome().matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("O nome da marca deve conter somente letras");
        }

        return this.marcaRepository.save(marca);
    }

    @Transactional
    public void delete(Long id){
        final Marca verificacao = this.marcaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não foi possível identificar o registro informado"));

        if (this.modeloRepository.existsByMarca(verificacao)) {
            throw new IllegalArgumentException("O condutor está presente em alguma movimentação e não pode ser excluído.");
        } else {
            this.marcaRepository.delete(verificacao);
        }

    }

}
