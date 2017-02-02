import java.util.*;
import java.math.*;

/**
 * MultiplicaDyV aplica el algoritmo a los dos números pasados por argumento.
 * 
 * En este archivo se hacen los cálculos empezando por la comprobación de los tamaños de los números.
 * 
 * @author Melvin Vermeulen
 * @version 10.01.2017
 */

public class MultiplicaDyV
{
    
    private String numeroUno;
    private String numeroDos;
    private boolean traza;
    private boolean trazaAArchivo;
    private String resultado;
    private boolean resultadoNegativo;
    private String trazaArchivo = "";
    
    /**
     * Constructor de objetos de la clase MultiplicaDyV
     * 
     * En el constructor comparamos directamente los tamaños de ambos números y escogemos un tamaño óptimo múltiplo de 18x2n.
     * 
     */
    public MultiplicaDyV(String numeroUno, String numeroDos, boolean traza, boolean trazaAArchivo, boolean resultadoNegativo)
    {
        int tamanhoA = numeroUno.length();
        int tamanhoB = numeroDos.length();
        
        if(tamanhoA > 9 || tamanhoB > 9){
            int tamanhoMayor = tamanhoA;
            int tamanhoMax = 18;
            if(tamanhoB > tamanhoA) {
                tamanhoMayor = tamanhoB;
            }
        
            while(tamanhoMayor>tamanhoMax){
                tamanhoMax = tamanhoMax*2;
            }
        
            this.numeroUno = corregirNumero(numeroUno, tamanhoA, tamanhoMax);
            this.numeroDos = corregirNumero(numeroDos, tamanhoB, tamanhoMax);
        } else {
            this.numeroUno = numeroUno;
            this.numeroDos = numeroDos;
        }
        
        this.traza = traza;
        this.trazaAArchivo = trazaAArchivo;
        this.resultadoNegativo = resultadoNegativo;
        resultado = "";
    }

    /**
     * Este método añadirá tantos ceros como sea necesario para evitar errores en el cálculo al aplicar el algoritmo Divide Y Vencerás.
     * 
     * @param numero el número a corregir
     * @param longitudNumero la longitud del número a corregir
     * @param tamanhoMayor la longitud a la que corregir el número
     * 
     * @return el número corregido con los ceros adicionales
     */
    
    private String corregirNumero(String numero, int longitudNumero, int tamanhoMayor){
        
        String numeroCorregido = numero;
        
        for(int numCeros = 0; tamanhoMayor > numCeros + longitudNumero; numCeros++){
            numeroCorregido = "0" + numeroCorregido;
        }
        
        return numeroCorregido;
        
    }
    
    /**
     * Método para dividir la cadena de números en dos. Devolverá un vector de cadenas de tamaño longitudNumero/2
     * 
     * @param numero la cadena a dividir en dos
     * @param longitudNumero la longitud de la cadena a dividir en dos
     * 
     * @return el vector con las dos cadenas
     */
    
    private ArrayList<String> dividirNumeroEnDos(String numero, int longitudNumero){
        
        int mitad = (longitudNumero/2);
        ArrayList<String> nuevoVector = new ArrayList<String>();
        
        nuevoVector.add(numero.substring(0,mitad));
        nuevoVector.add(numero.substring(mitad, longitudNumero));
        
        return nuevoVector;        
    }
    
    /**
     * Este método calculará el resultado de aplicar el algoritmo de Divide Y Vencerás.
     * La solución trivial es la multiplicación de dos números cuyo resultado no exceda el tamaño del tipo Long.
     * 
     * @param vectorNumeroA el primer número
     * @param vectorNumeroB el segundo número
     * 
     * @return el resultado
     */
    
