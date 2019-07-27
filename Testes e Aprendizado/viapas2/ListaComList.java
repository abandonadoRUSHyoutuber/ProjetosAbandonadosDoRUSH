package viapas2;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ListaComList {

    public static void main(String[] args) {

        Scanner teclado = new Scanner( System.in );
        
        List<String> lista = new LinkedList<>();
        
        String valor, valor_apos;
        int opcao , posicao;

        do
        {
            System.out.println("\n\nMenu\n----");
            System.out.println("1 - Inclusao");
            System.out.println("2 - Consulta");
            System.out.println("3 - Retirada");
            System.out.println("4 - Mostra");
            System.out.println("5 - Esvaziar");
            System.out.println("6 - Inclusao Apos");
            System.out.println("7 - Apagar ate um valor");
            System.out.println("0 - Fim");
            System.out.print("\nSua opcao : ");
            opcao = teclado.nextInt();
            
            switch( opcao )
            {
                case 1 :    System.out.print("\nDigite um valor :  ");
                            valor = teclado.next();
                            
                            if ( lista.add( valor ) )
                                System.out.println("\n**** INCLUSAO EFETUADA\n");
                            else
                                System.out.println("\n**** INCLUSAO NAO EFETUADA\n");
                            
                            break;
                    
                case 2 :    System.out.print("\nDigite um valor :  ");
                            valor = teclado.next();
                            
                            if ( lista.contains( valor ) )
                                System.out.println("\nValor existente");
                            else
                                System.out.println("\nValor inexistente");
                                
                            break;
                
                case 3 :    if( lista.isEmpty() )
                                System.out.println("\n==> UNDERFLOW");
                            else
                            {
                                System.out.print("\nDigite um valor :  ");
                                valor = teclado.next();
                                
                                if ( lista.remove( valor ) )
                                    System.out.println("\n**** RETIRADA EFETUADA\n");
                                else
                                    System.out.println("\n**** VALOR NAO ENCONTRADO\n");
                            
                            }
                
                            break;

                case 4 :    if ( lista.isEmpty() )    
                                System.out.println("\n**** LISTA VAZIA");
                            else
                            {
                                System.out.println("\nInformacoes na lista :\n");
                                System.out.print("\ninicio => ");

                                for( String s : lista )
                                    System.out.print(s + "  ");

                                System.out.println("<= fim\n");
                            }
                            break;
                                       
                case 5 :    System.out.println("*** ESVAZIAR");
                            while( lista.isEmpty() == false )
                                System.out.println("\n"+ lista.remove( 0 ) );
                            break;
                
                case 6 :    System.out.print("\nDigite o valor a ser incluido :  ");
                            valor = teclado.next();
                            
                            System.out.print("Digite o valor apos           :  ");
                            valor_apos = teclado.next();
                            
                            if ( lista.contains( valor_apos ) == true )
                            {
                                //aqui que vai ocorrer a inclusao
                                // acha a posicao do valor_apos
                                posicao = lista.indexOf( valor_apos );
                                // inclui na posicao posterior (+1) do valor_apos
                                lista.add( posicao+1 , valor );
                            }
                            else
                                System.out.println("\nValor apos inex1istente");
                    
            }
        } while ( opcao != 0 );
              
    }
    
}
