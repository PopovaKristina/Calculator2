package com.company;

public class WorkWithNumbers {
    //массив с математическими операциями
    static final String[] operations = new String[]{"+", "-", "/", "*"};
    //массив с арабскими цифрами
    static final String[] arabicNumbers = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    //массив с римскими цифрами
    static final String[] romanNumbers = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

    static void runParseAndCalc(String example) throws MyException {

        //получаем знак математической операции
        String operation = getOperation(example);

        if (operation.length() > 0) {

            //получаем первый и второй операнды из примера
            String first = getFirst(example, operation);
            String second = getSecond(example, operation);

            //проверяем длину операндов
            if ((first.length() > 0) && (second.length() > 0)) {

                //если длина больше 0, то  получаем типы операндов
                String typeFirst = getTypeNum(first);
                String typeSecond = getTypeNum(second);

                //Если типы не равны неизвестном и равны друг другу
                if (!typeFirst.equals("unknown") && (typeFirst.equals(typeSecond))) {

                    //вычислить значение
                    String arabicResult = CalcNumbers.calc(first, second, operation, typeFirst);

                    //проверяем римские цифры на ноль и отрицательные
                    if (Integer.valueOf(arabicResult) <= 0 && typeFirst.equals("roman")) {
                        //если да то выкинуть исключение
                        throw new MyException("in Roman numbers, the value cannot be zero or negative!");

                    } else
                        //если операция производилась над римским цифрами
                        if (typeFirst.equals("roman")) {
                            //конвертировать результат в римские цифры
                        String romanResult = CalcNumbers.convertArabicToRoman(arabicResult);
                        System.out.println("Output:");
                        System.out.println(romanResult);

                    } else {
                            //иначе операция производилась над арабскими цифрами
                        System.out.println("Output:");
                        //вывести результат в виде арабских цифр
                        System.out.println(arabicResult);

                    }

                } else
                    //проверяем если оба числа непонятного формата то
                    if (typeFirst.equals("unknown") || typeSecond.equals("unknown")) {
                            //выкидываем исключение
                             throw new MyException("Incorrect format example");

                    } else {
                            //иначе выкидываем исключение когда два разных типа цифр
                            throw new MyException("Variables different types");
                     }

            } else
                //если один из операндов пуст выкидываем исключение
                throw new MyException("Incorrect format example");

        } else {
            //если переменная с математической операцией пуста - выкидываем исключение
            throw new MyException("Incorrect format example");
        }

    }

    //получение первого операнда
    static String getFirst(String example, String operation) {
        return example.substring(0, example.indexOf(operation));
    }

    //получение второго операнда
    static String getSecond(String example, String operation) {

        return example.substring(example.indexOf(operation) + 1);
    }

    //получение знака математической операции
    static String getOperation(String example) {

        //в цикле сравниваем полученную операцию с данными из массива операций
        for (String operation : operations) {
            Integer indexOperation = example.indexOf(operation);
            //если операция не стоит в начале или в конце примера
            if ((indexOperation >= 1) && (indexOperation < example.length())) {
                //возвращаем её
                return operation;
            }
        }
        return "";
    }

    //проверка является ли число арабским
    static boolean isArabic(String num) {
        for (String arabic : arabicNumbers) {
            if (arabic.equals(num)) {
                return true;
            }
        }
        return false;
    }

    //проверка является ли число римским
    static boolean isRoman(String num) {
        for (String roman : romanNumbers) {
            if (roman.equals(num)) {
                return true;
            }
        }
        return false;
    }


    //получение типа числа (арабское или римское)
    static String getTypeNum(String num) {
        if (isArabic(num)) {
            return "arabic";
        } else if (isRoman(num)) {
            return "roman";
        } else
            return "unknown";
    }
}
