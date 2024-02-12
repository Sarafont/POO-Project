package Model;

import java.io.Serializable;

public abstract class SmartDevice implements Serializable {
    private String id;

    public SmartDevice() {
        this.id = "";
    }

    /**
     * Construtor por omissão
     */
    public SmartDevice(String id) {
        this.id = id;
    }

    /**
     * Construtor parametrizado
     * @param dispositivos
     */
    public SmartDevice(SmartDevice dispositivo) {
        this.id = dispositivo.getId();
    }


    /**
     * Método que verifica se dois dispositivos são iguais.
     * @param o dispositivo passado como parâmetro
     * @return True se os dispositivos forem iguais | False se os dispositivos forem diferentes
     */
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || o.getClass() != this.getClass())
            return false;

        SmartDevice sd = (SmartDevice) o;
        return (sd.getId().equals(this.id));
    }

    /**
     * Método que representa um dispositivo em formato de texto.
     * @return String com as características do dispositivo.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("id: " + this.id + "\n");

        return sb.toString();
    }

    /**
     * Método que cria um clone do dispositivo.
     * @return Clone do dispositivo.
     */
    public abstract SmartDevice clone();

    /**
     * Método que liga um dispositivo.
     */
    public abstract void turnOn();

    /**
     * Método que desliga um dispositivo.
     */
    public abstract void turnOff();

    /**
     * Método que determina o consumo base de um dispositivo.
     */
    public abstract double getConsumoBase();

    /**
     * Método que determina o consumo final de um dispositivo.
     */
    public abstract double getConsumoF();

    /**
     * Método que determina o indentificador de um dispositivo
     */
    public abstract int getIdentificador();





    // Getters and Setters
    /**
     * Método que retorna o id de um dispositivo
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Método set que altera o id de um dispositivo
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

}
