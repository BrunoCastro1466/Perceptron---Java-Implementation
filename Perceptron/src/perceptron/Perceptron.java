/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package perceptron;

import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;
import java.util.Random;
/**
 *
 * @author bruno
 */
public class Perceptron {

    /**
     * @param args the command line arguments
     */
    static double theta = 0;
    static int nTraining = 30;
    static int output;
    static int epoca = 0;
    static int saida[] = new int[10];
    static int amostra;
    
    static double learning_rate = 0.01;
    static double weights[] = new double[4];
    static double globalErro;
    static boolean erro;
   
    //Dados para treinamento
    static double x0[] = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    static double x1[] = {-0.6508, -1.4492, 2.0850, 0.2626, 0.6418, 0.2569, 1.1155, 0.0914, 0.0121, -0.0429, 0.4340, 0.2735, 0.4839, 0.4089, 1.4391, -0.9115, 0.3654, 0.2144, 0.2013, 0.6483, -0.1147, -0.7970, -1.0625, 0.5307, -1.2200, 0.3957, -0.1013, 2.4482, 2.0149, 0.2012};
    static double x2[] = {0.1097, 0.8896, 0.6876, 1.1476, 1.0234, 0.6730, 0.6043, 0.3399, 0.5256, 0.4660, 0.6870, 1.0287, 0.4851, -0.1267, 0.1614, -0.1973, 1.0475, 0.7515, 1.0014, 0.2183, 0.2242, 0.8795, 0.6366, 0.1285, 0.7777, 0.1076, 0.5989, 0.9455, 0.6192, 0.2611};
    static double x3[] = {4.0009, 4.4005, 12.0710, 7.7985, 7.0427, 8.3265, 7.4446, 7.0677, 4.6316, 5.4323, 8.2287, 7.1934, 7.4850, 5.5019, 8.5843, 2.1962, 7.4858, 7.1699, 6.5489, 5.8991, 7.2435, 3.8762, 2.4707, 5.6883, 1.7252, 5.6623, 7.1812, 11.2095, 10.9263, 5.4631};
    static int y[] = {-1, -1, -1, 1, 1, -1, 1, -1, 1, 1, -1, 1, -1, -1, -1, -1, 1, 1, 1, 1, -1, 1, 1, 1, 1, -1, -1, 1, -1, 1};
    
    //Dados para teste
    static double data[][] = {{-0.3565, 0.0620, 5.9891},{-0.7842, 1.1267, 5.5912},{0.3012, 0.5611, 5.8234},{0.7757, 1.0648, 8.0677},{0.1570, 0.8028, 6.3040},{-0.7014, 1.0316, 3.6005},{0.3748, 0.1536, 6.1537},{-0.6920, 0.9404, 4.4058},{-1.3970, 0.7141, 4.9263},{-1.8842, -0.2805, 1.2548}};
    
    //Randomizar valores para os pesos
    public static double[] weightRandom(){
        Random rand = new Random();
        for(int count = 0; count < 4; count ++){
          weights[count] = rand.nextDouble();
        }        
        return weights;
    }
    
    //Calcular a saída Y simulada
    public static int outputCalc(double theta, double weights[], double x0, double x1, double x2, double x3){
        double sum =  x0*weights[0] + x1*weights[1] + x2*weights[2] + x3*weights[3];
        
        int retorno;
        if(sum >= theta){
            retorno = 1;
        }else{
            retorno = -1;
        }
        
        return retorno;
    }
    
    //Calcular a saída Y pós treinamento
    public static int perceptronOutput(double theta, double weights[], double input1, double input2, double input3){
        double y =  -1*weights[0] + input1*weights[1] + input2*weights[2] + input3*weights[3];        
        int retorno;
        if(y >= theta){
            retorno = 1;
        }else{
            retorno = -1;
        }
        return retorno;
        
    }
    
    
    public static void main(String[] args) {       
        
       
        double pesos[] = weightRandom();
        System.out.println("Pesos Iniciais: " + "\n" + "Peso w0 " + pesos[0] + "\n" + "Peso w1 "+ pesos[1] +"\n"+"Peso w2 " + pesos[2] + "\n" +"Peso w3 " + pesos[3]);
        do{
            erro = false;
         //globalErro = 0;
         //Loop por tds entradas(1 época)
            for(int p = 0; p < nTraining; p++){
                //Obter saída calculada
                output = outputCalc(theta, pesos, x0[p], x1[p], x2[p], x3[p]);
                //Diferença entre saída esperada e saída calculada
                if(y[p] != output){
                erro = true;
                //Atualização dos pesos
                pesos[0] = pesos[0] + learning_rate * (y[p]-output) *x0[p];
                pesos[1] = pesos[1] + learning_rate * (y[p]-output) *x1[p];
                pesos[2] = pesos[2] + learning_rate * (y[p]-output) *x2[p];
                pesos[3] = pesos[3] + learning_rate * (y[p]-output) *x3[p];
                }
                //Atualização Erro Global
                //globalErro = globalErro*(erro*erro);
               
            }
         epoca = epoca + 1;
         //System.out.println("Época " +epoca+": Erro atual"+globalErro);
        }while(erro);
        
        System.out.println("Epocas:"+epoca+"\n"+ "Peso w0:" + weights[0] + "\n" + "Peso w1:" + weights[1] + "\n" + "Peso w2:" +weights[2]+ "\n"+"Peso w3:"+weights[3]);
        
        
        
        //Classificação das 10 amostras para 5 treinamentos
        for(int counter1 = 0; counter1 < 10; counter1++){
              
              saida[counter1] = perceptronOutput(theta, pesos, data[counter1][0], data[counter1][1], data[counter1][2]);
               
              amostra = counter1 + 1;
              
              System.out.println("Amostra: " +amostra+"saida:"+saida[counter1]+"\n");
        }
        
        
        
    }
    
}
