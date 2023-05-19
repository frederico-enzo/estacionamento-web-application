package br.com.uniamerica.estacionamento.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalTime;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
@Entity
@Audited
@Table(name = "movimentacoes", schema = "public")
@AuditTable(value = "movimentacao_audit", schema = "audit")
public class Movimentacao extends AbstractEntity{

    @Getter @Setter
    @Column(name = "entrada", nullable = false)
    @NotNull(message = "A entrada é obrigatória")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH'h'mm'm'")
    private LocalTime  entrada;

    @Getter @Setter
    @Column(name = "saida")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH'h'mm'm'")
    private LocalTime  saida = LocalTime.of(0, 0);;

    @Getter @Setter
    @Column(name = "tempo_desconto")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH'horas'mm'minutos'")
    private LocalTime tempoDesconto;

    @Getter @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH'horas'mm'minutos'")
    @Column(name = "tempo")
    private LocalTime tempo = LocalTime.of(0, 0);;

    @Getter @Setter
    @Column(name = "tempo_multao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH':'mm'")
    private LocalTime tempoMulta = LocalTime.of(0, 0);

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @NotEmpty(message = "O Id do veiculo é obrigatório")
    @JoinColumn(name = "veiculo_id", nullable = false, unique = true)
    private Veiculo veiculo;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @NotEmpty(message = "O Id da configuracao é obrigatório")
    @JoinColumn(name = "configuracao_id", nullable = false, unique = true)
    private Configuracao configuracao;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @NotEmpty(message = "O Id do condutor é obrigatório")
    @JoinColumn(name = "condutor_id", nullable = false)
    private Condutor condutor;

    @Getter @Setter
    @Column(name = "valor_multa")
    private BigDecimal valorMulta = BigDecimal.ZERO;

    @Getter @Setter
    @Column(name = "valor_total")
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @Getter @Setter
    @Column(name = "valor_hora" )
    private BigDecimal valorHora = BigDecimal.ZERO;
}
