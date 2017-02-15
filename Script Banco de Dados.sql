drop database GoldFork;
create database GoldFork;
use GoldFork;

####                ####
###### T A B L E S #####
####                ####

create table dono(
	id_dono int primary key auto_increment,
	nome varchar(50) not null,
	login varchar(50) not null,
	senha varchar(50) not null
);

create table lanchonete(
	id_lanchonete int primary key auto_increment,
	nome varchar(50) not null,
	telefone varchar(13) not null,
	cnpj varchar(18) not null,
	endereco_rua varchar(100) not null,
	endereco_numero int not null,
	endereco_bairro varchar(50) not null,
	endereco_cidade varchar(50) not null,
	endereco_cep varchar(9) not null,
	endereco_estado varchar(2) not null,
	cod_dono int not null,

	constraint fk_cod_dono_lanchonete foreign key(cod_dono) references dono(id_dono)	
);

create table gerente(
	id_gerente int primary key auto_increment,
	nome varchar(50) not null,
	telefone varchar(13) not null,
	cpf varchar(14) not null,
	login varchar(50) not null,
	senha varchar(50) not null,
	cod_lanchonete int not null,
	ativo boolean not null,
	
	constraint fk_cod_loja_gerente foreign key (cod_lanchonete) references lanchonete(id_lanchonete)
);

create table caixa(
	id_caixa int primary key auto_increment,
	nome varchar(50) not null,
	telefone varchar(13) not null,
	cpf varchar(14) not null,
	login varchar(50) not null,
	senha varchar(50) not null,
	cod_lanchonete int not null,
	ativo boolean not null,

	constraint fk_cod_loja_caixa foreign key (cod_lanchonete) references lanchonete(id_lanchonete)
);

create table cozinheiro(
	id_cozinheiro int primary key auto_increment,
	nome varchar(50) not null,
	telefone varchar(13) not null,
	cpf varchar(14) not null,
	cod_lanchonete int not null,
	ativo boolean not null,
	
	constraint fk_cod_loja_cozinheiro foreign key (cod_lanchonete) references lanchonete(id_lanchonete)
);

create table faxineiro(
	id_faxineiro int primary key auto_increment,
	nome varchar(50) not null,
	telefone varchar(13) not null,
	cpf varchar(14) not null,
	cod_lanchonete int not null,
	ativo boolean not null,
	
	constraint fk_cod_loja_faxineiro foreign key (cod_lanchonete) references lanchonete(id_lanchonete)
);

create table produto_atomico(
	id_produto_atomico int primary key auto_increment,
	nome varchar(100) not null,
	unidade_medida varchar(25) not null
);

create table produto(
	id_produto int primary key auto_increment,
	nome varchar(200) not null,
	preco decimal(7,2) not null,
    ativo boolean not null
);

create table estoqueLoja(
	cod_lanchonete int not null,
	cod_produto_atomico int not null,
	quantidade decimal(10,3) not null,

	primary key (cod_lanchonete, cod_produto_atomico),
    
	constraint fk_cod_produto_atomico_estoqueLoja foreign key (cod_produto_atomico) references produto_atomico(id_produto_atomico),
	constraint fk_cod_loja_estoqueLoja foreign key (cod_lanchonete) references lanchonete(id_lanchonete)
);

create table composicao_produto(
	cod_produto int not null,
	cod_produto_atomico int not null,
	quantidade decimal(10,3) not null,

	primary key (cod_produto, cod_produto_atomico),
	
	constraint fk_cod_produto_composicao foreign key (cod_produto) references produto(id_produto),
	constraint fk_cod_produto_atomico_composicao foreign key (cod_produto_atomico) references produto_atomico(id_produto_atomico)	
);

create table fornecedor(
	id_fornecedor int primary key auto_increment,
	nome varchar(200) not null,
	telefone varchar(13) not null,
	cnpj varchar(18) not null,
    ativo boolean not null
);

create table fornecimento(
	cod_fornecedor int not null,
	cod_lanchonete int not null,

	primary key (cod_fornecedor, cod_lanchonete),
	
	constraint fk_cod_fornecedor_fornecimento foreign key (cod_fornecedor) references fornecedor(id_fornecedor),
	constraint fk_cod_lanchonete_fornecimento foreign key (cod_lanchonete) references lanchonete(id_lanchonete)
);

create table nota_de_compra(
	id_nota_compra int primary key auto_increment,
	data date not null,
	valor_total decimal (7,2) not null,
	cod_fornecedor int not null,
	cod_lanchonete int not null,
	
	constraint fk_cod_fornecedor_nota_de_compra foreign key (cod_fornecedor) references fornecedor(id_fornecedor),
	constraint fk_cod_lanchonete_nota_de_compra foreign key (cod_lanchonete) references lanchonete(id_lanchonete)
);

