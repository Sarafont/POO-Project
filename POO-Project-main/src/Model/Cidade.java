package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import java.io.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Cidade implements Serializable {
    private Map<String, Casa> casas = new HashMap<>(); // Id da casa - Casa (Todas as casas)
    private Map<String, FornecedorEnergia> fornecedorDeCadaCasa = new HashMap<>(); // idCasa - Fornecedor (Fornecedor de cada casa)
    private Map<String, FornecedorEnergia> fornecedores = new HashMap<>(); // Nome do fornecedor - Fornecedor (Todos os fornecedores existentes)
    private Map<String, List<Fatura>> faturas = new HashMap<>(); // Nome da Casas - Lista de faturas (Todas as faturas de todas as casas)
    private List<LocalDateTime> datas = new ArrayList<>();

    /**
     * Construtor por omissão
     */
    public Cidade() {
        this.casas = new HashMap<>();
        this.fornecedorDeCadaCasa = new HashMap<>();
        this.fornecedores = new HashMap<>();
        this.faturas = new HashMap<>();
    }

    /**
     * Construtor parametrizado
     * @param casas
     * @param fornecedorDaCasa
     * @param fornecedores
     * @param faturas
     */
    public Cidade(Map<String, Casa> casas, Map<String, FornecedorEnergia> fornecedorDeCadaCasa, Map<String, FornecedorEnergia> fornecedores, Map<String, List<Fatura>> faturas) {
        this.casas = casas;
        this.fornecedorDeCadaCasa = fornecedorDeCadaCasa;
        this.fornecedores = fornecedores;
        this.faturas = faturas;
    }

    /**
     * Construtor por cópia
     * @param cidade
     */
    public Cidade(Cidade cidade) {
        this.casas = cidade.getCasas();
        this.fornecedorDeCadaCasa = cidade.getFornecedorDeCadaCasa();
        this.fornecedores = cidade.getFornecedores();
        this.faturas = cidade.getFaturas();
    }

    /**
     * Método que representa uma cidade em formato de texto.
     * @return String com as características de uma cidade.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Casas:\n");
        for (Casa casa: this.casas.values()){
            sb.append(casa.toString());
            sb.append("\n");
        }
       sb.append("\nFornecedores:\n");
       for (FornecedorEnergia fe: this.fornecedores.values()){
            sb.append(fe.toString());
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Método que testa se duas cidades são iguais
     * @param o Cidade passada como parâmetro
     */
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if(o == null || this.getClass() != o.getClass())
            return false;

        Cidade c = (Cidade) o;
        return (this.casas.equals(c.getCasas()) &&
                this.fornecedorDeCadaCasa.equals(c.getFornecedorDeCadaCasa()) &&
                this.fornecedores.equals(c.getFornecedores()) &&
                this.faturas.equals(c.getFaturas()));
    }

    /**
     * Método que cria um clone da cidade.
     * @return Clone da cidade.
     */
    public Cidade clone() {
        return new Cidade(this);
    }

    /**
     * Método que adiciona uma casa a uma cidade
     * @param idCasa
     * @param casa
     */
    public void add_Casa(String idCasa, Casa casa){
        this.casas.put(idCasa, casa);
    }

    /**
     * Método que remove uma casa de uma cidade
     * @param idCasa
     */
    public void remove_Casa(String idCasa){
        this.casas.remove(idCasa);
    }

    /**
     * Método get que retorn uma casa de uma cidade
     * @param idCasa
     * @return retorn casa
     */
    public Casa getCasa(String idCasa) {
        return this.casas.get(idCasa);
    }

    /**
     * Método que retorna o fornecedor de uma casa
     * @param idCasa
     * @return fornecedor da casa
     */
    public FornecedorEnergia getFornecedorDeCadaCasa(String idCasa) {
        return this.fornecedorDeCadaCasa.get(idCasa);
    }

    /**
     * Método que retorna o fornecedor
     * @param nomeFornecedor
     * @return
     */
    public FornecedorEnergia getFornecedor(String nomeFornecedor) {
        FornecedorEnergia fe = this.fornecedores.get(nomeFornecedor);

        return fe;
    }

    /**
     * Método que adiciona um fornecedor de energia ao conjunto de fornecedores
     * @param fe
     */
    public void add_Fornecedor(FornecedorEnergia fe) {
        this.fornecedores.put(fe.getNomeEmpresa(), fe);
    }

    /**
     * Método que verifica se existe um fornecedor de energia
     * @param nomeDoFornecedor
     */
    public boolean hasFornecedor(String nomeDoFornecedor){
        return this.fornecedores.containsKey(nomeDoFornecedor);
    }


    /**
     * Método que remove um fornecedor de energia
     * @param nomeFornecedor
     * @return flag que indica se foi removido ou não
     */
    public int removeFornecedor(String nomeFornecedor) {
        int flag = 1;

        for(String idCasa: this.fornecedorDeCadaCasa.keySet()) {
            if(this.fornecedorDeCadaCasa.get(idCasa).getNomeEmpresa().equals(nomeFornecedor))
                flag = 0;
        }
        if(flag == 1)
            this.fornecedores.remove(nomeFornecedor);

        return flag;
    }

    /**
     * Método que calcula o consumo de uma casa inteira num determinado periodo de tempo
     * @param casa
     * @param cidade
     * @param periodo
     * @return consumoTotal*periodo
     */
    public double getConsumoCasaPeriodo(Casa casa, long periodo) {
        double consumoTotal = 0;

        for(SmartDevice sd: casa.getDispositivos().values()) {
            consumoTotal += sd.getConsumoF();
        }

        return consumoTotal*periodo;
    }

    /**
     * Método que determina qual a casa mais gastora num dado periodo de tempo
     * @param cidade
     * @param periodo
     * @return casaM
     */
    public Casa getCasaMaisGastadoraPeriodo(long periodo) {
        double maiorValor = 0;
        Casa casaM = null;

        for(Casa c: this.getCasas().values()) {
            double consumo = getConsumoCasaPeriodo(c, periodo);
            if(consumo > maiorValor) {
                maiorValor = consumo;
                casaM = c;
            }
        }

        return casaM;
    }

    /**
     * Método que retorna o preco da casa num dado periodo de tempo
     * @param casa
     * @param periodo
     * @return precoTotal
     */
    public double getPrecoCasaPeriodo(Casa casa, long periodo) {
        double precoTotal = 0;

        for(SmartDevice sd: casa.getDispositivos().values()) {
            precoTotal += getPrecoDispositivoPeriodo(casa, sd, periodo);
        }

        return precoTotal;
    }

    /**
     * Método que determina o preco de um dispositivo num dado periodo de tempo
     * @param casa
     * @param sd
     * @param periodo
     */
    public double getPrecoDispositivoPeriodo(Casa casa, SmartDevice sd, long periodo){
        return ((casa.getFornecedor().getValorBase() * sd.getConsumoF() * casa.getFornecedor().getImposto()) * (1 - (casa.getFornecedor().getDesconto()/100)) * periodo)/100000000;
    }


    /**
     * Método que determina o volume de faturação de um fornecedor
     * @param fornecedor
     * @param periodo
     * @return volumeFatuacao
     */
    public double volumeFatFornecedor (FornecedorEnergia fornecedor, long periodo){
        double volumeFaturacao = 0;
        for(String idCasa: this.fornecedorDeCadaCasa.keySet()){
            if(this.fornecedorDeCadaCasa.get(idCasa).getNomeEmpresa().equals(fornecedor.getNomeEmpresa())){
                volumeFaturacao += getPrecoCasaPeriodo(getCasa(idCasa), periodo);
            }
        }
        return volumeFaturacao;
    }

    public Set<String> casasMaisConsumidoras(Cidade cidade, long periodo) {
        Map<String,Double> casas = new HashMap<>();

        for(String idCasa: this.casas.keySet()) {
            casas.put(idCasa, getConsumoCasaPeriodo(this.casas.get(idCasa), periodo));
        }
        Map<String,Double> listaCasaMaisConsumidora = casas.entrySet()
                     .stream()
                     .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                     .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue , (e1,e2)->e2, LinkedHashMap::new));

        return listaCasaMaisConsumidora.keySet();
    }



    /**
     * Método que indica uma lista de casas
     * @return sb em formato string
     */
    public String listaCasas() {
        StringBuilder sb = new StringBuilder();

        sb.append("Houses ids:\n");
        for(String idCasa: this.casas.keySet()) {
            sb.append(idCasa);
            sb.append("\n");
        }

        return sb.toString();
    }


    /**
     * Método que indica todas as informações sobre uma casa
     * @param cidade
     * @param idCasa
     * @return sb em formato string
     */
    public String listaInfoCasa(Cidade cidade, String idCasa) {
        StringBuilder sb = new StringBuilder();

        sb.append(this.casas.get(idCasa).toString());

        return sb.toString();
    }

    /**
     * Método que determina uma lista de fornecedores
     * @return sb em formato string
     */
    public String listaFornecedores(){
        StringBuilder sb = new StringBuilder();

        sb.append("List of energy suppliers:\n");
        for(String nomeF: this.fornecedores.keySet()){
            sb.append(nomeF);
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Método que determina todas as informações de um fornecedor
     * @param nomeF
     * @return sb em formato string
     */
    public String listaInfoFornecedor(String nomeF) {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getFornecedor(nomeF).toString());

        return sb.toString();
    }

/*
    private Map<String, Casa> casas = new HashMap<>(); // Id da casa - Casa (Todas as casas)
    private Map<String, FornecedorEnergia> fornecedorDeCadaCasa = new HashMap<>(); // idCasa - Fornecedor (Fornecedor de cada casa)
    private Map<String, FornecedorEnergia> fornecedores = new HashMap<>(); // Nome do fornecedor - Fornecedor (Todos os fornecedores existentes)
    private Map<String, List<Fatura>> faturas = new HashMap<>(); // Nome da Casas - Lista de faturas (Todas as faturas de todas as casas)
*/

    public String listaFaturasCasa(String casaID) {
        StringBuilder sb = new StringBuilder();

        for(Fatura f: this.faturas.get(casaID)) {
            sb.append(f.toString());
            sb.append("\n");
        }

        return sb.toString();
    }

    public String listaFaturasFornecedor(String nomeFornecedor) {
        StringBuilder sb = new StringBuilder();

        for(String idCasa: this.faturas.keySet()) {
            if(this.fornecedorDeCadaCasa.get(idCasa).getNomeEmpresa().equals(nomeFornecedor)) {
                sb.append(listaFaturasCasa(idCasa));
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    public String listaTodasFaturas() {
        StringBuilder sb = new StringBuilder();

        for(String idCasa: this.faturas.keySet()) {
            for(Fatura f: this.faturas.get(idCasa)) {
                sb.append(f.toString());
                sb.append("\n");
            }
        }

        return sb.toString();
    }



    public void geraFaturas(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        long periodo = ChronoUnit.DAYS.between(dataInicial, dataFinal);

        for(Casa casa: this.casas.values()) {
            String idCasa = casa.getIdCasa();
            double consumo = getConsumoCasaPeriodo(casa, periodo);
            double custo = getPrecoCasaPeriodo(casa, periodo);

            Fatura fatura = new Fatura(idCasa, consumo, custo, dataInicial, dataFinal);

            if (!faturas.containsKey(idCasa)) {
                this.faturas.put(idCasa, new ArrayList<Fatura>());
                this.faturas.get(idCasa).add(fatura);
            } else {
                this.faturas.get(idCasa).add(fatura);
            }
        }
    }

    public Fatura listaFaturaDataI(LocalDateTime dataInicial, Casa casa) {
        Fatura f = null;

        for(Fatura fatura: this.faturas.get(casa.getIdCasa())) {
            if(fatura.getDataInicial().equals(dataInicial)) {
                return fatura;
            }
        }

        return f;
    }

    public List<LocalDateTime> getDatasClone() {
        List<LocalDateTime> novaLista = new ArrayList<>();

        for(LocalDateTime d: this.datas) {
            novaLista.add(d);
        }

        return novaLista;
    }








    // Getters and Setters

    /**
     * Método get que retorna o conjunto de casas
     * @return casas
     */
    public Map<String, Casa> getCasas() {
        return this.casas;
    }

    /**
     * Método set que altera o conjunto de casas
     * @param casas conjunto de casas
     */
    public void setCasas(HashMap<String, Casa> casas){
        this.casas = casas;
    }

    /**
     * Método get que retorna o fornecedor de cada casa
     * @return fornecedorDeCadaCasa
     */
    public Map<String, FornecedorEnergia> getFornecedorDeCadaCasa(){
        return this.fornecedorDeCadaCasa;
    }

    /**
     * Método set que altera o fornecedor de cada casa
     * @param fornecedorDeCadaCasa
     */
    public void setFornecedorDeCadaCasa(Map<String, FornecedorEnergia> fornecedorDeCadaCasa){
        this.fornecedorDeCadaCasa = fornecedorDeCadaCasa;
    }

    /**
     * Método get que retorna o fornecedor
     * @return fornecedores
     */
    public Map<String, FornecedorEnergia> getFornecedores() {
        return this.fornecedores;
    }

    /**
     * Método set que altera o conjunto de fornecedores
     * @param fornecedores
     */
    public void setFornecedores(Map<String, FornecedorEnergia> fornecedores) {
        this.fornecedores = fornecedores;
    }

    /**
     * Método get que retorna as faturas
     * @return faturas
     */
    public Map<String, List<Fatura>> getFaturas(){
        return this.faturas;
    }

    /**
     * Método set que altera as faturas
     * @param faturas
     */
    public void setFatura(Map<String, List<Fatura>> faturas){
        this.faturas = faturas;
    }

    public List<LocalDateTime> getDatas() {
        return this.datas;
    }

    public void setDatas(List<LocalDateTime> datas) {
        this.datas = datas;
    }
}
