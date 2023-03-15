
create table order_product (
       order_id bigint not null,
        product_id bigint not null
    );
create table product_catalog (
           product_id bigint not null,
            catalog_id bigint not null
        );
create table product_image (
           product_id bigint not null,
            url_img bigint not null
        );
create table tb_catalog (
           id bigserial not null,
            ativo boolean,
            culture varchar(255),
            id_company integer,
            linha varchar(255),
            primary key (id)
        );
create table tb_company (
           id bigserial not null,
            ativo boolean,
            business_branch varchar(255),
            cnpj varchar(255),
            founding_yeans integer,
            name varchar(255),
            primary key (id)
        );
create table tb_image (
           id bigserial not null,
            ativo boolean,
            img varchar(255),
            primary key (id)
        );
create table tb_order (
           id bigserial not null,
            ativo boolean,
            country varchar(255),
            e_mail varchar(255),
            name varchar(255),
            phone varchar(255) not null,
            primary key (id)
        );
create table tb_product (
           id bigserial not null,
            ativo boolean,
            culture varchar(255),
            date_register date,
            description Text,
            name varchar(255),
            size varchar(255),
            status boolean not null,
            primary key (id)
        );
alter table if exists product_image
           add constraint UK_9ow4x3vg8qvuxdpd0trv8l559 unique (url_img);

alter table if exists tb_company
           add constraint UK_j5xlyp73va8ucavnqk0tok902 unique (name);

alter table if exists tb_order
           add constraint UK_2oeaeatdsqr0omxecc34548i2 unique (e_mail);

alter table if exists tb_product
           add constraint UK_lovy3681ry0dl5ox28r6679x6 unique (name);

alter table if exists order_product
           add constraint FK8bu68rldjkfljnchrtrhmmrkc
           foreign key (product_id)
           references tb_product;

alter table if exists order_product
           add constraint FKc1hr3gkcmqc95fqpu9k97fq60
           foreign key (order_id)
           references tb_order;

alter table if exists product_catalog
           add constraint FKa7s5tpwyu7jw0m8ipwl9sjv06
           foreign key (catalog_id)
           references tb_catalog;

alter table if exists product_catalog
           add constraint FKt3koxoosc5889p9ekrlle4irp
           foreign key (product_id)
           references tb_product;

alter table if exists product_image
           add constraint FK5cp6ks5x7uo3mmm6isi9apnku
           foreign key (url_img)
           references tb_image;

alter table if exists product_image
           add constraint FKla5o5f14a91ig5cxq31rqmt3c
           foreign key (product_id)
           references tb_product;

alter table if exists order_product
       drop constraint if exists FKc1hr3gkcmqc95fqpu9k97fq60;
alter table if exists product_catalog
         drop constraint if exists FKa7s5tpwyu7jw0m8ipwl9sjv06;
alter table if exists product_catalog
       drop constraint if exists FKt3koxoosc5889p9ekrlle4irp;
alter table if exists product_image
       drop constraint if exists FK5cp6ks5x7uo3mmm6isi9apnku;
alter table if exists product_image
       drop constraint if exists FKla5o5f14a91ig5cxq31rqmt3c;