create table itens_nota_compra(
	cod_nota_de_compra int not null,
	cod_produto_atomico int not null,
	quantidade decimal (7,2) not null,
	valor_unitario decimal (7,2) not null,

	primary key (cod_nota_de_compra, cod_produto_atomico),

	constraint fk_cod_nota_de_compra_itens foreign key (cod_nota_de_compra) references nota_de_compra(id_nota_compra),
	constraint fk_cod_produto_atomico_itens foreign key (cod_produto_atomico) references produto_atomico(id_produto_atomico)
);

create table nota_de_venda(
	id_nota_venda int primary key auto_increment,
    data date not null,
    valor_total decimal(7,2) not null,
    cod_caixa int not null,
    cod_lanchonete int not null,
    
    constraint fk_cod_caixa_nota_de_venda foreign key(cod_caixa) references caixa(id_caixa),
    constraint fk_cod_lanchonete_nota_de_venda foreign key(cod_lanchonete) references lanchonete(id_lanchonete)
);

create table itens_nota_venda(
	cod_nota_venda int not null,
    cod_produto int not null,
    quantidade int not null,
    valor_unitario decimal(7,2) not null,
    
    primary key(cod_nota_venda, cod_produto),
    
    constraint fk_cod_nota_venda_itens foreign key(cod_nota_venda) references nota_de_venda(id_nota_venda),
    constraint fk_cod_produto_itens foreign key(cod_produto) references produto(id_produto)
);

create table pagamento_dinheiro(
	id_pagamento int primary key auto_increment,
	valor decimal(7,2) not null,
    cod_nota_venda int not null,
    
    constraint fk_cod_nota_venda_pagamento_dinheiro foreign key(cod_nota_venda) references nota_de_venda(id_nota_venda)
);

create table pagamento_cartao(
	id_pagamento int primary key auto_increment,
	valor decimal(7,2) not null,
    cod_nota_venda int not null,
    ultimos_digitos decimal(4) not null,
    bandeira varchar(20) not null,
    tipo varchar(7) not null,
    
    constraint fk_cod_nota_venda_pagamento_cartao foreign key(cod_nota_venda) references nota_de_venda(id_nota_venda)
);

create table log_insert(
	id int primary key auto_increment,
	data timestamp not null,
    permissao_usuario int not null,
    cod_usuario int not null,
    cod_loja int not null,
    sentenca longtext
);

create table log_update(
	id int primary key auto_increment,
	data timestamp not null,
    permissao_usuario int not null,
    cod_usuario int not null,
    cod_loja int not null,
    sentenca longtext
);

create table log_delete(
	id int primary key auto_increment,
	data timestamp not null,
    permissao_usuario int not null,
    cod_usuario int not null,
    cod_loja int not null,
    sentenca longtext
);

####                        ####
###### P R O C E D U R E S #####
####                        ####

DELIMITER $$
DROP PROCEDURE IF EXISTS verificaIfLoginCaixaExistente $$
CREATE PROCEDURE verificaIfLoginCaixaExistente(login varchar(50))
BEGIN
	set @x= (SELECT COUNT(*) from caixa where caixa.login = login and caixa.ativo = true);
	if(@x = 0) then select "false" as Msg;
	else select "true" as Msg;
	end if;
END $$

DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS verificaIfLoginGerenteExistente $$
CREATE PROCEDURE verificaIfLoginGerenteExistente(login varchar(50))
BEGIN
	set @x = (SELECT COUNT(*) from gerente where gerente.login = login and gerente.ativo = true);
	if(@x = 0) then select "false" as Msg;
	else select "true" as Msg;
	end if;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS verificaIfCpfFuncionarioExistente $$
CREATE PROCEDURE verificaIfCpfFuncionarioExistente(cpf varchar(14))
BEGIN
	set @x = (SELECT COUNT(*) from gerente where gerente.cpf = cpf and gerente.ativo = true);
	set @y = (SELECT COUNT(*) from caixa where caixa.cpf = cpf and caixa.ativo = true);
	set @z = (SELECT COUNT(*) from faxineiro where faxineiro.cpf = cpf and faxineiro.ativo = true);
	set @w = (SELECT COUNT(*) from cozinheiro where cozinheiro.cpf = cpf and cozinheiro.ativo = true);
	if((@x=0)&&(@y=0)&&(@z=0)&&(@w=0)) then select "false" as Msg;
	else select "true" as Msg;
	end if;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS verificaIfProdutoAtomicoComNomeIgual $$
