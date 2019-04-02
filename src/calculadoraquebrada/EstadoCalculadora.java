/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadoraquebrada;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


/**
 *
 * @author Marcos
 */

class EstadoCalculadora {
    
    private ArrayList<Integer> teclas;
    private ArrayList<String> operadores;
    
    private int numeroOperacoes;
    
    private int numeroAlvo;
    
    private Set<No> nivelAtual = new HashSet();
    
    private boolean buscando = true;
    
    public void preencheEstado () {
        teclas.add(2);
        teclas.add(4);
        teclas.add(5);
        operadores.add("+");
        operadores.add("-");
        
        No no = new No(0);
        nivelAtual.add(no);
    }
    
    public void iniciaBusca () {
        Set<No> filhos;
        int resultado = 0;
        
        while (buscando) {
            filhos = new HashSet();
            
            //n³ dentro de um while. Complexidade > 99999999
            for(No objeto : nivelAtual) {
                for (int j = 0; j < teclas.size(); j++) {
                    for (int k = 0; k < operadores.size(); k++) {
                        switch (operadores.get(k)) {
                            case "+":
                                resultado = teclas.get(j) + objeto.getValor();
                                break;
                            case "-":
                                resultado = teclas.get(j) + objeto.getValor();
                                break;
                            case "*":
                                resultado = teclas.get(j) + objeto.getValor();
                                break;
                            case "/":
                                resultado = teclas.get(j) + objeto.getValor();
                                break;
                        }
                        if (resultado != numeroAlvo) {
                            No result = new No (resultado);
                        
                            filhos.add(result);
                        } else {
                            buscando = false;
                        }
                    }
                }
            }
            numeroOperacoes++;
            
            //Apago o nivel que estava salvo e seto o novo nivel como os atuais filhos que acabei de gerar
            nivelAtual.addAll(filhos);
        }
        
        
    }
}


class No {
    
    private int valor;
    private Set filhos;
    
    public No (int valor) {
        this.valor = valor;
        this.filhos = new HashSet();
    }
    
    public int getValor() {
        return this.valor;
    }
}  