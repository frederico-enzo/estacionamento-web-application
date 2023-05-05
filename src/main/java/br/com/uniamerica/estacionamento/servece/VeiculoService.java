package br.com.uniamerica.estacionamento.servece;

import br.com.uniamerica.estacionamento.Repository.ModeloRepository;
import br.com.uniamerica.estacionamento.Repository.MovimentacaoRepository;
import br.com.uniamerica.estacionamento.Repository.VeiculoRepository;
import br.com.uniamerica.estacionamento.entity.Modelo;
import br.com.uniamerica.estacionamento.entity.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private ModeloRepository modeloRepository;
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    public List<Veiculo> findAll(){
        return veiculoRepository.findAll();
    }

    public List<Veiculo> findByAtivo(){
        return veiculoRepository.findByAtivo(true);
    }

    public Veiculo findById(Long id){
        Optional<Veiculo> bancoVeiculo = this.veiculoRepository.findById(id);
        if (bancoVeiculo.isPresent()){
            return bancoVeiculo.get();
        }else
            throw new RuntimeException("Veiculo não encontrado");
    }

    @Transactional
    public Veiculo cadastrar (Veiculo veiculo){
        String placa = veiculo.getPlaca();

        Modelo modelo = modeloRepository.findById(veiculo.getId())
                .orElseThrow(() -> new IllegalArgumentException("Modelo não encontrado"));

        if (placa == null || !placa.matches("[A-Z]{3}[0-9][A-Z][0-9]{2}")) {
            throw new IllegalArgumentException("A placa deve estar no formato AAA0A00");
        }
        if (veiculo.getAno().getYear() < 1900 || veiculo.getAno().getYear() > Year.now().getValue()) {
            throw new IllegalArgumentException("Ano do veículo inválido");
        }
        if (veiculo.getTipo() == null) {
            throw new IllegalArgumentException("O tipo tem que ser preenchido");
        }
        if (veiculo.getCor() == null) {
            throw new IllegalArgumentException("O cor tem que ser preenchido");
        }
        return this.veiculoRepository.save(veiculo);
    }

    @Transactional
    public Veiculo editar (Veiculo veiculo){
        String placa = veiculo.getPlaca();

        Modelo modelo = modeloRepository.findById(veiculo.getId())
                .orElseThrow(() -> new IllegalArgumentException("Modelo não encontrado"));

        if (placa == null || !placa.matches("[A-Z]{3}[0-9][A-Z][0-9]{2}")) {
            throw new IllegalArgumentException("A placa deve estar no formato AAA0A00");
        }
        if (veiculo.getAno().getYear() < 1900 || veiculo.getAno().getYear() > Year.now().getValue()) {
            throw new IllegalArgumentException("Ano do veículo inválido");
        }
        if (veiculo.getTipo() == null) {
            throw new IllegalArgumentException("O tipo tem que ser preenchido");
        }
        if (veiculo.getCor() == null) {
            throw new IllegalArgumentException("O cor tem que ser preenchido");
        }
        return this.veiculoRepository.save(veiculo);
    }
    public void delete(Long id) {
        Optional<Veiculo> optionalVeiculo = this.veiculoRepository.findById(id);
        if (optionalVeiculo.isPresent()) {
            Veiculo veiculo = optionalVeiculo.get();
            if (this.movimentacaoRepository.existsByveiculo(veiculo)) {
                throw new IllegalArgumentException("O veiculo está presente em alguma movimentação e não pode ser excluído.");
            } else {
                this.veiculoRepository.delete(veiculo);
            }
        } else {
            throw new IllegalArgumentException("veiculo não encontrado");
        }
    }

}
