import java.io.*;
import java.util.*;


/**
 * Programa 'multiplica' para multiplicar dos números grandes.
 * 
 * La primera parte del programa se encargará de las operaciones de entrada y salida.
 * El cálculo se hará en el archivo MultiplicaDyV.java
 * 
 * @author Melvin Vermeulen
 * @version 10.01.2017
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
        String trazaArchivo;
        String cabeceraArchivo = "";
        String textoSalida = "";
        boolean resultadoNegativo = false;
        boolean numeroANegativo = false;
        boolean numeroBNegativo = false;
        
        BufferedReader lector = null;
        
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
        
        fichero_entrada = args[argIndex].trim();

            
        if(argIndex+1 == args.length-1){
            fichero_salida = args[argIndex+1];
            salidaFichero = true;
        }
            
            
        try{
            File fichero = new File(fichero_entrada);
            lector = new BufferedReader(new FileReader(fichero));
            String linea = null;
            int leidos = 0;
                
            while((linea = lector.readLine()) != null && leidos < 2){
                
                if(linea.length() > 0){
                    if(leidos == 0){
                        numeroA = linea;
                        if(!esNumero(numeroA)){
                            System.out.println("Error en el archivo. La primera línea no es un número.");
                            System.exit(1);
                        }
                        
                        System.out.println("\n-----Multiplicación de números grandes.-----\n");
                        System.out.println("(Fichero de entrada " + fichero_entrada + " encontrado.)\n");
                        
                        String primerDigitoA = numeroA.substring(0,1);
                            
                        if(primerDigitoA.matches("[-+]")){
                            if(primerDigitoA.matches("-")){
                                numeroANegativo = true;
                            }
                            System.out.println("Numero A: " + numeroA);
                            numeroA = numeroA.substring(1);
                        } else{
                             System.out.println("Numero A: " + numeroA);
                        }
                        
                    }
                    
                    if(leidos == 1){
                        numeroB = linea;
                        if(!esNumero(numeroB)){
                            System.out.println("Error en el archivo. La segunda línea no es un número.");
                            System.exit(1);
                        }
                            
                        String primerDigitoB = numeroB.substring(0,1);
                            
                        if(primerDigitoB.matches("[-+]")){
                            if(primerDigitoB.matches("-")){
                                numeroBNegativo = true;
                            }
                            System.out.println("Numero B: " + numeroB);
                            numeroB = numeroB.substring(1);
                        } else{
                            System.out.println("Numero B: " + numeroB);
                        }
                        
                    }
                    
                    System.out.println();
                    
                    leidos++;
                }
            }
                
            if(numeroANegativo^numeroBNegativo){
                resultadoNegativo = true;
            }
            
            MultiplicaDyV multiplica = new MultiplicaDyV(numeroA, numeroB, traza, salidaFichero, resultadoNegativo);
            
            resultado = multiplica.devolverResultado();
            trazaArchivo = multiplica.devolverTraza();
                
            if(salidaFichero){
                
                try{
                    
                    File ficheroSalida = new File(fichero_salida);
                                        
                    cabeceraArchivo = "----Multiplicación de números grandes.-----\n";
                    cabeceraArchivo += "Numero A: ";
                    if(numeroANegativo){
                        cabeceraArchivo += "-";
                    }
                    cabeceraArchivo += "" + numeroA + "\n";
                    cabeceraArchivo += "Numero B: ";
                    if(numeroBNegativo){
                        cabeceraArchivo += "-";
                    }
                    cabeceraArchivo += "" + numeroB + "\n";
                
                    if(traza){

                        textoSalida = cabeceraArchivo + trazaArchivo;
                        System.out.println("Traza y resultado en fichero de salida: " + fichero_salida + ".");

                    } else {
                        System.out.println("Resultado en fichero de salida: " + fichero_salida + ".");
                    }
                    
                    textoSalida += resultado;
                    escribirArchivo(textoSalida, ficheroSalida);
                    
                } catch(FileNotFoundException e){
                    e.printStackTrace();
                }
                
            } else {
                if(traza){
                    System.out.println(trazaArchivo);
                }
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
    
    /**
     * Definimos un método para poder imprimir la traza y el resultado línea por línea en el fichero de salida.
     * 
     * @param texto el texto a escribir en el archivo
     * @param archivo el archivo al que se va a escribir el texto
     * 
     * @return - nada, ya que se escribe en el archivo
     *
     */
    
    private static void escribirArchivo(String texto, File archivo) throws IOException {
        try (
            BufferedReader lector2 = new BufferedReader(new StringReader(texto));
            PrintWriter escritor = new PrintWriter(new FileWriter(archivo));
            ) {
                lector2.lines().forEach(line -> escritor.println(line));
            }
    }
    
    /**
     * Este método se encargará de comprobar si el string pasado es un número.
     * 
     * @param numero el numero a comprobar
     * 
     * @return - true o false dependiendo de la comprobación
     */
    
    public static boolean esNumero(String numero) { 
        return numero.matches("[+-]?\\d*"); 
    }
}
