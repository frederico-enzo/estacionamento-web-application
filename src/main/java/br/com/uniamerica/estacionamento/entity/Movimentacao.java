package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
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
    private LocalDate entrada;
    @Getter @Setter
    @Column(name = "saida", nullable = false)
    private LocalDate saida;
    @Getter @Setter
    @Column(name = "tempo_desconto")
    private LocalTime tempoDesconto;
    @Getter @Setter
    @Column(name = "tempo")
    private Duration tempo;
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
