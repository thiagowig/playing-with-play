# --- First database schema

# --- !Ups

create table posicao (
  id                        bigint not null,
  nome                      varchar(255),
  constraint pk_posicao primary key (id))
;

create table time (
  id                        bigint not null,
  nome                      varchar(255),
  constraint pk_time primary key (id))
;

create table jogador (
  id                        bigint not null,
  nomeCompleto                      varchar(255),
  nomeProfissional                      varchar(255),
  dataNascimento                timestamp,
  posicao_id                bigint,
  time_id                bigint,
  constraint pk_jogador primary key (id))
;

create sequence posicao_seq start with 1000;

create sequence time_seq start with 1000;

create sequence jogador_seq start with 1000;

alter table jogador add constraint fk_jogador_posicao_1 foreign key (posicao_id) references posicao (id) on delete restrict on update restrict;
alter table jogador add constraint fk_jogador_time_1 foreign key (time_id) references time (id) on delete restrict on update restrict;

create index ix_jogador_posicao_1 on jogador (posicao_id);
create index ix_jogador_time_1 on jogador (time_id);


# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists posicao;

drop table if exists time;

drop table if exists jogador;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists posicao_seq;

drop sequence if exists time_seq;

drop sequence if exists jogador_seq;

