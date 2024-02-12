package Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FornecedorEnergia implements Serializable {
    private String nomeEmpresa;
    private double imposto;
    private double valorBase;
    private double desconto;
    private Map<String, Casa> casasDoFornecedor; //Id da casa - Casa
    private Map<String, List<Fatura>> faturas;  //Id da casa - Lista de faturas

    /**
     * Construtor por omissão
     */
    public FornecedorEnergia(){
        this.nomeEmpresa = "";
        this.imposto = 1.23;
        this.valorBase = 0;
        this.desconto = 0;
        this.casasDoFornecedor = new HashMap<>();
        this.faturas = new HashMap<>();
    }

    /**
     * Contrutor parametrizado
     * @param nomeEmpresa
     * @param imposto
     * @param valorBase
     * @param desconto
     * @param faturas
     */
    public FornecedorEnergia(String nomeEmpresa, double valorBase, double desconto, HashMap<String, Casa> casas, Map<String, List<Fatura>> faturas){
        this.nomeEmpresa = nomeEmpresa;
        this.imposto = 1.23;
        this.valorBase = valorBase;
        this.desconto = desconto;
        this.casasDoFornecedor = casas;
        this.faturas = faturas;
    }

    /**
     * Contrutor parametrizado especializado
     * @param nomeEmpresa
     * @param valorBase
     * @param desconto
     */
    public FornecedorEnergia(String nomeEmpresa, double valorBase, double desconto){
        this.nomeEmpresa = nomeEmpresa;
        this.imposto = 1.23;
        this.valorBase = valorBase;
        this.desconto = desconto;
        this.casasDoFornecedor = new HashMap<String, Casa>();
        this.faturas = new HashMap<String, List<Fatura>>();
    }

    /**
     * Constutor por cópia
     * @param fe
     */
    public FornecedorEnergia (FornecedorEnergia fe){
        this.nomeEmpresa = fe.getNomeEmpresa();
        this.imposto = fe.getImposto();
        this.valorBase = fe.getValorBase();
        this.desconto = fe.getDesconto();
        this.casasDoFornecedor = fe.getCasasDoFornecedor();
        this.faturas = fe.getFaturas();
    }

    /**
     * Método que verifica se dois fornecedores de energia são iguais.
     * @param o fornecedor de energia passada como parâmetro
     * @return True se os fornecedores forem iguais | False se os fornecedores forem diferentes
     */
    public boolean equals (Object o) {
        if (this == o)
            return true;

        if (o == null || o.getClass() != this.getClass())
            return false;

        FornecedorEnergia fe = (FornecedorEnergia) o;
        return (fe.getNomeEmpresa().equals(this.nomeEmpresa) &&
                fe.getImposto() == this.imposto &&
                fe.getValorBase() == this.valorBase &&
                fe.getDesconto() == this.desconto &&
                fe.getCasasDoFornecedor().equals(this.casasDoFornecedor) &&
                fe.getFaturas().equals(this.faturas));
    }

    /**
     * Método que representa um fornecedor de energia em formato de texto.
     * @return String com as características de uma fornecedor de energia.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Name: " + this.nomeEmpresa + "\n");
        sb.append("Tax: " + this.imposto + "\n");
        sb.append("Base value: " + this.valorBase + "\n");
        sb.append("Discount: " + this.desconto + "\n");
        sb.append("\n");

        //sb.append("Casas: " + this.casas + "\n");
        //sb.append("Bill: " + this.faturas + "\n");

        return sb.toString();
    }

    /**
     * Método que cria um clone da fatura.
     * @return Clone da fatura.
     */
    public FornecedorEnergia clone() {
        return new FornecedorEnergia(this);
    }

     /**
      * Método que determina o preco de um dispositivo
      * @param sd
      * @return
      */
    public double getPrecoDispositivo(SmartDevice sd){
        return (this.valorBase * sd.getConsumoF() * this.imposto) * (1 - (this.desconto/100));
    }


    // Quando recebe a casa
    /**
     * Método que determina o preco da casa.
     * @param casa
     * @return precoTotal
     */
    public double getPrecoCasa(Casa casa) {
        double precoTotal = 0;

        for(SmartDevice sd: casa.getDispositivos().values()) {
            precoTotal += getPrecoDispositivo(sd);
        }

        return precoTotal;
    }


    // Quando recebe os dispositivos da casa
    /**
     * Método que determina o preco da casa.
     * @param dispositivos
     * @return precoTotal
     */
    public double getPrecoCasa(Map<String, SmartDevice> dispositivos) {
        double precoTotal = 0;

        for(SmartDevice sd: dispositivos.values()) {
            precoTotal += getPrecoDispositivo(sd);
        }

        return precoTotal;
    }

    // Quando recebe a casa
    /**
     * Método que determina o consumo da casa.
     * @param casa
     * @return consumoTotal
     */
    public double getConsumoCasa(Casa casa) {
        double consumoTotal = 0;

        for(SmartDevice sd: casa.getDispositivos().values()) {
            consumoTotal += sd.getConsumoF();
        }

        return consumoTotal;
    }

    // Quando recebe os dispositivos da casa
    /**
     * Método que determina o consumo da casa.
     * @param dispositivos
     * @return consumoTotal
     */
    public double getConsumoCasa(Map<String, SmartDevice> dispositivos) {
        double consumoTotal = 0;

        for(SmartDevice sd: dispositivos.values()) {
            consumoTotal += sd.getConsumoF();
        }

        return consumoTotal;
    }



    // Getters and Setters
    /**
     * Método que retorna o nome da empresa
     * @return nomeEmpresa
     */
    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    /**
     * Método que altera o nome da empresa
     * @param nomeEmpresa
     */
    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    /**
     * Método que retorna o imposto
     * @return imposto
     */
    public double getImposto() {
        return imposto;
    }

    /**
     * Método que altera o imposto
     * @param imposto
     */
    public void setImposto(double imposto) {
        this.imposto = imposto;
    }

    /**
     * Método que retorna o valor base
     * @return valorBase
     */
    public double getValorBase() {
        return valorBase;
    }

    /**
     * Método que altera o valor base
     * @param valorBase
     */
    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }

    /**
     * Método que retorna o desconto
     * @return desconto
     */
    public double getDesconto() {
        return desconto;
    }

    /**
     * Método que altera o desconto
     * @param desconto
     */
    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    /**
     * Método que retorna o conjunto das casas de um dado fornecedor
     * @return casasDoFornecedor
     */
    public Map<String, Casa> getCasasDoFornecedor() {
        return this.casasDoFornecedor;
    }

    /**
     * Método que altera o conjunto das casas de um dado fornecedor
     * @param casasDoFornecedor
     */
    private void setCasasDoFornecedor(HashMap<String, Casa> casasDoFornecedor){
        this.casasDoFornecedor = casasDoFornecedor;
    }

    /**
     * Método que retorna as faturas
     * @return faturas
     */
    public Map<String, List<Fatura>> getFaturas() {
        return this.faturas;
    }

    /**
     * Método que altera as faturas
     * @param faturas
     */
    public void setFatura(Map<String, List<Fatura>> faturas) {
        this.faturas = faturas;
    }
}
