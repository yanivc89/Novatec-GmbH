# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table book (
  id                            serial not null,
  title                         varchar(255),
  price                         integer not null,
  author                        varchar(255),
  constraint pk_book primary key (id)
);


# --- !Downs

drop table if exists book cascade;
