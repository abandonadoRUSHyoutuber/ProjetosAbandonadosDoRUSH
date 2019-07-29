package trabalho;

import java.util.HashSet;

public class Cinco {

    public static void main (String[] args) {
        Integer teste = 10;
        boolean cancel = false;
        Causa c = Causa.fall;

        long inicio;
        long fim;
        
        inicio = System.nanoTime();
        if (teste instanceof Object) {
        	cancel = true;
        }
        if (teste instanceof Object) {
        	cancel = true;
        }
        if (teste instanceof Object) {
        	cancel = true;
        }
        if (teste instanceof Object) {
        	cancel = true;
        }
        if (teste instanceof Object) {
        	cancel = true;
        }
        if (teste instanceof Object) {
        	cancel = true;
        }
        if (teste instanceof Object) {
        	cancel = true;
        }
        fim = System.nanoTime();
        System.out.println("Tempo instanceof: " + (fim-inicio) + " ns");
        
        inicio = System.nanoTime();
        if (c == Causa.lava) {
        	cancel = true;
        }
        if (c == Causa.lava) {
        	cancel = true;
        }
        if (c == Causa.lava) {
        	cancel = true;
        }
        if (c == Causa.lava) {
        	cancel = true;
        }
        if (c == Causa.lava) {
        	cancel = true;
        }
        if (c == Causa.lava) {
        	cancel = true;
        }
        if (c == Causa.lava) {
        	cancel = true;
        }
        fim = System.nanoTime();
        System.out.println("Tempo enum: " + (fim-inicio) + " ns");
    }
    
    enum Causa {
    	fall, player, lava, pvp;
    }
}
