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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH'h'mm'm'")
    private LocalTime  entrada;
    @Getter @Setter
    @Column(name = "saida")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH'h'mm'm'")
    private LocalTime  saida;
    @Getter @Setter
    @Column(name = "tempo_desconto")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH'h'mm'm'")
    private LocalTime tempoDesconto;
    @Getter @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH'h'mm'm'")
    @Column(name = "tempo")
    private LocalTime tempo;
    @Getter @Setter
    @Column(name = "tempo_multao")
    private LocalTime tempoMulta;
    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false, unique = true)
    private Veiculo veiculo;
    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "configuracao_id",  unique = true)
    private Configuracao configuracao;
    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "condutor_id", nullable = false)
    private Condutor condutor;
    @Getter @Setter
    @Column(name = "valor_multa")
    private BigDecimal valorMulta;
    @Getter @Setter
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    @Getter @Setter
    @Column(name = "valor_hora" )
    private BigDecimal valorHora;
}
