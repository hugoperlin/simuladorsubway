CREATE TABLE bebidas (id serial PRIMARY KEY, nome varchar(255) NOT NULL, valor real not null);
CREATE TABLE clientes (id serial PRIMARY KEY, nome varchar(255) NOT NULL, telefone VARCHAR(255) NOT NULL, email VARCHAR(255) not null);
CREATE TABLE ingredientes(id serial primary key, nome varchar(255) not null, descricao varchar(255) not null, valor real not null );
CREATE TABLE sanduiches (id serial PRIMARY KEY , valor real not null);
CREATE TABLE sanduiche_ingrediente (id serial PRIMARY KEY, id_sanduiche INTEGER REFERENCES sanduiches (id), id_ingrediente INTEGER REFERENCES ingredientes (id), valor real not null);
CREATE TABLE pedidos (id serial PRIMARY KEY, data timestamp, valor real not null, id_cliente INTEGER REFERENCES clientes (id), id_sanduiche INTEGER REFERENCES sanduiches (id));
CREATE TABLE pedido_bebida (id serial PRIMARY KEY, id_pedido INTEGER REFERENCES pedidos (id), id_bebida INTEGER REFERENCES bebidas (id), valor real not null);