CREATE PROCEDURE verificaIfProdutoAtomicoComNomeIgual(nome varchar(50))
BEGIN
	set @x = (SELECT COUNT(*) from produto_atomico where produto_atomico.nome = nome);
	if(@x = 0) then select "false" as Msg;
	else select "true" as Msg;
	end if;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS verificaIfProdutoComNomeIgual $$
CREATE PROCEDURE verificaIfProdutoComNomeIgual(nome varchar(50))
BEGIN
	set @x = (SELECT COUNT(*) from produto where produto.nome = nome and produto.ativo = true);
	if(@x = 0) then select "false" as Msg;
	else select "true" as Msg;
	end if;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS verificaIfFornecedorComNomeIgualUtilizandoIdDono $$
CREATE PROCEDURE verificaIfFornecedorComNomeIgualUtilizandoIdDono(nomeFornecedor varchar(200), idDono int)
BEGIN
	set @x = (
		select count(*) from dono 
		inner join lanchonete on idDono = lanchonete.cod_dono
		inner join fornecimento on fornecimento.cod_lanchonete = lanchonete.id_lanchonete
		inner join fornecedor on fornecedor.id_fornecedor = fornecimento.cod_fornecedor
		where fornecedor.nome = nomeFornecedor and fornecedor.ativo = true
    );
	if(@x = 0) then select "false" as Msg;
	else select "true" as Msg;
	end if;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS verificaIfFornecedorComCNPJIgualUtilizandoIdDono $$
CREATE PROCEDURE verificaIfFornecedorComCNPJIgualUtilizandoIdDono(cnpjFornecedor varchar(18), idDono int)
BEGIN
	set @x = (
		select count(*) from dono 
		inner join lanchonete on idDono = lanchonete.cod_dono
		inner join fornecimento on fornecimento.cod_lanchonete = lanchonete.id_lanchonete
		inner join fornecedor on fornecedor.id_fornecedor = fornecimento.cod_fornecedor
		where fornecedor.cnpj = cnpjFornecedor and fornecedor.ativo = true
    );
	if(@x = 0) then select "false" as Msg;
	else select "true" as Msg;
	end if;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS atualizarEstoque $$
CREATE PROCEDURE atualizarEstoque(id_lanchonete int, id_produto_atomico int, quantidade decimal(10,3))
BEGIN
	set @x = (
    select count(*) from estoqueLoja where estoqueLoja.cod_lanchonete = id_lanchonete and estoqueloja.cod_produto_atomico = id_produto_atomico
    );
	if(@x = 0) then insert into estoqueLoja(cod_lanchonete, cod_produto_atomico, quantidade) values(id_lanchonete, id_produto_atomico, quantidade);
	else update estoqueLoja set estoqueLoja.quantidade = estoqueLoja.quantidade + quantidade where estoqueLoja.cod_lanchonete = id_lanchonete and estoqueLoja.cod_produto_atomico = id_produto_atomico;
	end if;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS validaLogin $$
CREATE PROCEDURE validaLogin(login varchar(50), senha varchar(50), selec int)
BEGIN
	if(selec = 1) then select * from dono where dono.login = login and dono.senha=senha;
    end if;
    
    if(selec = 2) then select * from caixa where caixa.login = login and caixa.senha = senha and caixa.ativo = true;
    end if;
    
    if(selec = 3) then select * from gerente where gerente.login = login and gerente.senha = senha and caixa.ativo = true;
    end if;
    
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS verificaDisponibilidadeNoEstoque $$
CREATE PROCEDURE verificaDisponibilidadeNoEstoque(id_lanchonete int, id_produto_atomico int, quantidade decimal(10,3))
BEGIN
	set @x = (select count(*) from estoqueLoja where
    estoqueLoja.cod_lanchonete = id_lanchonete and 
    estoqueloja.cod_produto_atomico = id_produto_atomico and 
    estoqueLoja.quantidade >= quantidade);
    
    if(@x = 0) then select "false" as Msg;
	else select "true" as Msg;
	end if;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS atualizarEstoqueSaida $$
CREATE PROCEDURE atualizarEstoqueSaida(id_lanchonete int, id_produto_atomico int, quantidade decimal(10,3))
BEGIN
	update estoqueLoja set estoqueLoja.quantidade = estoqueLoja.quantidade - quantidade where estoqueLoja.cod_lanchonete = id_lanchonete and estoqueLoja.cod_produto_atomico = id_produto_atomico;
