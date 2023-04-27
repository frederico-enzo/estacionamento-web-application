package br.com.uniamerica.estacionamento.entity;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;
@Entity
@Table(name = "condutores", schema = "public")
//@AuditTable(value = "condutores_audit", schema = "audit")
public class Condutor extends AbstractEntity{
    @Getter @Setter
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    @Getter @Setter
    @Column(name = "cpf", nullable = false, unique = true, length = 15)
    private String cpf;
    @Getter @Setter
    @Column(name = "telefone", nullable = false, unique = true, length = 17)
    private String telefone;
    @Getter @Setter
    @Column(name = "tempo_pago")
    private LocalTime tempoPago;
    @Getter @Setter
    @Column(name = "tempo_desconto")
    private LocalTime tempoDesconto;

    @Entity
    @Table(name = "configuracoes", schema = "public")
    public static class Configuracao  extends AbstractEntity{
        private int vagasVan;
        private int vagasCarro;
        private int vagasMoto;
        private BigDecimal valorMulta;
        private boolean desconto;
    }
}
