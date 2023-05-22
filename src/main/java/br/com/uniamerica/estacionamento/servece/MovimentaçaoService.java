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
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
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
                .orElseThrow(() -> new IllegalArgumentException("Condutor não correspondente"));

        final Veiculo veiculo = this.veiculoRepository.findById(movimentacao.getVeiculo().getId())
                .orElseThrow(() -> new IllegalArgumentException("Veiculo não correspondente"));

        if (condutor == null || condutor.isAtivo()){
            throw new IllegalArgumentException("O condutor invalido");
        }
        if (veiculo == null || veiculo.isAtivo()){
            throw new IllegalArgumentException("O veiculo invalido");
        }
        BigDecimal valorTotal = BigDecimal.ZERO;
        BigDecimal valorMulta = BigDecimal.ZERO;
        LocalTime tempo = LocalTime.of(0, 0);
        LocalTime tempoDesconto = LocalTime.of(0, 0);

        movimentacao.setTempoDesconto(tempoDesconto);
        movimentacao.setSaida(tempo);
        movimentacao.setTempo(tempo);
        movimentacao.setTempoMulta(tempo);
        movimentacao.setTempoDesconto(tempo);
        movimentacao.setValorTotal(valorTotal);
        movimentacao.setValorMulta(valorMulta);
        movimentacao.setValorHora(configuracao.getValorHora());

        veiculo.setAtivo(true);
        condutor.setAtivo(true);
        movimentacao.setAtivo(true);
        veiculoRepository.save(veiculo);
        condutorRepository.save(condutor);
        return this.movimentacaoRepository.save(movimentacao);
    }

    @Transactional
    public Movimentacao update(Movimentacao movimentacao) {
        final Configuracao configuracao = this.configuracaoRepository.findById(movimentacao.getConfiguracao().getId())
                .orElseThrow(() -> new IllegalArgumentException("Configuração não correspondente"));

        final Condutor condutor = this.condutorRepository.findById(movimentacao.getCondutor().getId())
                .orElseThrow(() -> new IllegalArgumentException("Condutor não correspondente"));

        final Veiculo veiculo = this.veiculoRepository.findById(movimentacao.getVeiculo().getId())
                .orElseThrow(() -> new IllegalArgumentException("Veiculo não correspondente"));

        BigDecimal valorTotal = movimentacao.getValorTotal();
        LocalTime entrada = movimentacao.getEntrada();
        LocalTime saida = movimentacao.getSaida();
        LocalTime tempoDescMov = movimentacao.getTempoDesconto();
        int horasDesconto = condutor.getHorasDesconto();
        int minutosDesconto = condutor.getMinutosDesconto();
        int totalMinMovDesconto = tempoDescMov.getHour() * 60 + tempoDescMov.getMinute();
        int totalMinDesconto = horasDesconto * 60 + minutosDesconto;
        long saidaMinutos = saida.getHour() * 60 + saida.getMinute();
        long fimExpedienteMin = configuracao.getFimExpediente().getHour() * 60;

        //Se o tempo do condutor for menor que o desconto incerido em finlizaçao da movimentaçao
        if (totalMinMovDesconto > totalMinDesconto) {
            throw new IllegalArgumentException("Tempo de desconto excede o tempo disponível do condutor");
        }
        //calcula o valor minuto com base na hora
        BigDecimal valorPorMinuto = configuracao.getValorHora().divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP);

        //calculando o tempo que ó condutor ficou estacionado (usei duration mas nao gostei)
        Duration duracao = Duration.between(entrada, saida);
        long segundos = duracao.toSeconds();
        LocalTime tempo = LocalTime.ofSecondOfDay(segundos);

        if (saidaMinutos > fimExpedienteMin) {
            long timeOut = saidaMinutos - fimExpedienteMin; // calculo quanto tempo ele ficou a mais
            int min = (int) (timeOut % 60); // calculo para saber as minutos que ele ficou a mais (% pela o valor do resto da operaçao que seria os minutos)
            int hrs = (int) (timeOut / 60); // calculo para saber as horas que ele ficou a mais
            LocalTime tempoMulta = LocalTime.of(hrs, min);
            movimentacao.setTempoMulta(tempoMulta);
            BigDecimal valorMulta = BigDecimal.valueOf(timeOut).multiply(configuracao.getValorMultaMinuto());

            long tempoEmMinutos = ChronoUnit.MINUTES.between(entrada, saida);
            int horas = (int) (tempoEmMinutos / 60);
            int minutos = (int) (tempoEmMinutos % 60);
            BigDecimal valorTotalHoras = BigDecimal.valueOf(horas).multiply(configuracao.getValorHora());
            BigDecimal valorTotalMinutos = BigDecimal.valueOf(minutos).multiply(valorPorMinuto);
            valorTotal = valorTotalHoras.add(valorTotalMinutos);

            valorTotal = valorTotal.add(valorMulta);

            BigDecimal descontoTotal = BigDecimal.ZERO;

            if (totalMinMovDesconto > 0) {
                BigDecimal valorDescontoHora = configuracao.getValorHora();
                BigDecimal valorDescontoMinuto = valorPorMinuto;

                BigDecimal descontoHoras = valorDescontoHora.multiply(BigDecimal.valueOf(horasDesconto));
                BigDecimal descontoMinutos = valorDescontoMinuto.multiply(BigDecimal.valueOf(minutosDesconto));

                descontoTotal = descontoHoras.add(descontoMinutos);

                valorTotal = valorTotal.subtract(descontoTotal);
            }
            movimentacao.setValorTotal(valorTotal);
            movimentacao.setValorMulta(valorMulta);

            String tempoPago = condutor.getTempoPago();
            int hPago = condutor.getHorasPago();
            int mPago = condutor.getMinutosPago();

            String tempoDesconto = condutor.getTempoDesconto();
            int hDesconto = condutor.getHorasDesconto();
            int mDesconto = condutor.getMinutosDesconto();

            hPago = hPago + horas;
            mPago = mPago + minutos;
            if (mPago >= 59) {
                hPago = hPago + 1;
                mPago = 0;
            }

            int contador = condutor.getContadorDeDesconto();
            contador = contador + horas;

            if (contador >= 60) {
                hDesconto += 5;
                contador = 0;
            }

            condutor.setContadorDeDesconto(contador);
            condutor.setHorasDesconto(hDesconto);
            condutor.setMinutosDesconto(mDesconto);
            tempoDesconto = hDesconto + " horas " + mDesconto + " minutos";
            condutor.setTempoDesconto(tempoDesconto);
            condutor.setContadorDeDesconto(contador);

            condutor.setHorasPago(hPago);
            condutor.setMinutosPago(mPago);
            tempoPago = hPago + " horas " + mPago + " minutos";
            condutor.setTempoPago(tempoPago);
            movimentacao.setValorTotal(valorTotal);
            movimentacao.setValorMulta(valorMulta);

        } else {
            BigDecimal valorMulta = BigDecimal.ZERO;
            long tempoEmMinutos = ChronoUnit.MINUTES.between(entrada, saida);
            int horas = (int) (tempoEmMinutos / 60);
            int minutos = (int) (tempoEmMinutos % 60);
            BigDecimal valorTotalHoras = BigDecimal.valueOf(horas).multiply(configuracao.getValorHora());
            BigDecimal valorTotalMinutos = BigDecimal.valueOf(minutos).multiply(valorPorMinuto);
            valorTotal = valorTotalHoras.add(valorTotalMinutos);
            BigDecimal descontoTotal = BigDecimal.ZERO;

            if (totalMinMovDesconto > 0) {
                BigDecimal valorDescontoHora = configuracao.getValorHora();
                BigDecimal valorDescontoMinuto = valorPorMinuto;

                BigDecimal descontoHoras = valorDescontoHora.multiply(BigDecimal.valueOf(horasDesconto));
                BigDecimal descontoMinutos = valorDescontoMinuto.multiply(BigDecimal.valueOf(minutosDesconto));

                descontoTotal = descontoHoras.add(descontoMinutos);

                valorTotal = valorTotal.subtract(descontoTotal);
            }
            String tempoPago = condutor.getTempoPago();
            int hPago = condutor.getHorasPago();
            int mPago = condutor.getMinutosPago();

            String tempoDesconto = condutor.getTempoDesconto();
            int hDesconto = condutor.getHorasDesconto();
            int mDesconto = condutor.getMinutosDesconto();

            hPago = hPago + horas;
            mPago = mPago + minutos;
            if (mPago >= 60) {
                hPago = hPago + 1;
                mPago = 0;
            }

            int contador = condutor.getContadorDeDesconto();
            contador += horas;

            if (contador >= 50) {
                hDesconto += 5;
                contador = 0;
            }

            condutor.setContadorDeDesconto(contador);
            condutor.setHorasDesconto(hDesconto);
            condutor.setMinutosDesconto(mDesconto);
            tempoDesconto = hDesconto + " horas " + mDesconto + " minutos";
            condutor.setTempoDesconto(tempoDesconto);
            condutor.setContadorDeDesconto(contador);

            condutor.setHorasPago(hPago);
            condutor.setMinutosPago(mPago);
            tempoPago = hPago + " horas " + mPago + " minutos";
            condutor.setTempoPago(tempoPago);
            movimentacao.setValorTotal(valorTotal);
            movimentacao.setValorMulta(BigDecimal.ZERO);

        }

        int horasUtilizadas = totalMinMovDesconto / 60;
        int minutosUtilizados = totalMinMovDesconto % 60;

        condutor.setHorasDesconto(horasDesconto - horasUtilizadas);
        condutor.setMinutosDesconto(minutosDesconto - minutosUtilizados);
        veiculo.setAtivo(false);
        condutor.setAtivo(false);
        movimentacao.setAtivo(false);
        movimentacao.setValorHora(configuracao.getValorHora());
        movimentacao.setTempo(tempo);
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
