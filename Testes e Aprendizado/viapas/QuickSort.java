package viapas;

import java.util.Random;
import java.util.Scanner;

public class QuickSort {
    
    // funcao recursiva que particiona e ordena o vetor
    private static void quickDecrescente( int[ ] v , int inicio , int fim )
    {
        int aux, down, up, pivo, i;
        // determinar qual valor sera o pivo do particionamento do vetor
        pivo = v[ inicio ];
        down = inicio; 
        up   = fim;
        while( down < up )
        {
            while( v[ down ] >= pivo && down < fim )
                down++;
            while( v[ up ] < pivo    && up > inicio )
                up--;
            if( down < up )
            {
                aux       = v[ down ];
                v[ down ] = v[ up ];
                v[ up ]   = aux;
            }
        }
        v[ inicio ] = v[ up ];
        v[ up ] = pivo;
        
        System.out.println("\n");
        for( i = 0 ; i < v.length ; i++ )
            System.out.print(v[ i ] + "  ");

        if( inicio < up && inicio != up-1 )
            quickDecrescente( v , inicio , up-1 );
        
        if( fim > down && up+1 != fim )
            quickDecrescente( v , up+1 , fim );
    }
    
    
    // funcao recursiva que particiona e ordena o vetor
    private static void quickCrescente( int[ ] v , int inicio , int fim )
    {
        int aux, down, up, pivo, i;
        // determinar qual valor sera o pivo do particionamento do vetor
        pivo = v[ inicio ];
        down = inicio; 
        up   = fim;
        while( down < up )
        {
            while( v[ down ] <= pivo && down < fim )
                down++;
            while( v[ up ] > pivo    && up > inicio )
                up--;
            if( down < up )
            {
                aux       = v[ down ];
                v[ down ] = v[ up ];
                v[ up ]   = aux;
            }
        }
        v[ inicio ] = v[ up ];
        v[ up ] = pivo;
        
        System.out.println("\n");
        for( i = 0 ; i < v.length ; i++ )
            System.out.print(v[ i ] + "  ");

        if( inicio < up && inicio != up-1 )
            quickCrescente( v , inicio , up-1 );
        
        if( fim > down && up+1 != fim )
            quickCrescente( v , up+1 , fim );
    }

    public static void main(String[] args) {

        System.err.println("De que maneira você deseja ordenar o vetor?\nC = Crescente \nD = Descrescente");
        Scanner leitor = new Scanner(System.in);
        char opcao = leitor.next().toUpperCase().charAt(0);
        int v[] = new int[ 10 ];
        int valor, cont, i;  
        
        cont = 0;
        
        while( cont < 10 )
        {
            valor = new Random().nextInt(99);
            for( i = 0 ; i < cont ; i++ )
                if( valor == v[i] )
                    break;
            if( i == cont )
            {
                v[ cont ] = valor;
                cont++;
                //System.out.println("\n" + valor + " incluido com sucesso");
            }
            else
                System.out.println("\nValor duplicado. Nao incluido");
        }
        System.out.println("\n\n");
        for( i = 0 ; i < v.length ; i++ )
            System.out.print(v[ i ] + "  ");
        
        // primeira chamada a funcao recursiva quick deve passar o primeiro indice e o ultimo indice do vetor. ou seja, todo o vetor. ainda nao foi particionado
        if (opcao == 'C')
        	quickCrescente( v , 0 , v.length-1 );
        else 
        	quickDecrescente( v , 0 , v.length-1 );
        
        System.out.println("\n\nVETOR ORDENADO:");
        for( i = 0 ; i < v.length ; i++ )
            System.out.print(v[ i ] + "  ");
        System.out.println("\n\n");
    }
    
}
