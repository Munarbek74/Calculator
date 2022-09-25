package com.company;

public class Main {
    private static final char exitCharacter = '!';

    public static void main(String[] args) {
        DataReader reader = new DataReader(exitCharacter);
        while (true) {
            try {
                reader.read();
            } catch (RuntimeException e) {
                System.err.println(e.getMessage());
                continue;
            }
            if (reader.isExitFlag()) {
                System.out.println("Бутуруучу знак берилди: " + exitCharacter);
                System.out.println("Программа аяктады.");
                break;
            }
            double result = Calculator.calculate(reader.getVar1(), reader.getVar2(), reader.getOper());
            if (reader.getArabSimvol() !=1 )
                System.out.printf("%s %s %s = %s\n",reader.getVar1(), reader.getOper(), reader.getVar2(),result);
            else
                System.out.printf("%s %s %s = %s\n",convertToRoman(reader.getVar1()), reader.getOper(), convertToRoman(reader.getVar2()),convertToRoman((int) result));
        }
    }

    private static String convertToRoman(int mInt) {
        String[] rnChars = { "M",  "CM", "D", "C",  "XC", "L",  "X", "IX", "V", "I" };
        int[] rnVals = {  1000, 900, 500, 100, 90, 50, 10, 9, 5, 1 };
        String retVal = "";

        for (int i = 0; i < rnVals.length; i++) {
            int numberInPlace = mInt / rnVals[i];
            if (numberInPlace == 0) continue;
            retVal += numberInPlace == 4 && i > 0? rnChars[i] + rnChars[i - 1]:
                    new String(new char[numberInPlace]).replace("\0",rnChars[i]);
            mInt = mInt % rnVals[i];
        }
        return retVal;
    }

}

