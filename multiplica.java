import java.io.*;
import java.util.*;


/**
 * Programa 'multiplica' para multiplicar dos números grandes.
 * 
 * @author Melvin Vermeulen
 * @version 1.0
 */

public class multiplica
{    
    public static void main(String args[]){
        
        boolean traza = false;
        boolean salidaFichero = false;
        
        int argIndex = 0;
        
        String fichero_entrada = "";
        String fichero_salida = "";
        
        String numeroA = "";
        String numeroB = "";
        String resultado = "";
        boolean resultadoNegativo = false;
        boolean numeroANegativo = false;
        boolean numeroBNegativo = false;
        
        if(args.length == 0){
            System.out.println("No se ha pasado ningún parámetro.");
            System.exit(1);
        } else if(args.length == 1 && args[0].equals("-h")){

            System.out.println("\nSINTAXIS: \n" +
                                "multiplica [-t][-h] [fichero_entrada] [fichero_salida] \n" +
                                "-t                     Traza \n" +
                                "-h                     Muestra esta ayuda \n" +
                                "fichero_salida         Nombre del fichero de salida \n");
            System.exit(1);
            
        } else if(args[0].equals("-t") && args.length == 1){
            
                System.out.println("Debes indicar un fichero de entrada.");
                System.exit(1);
            
        } else if(args[0].equals("-t")){
                traza = true;
                argIndex = 1;
        } 
        fichero_entrada = args[argIndex];
            
        if(args[argIndex+1] != null){
            fichero_salida = args[argIndex+1];
            salidaFichero = true;
        }
            
        File fichero = new File(fichero_entrada);
        BufferedReader lector = null;
            
        try{
                
            lector= new BufferedReader(new FileReader(fichero));
            String linea = null;
            int leidos = 0;
                
            while((linea = lector.readLine()) != null && leidos < 2){
                if(linea.trim().length() > 0){
                    if(leidos == 0){
                        numeroA = linea;
                        if(!esNumero(numeroA)){
                            System.out.println("Error en el archivo. La primera línea no es un número.");
                            System.exit(1);
                        }
                            
                        String primerDigitoA = numeroA.substring(0,1);
                            
                        if(primerDigitoA.matches("[-+]")){
                            if(primerDigitoA == "-"){
                                numeroANegativo = true;
                            }
                            numeroA = numeroA.substring(1);
                        }
                     
                        leidos++;
                    }
                    if(leidos == 1){
                        numeroB = linea;
                        if(!esNumero(numeroA)){
                            System.out.println("Error en el archivo. La segunda línea no es un número.");
                            System.exit(1);
                        }
                            
                        String primerDigitoB = numeroB.substring(0,1);
                            
                        if(primerDigitoB.matches("[-+]")){
                            if(primerDigitoB == "-"){
                                numeroBNegativo = true;
                            }
                            numeroB = numeroB.substring(1);
                        }
                            
                        leidos++;
                    }
                }
            }
                
            if(numeroANegativo^numeroBNegativo){
                resultadoNegativo = true;
            }
                
            resultado = (new MultiplicaDyV(numeroA, numeroB, traza, resultadoNegativo)).devolverResultado();
                
            if(salidaFichero){
                /**Imprime el resultado en el archivo dado.*/
            } else {
                System.out.println(resultado);
            }
                
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                if(lector != null){
                    lector.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
             
    }
    
    public static boolean esNumero(String numero) { 
        return numero.matches("[+-]?\\d*"); 
    }
}