END 
$$ DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS validaSenha $$
CREATE PROCEDURE validaSenha(senha varchar(50), selec int, id_usuario int)
BEGIN
	#dono
	if(selec = 0) then
		set @x = (select count(*) from dono where dono.id_dono = id_usuario and dono.senha = senha);
        if(@x = 1) then select "true" as Msg;
        else select "false" as Msg;
        end if;
    end if;
    
    #gerente
    if(selec = 1) then
		set @x = (select count(*) from caixa where caixa.id_caixa = id_usuario and caixa.senha = senha);
        if(@x = 1) then select "true" as Msg;
        else select "false" as Msg;
        end if;
    end if;
    
    #caixa
    if(selec = 2) then select * from gerente where gerente.login = login and gerente.senha = senha;
		set @x = (select count(*) from gerente where gerente.id_gerente = id_usuario and gerente.senha = senha);
        if(@x = 1) then select "true" as Msg;
        else select "false" as Msg;
        end if;
    end if;
    
END 
$$ DELIMITER ;

####                  ####
###### I N S E R T S #####
####                  ####

#donos
insert into dono(nome, login, senha) values ("Eymar", "eymar@dono.goldfork.com", "eymar");

#lojas
INSERT INTO lanchonete(nome, telefone, cnpj, endereco_rua, endereco_numero, endereco_bairro, endereco_cidade, endereco_cep, endereco_estado, cod_dono) VALUES("BURGUER QUEEN LATORRE","(11)3446-2510","12.345.678/9012-34","Caetés",500,"Jardim Caiçara","Presidente Prudente","19061-390","SP","1");
INSERT INTO lanchonete(nome, telefone, cnpj, endereco_rua, endereco_numero, endereco_bairro, endereco_cidade, endereco_cep, endereco_estado, cod_dono) VALUES("BURGUER QUEEN OSASCO","(15)6198-4984","19.819.819/8198-49","Rua das Andorinhas",1598,"Pacaembu","São Paulo","15987-984","SP","1");
INSERT INTO lanchonete(nome, telefone, cnpj, endereco_rua, endereco_numero, endereco_bairro, endereco_cidade, endereco_cep, endereco_estado, cod_dono) VALUES("BURGUER QUEEN GUARULHOS","(98)4984-9849","49.841.651/3135-13","Rua das Cachoeiras",158,"Santo Antônio","Guarulhos","15984-878","SP","1");

#gerente
INSERT INTO gerente(nome, telefone, cpf, login, senha, cod_lanchonete, ativo) VALUES("JOAQUIM ENRICO MENDES","(15)2362-3237","814.108.848-30","joaquimmendes@gerente.goldfork.com","joaquim35745600",1,true);
INSERT INTO gerente(nome, telefone, cpf, login, senha, cod_lanchonete, ativo) VALUES("PEDRO HENRIQUE CAUÃ NICOLAS OLIVEIRA","(11)7353-7837","901.763.578-90","pedronicolas@gerente.goldfork.com","nickoliveira78785",2, true);
INSERT INTO gerente(nome, telefone, cpf, login, senha, cod_lanchonete, ativo) VALUES("MIGUEL BRENO CALEBE MENDES","(22)9819-8198","644.234.608-07","miguelcelebe85@gerente.goldfork.com","brenoveloz13",3, true);


#caixa
INSERT INTO caixa(nome, telefone, cpf, login, senha, cod_lanchonete, ativo) VALUES("MARIA D'ASSUNÇÃO SILVA","(98)1984-9841","651.689.497-97","mariasilva@caixa.goldfork.com","mmsilva7898",2, true);
INSERT INTO caixa(nome, telefone, cpf, login, senha, cod_lanchonete, ativo) VALUES("CÉSAR DE MORAES JUNIOR","(16)9819-8498","165.168.198-49","cesarjunior@caixa.goldfork.com","cesarmoraes1515",1, true);
INSERT INTO caixa(nome, telefone, cpf, login, senha, cod_lanchonete, ativo) VALUES("BRUNO SANTOS DE LIMA","(25)8481-9812","698.547.818-48","brunolima@caixa.goldfork.com","bruno12345",1, true);
INSERT INTO caixa(nome, telefone, cpf, login, senha, cod_lanchonete, ativo) VALUES("EDUARDO COSTA DE SOUZA","(12)6548-9789","254.148.489-51","eduardocosta@caixa.goldfork.com","educostinha13",2, true);
INSERT INTO caixa(nome, telefone, cpf, login, senha, cod_lanchonete, ativo) VALUES("SOPHIE EMANUELLY DE PAULA","(62)8655-7929","361.580.191-19","sophiepaula@caixa.goldfork.com","emanuelly361",1, true);

