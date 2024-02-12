package Model;

public class SmartCamera extends SmartDevice {
    private enum Estado {
        ON,
        OFF
    }
    private Estado estado;
    private int n_estado;
    private int x; //resolução (comprimento)
    private int y; //resolução (largura)
    private long tempoLigada;
    private double tamanhoPacote;
    private double consumoBase;
    private double consumoF;
    private double precoInstalacao;
    private int identificador;

    /**
     * Construtor por omissão.
     */
    public SmartCamera() {
        super();
        this.estado = Estado.OFF;
        this.x = 0;
        this.y = 0;
        this.tempoLigada = 0;
        this.tamanhoPacote = 0;
        this.consumoBase = 0;
        this.consumoF = 0;
        this.precoInstalacao = 10;
        this.identificador = 3;
    }

    /**
     * Construtor parametrizado
     * @param id
     * @param estado
     * @param resolucao
     * @param precoInstalacao
     */
    public SmartCamera(String id, Estado estado, int x, int y, double tamanhoPacote, double consumoBase){
        super(id);
        this.estado = estado;
        this.x = x;
        this.y = y;
        this.tamanhoPacote = tamanhoPacote;
        this.consumoBase = consumoBase;
        this.consumoF = consumoBase * x * y;
        this.precoInstalacao = 10;
        this.identificador = 3;
    }

    /**
     * Construtor parametrizado especializado
     * @param id
     * @param n_estado
     * @param x
     * @param y
     * @param tamanhoPacote
     * @param consumoBase
     */
    public SmartCamera(String id, int n_estado, int x, int y, double tamanhoPacote, double consumoBase){
        super(id);
        this.n_estado = n_estado;
        this.x = x;
        this.y = y;
        this.tamanhoPacote = tamanhoPacote;
        this.consumoBase = consumoBase;
        this.consumoF = consumoBase * x * y;
        this.precoInstalacao = 10;
        this.identificador = 3;
    }

    /**
     * Construtor de cópia.
     * @param sc SmartCamera passada como parâmetro.
     */
    public SmartCamera(SmartCamera sc) {
        super();
        this.estado = sc.getEstado();
        this.x = sc.getX();
        this.y = sc.getY();
        this.tempoLigada = sc.getTempoLigada();
        this.tamanhoPacote = sc.getTamanhoPacote();
        this.consumoBase = sc.getConsumoBase();
        this.consumoF = sc.getConsumoF();
        this.precoInstalacao = sc.getPrecoInstalacao();
        this.identificador = sc.getIdentificador();
    }

	/**
     * Método que verifica se os duas SmartCameras são iguais.
     * @param o SmartCamera passada como parâmetro.
     * @return True se as SmartCamera forem iguais | False se as SmartCamera forem diferentes.
     */
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || o.getClass() != this.getClass())
            return false;

        SmartCamera sc = (SmartCamera) o;
        return (sc.getId().equals(this.getId()) &&
                sc.getEstado() == this.estado &&
                sc.getX() == this.x &&
                sc.getY() == this.y &&
                sc.getTamanhoPacote() == this.tamanhoPacote &&
                sc.getConsumoBase() == this.consumoBase &&
                sc.getConsumoF() == this.consumoF &&
                sc.getPrecoInstalacao() == this.precoInstalacao &&
                sc.getIdentificador() == this.identificador);
    }

    /**
     * Método que representa a SmartCamera em formato de texto.
     * @return String com as características da SmartCamera.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Device ID:" + this.getId() + "\n");
        sb.append("Mode: " + this.estado + "\n");
        sb.append("Resolution: " + "(" + this.x + "x" + this.y + ")" + "\n");
        sb.append("File size: " + this.tamanhoPacote + " sec" + "\n");
        sb.append("Device base consumption: " + this.consumoBase + " kWh" + " \n");
        sb.append("Device consumption: " + this.consumoF + " kWh" + "\n");
        sb.append("Installation price: " + this.precoInstalacao + " euros" + "\n");

        return sb.toString();
    }

    /**
     * Método que cria um clone da SmartCamera.
     * @return Clone da SmartCamera.
     */
    public SmartCamera clone() {
        return new SmartCamera(this);
    }

    /**
     * Método que liga uma SmartCamera.
     */
    public void turnOn() {
        this.estado = Estado.ON;
    }

    /**
     * Método que desliga uma SmartCamera.
     */
    public void turnOff() {
        this.estado = Estado.OFF;
    }




    /**
     * Método que retorna o estado da SmartCamera.
     * @return estado
     */
    public Estado getEstado() {
        return this.estado;
    }

    /**
     * Método set que o eera stado da SmartCamera.
     * @param estado
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * Método que retorna o n_estado da SmartCamera.
     * @return n_estado
     */
    public int getN_estado() {
        return this.n_estado;
    }

    /**
     * Método set que altera o estado da SmartCamera.
     * @param n_estado
     */
    public void setN_estado(int n_estado) {
        this.n_estado = n_estado;
    }

    /**
     * Método que retorna o x da resolução da SmartCamera.
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Método set que altera o x da resolução da SmartCamera.
     * @param x o
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Método que retorna y da resolução da SmartCamera.
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Método set que altera o y da resolução da SmartCamera.
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Método que retorna o tempo ligado da SmartCamera.
     * @return tempoLigada
     */
    public long getTempoLigada() {
        return this.tempoLigada;
    }

    /**
     * Método set que altera o tempo ligado da SmartCamera.
     * @param tempoLigada
     */
    public void setTempoLigada(long tempoLigada){
        this.tempoLigada = tempoLigada;
    }

    /**
     * Método que retorna o tamanho do pacote da SmartCamera.
     * @return tamanhoPacote
     */
    public double getTamanhoPacote() {
        return tamanhoPacote;
    }

    /**
     * Método set que altera o tamnho do pacote da SmartCamera.
     * @param tempoLigada
     */
    public void setTamanhoPacote(double tamanhoPacote){
        this.tamanhoPacote = tamanhoPacote;
    }

    /**
     * Método que retorna o consumo base da SmartCamera.
     * @return consumoBase
     */
    public double getConsumoBase() {
        return consumoBase;
    }

    /**
     * Método set que altera o consumo base da SmartCamera.
     * @param consumoBase
     */
    public void setConsumoBase(double consumoBase) {
        this.consumoBase = consumoBase;
    }

    /**
     * Método que retorna o consumo final da SmartCamera.
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
     * Método set que altera o  onsumo finalartCamera.
     * @param consumoF
     */
    public void setConsumoF(double consumoF) {
        this.consumoF = consumoF;
    }

    /**
     * Método que retorna o preco por instalacao
     * @return precoInstalacao
     */
    public double getPrecoInstalacao(){
        return this.precoInstalacao;
    }

    /**
     * Método que altera o preco por instalação
     * @param precoInstalacao
     */
    public void setPrecoInstalacao(double precoInstalacao){
        this.precoInstalacao = precoInstalacao;
    }

    /**
     * Método que retorna o identificador da SmartCamera
     * @return identificador
     */
    public int getIdentificador(){
        return this.identificador;
    }

}
