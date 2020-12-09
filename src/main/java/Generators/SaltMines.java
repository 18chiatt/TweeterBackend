package Generators;

import java.util.Random;

public class SaltMines {
    private static Random random = new Random(System.currentTimeMillis());
    public static String getSalt(int length){
        StringBuilder builder = new StringBuilder();

        for(int i=0; i< length; i++){
            char toAdd = (char)random.nextInt(91);
            toAdd +=35;
            builder.append(toAdd);
        }
        return builder.toString();

    }
}
