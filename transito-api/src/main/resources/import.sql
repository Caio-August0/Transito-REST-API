insert into propietario (cpf, nome, email, telefone) values (86103620007, "Caio", "caio@gmail.com", "98201-0323");
insert into propietario (cpf, nome, email, telefone) values (77430449042, "Wanderson", "wanderson@gmail.com", "98201-0424");
insert into propietario (cpf, nome, email, telefone) values (27866514097, "Maria", "Maria@gmail.com", "98201-3341");

insert into veiculo (placa, marca, modelo, status_veiculo, propietario_cpf,data_cadastro, data_apreensao) values ("JJJ000", "FIAT", "FIAT Uno Fire", "REGULAR", 77430449042, "2023-02-15 18:00:00",null);
insert into veiculo (placa, marca, modelo, status_veiculo, propietario_cpf,data_cadastro, data_apreensao) values ("KKK111", "FIAT", "FIAT Strada", "REGULAR", 86103620007, "2023-02-15 18:00:00",null);


insert into autuacao (id, placa_veiculo, descricao, valor_multa, data_da_ocorrencia) values (1, "JJJ000", "Alta velocidade", 500.00, "2024-12-12");
insert into autuacao (id, placa_veiculo, descricao, valor_multa, data_da_ocorrencia) values (2, "JJJ000", "Sem cito", 350.00, "2024-12-12");
insert into autuacao (id, placa_veiculo, descricao, valor_multa, data_da_ocorrencia) values (3, "JJJ000", "Mexendo no telefone", 450.00, "2024-12-12");