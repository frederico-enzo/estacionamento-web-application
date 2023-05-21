package br.com.uniamerica.estacionamento.entity;
import jakarta.persistence.Transient;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


import java.time.Duration;

@Entity
@Audited
@Table(name = "condutores", schema = "public")
@AuditTable(value = "condutores_audit", schema = "audit")
public class Condutor extends AbstractEntity{


    @Getter @Setter
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Getter @Setter
    @Column(name = "cpf", nullable = false, unique = true, length = 20)
    private String cpf;

    @Getter @Setter
    @Column(name = "telefone", nullable = false, length = 20)
    private String telefone;

    @Getter @Setter
    private int horasDesconto = 0;

    @Getter @Setter
    private int minutosDesconto = 0;

    @Getter @Setter
    private int horasPago = 0;

    @Getter @Setter
    private int minutosPago = 0;

    @Getter @Setter
    private int contadorDeDesconto = 50;

    @Getter @Setter
    @Column(name = "tempo_Pago")
    private String tempoPago = horasPago + "hrs" + minutosPago + "mim";

    @Getter @Setter
    @Column(name = "tempo_desconto")
    private String tempoDesconto =  horasDesconto + "hrs" + minutosDesconto + "mim";

}
