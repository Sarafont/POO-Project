package Model;

import java.io.Serializable;
import java.util.*;

public class Casa implements Serializable {
    private String idCasa;
    private String morada;
    private String nome;
    private String NIF;
    private Map<String, SmartDevice> dispositivos;
    private Map<String, List<String>> divisoes; // Nome da divisão - Dispositivos
    private FornecedorEnergia fornecedor;


    /**
     * Construtor por omissão
     */
    public Casa() {
        this.idCasa = "";
        this.morada = "";
        this.nome = "";
        this.NIF = "";
        this.dispositivos = new HashMap<>();
        this.divisoes = new HashMap<>();
        this.fornecedor = null;
    }

    /**
     * Construtor parametrizado
     * @param idCasa
     * @param morada
     * @param nome
     * @param NIF
     * @param dispositivos
     * @param divisoes
     * @param fornecedor
     */
    public Casa(String idCasa, String morada, String nome, String NIF, Map<String, SmartDevice> dispositivos, Map<String, List<String>> divisoes, FornecedorEnergia fornecedor) {
        this.idCasa = idCasa;
        this.morada = morada;
        this.nome = nome;
        this.NIF = NIF;
        setDispositivos(dispositivos);
        setDivisoes(divisoes);
        this.fornecedor = fornecedor;
    }

    /**
     * Construtor parametrizado especializado
     * @param idCasa
     * @param morada
     * @param nome
     * @param NIF
     * @param dispositivos
     * @param divisoes
     */
    public Casa(String idCasa, String morada, String nome, String NIF, Map<String, SmartDevice> dispositivos, Map<String, List<String>> divisoes) {
        this.idCasa = idCasa;
        this.morada = morada;
        this.nome = nome;
        this.NIF = NIF;
        setDispositivos(dispositivos);
        setDivisoes(divisoes);
    }

    /**
     * Construtor parametrizado
     * @param idCasa
     * @param morada
     * @param nome
     * @param NIF
     * @param dispositivos
     * @param divisoes
     * @param fornecedor
     */
    public Casa(String idCasa, String nome, String NIF, FornecedorEnergia fornecedor) {
        this.idCasa = idCasa;
        this.nome = nome;
        this.NIF = NIF;
        this.divisoes = new HashMap<String,List<String>>();
        this.dispositivos = new HashMap<String,SmartDevice>();
        this.fornecedor = fornecedor;
    }

    /**
     * Construtor por cópia
     * @param casa
     */
    public Casa(Casa casa) {
        this.idCasa = casa.getIdCasa();
        this.morada = casa.getMorada();
        this.nome = casa.getNome();
        this.NIF = casa.getNIF();
        this.dispositivos = casa.getDispositivos();
        this.divisoes = casa.getDivisoes();
        this.fornecedor = getFornecedor();
    }

