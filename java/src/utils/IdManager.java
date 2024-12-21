package utils;

import java.util.Random;

public class IdManager {
    private static final Random rand = new Random();

    private String generateRandomString(String characters, int length){

        StringBuilder result = new StringBuilder(length);

        int nbCharacters = characters.length();

        for (int i = 0; i < length; i++){
            result.append(characters.charAt(rand.nextInt(nbCharacters)));
        }
        return result.toString();
    }

    private int getLengthByType(int type) {
        return switch (type) {
            case 1 -> 3;
            case 2 -> 5;
            default -> throw new IllegalArgumentException("Invalid type");
        };
    }

    private String getCharactersAvailable(int type) {
        return switch (type) {
            case 1 -> "ABCDE";
            case 2 -> "ABCDE1234";
            default -> throw new IllegalArgumentException("Invalid type");
        };
    }

    public String generateId(int type) {
        int length = getLengthByType(type);
        String characters = getCharactersAvailable(type);

        return generateRandomString(characters, length);
    }

    public static void main(String[] args){
        IdManager test = new IdManager();
        System.out.println("Id:" + test.generateId(2));
    }

}
