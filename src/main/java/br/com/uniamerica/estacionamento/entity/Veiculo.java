package br.com.uniamerica.estacionamento.entity;
import jakarta.validation.constraints.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;


@Entity
@Audited
@Table(name = "veiculos", schema = "public")
@AuditTable(value = "veiculo_audit", schema = "audit")
public class Veiculo extends AbstractEntity {

    @NotEmpty(message = "O placa é obrigatório")
    @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres")
    @Pattern(regexp = "[A-Z]{3}[0-9][A-Z][0-9]{2}|([A-Z]{3}-[0-9]{4})", message = "A placa deve estar no formato AAA0A00 ou AAA-0000")
    @UniqueElements(message = "A placa  informada já está cadastrada")
    @Getter @Setter
    @Column(name = "placa", length = 10, nullable = false, unique = true)
    private String placa;

    @Getter @Setter
    @NotNull(message = "A cor é obrigatória")
    @Length(max = 5, message = "O tipo deve ter no máximo 15 caracteres")
    @Pattern(regexp = "^(AZUL|PRETO|CINZA|MARRON|VERMELHO|PRATA|BRANCO|AMARELO|VERDE)$", message = "A cor deve ser um valor válido (AZUL|PRETO|CINZA|MARRON|VERMELHO|PRATA|BRANCO|AMARELO|VERDE)")
    @Column(name = "cor", length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    private Cor cor;

    @ManyToOne
    @NotNull(message = "O modelo é obrigatório")
    @Getter @Setter
    @JoinColumn(name = "modelo_id", nullable = false)
    private Modelo modeloId;


    @NotNull(message = "O tipo é obrigatório")
    @Length(max = 5, message = "O tipo deve ter no máximo 5 caracteres")
    @Pattern(regexp = "^(CARRO|VAN|MOTO)$", message = "O tipo deve ser um valor válido (CARRO|VAN|MOTO)")
    @Getter @Setter
    @Column(name = "tipo", length = 5, nullable = false)
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @NotNull(message = "O ano é obrigatório")
    @Min(value = 1900, message = "O ano deve ser maior que 1900")
    @Max(value = 2023, message = "O ano deve ser menor ou igual ao ano 2023")
    @Getter @Setter
    @Column(name = "ano", nullable = false)
    private int ano;
}
