package br.com.uniamerica.estacionamento.Repository;

import br.com.uniamerica.estacionamento.entity.Condutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfiguracaoRepository extends JpaRepository<Condutor.Configuracao, Long> {

}