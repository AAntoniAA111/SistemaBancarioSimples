package model;

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
}
