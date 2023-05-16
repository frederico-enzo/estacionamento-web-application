package br.com.uniamerica.estacionamento.servece;

import br.com.uniamerica.estacionamento.Repository.MarcaRepository;
import br.com.uniamerica.estacionamento.Repository.ModeloRepository;
import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.entity.Modelo;
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

    @Transactional(rollbackFor = Exception.class)
    public Marca newMarca(final Marca marca) {

        Optional<Marca> nomeJaCadastrado = marcaRepository.findByNome(marca.getNome());
        String nome = marca.getNome();

        if (nomeJaCadastrado.isPresent()){
            throw new IllegalArgumentException("Já há um marca cadastrado com esse nome");
        }else if (nome == null || !nome.matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("O nome deve conter apenas letras ");
        }

        marca.setAtivo(false);
        return marcaRepository.save(marca);
    }


    @Transactional(rollbackFor = Exception.class)
    public Marca update (Marca marca){
        marca.setAtivo(false);
        Optional<Marca> nomeJaCadastrado = marcaRepository.findByNome(marca.getNome());
        Optional<Marca> verificacao = this.marcaRepository.findById(marca.getId());

        if (verificacao.isPresent() && !nomeJaCadastrado.get().getId().equals(marca.getId())){
            throw new IllegalArgumentException("Essa Marca já está cadastrada");
        } else if (marca.getNome() == null || !marca.getNome().matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("O nome da marca deve conter somente letras");
        }

        return this.marcaRepository.save(marca);
    }
    @Transactional(rollbackFor = Exception.class)
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
