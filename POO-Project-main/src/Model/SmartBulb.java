package Model;

public class SmartBulb extends SmartDevice {
    private enum Estado {
        ON,
        OFF
    }
    private Estado estado;
    private int n_estado;
    private enum Tonalidade {
        NEUTRAL,
        WARM,
        COLD
    }
    private Tonalidade tone;
    private String string_tone;
    private double dimensao;
    private double consumoBase;
    private double consumoF;
    private double precoInstalacao;
    private int identificador;

    /**
     * Construtor por omissão.
     */
    public SmartBulb() {
        super();
        this.tone = Tonalidade.NEUTRAL;
        this.dimensao = 0;
        this.consumoBase = 0;
        this.consumoF = 0;
        this.precoInstalacao = 10;
        this.identificador = 1;
    }

    /**
     * Construtor com parâmetros.
     * @param id identificação da SmartBulb.
     * @param estado Estado da SmartBulb.
     * @param tone Tonalidade da SmartBulb.
     * @param precoInstalacao preco pela instalação do aparelho
     */
    public SmartBulb(String id, Estado estado, Tonalidade tone, double dimensao, double consumoBase) {
        super(id);
        this.estado = estado;
        this.tone = tone;
        this.consumoBase = consumoBase;
        if(tone.equals(Tonalidade.COLD))
            this.consumoF = consumoBase * 1;
        else if(tone.equals(Tonalidade.NEUTRAL))
            this.consumoF = consumoBase * 2;
        else
            this.consumoF = consumoBase * 3;
        this.precoInstalacao = 10;
        this.identificador = 1;
    }

    /**
     * Construtor parametrizado especializado
     * @param id
     * @param n_estado
     * @param string_tone
     * @param dimensao
     * @param consumoBase
     */
    public SmartBulb(String id, int n_estado, String string_tone, double dimensao, double consumoF) {
        super(id);
        this.n_estado = n_estado;
        this.string_tone = string_tone;
        this.dimensao = dimensao;
        this.consumoBase = consumoF;
        this.consumoF = consumoF;
        this.precoInstalacao = 10;
        this.identificador = 1;
    }

    /**
     * Construtor de cópia.
     * @param sb SmartBulbs passada como parâmetro.
     */
    public SmartBulb(SmartBulb sb) { // SB de SmartBulb (usem SC e SS)
        super();
        this.estado = sb.getEstado();
        this.tone = sb.getTone();
        this.dimensao = sb.getDimensao();
        this.consumoBase = sb.getConsumoBase();
        this.consumoF = sb.getConsumoF();
        this.precoInstalacao = sb.getPrecoInstalacao();
        this.identificador = sb.getIdentificador();
    }

    /**
     * Método que verifica se os duas SmartBulbs são iguais.
     * @param o SmartBBulb passada como parâmetro.
     * @return True se as SmartBulbs forem iguais | False se as SmartBulbs forem diferentes.
     */
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || o.getClass() != this.getClass())
            return false;

        SmartBulb sb = (SmartBulb) o;
        return (sb.getId().equals(this.getId()) &&
                sb.getEstado() == this.estado &&
                sb.getTone() == this.tone &&
                sb.getDimensao() == this.dimensao &&
                sb.getConsumoBase() == this.consumoBase &&
                sb.getConsumoF() == this.consumoF &&
                sb.getPrecoInstalacao() == this.precoInstalacao &&
                sb.getIdentificador() == this.identificador);
    }

    /**
     * Método que cria um clone da SmartBulb.
     * @return Clone da SmartBulb.
     */
    public SmartBulb clone() {
        return new SmartBulb(this);
    }

    /**
     * Método que representa a SmartBulb em formato de texto.
     * @return String com as características da SmartBulb.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Device id: " + this.getId() + "\n");
        sb.append("Mode: " + this.estado + "\n");
        sb.append("Tone: " + this.tone + "\n");
        sb.append("Dimension: " + this.dimensao + " cm" + "\n");
        sb.append("Device base consumption: " + this.consumoBase + " kWh" + " \n");
        sb.append("Device consumption: " + this.consumoF + " kWh" + " \n");
        sb.append("Installation price: " + this.precoInstalacao + " euros" + "\n");

        return sb.toString();
    }

    /**
     * Método que liga uma SmartBulb
     */
    public void turnOn() {
        this.estado = Estado.ON;
    }

    /**
     * Método que desliga uma SmartBulb
     */
    public void turnOff() {
        this.estado = Estado.OFF;
    }

    /**
     * Método que muda a tonalidade da SmartBulb para NEUTRAL.
     */
    public void turn_NEUTRAL() {
        this.tone = Tonalidade.NEUTRAL;
    }

    /**
     * Método que muda a tonalidade da SmartBulb para WARM.
     */
    public void turn_WARM() {
        this.tone = Tonalidade.WARM;
    }

    /**
     * Método que muda a tonalidade da SmartBulb para COLD.
     */
    public void turn_COLD() {
        this.tone = Tonalidade.COLD;
    }



    /**
     * Método que retorna o valor do estado
     * @return estado
     */
    public Estado getEstado() {
        return this.estado;
    }

    /**
     * Método set que altera o valor do estado
     * @param estado
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * Método que retorna o valor da tonalidade
     * @return tone
     */
    public Tonalidade getTone() {
        return tone;
    }

    /**
     * Método set que altera o valor da tonalidade
     * @param tone
     */
    public void setTone(Tonalidade tone) {
        this.tone = tone;
    }

    /**
     * Método que retorna o valor da string tonalidade
     * @return string_tonalidade
     */
    public String getString_tone() {
        return this.string_tone;
    }

    /**
     * Método set que altera o valor da string tonalidade
     * @return string_tonalidade
     */
    public void setString_tone(String string_tone) {
        this.string_tone = string_tone;
    }

    /**
     * Método que retorna o valor do n_estado
     * @return n_estado
     */
    public int getN_estado() {
        return this.n_estado;
    }

    /**
     * Método set que altera o valor do n_estado
     * @param n_estado
     */
    public void setN_estado(int n_estado) {
        this.n_estado = n_estado;
    }

    /**
     * Método que retorna o valor da dimensão de uma SmartBulb
     * @return dimensao
     */
    public double getDimensao() {
        return this.dimensao;
    }

    /**
     * Método set que altera o valor da dimensao
     * @param dimensao
     */
    public void setDimensao(double dimensao) {
        this.dimensao = dimensao;
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
     * Método que retorna o identificador do tipo SmartBulb
     * @return identificador
     */
    public int getIdentificador(){
        return this.identificador;
    }

    /**
     * Método get que retorna o valor do consumo base
     * @return consumoBase
     */
    public double getConsumoBase() {
        return consumoBase;
    }

    /**
     * Método set que altera o valor do consumo por hora
     * @param consumobase
     */
    public void setConsumoBase(double consumoBase) {
        this.consumoBase = consumoBase;
    }

    /**
     * Método que retorna o valor do consumo final
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
     * Método set que altera o valor do consumo final
     * @param consumoF
     */
    public void setConsumoF(double consumoF) {
        this.consumoF = consumoF;
    }

}