#faxineiro
INSERT INTO faxineiro(nome, telefone, cpf, cod_lanchonete, ativo) VALUES("JOSÉ MARIA DA SILVA","(15)6168-4984","051.668.498-91",1, true);
INSERT INTO faxineiro(nome, telefone, cpf, cod_lanchonete, ativo) VALUES("ANDERSON COSTA RIBEIRO","(75)2753-7863","277.507.507-52",1, true);
INSERT INTO faxineiro(nome, telefone, cpf, cod_lanchonete, ativo) VALUES("MARCOS SANTOS DA SILVA","(43)4323-4235","215.125.123-22",1, true);
INSERT INTO faxineiro(nome, telefone, cpf, cod_lanchonete, ativo) VALUES("PEDRO CAUÊ CAIO COSTA","(41)2312-5125","402.245.306-07",1, true);

#cozinheiro
INSERT INTO cozinheiro(nome, telefone, cpf, cod_lanchonete, ativo) VALUES("RAFAEL FERRARI DO ESPIRITO SANTO","(23)4336-2366","424.125.236-23",1, true);
INSERT INTO cozinheiro(nome, telefone, cpf, cod_lanchonete, ativo) VALUES("CÉSAR CALEBE ALEXANDRE ALMEIDA","(37)5378-3783","773.732.148-94",1, true);
INSERT INTO cozinheiro(nome, telefone, cpf, cod_lanchonete, ativo) VALUES("DIOGO THEO BARBOSA","(11)2342-3632","025.579.468-18",1, true);
INSERT INTO cozinheiro(nome, telefone, cpf, cod_lanchonete, ativo) VALUES("FILIPE JÚLIO MARCELO ALVES","(12)5484-9819","634.426.268-03",1, true);
INSERT INTO cozinheiro(nome, telefone, cpf, cod_lanchonete, ativo) VALUES("EDUARDA HELOISA COSTA","(38)3648-9066","457.855.256-07",1, true);

#produto atômico
INSERT INTO produto_atomico(nome, unidade_medida) VALUES("ALFACE","Pé");
INSERT INTO produto_atomico(nome, unidade_medida) VALUES("TOMATE","Quilos");
INSERT INTO produto_atomico(nome, unidade_medida) VALUES("HAMBURGUER","Unidade 200g");
INSERT INTO produto_atomico(nome, unidade_medida) VALUES("PÃO COM GERGILIM","Unidade 150g");
INSERT INTO produto_atomico(nome, unidade_medida) VALUES("QUEIJO MUSSARELA","Quilos");
INSERT INTO produto_atomico(nome, unidade_medida) VALUES("PRESUNTO DEFUMADO","Quilos");
INSERT INTO produto_atomico(nome, unidade_medida) VALUES("COCA-COLA LATA 350ML","Unidade");
INSERT INTO produto_atomico(nome, unidade_medida) VALUES("COCA-COLA GARRAFA 2L","Unidade");
INSERT INTO produto_atomico(nome, unidade_medida) VALUES("COCA-COLA GARRAFA 1L","Unidade");
INSERT INTO produto_atomico(nome, unidade_medida) VALUES("COCA-COLA GARRAFA 600ML","Unidade");
INSERT INTO produto_atomico(nome, unidade_medida) VALUES("GUARANÁ ANTÁRTICA LATA 350ML","Unidade");
INSERT INTO produto_atomico(nome, unidade_medida) VALUES("SPRITE LATA 350ML","Unidade");
INSERT INTO produto_atomico(nome, unidade_medida) VALUES("FANTA LARANJA LATA 350ML","Unidade");
INSERT INTO produto_atomico(nome, unidade_medida) VALUES("BACON DEFUMADO","Quilos");
INSERT INTO produto_atomico(nome, unidade_medida) VALUES("KETCHUP","Quilos");
INSERT INTO produto_atomico(nome, unidade_medida) VALUES("MOSTARDA","Quilos");
INSERT INTO produto_atomico(nome, unidade_medida) VALUES("MAIONESE","Quilos");
INSERT INTO produto_atomico(nome, unidade_medida) VALUES("HAMBURGUER DE PICANHA","Unidade 200g");
INSERT INTO produto_atomico(nome, unidade_medida) VALUES("SALSICHA","Unidade");
INSERT INTO produto_atomico(nome, unidade_medida) VALUES("LINGUIÇA CALABRESA","Quilos");
INSERT INTO produto_atomico(nome, unidade_medida) VALUES("OVO","Unidade");
INSERT INTO produto_atomico(nome, unidade_medida) VALUES("PEITO DE FRANGO","Quilo");

#produto
INSERT INTO produto(nome, preco, ativo) VALUES("XBURGUER",10.0,true);
INSERT INTO produto(nome, preco, ativo) VALUES("XBURGUER PICANHA",15.0,true);
INSERT INTO produto(nome, preco, ativo) VALUES("XBURGUER TUDO",25.0,true);
INSERT INTO produto(nome, preco, ativo) VALUES("COCA-COLA 1L",5.0,true);
INSERT INTO produto(nome, preco, ativo) VALUES("COCA-COLA 600ml",7.0,true);

