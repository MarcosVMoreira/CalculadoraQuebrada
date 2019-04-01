/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadoraquebrada;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author Marcos
 */
public class EstadoCalculadora {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
       // Atributo para representar os pinos do jogo.
    private int pinos[];
    
    private ArrayList<Integer> teclas;
    private ArrayList<Integer> valoresAtuais;
    
    // Numero de pinos no jogo
    private static int NUM_PINOS = 3;
    
    private int numDiscos;
    
    public EstadoCalculadora() {
        this.valoresAtuais =  new ArrayList();
    }
    
    public void addTeclaFuncional (int tecla) {
        this.teclas.add(tecla);
    }
    
    public void adicionaNovoValor (int valor) {
        this.valoresAtuais.add(valor);
    }
    
    public boolean compararCom(int estadoFinal) {
        /* apos gerar um nivel completo, comparo com o estadoFinal */
        for(int i = 0; i < valoresAtuais.size(); ++i) {
            if (valoresAtuais.get(i) == estadoFinal) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    
    public void mostrarEstado() {
        System.out.printf("Valores: ");
        for(int i =0; i < valoresAtuais.size(); ++i) {
            while(it.hasNext()) {
                System.out.print(" "+it.next());
            }
            System.out.println();
        }
    }
    
    public EstadoHanoi clonar() {
        EstadoHanoi clone = new EstadoHanoi(numDiscos);
        for(int i = 0; i < NUM_PINOS; ++i) {
            clone.pinos[i] = (Stack<Integer>) 
                    pinos[i].clone();
        } 
        return clone;
    }
    
    public static void main(String args[]) {
        EstadoHanoi e = new EstadoHanoi(5);
        
        e.addDisco(0, 5);
        e.addDisco(0, 4);
        e.addDisco(0, 3);
        e.addDisco(0, 2);
        e.addDisco(0, 1);
        
        EstadoHanoi objetivo = new EstadoHanoi(5);
        objetivo.addDisco(1, 5);
        objetivo.addDisco(1, 4);
        objetivo.addDisco(1, 3);
        objetivo.addDisco(1, 2);
        objetivo.addDisco(1, 1);
        
        No raiz = new No(e);
        //No solucao = raiz.buscaLargura(objetivo);
        No solucao = raiz.buscaProfundidade(objetivo, raiz);
        
        Stack<No> passos = new Stack();
        while(solucao != null) {
            passos.push(solucao);
            solucao = solucao.getPai();
        }
        
        System.out.println("Numero de açoes = "+passos.size());
        int numPassos = passos.size();
        for(int i = 0; i < numPassos; ++i) {
            System.out.println("Passo "+(i+1));
            passos.pop().getEstadoHanoi().mostrarEstado();
        }
        
    }
    
}
