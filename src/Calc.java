import java.util.Scanner;

 class Calc {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in); // здесь создал scanner
        System.out.println("Введите два числа (арабских или римских) от 1 до 10(включительно):");
        String expression = scanner.nextLine();  // считываем введенное нами выражение, которое далее будет переданно в
                                                // метод parse, где оно и будет вычисляться
        System.out.println(parse(expression));
    }
    public static String parse(String expression) throws Exception {
        int num1; int num2;
        String oper;
        String result; boolean isRoman;
        String[] operands = expression. split ( "[+\\-*/]"); // проверяем на количество операндов
        if (operands.length < 2 ) throw new Exception("т.к. строка не является математической операцией");
        oper = detectOperation(expression);  // определяем какую операцию ввел пользователь
        if (operands.length > 2 || oper == null) throw new Exception("т.к. формат математической операции не удовлетворяет заданию - " +
                "два операнда и один оператор (+, -, /, *)");
        // проверяем введенные числа, на то что они римские
        if (Roman.isRoman(operands [0]) && Roman.isRoman (operands [1])) {
            num1 = Roman.convertToArabian(operands[0]); //конвертируем оба числа в арабские для вычесления действия
            num2 = Roman.convertToArabian(operands[1]);
            isRoman = true;
        }
        // проверяем введенные числа, на то что они арабские
        else if (!Roman.isRoman (operands [0]) && !Roman.isRoman (operands [1])) {
            num1 = Integer.parseInt(operands[0]); // с помощью функции Integer.parseInt мы получаем число из строки
            num2 = Integer.parseInt(operands[1]);
            isRoman = false;
        }
        else {
            // иначе у нас разные системы счисления
            throw new Exception("т.к. используются одновременно разные системы счисления");
        }
        // проверяем числа, чтоб они были не больше 10
        if (num1 > 10 ||  num2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int arabian = calc(num1, num2, oper); // с помощью метода calc мы вычисляем наше выражение
        if (isRoman) {
            //если римское число получилось меньше либо равно нулю, генерируем ошибку
            if (arabian <= 0){
                throw new Exception("т.к. в римской системе нет отрицательных чисел и римское число не может = 0");
            }
            result = Roman.convertToRoman(arabian);  // конвертируем результат операции из арабского в римское
        } else {
            result = String.valueOf(arabian); // конвертируем арабское число в тип String
        }
        return result; // возвращаем результат
    }
    static String detectOperation(String expression) { // в данном методе мы проверяем какую операцию ввел пользователь
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }
    static int calc(int a, int b, String oper) { // в данном методе мы вычисляем наше выражение
        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
        else return a / b;
    }
}
class Roman {
    static String[] romanArray = new String[]{"О", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV",
            "XXVI","XXVII", "XXVIII", "XIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII",
            "XXXVIII", "XXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV",
            "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI",
            "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII",
            "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

    // в данном методе проверяем введенные числа(римские они или нет)
    public static boolean isRoman(String val) {
        for (int i = 0; i < romanArray.length; i++) {  // с помощю цикла мы просто проверяем на соответствие введенные
                                                      // нами числа с массивом из римских чисел romanArray
            if (val.equals (romanArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabian(String roman) { // в этом методе мы конвертируем римские числа в арабские
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals (romanArray[i])) {
                return i;
            }
        }
        return -1;
    }
    public static String convertToRoman(int arabian) { // в этом методе мы конвертируем полученное арабское число в рим.
        return romanArray[arabian];
    }
}