#composicao_produto
INSERT INTO composicao_produto(cod_produto, cod_produto_atomico, quantidade) VALUES (1,1,0.25);
INSERT INTO composicao_produto(cod_produto, cod_produto_atomico, quantidade) VALUES (1,6,0.1);
INSERT INTO composicao_produto(cod_produto, cod_produto_atomico, quantidade) VALUES (1,5,0.1);
INSERT INTO composicao_produto(cod_produto, cod_produto_atomico, quantidade) VALUES (1,2,0.1);
INSERT INTO composicao_produto(cod_produto, cod_produto_atomico, quantidade) VALUES (1,4,1.0);
INSERT INTO composicao_produto(cod_produto, cod_produto_atomico, quantidade) VALUES (1,3,1.0);
INSERT INTO composicao_produto(cod_produto, cod_produto_atomico, quantidade) VALUES (2,1,0.25);
INSERT INTO composicao_produto(cod_produto, cod_produto_atomico, quantidade) VALUES (2,18,1.0);
INSERT INTO composicao_produto(cod_produto, cod_produto_atomico, quantidade) VALUES (2,4,1.0);
INSERT INTO composicao_produto(cod_produto, cod_produto_atomico, quantidade) VALUES (2,6,0.1);
INSERT INTO composicao_produto(cod_produto, cod_produto_atomico, quantidade) VALUES (2,5,0.1);
INSERT INTO composicao_produto(cod_produto, cod_produto_atomico, quantidade) VALUES (2,2,0.1);
INSERT INTO composicao_produto(cod_produto, cod_produto_atomico, quantidade) VALUES (3,1,0.25);
INSERT INTO composicao_produto(cod_produto, cod_produto_atomico, quantidade) VALUES (3,14,0.15);
INSERT INTO composicao_produto(cod_produto, cod_produto_atomico, quantidade) VALUES (3,20,0.15);
INSERT INTO composicao_produto(cod_produto, cod_produto_atomico, quantidade) VALUES (3,21,1.0);
INSERT INTO composicao_produto(cod_produto, cod_produto_atomico, quantidade) VALUES (3,4,1.0);
INSERT INTO composicao_produto(cod_produto, cod_produto_atomico, quantidade) VALUES (3,22,0.15);
INSERT INTO composicao_produto(cod_produto, cod_produto_atomico, quantidade) VALUES (3,6,0.1);
INSERT INTO composicao_produto(cod_produto, cod_produto_atomico, quantidade) VALUES (3,5,0.1);
INSERT INTO composicao_produto(cod_produto, cod_produto_atomico, quantidade) VALUES (3,19,2.0);
INSERT INTO composicao_produto(cod_produto, cod_produto_atomico, quantidade) VALUES (3,2,0.1);
INSERT INTO composicao_produto(cod_produto, cod_produto_atomico, quantidade) VALUES (4,9,1.0);
INSERT INTO composicao_produto(cod_produto, cod_produto_atomico, quantidade) VALUES (5,10,1.0);

#fornecedor
INSERT INTO fornecedor(nome, telefone, cnpj, ativo) VALUES("DISTRIBUIDOR ALIMENTÍCIO TRIANGULO LTDA","(15)6184-8964","16.516.516/1489-64",true);
INSERT INTO fornecedor(nome, telefone, cnpj, ativo) VALUES("FRIGORÍFICO SANTO ANASTÁCIO LTDA","(14)3518-8554","15.159.548/1268-55",true);
INSERT INTO fornecedor(nome, telefone, cnpj, ativo) VALUES("ARMAZÉM DE PRODUTOS ALIMENTÍCIOS SÃO MANOEL LTDA","(12)4125-1251","18.192.849/2834-28",true);
INSERT INTO fornecedor(nome, telefone, cnpj, ativo) VALUES("DESTRO ATACADISTA LTDA","(11)5652-1521","81.198.214/8961-42",true);
INSERT INTO fornecedor(nome, telefone, cnpj, ativo) VALUES("BRETAS FORNECEDOR DE ALIMENTOS LTDA","(34)1521-2512","32.112.153/1624-84",true);

