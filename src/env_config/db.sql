CREATE database pizza5;

use pizza5;

CREATE TABLE `pizza` (
  `id`              BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `name`            VARCHAR(255) NOT NULL,
  `price`           DOUBLE NOT NULL,
  `description`     VARCHAR(4000) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE `ingredient` (
  `id`              BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `name`            VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE `pizza_ingredient` (
`pizza_id`              BIGINT(20)   NOT NULL,
`ingredient_id`              BIGINT(20)   NOT NULL,
CONSTRAINT pizza_FK FOREIGN KEY (pizza_id) REFERENCES pizza (id),
CONSTRAINT ingredient_FK FOREIGN KEY (ingredient_id) REFERENCES ingredient (id)
	ON DELETE CASCADE
);

insert into pizza (id, name,price,description) values (1, 'Margherita',5,'The timeless classic');
insert into pizza (id, name,price,description) values (2, 'Napoli',5.7,'Ue ue ue');
insert into pizza (id, name,price,description) values (3, 'Four Season',7.9,'Kaboom');
insert into pizza (id, name,price,description) values (4, 'Mushrooms',5.5,'Mush');


insert into ingredient(id, name) values (1,'Flour');
insert into ingredient(id, name) values (2,'Salt');
insert into ingredient(id, name) values (3,'Cheese');
insert into ingredient(id, name) values (4,'Tomato');
insert into ingredient(id, name) values (5,'Mushroom');
insert into ingredient(id, name) values (6,'Olives');
insert into ingredient(id, name) values (7,'Anchovies');
insert into ingredient(id, name) values (8,'Egg');


insert into pizza_ingredient (pizza_id, ingredient_id) values (1,1);
insert into pizza_ingredient (pizza_id, ingredient_id) values (1,2);
insert into pizza_ingredient (pizza_id, ingredient_id) values (1,3);
insert into pizza_ingredient (pizza_id, ingredient_id) values (2,1);
insert into pizza_ingredient (pizza_id, ingredient_id) values (2,2);
insert into pizza_ingredient (pizza_id, ingredient_id) values (2,7);
insert into pizza_ingredient (pizza_id, ingredient_id) values (3,1);
insert into pizza_ingredient (pizza_id, ingredient_id) values (3,2);
insert into pizza_ingredient (pizza_id, ingredient_id) values (3,3);
insert into pizza_ingredient (pizza_id, ingredient_id) values (3,4);
insert into pizza_ingredient (pizza_id, ingredient_id) values (3,5);
insert into pizza_ingredient (pizza_id, ingredient_id) values (3,6);
insert into pizza_ingredient (pizza_id, ingredient_id) values (4,1);
insert into pizza_ingredient (pizza_id, ingredient_id) values (4,2);
insert into pizza_ingredient (pizza_id, ingredient_id) values (4,3);
insert into pizza_ingredient (pizza_id, ingredient_id) values (4,5);




