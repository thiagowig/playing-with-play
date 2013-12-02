# --- Sample dataset

# --- !Ups

insert into posicao (id,nome) values (1,'Goleiro');
insert into posicao (id,nome) values (2,'Zagueiro');
insert into posicao (id,nome) values (3,'Lateiral Direito');
insert into posicao (id,nome) values (4,'Lateiral Esquerdo');
insert into posicao (id,nome) values (5,'Volante');
insert into posicao (id,nome) values (6,'Meia');
insert into posicao (id,nome) values (7,'Meia Atacante');
insert into posicao (id,nome) values (8,'Atacante');

insert into time (id,nome) values (1,'Cruzeiro');
insert into time (id,nome) values (2,'Internacional');
insert into time (id,nome) values (3,'Gremio');
insert into time (id,nome) values (4,'Santos');
insert into time (id,nome) values (5,'Sao Paulo');
insert into time (id,nome) values (6,'Corinthians');
insert into time (id,nome) values (7,'Palmeiras');
insert into time (id,nome) values (8,'Flamengo');
insert into time (id,nome) values (9,'Vasco');
insert into time (id,nome) values (10,'Botafogo');
insert into time (id,nome) values (11,'Fluminense');

insert into jogador (id,nomeProfissional,nomeCompleto,dataNascimento,posicao_id,time_id) values (1,'Everton Ribeiro','Everton Augusto de Barros Ribeiro','1989-04-10',6,1);
insert into jogador (id,nomeProfissional,nomeCompleto,dataNascimento,posicao_id,time_id) values (2,'Ricardo Goulart','Ricardo Goulart Pereira','1991-05-05',7,1);
insert into jogador (id,nomeProfissional,nomeCompleto,dataNascimento,posicao_id,time_id) values (3,'Dagoberto','Dagoberto Pelentier','1983-03-22',8,1);
insert into jogador (id,nomeProfissional,nomeCompleto,dataNascimento,posicao_id,time_id) values (4,'Seedorf','Clarence Clyde Seedorf','1976-04-01',6,10);

# --- !Downs

delete from posicao;
delete from time;
delete from jogador;
