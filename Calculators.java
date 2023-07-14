import java.util.Scanner;

public class Calculators {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два числа с арифмитическим действием(арабские или римские): ");

        String input = scanner.nextLine();
        System.out.println(calc(input));

        scanner.close();
    }

    public static String calc(String input) throws Exception {
        int num1;
        int num2;
        String oper;
        String result;
        boolean isRoman;

        String[] operands = input.split("[+\\-*/]");
        if (operands.length != 2) throw new Exception("Дожно быть два числа");

        oper = detectOperation(input);
        if (oper == null) throw new Exception("Неподдерживаемая математическая операция");

        // оба числа римские
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {
            // конвертируем оба числа в арабские для вычесления действия
            num1 = Roman.convertToArabian(operands[0]);
            num2 = Roman.convertToArabian(operands[1]);
            isRoman = true;
        }
        // если оба числа арабские
        else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[1]);
            isRoman = false;
        }
        // если одно число арабское, а другое римское
        else {
            throw new Exception("Числа должны быть а одном формате");
        }

        if (num1 > 10 || num2 > 10) {
            throw new Exception("Числа должны быть меньше 10");
        }

        int arabian = calc(num1, num2, oper);

        if (isRoman) {
            // если число меньше 0 либо равно 0, то генерируем ошибку
            if (arabian <= 0) {
                throw new Exception("Римские число должно быть боьше 0");
            }
            //конвертируем результат оперции из арабского в римское
            result = Roman.convertToRoman(arabian);
        } else {
            //конвертируем арабское число в тип String
            result = String.valueOf(arabian);
        }
        //возвращиаем результат
        return result;
    }

    public static String detectOperation(String input) {
        if (input.contains("+")) return "+";
        else if (input.contains("-")) return "-";
        else if (input.contains("*")) return "*";
        else if (input.contains("/")) return "/";
        else return null;
    }

    public static int calc(int a, int b, String oper) {
        if (oper.equals("+")) return a + b;
        else if(oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
        else return a / b;
    }

}

class Roman {
    static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L"};

    public static boolean isRoman(String val) {
        for (int i = 0; i < romanArray.length; i++) {
            if (val.equals(romanArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabian(String roman) {
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRoman (int arabian) {
        return romanArray[arabian];
    }
}