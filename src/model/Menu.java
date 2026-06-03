package model;

import java.sql.SQLException;
import java.util.*;

public class Menu {
    public void inicia(ContaBancaria conta) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 4){
            System.out.println("1 - Depositar");
            System.out.println("2 - Sacar");
            System.out.println("3 - Consultar Saldo");
            System.out.println("4 - Sair\n");
            System.out.println("Escolha: ");
            opcao = scanner.nextInt();

            switch(opcao){
                case 1:
                    System.out.println("Digite o valor a depositar: ");
                    double valorDeposito = scanner.nextDouble();
                    conta.depositar(valorDeposito);
                    System.out.println("Depósito realizado!");
                    break;
                case 2:
                    System.out.println("Digite o valor a sacar: ");
                    double valorSaque = scanner.nextDouble();
                    conta.sacar(valorSaque);
                    System.out.println("Saque realizado!");
                    break;
                case 3:
                    System.out.println("Saldo atual: " + conta.consultarSaldo());
                    break;
                case 4: System.out.println("Saindo...");
            }
        }
    }
}
