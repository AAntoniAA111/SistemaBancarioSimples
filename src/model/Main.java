package model;
import model.ContaBancaria;
import model.Menu;
import java.sql.SQLException;

import org.w3c.dom.ls.LSOutput;

public class Main {
    public static void main(String[] args) throws SQLException{
        ContaBancaria conta = new ContaBancaria(1, 1, 0.0);

        Menu menu = new Menu();

        menu.inicia(conta);
    }
}