# Estacionamento-Rest

// Para não perder muito tempo


insert into marcas (id, ativo, dt_atualizacao, dt_cadastro, marca) values (1,'true', 'now()', 'now', 'ford');
insert into marcas (id, ativo, dt_atualizacao, dt_cadastro, marca) values (2,'true', 'now()', 'now', 'fiat');

insert into modelos (id, ativo, dt_atualizacao, dt_cadastro, marca_id, modelo) values (1,'true', 'now()', 'now', 1, 'fiesta');
insert into modelos (id, ativo, dt_atualizacao, dt_cadastro, marca_id, modelo) values (2,'true', 'now()', 'now', 2, 'palio');

insert into veiculos (id, ativo, dt_atualizacao, dt_cadastro, ano, modelo_id, placa, tipo, cor) values (1,'true', 'now()', 'now', 'now()', 1, 'AAA4E20', 'carro', 'preto');
insert into veiculos (id, ativo, dt_atualizacao, dt_cadastro, ano, modelo_id, placa, tipo, cor) values (2,'true', 'now()', 'now', 'now()', 2, 'BBB8Q23', 'carro', 'preto');

insert into condutores (id, ativo, dt_atualizacao, dt_cadastro, nome, cpf, telefone) values (1,'true', 'now()', 'now', 'Frederico', '111.123.111-11', '(45)22222-4691');
insert into condutores (id, ativo, dt_atualizacao, dt_cadastro, nome, cpf, telefone) values (2,'true', 'now()', 'now', 'Marcus', '112.222.333-44', '(45)11111-4691');
	
insert into movimentacoes (id, ativo, dt_atualizacao, dt_cadastro, entrada,saida,tempo, condutor_id, veiculo_id) values (1,'true', 'now()', 'now','now', 'now','now()', 1, 1);
insert into movimentacoes (id, ativo, dt_atualizacao, dt_cadastro, entrada,saida,tempo, condutor_id, veiculo_id) values (2,'true', 'now()', 'now','now', 'now','now()', 2, 2);

insert into configuracao (id, ativo, dt_atualizacao, dt_cadastro, entrada,saida,tempo, condutor_id, veiculo_id) values (2,'true', 'now()', 'now','now', 'now','now()', 2, 2);
