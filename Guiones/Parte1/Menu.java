


import monopolyetse.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Manuel Lama
 */
public class Menu {
    // Posiblemente esta clase tenga algunos atributos. Por ejemplo, en el proyecto
    // uno de los atributo de esta clase sería el tablero.
    private ArrayList<Float> numeros= new ArrayList<>();
    
    /**
     * 
     */
    public Menu() {
        // Inicializar valores
        Random gen= new Random(System.currentTimeMillis());
        for(int i=0;i<10;i++) numeros.add((float) gen.nextInt(100) + 1);
        
        boolean salir= false;
        while(!salir) { 
            System.out.print("$> ");
            Scanner scanner= new Scanner(System.in);
            String orden= scanner.nextLine();
            String[] partes=orden.split(" ");
            String comando= partes[0];
            
            // Acciones en función del comando introducido
            switch(comando){
                // minimo mayor <numero>
                // minimo
                case "minimo":
                    if(partes.length==1) System.out.println("El mínimo es " + minimo());
                    else if(partes.length==3) {
                            if(partes[1].equalsIgnoreCase("mayor")) {
                                float f= minimoMayorQue(new Float(partes[2]));
                                System.out.println("El número menor que " + partes[2] + " es " + minimoMayorQue(new Float(partes[2])));
                            }
                    } else System.out.println("Comando incorrecto");
                    break;
                case "salir":
                    if(partes.length!=1){
                        System.out.println("\nComando incorrecto.");
                    } else {
                        System.out.println("\nGracias por jugar.");
                    }
                    return;
                default:
                    System.out.println("\nComando incorrecto.");
                    break;
            }
        }
    }
    
    /**
     * 
     * @param num
     * @return 
     */
    private float minimoMayorQue(float num) {
        float minimo= numeros.get(0);
        for(int i=0;i<numeros.size();i++) {
            if(numeros.get(i)>num && numeros.get(i)<minimo) minimo= numeros.get(i);
        }
        return minimo;
    }
    
    /**
     * 
     * @param num
     * @return 
     */
    private float minimo() {
        float minimo= numeros.get(0);
        for(int i=0;i<numeros.size();i++) {
            if(numeros.get(i)<minimo) minimo= numeros.get(i);
        }
        return minimo;
    }
}