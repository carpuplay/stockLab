package utils;

public class ArrayManager {
    public int getIndexByString(String[] stringArray, String name) {
        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isStringInArray(String[] stringArray, String name) {
        int index = getIndexByString(stringArray, name);
        return index >= 0;
    }

    public static void main(String[] args){
        ArrayManager test = new ArrayManager();
        String[] names = {"Ludovic", "Ah", "Oh"};
        System.out.println("Is Ludovic in the array? " + test.isStringInArray(names, "Ludovic"));
        System.out.println("Id of Ah :" + test.getIndexByString(names, "Ah"));
    }
}
