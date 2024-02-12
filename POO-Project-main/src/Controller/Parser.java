package Controller;

import Model.*;

import java.io.IOException;
import java.nio.charset.*;
import java.nio.file.*;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private int houseID = 1;
    private int deviceID = 1;
    private Cidade cidade;

    /**
     * Construtor por omissão
     */
    public Parser() {
        this.cidade = new Cidade();
    }

    /**
     * Construtor parametrizado
     * @param cidade
     */
    public Parser(Cidade cidade) {
        this.cidade = cidade;
    }

    /**
     * Método que lê o ficheiro dado
     * @param nomeFich caminho do ficheiro
     * @return lines
     */
    public List<String> lerFicheiro(String nomeFich) {
        List<String> lines;

        try {lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8);}
        catch(IOException exc) {lines = new ArrayList<>();}

        return lines;
    }

    /**
     * Método parse
     * @param nomeFicheiro
     * @throws IOException
     */
    public void parse(String nomeFicheiro) throws IOException{
        Path path = Path.of(nomeFicheiro);
        String conteudo = Files.readString(path);
        String[] conteudoPartido = conteudo.split("\n");
        String[] linhaPartida;
        String divisao = "";
        Casa casa = null;
        FornecedorEnergia fe = null;

        for (String linha : conteudoPartido) {

            linhaPartida = linha.split(":", 2);

            switch(linhaPartida[0]) {
                case "Casa":
                    casa = parseCasa(linhaPartida[1]);
                    cidade.add_Casa(casa.getIdCasa(), casa);
                    break;

                case "Divisao":
                    if (casa == null)
                        System.out.println("Invalid line!");
                    divisao = linhaPartida[1].trim();
                    casa.add_Divisao(divisao);
                    break;

                case "SmartBulb":
                    if (divisao == null)
                        System.out.println("Invalid line!");
                    SmartBulb sb = parseSmartBulb(linhaPartida[1], casa);
                    casa.add_DispositivoNaDivisao(divisao, sb);

                    switch(sb.getN_estado()) {
                        case(1):
                            sb.turnOn();
                            break;

                        case(2):
                            sb.turnOff();
                            break;
                    }

                    switch(sb.getString_tone()) {
                        case("Neutral"):
                            sb.turn_NEUTRAL();
                            break;

                        case("Warm"):
                            sb.turn_WARM();
                            break;

                        case("Cold"):
                            sb.turn_COLD();
                            break;
                    }
                    break;

                case "SmartSpeaker":
                    if (divisao == null)
                        System.out.println("Invalid line!");
                    SmartSpeaker ss = parseSmartSpeaker(linhaPartida[1], casa);
                    casa.add_DispositivoNaDivisao(divisao, ss);

                    switch(ss.getN_estado()) {
                        case(1):
                            ss.turnOn();
                            break;

                        case(2):
                            ss.turnOff();
                            break;
                    }

                    break;

                case "SmartCamera":
                    if (divisao == null)
                        System.out.println("Invalid line!");
                    SmartCamera sc = parseSmartCamera(linhaPartida[1], casa);
                    casa.add_DispositivoNaDivisao(divisao, sc);

                    switch(sc.getN_estado()) {
                        case(1):
                            sc.turnOn();
                            break;

                        case(2):
                            sc.turnOff();
                            break;
                    }

                    break;

                case "Fornecedor":
                    fe = parseFornecedorEnergia(linhaPartida[1], cidade);
                    cidade.add_Fornecedor(fe);
                    break;

                default:
                    System.out.println("Linha inválida.");
                    break;
            }
        }
        System.out.println("Done!");
    }

    /**
     * Método que faz o parse de uma Casa
     * @param input
     * @return new Casa(idCasa, nome, NIF, fe) - retorna a casa contruída através dos parâmetros
     */
    public Casa parseCasa(String input) {
        String[] campos = input.split(",");
        String nome = campos[0];
        String NIF = campos[1];
        String nomeF = campos[2];
        String nomeFornecedor = nomeF.trim();
        FornecedorEnergia fe = this.cidade.getFornecedor(nomeFornecedor);
        String idCasa = Integer.toString(houseID);
        cidade.getFornecedorDeCadaCasa().put(idCasa, fe);
        this.houseID++;

        return new Casa(idCasa, nome, NIF, fe);
    }

    /**
     * Método que faz o parse de uma SmartBulb
     * @param imput
     * @param casa
     * @return new SmartBulb(deviceID, 1, string_tone, dimensoes, consumo) - retorna a SmartBulb construída através dos parâmetros
     */
    public SmartBulb parseSmartBulb(String input, Casa casa){
        String[] campos = input.split(",");
        String string_tone = campos[0];
        int dimensoes = Integer.parseInt(campos[1]);
        double consumo = Double.parseDouble(campos[2]);
        String deviceID = Integer.toString(this.deviceID);
        this.deviceID++;

        return new SmartBulb(deviceID, 1, string_tone, dimensoes, consumo);
    }

    /**
     * Método que faz o parse de um SmartSpeaker
     * @param imput
     * @param casa
     * @return new SmartSpeaker(deviceID, 1, marca, volume, radioOnline, consumo) - retorna o SmartSpeaker construído através dos parâmetros
     */
    public SmartSpeaker parseSmartSpeaker(String input, Casa casa) {
        String[] campos = input.split(",");
        int volume = Integer.parseInt(campos[0]);
        String radioOnline = campos[1];
        String marca = campos[2];
        double consumo = Double.parseDouble(campos[3]);
        String deviceID = Integer.toString(this.deviceID);
        this.deviceID++;

        return new SmartSpeaker(deviceID, 1, marca, volume, radioOnline, consumo);
    }

    /**
     * Método que faz o parse de uma SmartCamera
     * @param imput
     * @param casa
     * @return new SmartCamera(deviceID, 1, largura, altura, tamanho, consumo) - retorna a SmartCamera construída através dos parâmetros
     */
    public SmartCamera parseSmartCamera(String input, Casa casa) {
        String[] campos = input.split(",");
        String resolucao = campos[0];
        resolucao = resolucao.replace("(","");
        resolucao = resolucao.replace(")","");
        String[] larguraAltura = resolucao.split("x");
        int largura = Integer.parseInt(larguraAltura[0]);
        int altura = Integer.parseInt(larguraAltura[1]);
        double tamanho = Double.parseDouble(campos[1]);
        double consumo = Double.parseDouble(campos[2]);
        String deviceID = Integer.toString(this.deviceID);
        this.deviceID++;

        return new SmartCamera(deviceID, 1, largura, altura, tamanho, consumo);
    }

    /**
     * Método que faz o parse de um Fornecedor de Energia
     * @param input
     * @param cidade
     * @return new FornecedorEnergia(nome, valorBase, desconto) - retorna o Fornecedor de Energia construído através dos parâmetros
     */
    public FornecedorEnergia parseFornecedorEnergia(String input, Cidade cidade) {
        String[] campos = input.split(",");
        String nome = campos[0];
        double valorBase = Double.parseDouble(campos[1]);
        double desconto = Double.parseDouble(campos[2]);

        return new FornecedorEnergia(nome, valorBase, desconto);
    }
}
