package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;


@Entity
@Audited
@Table(name = "marcas", schema = "public")
@AuditTable(value = "marcas_audit", schema = "audit")

public class Marca extends AbstractEntity{

    @Getter @Setter
    @Column(name = "marca", nullable = false, unique = true)
    private String nome;
}
