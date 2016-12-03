import java.util.*;

/**
 * MultiplicaDyV aplica el algoritmo a los dos números pasados por argumento.
 * 
 * @author Melvin Vermeulen
 * @version 1.0
 */

public class MultiplicaDyV
{
    
    private String numeroA;
    private String aR;
    private String aL;
    private String aRaL;
    private String numeroB;
    private String bR;
    private String bL;
    private String bRbL;
    private String aRbR;
    private String aLbL;
    private String sumaMedia;
    private boolean traza;
    private String resultado;
    private boolean resultadoNegativo;
    private ArrayList<String> vectorNumeroA;
    private ArrayList<String> vectorNumeroB;
    private ArrayList<String> vectorResultadoParcial;
    private Long resultadoParcial;
    private int tamanhoMax = 9;
    private int tamanhoMayor;
    
    
    /**
     * Constructor de objetos de la clase MultiplicaDyV
     */
    public MultiplicaDyV(String numeroA, String numeroB, boolean traza, boolean resultadoNegativo)
    {
        int tamanhoA = this.numeroA.length();
        int tamanhoB = this.numeroB.length();
        if(tamanhoA >= tamanhoB){
            tamanhoMayor = tamanhoA;
        } else if(tamanhoB > tamanhoA) {
            tamanhoMayor = tamanhoB;
        }
        numeroA = corregirNumero(this.numeroA, tamanhoMayor);
        numeroB = corregirNumero(this.numeroB, tamanhoMayor);
        traza = this.traza;
        resultadoNegativo = this.resultadoNegativo;
        resultado = "";
    }

    /**
     * Este método añadirá tantos ceros como sea necesario para evitar errores en el cálculo al aplicar el algoritmo Divide Y Vencerás.
     * 
     * @param numero el número a corregir
     * @param longitudNumero la longitud del número a corregir
     * 
     * @return el número corregido con los ceros adicionales
     */
    
    private String corregirNumero(String numero, int longitudNumero){
        
        int numCeros = 0;
        String numeroCorregido = numero;
        
        if(longitudNumero > tamanhoMax){
            numCeros = longitudNumero%(tamanhoMax*2);
            while(numCeros > 0){
                numeroCorregido = "0" + numeroCorregido;
            }
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
        
        int mitad = (longitudNumero/2)-1;
        ArrayList<String> nuevoVector = new ArrayList<String>();
        
        nuevoVector.add(numero.substring(0,mitad));
        nuevoVector.add(numero.substring(mitad, longitudNumero-1));
        
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
    
    public String calcularResultado(String numeroA, String numeroB){
              
        if(numeroA.length() <= 9 && numeroB.length() <= 9){
            return "" + getNumber(numeroA)*getNumber(numeroB);
        } else{
            
            vectorNumeroA = dividirNumeroEnDos(numeroA, numeroA.length());
            aL = vectorNumeroA.get(0);
            aR = vectorNumeroA.get(1);

            vectorNumeroB = dividirNumeroEnDos(numeroB, numeroB.length());
            bL = vectorNumeroB.get(0);
            bR = vectorNumeroB.get(1);
            
            aLbL = calcularResultado(aL, bL);
            aRbR = calcularResultado(aR, bR);
            sumaMedia = ""+ (Long.valueOf(calcularResultado(suma(aL,aR),suma(bL,bR))) - (Long.valueOf(aLbL) + Long.valueOf(aRbR)));
            
            vectorResultadoParcial.add(aLbL);
            vectorResultadoParcial.add(sumaMedia);
            vectorResultadoParcial.add(aRbR);
            
            Long aLbLnum = Long.valueOf(aLbL);
            Long sumaMediaNum = Long.valueOf(sumaMedia);
            Long aRbRnum = Long.valueOf(aRbR);
                        
            resultadoParcial = ((aRbRnum/(10^9) + sumaMediaNum)/(10^9) + aLbLnum);
            String resultadoParcialUno = "" + resultadoParcial + "" + (sumaMediaNum%(10^9)) + "" + (aRbRnum%(10^9));
            
        }
        
        
        return "";
    }
    
    private String suma(String numero1, String numero2){
        
        String resulTemp = "";
        
        resulTemp += ((Long.valueOf(numero1))+(Long.valueOf(numero2)));
        
        return resulTemp;
        
    }
    
    public String devolverResultado(){
        
        if(resultadoNegativo){
            resultado+="-";
        }
        
        resultado += calcularResultado(numeroA, numeroB);
        
        return resultado;
    }
    
    /**
     * Este método devolverá el valor de tipo Long asociado a la cadena pasada como argumento
     * 
     * @param numero la cadena que representa el número
     * 
     * @return el valor de tipo Long de la cadena
     */
    
    private Long getNumber(String numero){
        return Long.valueOf(numero);
    }
}