#fornecimento
INSERT INTO fornecimento(cod_fornecedor, cod_lanchonete) VALUES(1, 1);
INSERT INTO fornecimento(cod_fornecedor, cod_lanchonete) VALUES(1, 2);
INSERT INTO fornecimento(cod_fornecedor, cod_lanchonete) VALUES(1, 3);
INSERT INTO fornecimento(cod_fornecedor, cod_lanchonete) VALUES(2, 1);
INSERT INTO fornecimento(cod_fornecedor, cod_lanchonete) VALUES(2, 2);
INSERT INTO fornecimento(cod_fornecedor, cod_lanchonete) VALUES(2, 3);
INSERT INTO fornecimento(cod_fornecedor, cod_lanchonete) VALUES(3, 1);
INSERT INTO fornecimento(cod_fornecedor, cod_lanchonete) VALUES(3, 2);
INSERT INTO fornecimento(cod_fornecedor, cod_lanchonete) VALUES(3, 3);
INSERT INTO fornecimento(cod_fornecedor, cod_lanchonete) VALUES(4, 3);
INSERT INTO fornecimento(cod_fornecedor, cod_lanchonete) VALUES(5, 1);

#nota de compra
INSERT INTO nota_de_compra (data, valor_total, cod_fornecedor, cod_lanchonete) VALUES("2016/12/01", 360.0, 1, 1);
INSERT INTO nota_de_compra (data, valor_total, cod_fornecedor, cod_lanchonete) VALUES("2016/12/01", 1900.0, 3, 1);
INSERT INTO nota_de_compra (data, valor_total, cod_fornecedor, cod_lanchonete) VALUES("2016/12/01", 2200.0, 2, 1);
INSERT INTO nota_de_compra (data, valor_total, cod_fornecedor, cod_lanchonete) VALUES("2016/12/07", 135.0, 1, 1);
INSERT INTO nota_de_compra (data, valor_total, cod_fornecedor, cod_lanchonete) VALUES("2016/12/08", 165.0, 3, 1);

#itens nota de compra e estoque
INSERT INTO itens_nota_compra (cod_nota_de_compra, cod_produto_atomico, quantidade, valor_unitario) VALUES(1, 1, 50.0, 5.0);
CALL atualizarEstoque(1, 1, 50.0);
INSERT INTO itens_nota_compra (cod_nota_de_compra, cod_produto_atomico, quantidade, valor_unitario) VALUES(1, 21, 200.0, 0.3);
CALL atualizarEstoque(1, 21, 200.0);
INSERT INTO itens_nota_compra (cod_nota_de_compra, cod_produto_atomico, quantidade, valor_unitario) VALUES(1, 2, 10.0, 5.0);
CALL atualizarEstoque(1, 2, 10.0);
INSERT INTO itens_nota_compra (cod_nota_de_compra, cod_produto_atomico, quantidade, valor_unitario) VALUES(2, 9, 100.0, 3.5);
CALL atualizarEstoque(1, 9, 100.0);
INSERT INTO itens_nota_compra (cod_nota_de_compra, cod_produto_atomico, quantidade, valor_unitario) VALUES(2, 8, 100.0, 5.0);
CALL atualizarEstoque(1, 8, 100.0);
INSERT INTO itens_nota_compra (cod_nota_de_compra, cod_produto_atomico, quantidade, valor_unitario) VALUES(2, 10, 100.0, 2.5);
CALL atualizarEstoque(1, 10, 100.0);
INSERT INTO itens_nota_compra (cod_nota_de_compra, cod_produto_atomico, quantidade, valor_unitario) VALUES(2, 7, 100.0, 2.0);
CALL atualizarEstoque(1, 7, 100.0);
INSERT INTO itens_nota_compra (cod_nota_de_compra, cod_produto_atomico, quantidade, valor_unitario) VALUES(2, 13, 100.0, 2.0);
CALL atualizarEstoque(1, 13, 100.0);
INSERT INTO itens_nota_compra (cod_nota_de_compra, cod_produto_atomico, quantidade, valor_unitario) VALUES(2, 11, 100.0, 2.0);
CALL atualizarEstoque(1, 11, 100.0);
INSERT INTO itens_nota_compra (cod_nota_de_compra, cod_produto_atomico, quantidade, valor_unitario) VALUES(2, 12, 100.0, 2.0);
CALL atualizarEstoque(1, 12, 100.0);
INSERT INTO itens_nota_compra (cod_nota_de_compra, cod_produto_atomico, quantidade, valor_unitario) VALUES(3, 14, 20.0, 10.0);
CALL atualizarEstoque(1, 14, 20.0);
INSERT INTO itens_nota_compra (cod_nota_de_compra, cod_produto_atomico, quantidade, valor_unitario) VALUES(3, 3, 200.0, 2.5);
CALL atualizarEstoque(1, 3, 200.0);
INSERT INTO itens_nota_compra (cod_nota_de_compra, cod_produto_atomico, quantidade, valor_unitario) VALUES(3, 18, 200.0, 4.5);
CALL atualizarEstoque(1, 18, 200.0);
INSERT INTO itens_nota_compra (cod_nota_de_compra, cod_produto_atomico, quantidade, valor_unitario) VALUES(3, 19, 300.0, 1.0);
CALL atualizarEstoque(1, 19, 300.0);
INSERT INTO itens_nota_compra (cod_nota_de_compra, cod_produto_atomico, quantidade, valor_unitario) VALUES(3, 20, 30.0, 10.0);
CALL atualizarEstoque(1, 20, 30.0);
INSERT INTO itens_nota_compra (cod_nota_de_compra, cod_produto_atomico, quantidade, valor_unitario) VALUES(4, 4, 20.0, 2.0);
CALL atualizarEstoque(1, 4, 20.0);
INSERT INTO itens_nota_compra (cod_nota_de_compra, cod_produto_atomico, quantidade, valor_unitario) VALUES(4, 6, 5.0, 10.0);
CALL atualizarEstoque(1, 6, 5.0);
INSERT INTO itens_nota_compra (cod_nota_de_compra, cod_produto_atomico, quantidade, valor_unitario) VALUES(4, 5, 5.0, 9.0);
CALL atualizarEstoque(1, 5, 5.0);
INSERT INTO itens_nota_compra (cod_nota_de_compra, cod_produto_atomico, quantidade, valor_unitario) VALUES(5, 16, 5.0, 15.0);
CALL atualizarEstoque(1, 16, 5.0);
INSERT INTO itens_nota_compra (cod_nota_de_compra, cod_produto_atomico, quantidade, valor_unitario) VALUES(5, 17, 5.0, 18.0);
CALL atualizarEstoque(1, 17, 5.0);

