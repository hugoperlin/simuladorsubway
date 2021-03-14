DELIMITER $$
CREATE PROCEDURE total_pedidos(OUT total INT)
BEGIN

    DECLARE totalPedidos REAL DEFAULT 0;
    DECLARE valorPedido REAL DEFAULT 0;
    DECLARE terminou INTEGER DEFAULT 0;

    DECLARE pedidos_cursor CURSOR FOR
SELECT valor FROM pedidos;

DECLARE CONTINUE HANDLER FOR NOT FOUND SET terminou = 1;
OPEN pedidos_cursor;

get_pedidos:
    LOOP
        FETCH pedidos_cursor into valorPedido;
        IF terminou = 1 THEN
            LEAVE get_pedidos;
END IF;
        SET totalPedidos = totalPedidos + valorPedido;

END LOOP get_pedidos;

CLOSE pedidos_cursor;

SET total = totalPedidos;

END $$
DELIMITER ;