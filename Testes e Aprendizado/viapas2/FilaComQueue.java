
package viapas2;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;  
        
public class FilaComQueue {

    public static void main(String[] args) {
        Scanner teclado = new Scanner( System.in );
        
        // Fila
        Queue<String> fila = new LinkedList<>();

        int opcao;
        String valor;
        
        do
        { 
            System.out.println("\n\nMenu\n----");
            System.out.println("1 - Inclusao");
            System.out.println("2 - Consulta");
            System.out.println("3 - Retirada");
            System.out.println("4 - Mostra");
            System.out.println("5 - Esvazia");
            System.out.println("0 - Fim");
            System.out.print("\nSua opcao : ");
            opcao = teclado.nextInt();

            switch( opcao )
            {
                case 1 :    System.out.print("\nDigite um valor :  ");
                            valor = teclado.next();
                    
                            fila.add( valor );
                            
                            System.out.println("\n==> Inclusao efetuada");
                            
                            break;
                    
                case 2 :    if ( fila.isEmpty() )
                                System.out.println("\n==> Fila vazia"); 
                            else
                                System.out.println("Informacao na frente : " + fila.peek() );
                            break;
                    
                case 3 :    if ( fila.isEmpty() )
                                System.out.println("\n==> Underflow");
                            else
                            {   
                                valor = fila.peek();
                                fila.remove();
                                System.out.println("Informacao retirada : " + valor );
                            }    
                            break;
                    
                case 4 :    if ( fila.isEmpty() ) 
                                System.out.println("\n==> Fila Vazia");
                            else
                            { 
                                System.out.println("\nInformacoes na fila :\n");
                                System.out.print("\nfrente => ");
                                for( String s : fila )
                                    System.out.print(s + "  ");
                                System.out.println("<= re\n");
                            }
                            break;
                    
                case 5 :    if ( fila.isEmpty() ) 
                                System.out.println("\n==> Fila Vazia");
                            else
                                while( !fila.isEmpty() )
                                    System.out.println("Retirado " + fila.poll() );
//                            { 
//                                while( !fila.isEmpty() )
//                                {
//                                    valor = fila.peek();
//                                    fila.remove();
//                                    System.out.println("Informacao retirada : " + valor );   
//                                }
//                            }
                               
            }
        }while ( opcao != 0 );
    }
    
}
