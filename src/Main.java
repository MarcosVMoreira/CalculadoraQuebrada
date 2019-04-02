import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 *
 * @author Marcos
 */

class Main {
    public static void main(String[] args) {
        
        EstadoCalculadora calculadora = new EstadoCalculadora();
        calculadora.preencheEstado();
        
    }
} 

class EstadoCalculadora {
    
    private ArrayList<Integer> teclas;
    private ArrayList<String> operadores;
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Integer> jaUsados;
    
    private int numeroOperacoes = -1;
    
    private int numeroAlvo;
    
    private LinkedHashSet<No> nivelAtual = new LinkedHashSet<No>();
    
    private boolean buscando = true;
    
    public void preencheEstado () {
        teclas = new ArrayList<Integer>();
        operadores = new ArrayList<String>();
        jaUsados = new ArrayList<Integer>();
        
        numeroAlvo = scanner.nextInt();
        
        int nTecla = scanner.nextInt();
        for (int i = 0; i < nTecla; i++) {
            teclas.add(scanner.nextInt());
        }
        
        int nOperadores = scanner.nextInt();
        for (int i = 0; i < nOperadores; i++) {
            operadores.add(Character.toString(scanner.next().charAt(0)));
        }
        
        No no = new No(0);
        nivelAtual.add(no);
        iniciaBusca();
        
        System.out.println(numeroOperacoes);
    }
    
    public void iniciaBusca () {
        
        int resultado = 0;
        
        while (buscando) {
            LinkedHashSet<No> filhos = new LinkedHashSet<No>();
            
            //n³ dentro de um while. Complexidade > 99999999
            
            //iterando pelos valores que tenho no nivel atual
            for(No objeto : nivelAtual) {
                //iterando pelos operadores disponiveis
                for (int k = 0; k < operadores.size(); k++) {
                    //iterando pelas teclas disponiveis (nao quebradas)
                     for (int j = 0; j < teclas.size(); j++) {
                        switch (operadores.get(k)) {
                            case "+":
                                resultado = objeto.getValor() + teclas.get(j);
                                break;
                            case "-":
                                resultado = objeto.getValor() - teclas.get(j);
                                break;
                            case "*":
                                resultado = objeto.getValor() * teclas.get(j);
                                break;
                            case "/":
                                resultado = objeto.getValor() / teclas.get(j);
                                break;
                        }
                        if (resultado != numeroAlvo) {
                            //Se nao achei o resultado, eu adiciono o resultado nos filhos
                            if (resultado > 0) {
                                boolean flag = false;
                                for (int i = 0; i < jaUsados.size(); i++) {
                                    if (jaUsados.get(i) == resultado) {
                                        flag = true;
                                    }
                                }
                                if (!flag) {
                                    No result = new No (resultado);
                                    filhos.add(result);
                                    jaUsados.add(resultado);
                                }
                            }
                           
                        } else {
                            //Se achei o resultado, saio do while
                            buscando = false;
                        }
                    }
                }
            }
            
            //adiciono mais um paço pra chegar no resultado
            numeroOperacoes++;
            
            //zero o nivel antigo pra poder
            nivelAtual.clear();
            
            //Apago o nivel que estava salvo e seto o novo nivel como os atuais filhos que acabei de gerar
            nivelAtual.addAll(filhos);
            

            filhos.clear();
        }
    }
}


class No {
    
    private int valor;
    private LinkedHashSet<No> filhos;
    
    public No (int valor) {
        this.valor = valor;
        this.filhos = new LinkedHashSet<No>();
    }
    
    public int getValor() {
        return this.valor;
    }
}  

