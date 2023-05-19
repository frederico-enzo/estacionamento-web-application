package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Audited
@Table(name = "modelos", schema = "public")
@AuditTable(value = "modelos_audit", schema = "audit")
public class Modelo extends AbstractEntity{


    @Getter @Setter
    @NotEmpty(message = "O Id da marca é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marca_Id", nullable = false)
    private Marca marcaId;


    @Getter @Setter
    @NotEmpty(message = "O nome do Modelo é obrigatório")
    @Size(max= 50, message = "o nome do Modelo deve conter somente 50 caracteres")
    @UniqueElements(message = "O modelo informado ja está cadastrado")
    @Column(name = "modelo", nullable = false, unique = true, length = 50)
    private String modelo;
}