#nota de venda
INSERT INTO nota_de_venda (data, valor_total, cod_caixa, cod_lanchonete) VALUES("2016/12/08", 20.0, 2, 1);
INSERT INTO nota_de_venda (data, valor_total, cod_caixa, cod_lanchonete) VALUES("2016/12/08", 44.0, 2, 1);
INSERT INTO nota_de_venda (data, valor_total, cod_caixa, cod_lanchonete) VALUES("2016/12/08", 20.0, 2, 1);

#itens_nota_venda
INSERT INTO itens_nota_venda (cod_nota_venda, cod_produto, quantidade, valor_unitario) VALUES(1, 1, 2, 10.0);
CALL atualizarEstoqueSaida(1, 1, 0.5);
CALL atualizarEstoqueSaida(1, 2, 0.2);
CALL atualizarEstoqueSaida(1, 3, 2.0);
CALL atualizarEstoqueSaida(1, 4, 2.0);
CALL atualizarEstoqueSaida(1, 5, 0.2);
CALL atualizarEstoqueSaida(1, 6, 0.2);
INSERT INTO itens_nota_venda (cod_nota_venda, cod_produto, quantidade, valor_unitario) VALUES(2, 2, 2, 15.0);
CALL atualizarEstoqueSaida(1, 1, 0.5);
CALL atualizarEstoqueSaida(1, 2, 0.2);
CALL atualizarEstoqueSaida(1, 4, 2.0);
CALL atualizarEstoqueSaida(1, 5, 0.2);
CALL atualizarEstoqueSaida(1, 6, 0.2);
CALL atualizarEstoqueSaida(1, 18, 2.0);
INSERT INTO itens_nota_venda (cod_nota_venda, cod_produto, quantidade, valor_unitario) VALUES(2, 5, 2, 7.0);
CALL atualizarEstoqueSaida(1, 10, 2.0);
INSERT INTO itens_nota_venda (cod_nota_venda, cod_produto, quantidade, valor_unitario) VALUES(3, 4, 4, 5.0);
CALL atualizarEstoqueSaida(1, 9, 4.0);

#pagamento dinheiro
INSERT INTO pagamento_dinheiro (valor, cod_nota_venda) VALUES(20.0, 1);
INSERT INTO pagamento_dinheiro (valor, cod_nota_venda) VALUES(10.0, 3);

#pagamento cartão
INSERT INTO pagamento_cartao (valor, cod_nota_venda, ultimos_digitos, bandeira, tipo) VALUES(30.0, 2, 7532, "Visa", "CRÉDITO");
INSERT INTO pagamento_cartao (valor, cod_nota_venda, ultimos_digitos, bandeira, tipo) VALUES(14.0, 2, 4124, "Visa Electron", "DÉBITO");
INSERT INTO pagamento_cartao (valor, cod_nota_venda, ultimos_digitos, bandeira, tipo) VALUES(10.0, 3, 4897, "Visa", "CRÉDITO");


