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
    private String numeroB;
    private boolean traza;
    private String resultado;
    private boolean resultadoNegativo;
    private ArrayList<Long> vectorNumeroA;
    private ArrayList<Long> vectorNumeroB;
    private ArrayList<Long> vectorResultado;
    
    
    /**
     * Constructor de objetos de la clase MultiplicaDyV
     */
    public MultiplicaDyV(String numeroA, String numeroB, boolean traza, boolean resultadoNegativo)
    {
        numeroA = this.numeroA;
        numeroB = this.numeroB;
        traza = this.traza;
        resultadoNegativo = this.resultadoNegativo;
    }

    private ArrayList<Long> dividirNumero(String numero, int longitudNumero){
        
        int indiceNumero = longitudNumero - 1;
        int indiceVector = 0;
        ArrayList<Long> nuevoVector = new ArrayList<Long>();
        
        if(longitudNumero <= 9){
            nuevoVector.add(Long.valueOf(numero));
            return nuevoVector;
        } else {
            while(indiceNumero > 0){
            
                nuevoVector.add(Long.valueOf(numero.substring(indiceNumero, indiceNumero-9)));
                indiceVector++;
                indiceNumero-=9;
            
            }
            return nuevoVector;
        }
    }
    
    
    public String devolverResultado(){
        
        vectorNumeroA = dividirNumero(numeroA, numeroA.length());
        vectorNumeroB = dividirNumero(numeroB, numeroB.length());
        
        return "";
    }
}