    public BigInteger calcularResultado(String numeroA, String numeroB){
        
        BigInteger resultado;
        BigInteger sumaInterna;
        BigInteger sumaMedia;
        BigInteger aLbL;
        BigInteger aRbR;
        ArrayList<String> vectorNumeroA;
        ArrayList<String> vectorNumeroB;
        String aL;
        String aR;
        String bL;
        String bR;
        String sumaaLaR;
        String sumabLbR;
        int tamanhoSuma = 0;
        int tamanhoMax = 9;
        BigInteger resultadoParcial;
        BigInteger exponenteDiez;
        BigInteger exponenteDiez2;
        int exponente;
              
        if(numeroA.length() <= 9 && numeroB.length() <= 9){
            resultado = BigInteger.valueOf(getNumber(numeroA)*getNumber(numeroB));
        } else{
            
            if(traza){
                trazaArchivo += "Longitud A: " + numeroA.length() + " LongitudB: " + numeroB.length() + "\n";
            }
            
            exponente = numeroA.length()/2;
            exponenteDiez = (BigInteger.TEN).pow(exponente);
            exponenteDiez2 = (BigInteger.TEN).pow(exponente*2);
            
            vectorNumeroA = dividirNumeroEnDos(numeroA, numeroA.length());
            aL = vectorNumeroA.get(0);
            aR = vectorNumeroA.get(1);
            
            if(traza){
                trazaArchivo += "aL: " + aL + " aR: " + aR + "\n";
            }

            vectorNumeroB = dividirNumeroEnDos(numeroB, numeroB.length());
            bL = vectorNumeroB.get(0);
            bR = vectorNumeroB.get(1);
            
            if(traza){
                trazaArchivo += "bL: " + bL + " bR: " + bR + "\n";
            }
            
            aLbL = calcularResultado(aL, bL);
            aRbR = calcularResultado(aR, bR);
            
            sumaaLaR = suma(aL,aR);
            sumabLbR = suma(bL,bR);
            
            tamanhoSuma = sumaaLaR.length();
            
            if(tamanhoSuma < sumabLbR.length()){
                tamanhoSuma = sumabLbR.length();
            }
            
            while(tamanhoSuma > tamanhoMax){
                tamanhoMax = tamanhoMax*2;
            }
            
            sumaaLaR = corregirNumero(sumaaLaR, sumaaLaR.length(), tamanhoMax);
            sumabLbR = corregirNumero(sumabLbR, sumabLbR.length(), tamanhoMax);
            
            sumaInterna = calcularResultado(sumaaLaR,sumabLbR);
            
            sumaMedia = sumaInterna.add((aLbL.add(aRbR)).negate());
            
            if(traza){
                trazaArchivo += "aLbL: " + aLbL + " aRbR: " + aRbR + "\nsumaInterna: " + sumaInterna + " sumaMedia: " + sumaMedia + "\n";
            }
            
            resultado = ((aLbL.multiply(exponenteDiez2)).add(sumaMedia.multiply(exponenteDiez))).add(aRbR);
            
            if(traza){
                trazaArchivo += "resultado final: " + resultado + "\n";
            }

        }
        
        return resultado;
    }
    
    /**
     * Este método calcula la suma de dos números e imprime la traza correspondiente.
     * 
     * @param numero1 el primer numero
     * @param numero2 el segundo numero
     * 
     * @return result el resultado de la suma
     */
    
    private String suma(String numero1, String numero2){
        
        String result = "";
        
        result = (new BigInteger(numero1).add(new BigInteger(numero2))).toString();
                
        if(traza){
            trazaArchivo += "Suma de " + numero1 + " + " + numero2 + ": " + result + "\n";
        }
        
        return result;
        
    }
    
    /**
     * Este método devuelve el resultado final al programa principal. Asimismo, será el que inicie los cálculos.
     * 
     * @return resultado el resultado del algoritmo
     */
    
    public String devolverResultado(){
        
        if(resultadoNegativo){
            resultado+="-";
        }
        
        resultado += calcularResultado(numeroUno, numeroDos).toString();
        
        return resultado;
    }
    
    /**
     * Este método devuelve el texto de la traza.
     * 
     * @return trazaArchivo la traza generada
     */
    
    public String devolverTraza(){
        return trazaArchivo;
    }
    
    /**
     * Este método devolverá el valor de tipo Long asociado a la cadena pasada como argumento
     * 
     * @param numero la cadena que representa el número
     * 
     * @return el valor de tipo Long de la cadena
     */
    
    private Long getNumber(String numero){
        return Long.parseLong(numero);
    }
}
