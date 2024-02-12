package Model;

public class SmartSpeaker extends SmartDevice {
    private enum Estado {
        ON,
        OFF
    }
    private Estado estado;
    private int n_estado;
    private String marca;
    private int volume;
    private String radioOnline;
    private double consumoBase;
    private double consumoF;
    private double precoInstalacao;
    private int identificador;

    /**
     * Construtor por omissão.
     */
    public SmartSpeaker() {
        super();
        this.estado = Estado.OFF;
        this.marca = "";
        this.volume = 0;
        this.radioOnline = "";
        this.consumoBase = 0;
        this.consumoF = 0;
        this.precoInstalacao = 10;
        this.identificador = 2;
    }

    /**
     * Construtor parametrizado
     * @param id
     * @param estado
     * @param marca
     * @param volume
     * @param radioOnline
     * @param consumoBase
     * @param precoInstalacao
     */
    public SmartSpeaker(String id, Estado estado, String marca, int volume, String radioOnline, double consumoBase) {
        super(id);
        this.estado = estado;
        this.marca = marca;
        this.volume = volume;
        this.radioOnline = radioOnline;
        this.consumoBase = consumoBase;
        this.consumoF = volume * consumoBase;
        this.precoInstalacao = 10;
        this.identificador = 2;
    }

    /**
     * Construtor parametrizado especializado
     * @param id
     * @param n_estado
     * @param marca
     * @param volume
     * @param radioOnline
     * @param consumoBase
     */
    public SmartSpeaker(String id, int n_estado, String marca, int volume, String radioOnline, double consumoBase) {
        super(id);
        this.n_estado = n_estado;
        this.marca = marca;
        this.volume = volume;
        this.radioOnline = radioOnline;
        this.consumoBase = consumoBase;
        this.consumoF = volume * consumoBase;
        this.precoInstalacao = 10;
        this.identificador = 2;
    }

    /**
    * Construtor por cópia.
    * @param ss
    */
    public SmartSpeaker (SmartSpeaker ss) {
        super();
        this.estado = ss.getEstado();
        this.marca = ss.getMarca();
        this.volume = ss.getVolume();
        this.radioOnline = ss.getRadioOnline();
        this.consumoBase = ss.getConsumoBase();
        this.consumoF = ss.getConsumoF();
        this.precoInstalacao = ss.getPrecoInstalacao();
        this.identificador = ss.getIdentificador();
    }

    /**
     * Método que verifica se os duas SmartSpeakers são iguais.
     * @param o SmartSpeaker passada como parâmetro.
     * @return True se as SmartSpeakers forem iguais | False se as SmartSpeakers forem diferentes.
     */
    public boolean equals (Object o) {
        if (this == o)
            return true;

        if (o == null || o.getClass() != this.getClass())
            return false;

        SmartSpeaker ss = (SmartSpeaker) o;
        return (ss.getId().equals(this.getId()) &&
                ss.getEstado() == this.estado &&
                ss.getMarca().equals(this.marca) &&
                ss.getVolume() == this.volume &&
                ss.getRadioOnline().equals(this.radioOnline) &&
                ss.getConsumoBase() == this.consumoBase &&
                ss.getConsumoF() == this.consumoF &&
                ss.getPrecoInstalacao() == this.precoInstalacao &&
                ss.getIdentificador() == this.identificador);
    }

    /**
     * Método que representa a SmartSpeaker em formato de texto.
     * @return String com as características da SmartSpeaker.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Device ID: " + this.getId() + "\n");
        sb.append("Mode: " + this.estado + "\n");
        sb.append("Brand: " + this.marca + "\n");
        sb.append("Online radio: " + this.radioOnline + "\n");
        sb.append("Volume: " + this.volume + "\n");
        sb.append("Device base consumption: " + this.consumoBase + " kWh" + "\n");
        sb.append("Device consumption: " + this.consumoF + " kWh" + "\n");
        sb.append("Installation price: " + this.precoInstalacao + " euros" + "\n");

        return sb.toString();
    }

    /**
     * Método que cria um clone da SmartSpeaker.
     * @return Clone da SmartSpeaker.
     */
    public SmartSpeaker clone() {
        return new SmartSpeaker(this);
    }

    /**
     * Método que liga uma SmartSpeaker
     */
    public void turnOn() {
        this.estado = Estado.ON;
    }

    /**
     * Método que desliga uma SmartSpeaker
     */
    public void turnOff() {
        this.estado = Estado.OFF;
    }



    /**
     * Método que retorna o estado da SmartSpeaker.
     * @return estado
     */
    public Estado getEstado() {
        return this.estado;
    }

    /**
     * Método set que altera o estado da SmartSpeaker.
     * @param estado
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * Método que retorna o n_estado da SmartSpeaker.
     * @return n_estado
     */
    public int getN_estado() {
        return this.n_estado;
    }

    /**
     * Método set que altera o estado da SmartSpeaker.
     * @param n_estado
     */
    public void setN_estado(int n_estado) {
        this.n_estado = n_estado;
    }

    /**
     * Método que retorna a marca da SmartSpeaker.
     * @return marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Método set que altera a marca da SmartSpeaker.
     * @param marca
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Métodoque retorna o volume da SmartSpeaker.
     * @return volume
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Método set que altera o volume da SmartSpeaker.
     * @param volume
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * Método que retorna a radio online da SmartSpeaker.
     * @return radioOnline
     */
    public String getRadioOnline() {
        return radioOnline;
    }

    /**
     * Método set que altera a radio online da SmartSpeaker.
     * @param radioOnline
     */
    public void setRadioOnline(String radioOnline) {
        this.radioOnline = radioOnline;
    }

    /**
     * Método que retorna o consumo base SmartSpeaker.
     * @return consumoBase
     */
    public double getConsumoBase() {
        return this.consumoBase;
    }

    /**
     * Método set que altera o estado da SmartSpeaker.
     * @param consumoBase
     */
    public void setConsumoBase(double consumoBase) {
        this.consumoBase = consumoBase;
    }

    /**
     * Método que retorna o consumo final da SmartSpeaker.
     * @return consumoF
     */
    public double getConsumoF() {
        double r = 0;
        if(estado == Estado.ON) {
            r = this.consumoF;
        }
        return r;
    }

    /**
     * Método set que altera o consumo final da SmartSpeaker.
     * @param consumoF
     */
    public void setConsumoF(double consumoF) {
        this.consumoF = consumoF;
    }

    /**
     * Método que retorna o preço por instalação
     * @return precoInstalacao
     */
    public double getPrecoInstalacao(){
        return this.precoInstalacao;
    }

    /**
     * Método que altera o preço por instalação
     * @param precoInstalacao
     */
    public void setPrecoInstalacao(double precoInstalacao){
        this.precoInstalacao = precoInstalacao;
    }

    /**
     * Método que retorna o identificador da SmartSpeacker
     * @return identificador
     */
    public int getIdentificador(){
        return this.identificador;
    }
}
