# 🏦 SistemaBancario

Sistema bancário desenvolvido em Java com MySQL, implementando as operações de depósito, saque e consulta de saldo, com registro automático de extrato no banco de dados.

## 📋 Sobre o projeto

O sistema modela as entidades **Correntista** e **ContaBancaria**, onde cada correntista possui uma conta. Todas as operações são registradas em uma tabela de extrato com tipo, valor e data/hora, e o saldo é atualizado a cada transação.

## 🛠️ Tecnologias utilizadas

- Java
- MySQL
- JDBC (MySQL Connector/J)
- Maven

## 🗄️ Estrutura do banco de dados

### Tabela `correntista`
| Coluna | Tipo | Descrição |
|--------|------|-----------|
| id | INT (PK, AUTO_INCREMENT) | Identificador único |
| nome | VARCHAR | Nome do correntista |
| cpf | VARCHAR | CPF do correntista (único) |

### Tabela `conta`
| Coluna | Tipo | Descrição |
|--------|------|-----------|
| id_conta | INT (PK, AUTO_INCREMENT) | Identificador único |
| id_correntista | INT (FK) | Referência ao correntista |
| saldo | DECIMAL(10,2) | Saldo atual da conta |

### Tabela `extrato`
| Coluna | Tipo | Descrição |
|--------|------|-----------|
| id | INT (PK, AUTO_INCREMENT) | Identificador único |
| id_conta | INT (FK) | Referência à conta |
| tipo_operacao | VARCHAR | Tipo: DEPOSITO, SAQUE ou CONSULTA |
| valor | DECIMAL(10,2) | Valor da operação |
| data_hora | DATETIME | Data e hora da operação |

### Stored Procedures
- `proc_depositar(p_id_conta, p_valor)` — atualiza o saldo e registra no extrato
- `proc_sacar(p_id_conta, p_valor)` — verifica saldo, atualiza e registra no extrato
- `proc_consultar_saldo(p_id_conta)` — retorna o saldo e registra a consulta no extrato

## 📁 Estrutura do projeto

```
src/
├── Conexao/
│   └── ConexaoDB.java        # Conexão com o banco de dados
└── model/
    ├── Correntista.java       # Entidade correntista
    ├── ContaBancaria.java     # Entidade conta com métodos de operação
    ├── Menu.java              # Interface interativa no terminal
    └── Main.java              # Ponto de entrada da aplicação
```

## ▶️ Como executar

1. Clone o repositório
2. Crie o banco de dados `SistemaBancario` no MySQL e execute o script de criação das tabelas e procedures
3. Configure as credenciais em `ConexaoDB.java`
4. Adicione o `mysql-connector-j` ao projeto
5. Execute a classe `Main`

## ✅ Funcionalidades

- Depositar valor em conta
- Sacar valor (com verificação de saldo suficiente)
- Consultar saldo atual
- Registro automático de todas as operações no extrato
- Menu interativo no terminal
