create table tb_ocorrencia (
	id bigint not null auto_increment,
    entrega_id bigint not null,
    descricao text not null,
    data_registro datetime not null,
    
    primary key (id)
);

alter table tb_ocorrencia add constraint fk_ocorrencia_entrega
foreign key (entrega_id) references tb_entrega (id);