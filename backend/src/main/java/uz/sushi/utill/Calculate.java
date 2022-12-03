package uz.sushi.utill;

public class Calculate {

    public static Float moneyToFloat(String str) {
        return Float.valueOf(str.substring(0, lastNumIndex(str)));
    }

    private static int lastNumIndex(String str) {
        int index = 0;
        while (index < str.length())
            if (Character.isDigit(str.charAt(index)))
                index++;
            else return index;
        return index;
    }


}