    /**
     * Método que verifica se duas casas são iguais
     * @param o casa passada como parâmetro
     */
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || o.getClass() != this.getClass())
            return false;

        Casa c = (Casa) o;
        return (c.getIdCasa().equals(this.idCasa) &&
                c.getMorada().equals(this.morada) &&
                c.getNome().equals(this.nome) &&
                c.getNIF().equals(this.NIF) &&
                c.getDispositivos().equals(this.dispositivos) &&
                c.getDivisoes().equals(this.divisoes) &&
                c.getFornecedor().equals(this.fornecedor));
    }

    /**
     * Método que representa uma casa em formato de texto.
     * @return String com as características de uma casa.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n");
        sb.append("House ID: " + this.idCasa + "\n");
        sb.append("Adress: " + this.morada + "\n");
        sb.append("House owner: " + this.nome + "\n");
        sb.append("NIF: " + this.NIF + "\n");
        sb.append("\n");
        for(String deviceID: this.dispositivos.keySet()) {
            sb.append(this.dispositivos.get(deviceID).toString());
            sb.append("\n");
        }

        sb.append("{");
        for(String divName: this.divisoes.keySet()) {
            sb.append(divName + ", ");
        }
        sb.append("\b\b}\n");
        sb.append("\n");

        sb.append("{");
        for(String div: divisoes.keySet()) {
            for(String sd: divisoes.get(div)) {
                sb.append(sd + ", ");
            }
        }
        sb.append("\b\b}\n");
        sb.append("\n");
        sb.append("Energy supplier:\n" + this.fornecedor.toString() + "\n");

        return sb.toString();
    }

    /**
     * Método que cria um clone da casa.
     * @return Clone da casa.
     */
    public Casa clone() {
        return new Casa(this);
    }

    /**
     * Método que liga todos os aparelhos numa divisão
     * @param div
     */
    public void turn_On_Divisao(String div) {
        if(this.divisoes.containsKey(div)) {
            for(String deviceID: this.divisoes.get(div)) {
                dispositivos.get(deviceID).turnOn();
            }
        }
    }

    /**
     * Método que desliga todos os aparelhos numa divisão
     * @param div
     */
    public void turn_Off_Divisao(String div) {
        if(this.divisoes.containsKey(div)) {
            for(String deviceID: this.divisoes.get(div)) {
                dispositivos.get(deviceID).turnOff();
            }
        }
    }

    /**
     * Método que liga todos os aparelhos de uma casa
     */
    public void turn_On_Casa() {
        for (SmartDevice disp: this.dispositivos.values()){
            disp.turnOn();
        }
    }

    /**
     * Método que desliga todos os aparelhos de uma casa
     */
    public void turn_Off_Casa() {
        for (SmartDevice disp: this.dispositivos.values()){
            disp.turnOff();
        }
    }

    /**
     * Método que adiciona uma divisão a uma casa
     * @param div
     */
    public void add_Divisao(String div) {
        if(!this.divisoes.containsKey(div)) {
            List<String> ids = new ArrayList<>();
            this.divisoes.put(div,ids);
        }
    }

    /**
     * Método que remove uma divisão a uma casa
     * @param div
     */
    public void remove_Divisao(String div) {
        if(this.divisoes.containsKey(div)) {
            for(String sd_ID: this.divisoes.get(div)) {
                this.divisoes.remove(sd_ID);
                this.dispositivos.remove(sd_ID);
            }
            this.divisoes.remove(div);
        }
    }

    /**
     * Método que altera o nome de uma divisão a uma casa
     * @param divName
     * @param newDivName
     */
    public void setNomeDaDivisao(String divName, String newDivName) {
        if (this.divisoes.containsKey(divName) && !this.divisoes.containsKey(newDivName)) {
            List<String> ids = this.divisoes.get(divName);
            this.divisoes.remove(divName);
            divisoes.put(newDivName, ids);
        }
    }

    /**
     * Método que adiciona um dispositivo a uma divisão de uma casa
     * @param div
     * @param sd
     */
    public void add_DispositivoNaDivisao(String div, SmartDevice sd){
        if (this.divisoes.containsKey(div)){
            if (!this.divisoes.get(div).contains(sd.getId())){
                this.divisoes.get(div).add(sd.getId());
                this.dispositivos.put(sd.getId(), sd);
            }
        }
        else{
            add_Divisao(div);
            if (!this.divisoes.get(div).contains(sd.getId())){
                this.divisoes.get(div).add(sd.getId());
                this.dispositivos.put(sd.getId(), sd);
            }
        }
    }

    public void setNomeDispositivo(String divName, SmartDevice sd, String novoID) {
        if (!this.divisoes.get(divName).contains(novoID) && this.divisoes.containsKey(divName)) {
            this.divisoes.get(divName).remove(sd.getId());
            this.dispositivos.remove(sd.getId());
            sd.setId(novoID);
            this.divisoes.get(divName).add(novoID);
            this.dispositivos.put(novoID, sd);
        }
    }

    /**
     * Método que remove um dispositivo de uma divisão de uma casa
     * @param id
     * @param div
     */
    public void remove_DispositivoNaDivisao(String id, String div) {
        if (this.dispositivos.containsKey(id)) {
            divisoes.get(div).remove(id);
            dispositivos.remove(id);
        }
    }

    /**
     * Método que adiciona um fornecedor
     * @param fornecedor
     */
    public void add_Fornecedor(FornecedorEnergia fornecedor) {
        this.fornecedor = fornecedor;
    }

    /**
     * Método que lista todos os dispositivos de uma casa
     * @return sb
     */
    public String listaDevices(){
        StringBuilder sb = new StringBuilder();
        sb.append("List of devices:\n");
        sb.append("{");
        for(String idDevice: this.dispositivos.keySet()){
            sb.append(idDevice + ", ");
        }
        sb.append("\b\b}");

        return sb.toString();
    }

    /**
     * Método que lista a informação de um determinado dispositivo
     * @param idDevice
     * @return sb
     */
    public String listaInfoDevice(String idDevice){
        StringBuilder sb = new StringBuilder();

        sb.append(this.dispositivos.get(idDevice).toString());

        return sb.toString();
    }

    /**
     * Método que lista as divisoes de uma casa
     * @return
     */
    public String listaDivisoes() {
        StringBuilder sb = new StringBuilder();

        for(String divName: this.divisoes.keySet()) {
            sb.append(divName);
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Método que lista os dispositivos numa determinada divisao
     * @param divName
     * @return
     */
    public String listaDispositivosNaDivisao(String divName) {
        StringBuilder sb = new StringBuilder();

        sb.append("{");
        for(String deviceID: this.divisoes.get(divName)) {
            sb.append(this.dispositivos.get(deviceID).getId() + ", ");
        }
        sb.append("\b\b}");

        return sb.toString();
    }




    // Getters and Setters
    /**
     * Método get que retorna o id da casa
     * @return idCasa
     */
    public String getIdCasa() {
        return this.idCasa;
    }

    /**
     * Método set que altera o id da casa
     * @param idCasa
     */
    public void setIdCasa(String idCasa) {
        this.idCasa = idCasa;
    }

    /**
     * Método get que retorna a morada
     * @return morada
     */
    public String getMorada() {
        return morada;
    }

    /**
     * Método set que altera a morada da casa
     * @param morada
     */
    public void setMorada(String morada) {
        this.morada = morada;
    }

    /**
     * Método get que retorna o nome da casa
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método set que altera o nome de uma casa
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método get que retorna o NIF associado a uma casa
     * @return NIF
     */
    public String getNIF() {
        return NIF;
    }


    /**
     * Método set altera o NIF de uma casa
     * @param NIF
     */
    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    /**
     * Método get que retorna o conjunto de dispositivos
     * @return dispositivos
     */
    public Map<String, SmartDevice> getDispositivos() {
        return this.dispositivos;
    }

    /**
     * Método set que altera o conjunto de dispositivos
     * @param dispositivos
     */
    public void setDispositivos(Map<String, SmartDevice> dispositivos) {
        this.dispositivos = dispositivos;
    }

    /**
     * Método get que retorna o conjunto de divisões da casa
     * @return divisoes
     */
    public Map<String, List<String>> getDivisoes() {
        return this.divisoes;
    }

    /**
     * Método set que altera o conjunto de divisoes
     * @param divisoes
     */
    public void setDivisoes(Map<String, List<String>> divisoes) {
        this.divisoes = divisoes;
    }

    /**
     * Método get que retorna o fornecedor da casa
     * @return fornecedor
     */
    public FornecedorEnergia getFornecedor() {
        return this.fornecedor;
    }

    /**
     * Método set que altera o fornecedor da casa
     * @param fornecedor
     */
    public void setFornecedor(FornecedorEnergia fornecedor) {
        this.fornecedor = fornecedor;
    }
}
