package model;
import java.util.*;
import java.sql.ResultSet;
import java.sql.Connection;
import Conexao.ConexaoDB;
import java.sql.SQLException;
import java.sql.CallableStatement;

public class ContaBancaria {
    private int id;
    private int idCorrentista;
    private double saldo;

    public ContaBancaria(int id, int idCorrentista, double saldo){
        this.id = id;
        this.idCorrentista = idCorrentista;
        this.saldo = saldo;
    }

    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    public int getIdCorrentista(){return idCorrentista;}
    public void setIdCorrentista(int idCorrentista){this.idCorrentista = idCorrentista;}

    public double getSaldo(){return saldo;}
    public void setSaldo(double saldo){this.saldo = saldo;}

    public void depositar(double valor) throws SQLException{
        Connection conn = ConexaoDB.getConexao();
        CallableStatement cs = conn.prepareCall("{CALL proc_depositar(?, ?)}");

        cs.setInt(1, this.id);
        cs.setDouble(2, valor);

        cs.execute();
    }
    public void sacar(double valor) throws SQLException{
        Connection conn = ConexaoDB.getConexao();
        CallableStatement cs = conn.prepareCall("{CALL proc_sacar(?, ?)}");

        cs.setInt(1, this.id);
        cs.setDouble(2, valor);

        cs.execute();
    }
    public double consultarSaldo() throws SQLException{
        Connection conn = ConexaoDB.getConexao();
        CallableStatement cs = conn.prepareCall("{CALL proc_consultar_saldo(?)}");

        cs.setInt(1, this.id);

        cs.execute();

        ResultSet rs = cs.getResultSet();
        if(rs.next()){
            return rs.getDouble("saldo");
        }
        return 0;
    }
}
