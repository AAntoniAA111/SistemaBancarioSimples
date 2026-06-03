CREATE DATABASE SistemaBancario;
USE SistemaBancario;

CREATE TABLE correntista(
id_correntista INT PRIMARY KEY AUTO_INCREMENT UNIQUE, 
nome VARCHAR(200),
cpf VARCHAR(14)
);

CREATE TABLE conta(
id_conta INT PRIMARY KEY AUTO_INCREMENT,
id_correntista INT,
saldo DECIMAL(10, 2),
FOREIGN KEY (id_correntista) REFERENCES correntista (id_correntista)
);

CREATE TABLE extrato(
id_extrato INT PRIMARY KEY AUTO_INCREMENT,
id_conta INT,
tipo_operacao ENUM("DEPÓSITO","SALDO","CONSULTA","SACAR"),
valor DECIMAL(10, 8),
data_hora DATETIME
);

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
-- STORED PROCEDURES
-- SP PARA DEPOSITAR
DELIMITER //
CREATE PROCEDURE proc_depositar(
IN p_id_conta INT,
IN p_valor DECIMAL(10, 2)
)
BEGIN
	INSERT INTO extrato (id_conta, tipo_operacao, valor, data_hora)
    VALUES (p_id_conta, 'DEPOSITO', p_valor, NOW());
    UPDATE conta
    SET saldo = saldo + p_valor
    WHERE id_conta = p_id_conta;
END //
DELIMITER ;

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
-- SP PARA SACAR
DELIMITER //
CREATE PROCEDURE proc_sacar(
IN p_id_conta INT,
IN p_valor DECIMAL(10, 2)
)
BEGIN
    IF (SELECT saldo FROM conta WHERE id_conta = p_id_conta) >= p_valor THEN
		UPDATE conta
		SET saldo = saldo - p_valor
		WHERE id_conta = p_id_conta;
        INSERT INTO extrato (id_conta, tipo_operacao, valor, data_hora)
    VALUES (p_id_conta, 'SAQUE', p_valor, NOW());
	END IF;
END //
DELIMITER ;

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
-- SP PARA CONSULTAR
DELIMITER //
CREATE PROCEDURE proc_consultar_saldo(
IN p_id_conta INT
)
BEGIN
	INSERT INTO extrato (id_conta, tipo_operacao, valor, data_hora)
    VALUES (p_id_conta, 'CONSULTA', 0, NOW());
    
    SELECT saldo FROM conta WHERE id_conta = p_id_conta;
END //
DELIMITER ;

-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 
-- INSERTS
INSERT INTO correntista (nome, cpf) VALUES ('João', '12345678901');
INSERT INTO conta (id_correntista, saldo) VALUES (1, 0.0);