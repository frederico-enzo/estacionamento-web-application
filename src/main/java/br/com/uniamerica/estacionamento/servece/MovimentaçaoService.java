package br.com.uniamerica.estacionamento.servece;

import br.com.uniamerica.estacionamento.Repository.CondutorRepository;
import br.com.uniamerica.estacionamento.Repository.ConfiguracaoRepository;
import br.com.uniamerica.estacionamento.Repository.MovimentacaoRepository;
import br.com.uniamerica.estacionamento.Repository.VeiculoRepository;
import br.com.uniamerica.estacionamento.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;


@Service
public class MovimentaçaoService {

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    @Autowired
    private CondutorRepository condutorRepository;
    @Autowired
    private VeiculoRepository veiculoRepository;

    @Transactional
    public Movimentacao cadastrar(Movimentacao movimentacao){

    final Configuracao configuracao = this.configuracaoRepository.findById(movimentacao.getConfiguracao().getId())
            .orElseThrow(() -> new IllegalArgumentException("Configuração não correspondente"));
    final Condutor condutor = this.condutorRepository.findById(movimentacao.getCondutor().getId())
            .orElseThrow(()-> new IllegalArgumentException("Condutor não correspondente"));
    final Veiculo veiculo = this.veiculoRepository.findById(movimentacao.getVeiculo().getId())
            .orElseThrow(()-> new IllegalArgumentException("Veiculo não correspondente"));


        if (condutor == null || condutor.isAtivo()){
            throw new IllegalArgumentException("O condutor invalido");
        }
        if (veiculo == null || veiculo.isAtivo()){
            throw new IllegalArgumentException("O veiculo invalido");
        }



        BigDecimal valorTotal = BigDecimal.ZERO;
        movimentacao.setValorHora(movimentacao.getConfiguracao().getValorHora());
        movimentacao.setValorTotal(valorTotal);
        movimentacao.setValorMulta(movimentacao.getConfiguracao().getValorMultaMinuto());
        veiculo.setAtivo(true);
        condutor.setAtivo(true);
        veiculoRepository.save(veiculo);
        condutorRepository.save(condutor);
        return this.movimentacaoRepository.save(movimentacao);
    }



    @Transactional
    public void delete(Long id) {
        Optional<Movimentacao> optionalMovimentacao = movimentacaoRepository.findById(id);
        if (optionalMovimentacao.isPresent()) {
            Movimentacao movimentacao = optionalMovimentacao.get();
            if (movimentacao.getSaida() == null) {
                movimentacaoRepository.delete(movimentacao);
            } else {
                throw new IllegalArgumentException("Não é possível excluir uma movimentação com data de saída preenchida");
            }
        } else {
            throw new IllegalArgumentException("Movimentação não encontrada");
        }
    }



}
