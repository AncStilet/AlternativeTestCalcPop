import java.util.Scanner;

public class alternativeTestCalc {
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            String input_data = scan.nextLine();
            if (input_data.charAt(0) != '"') throw new Exception();
            String[] saved = {"", "", ""};
            int[] saved_2 = new int[4];
            int j = 0;

            for (int i = 0; i < input_data.length(); i++) {
                char symbol = input_data.charAt(i);
                if (symbol == '"') {
                    saved_2[j] = i;
                    j++;
                }
            }
            if (j == 4 && input_data.charAt(input_data.length()-1) != '"') throw new Exception();
            if (j != 2 && j != 4) throw new Exception();
            int k = j;
            j = 0;
            for (int i = 0; i < input_data.length(); i++) {
                char symbol = input_data.charAt(i);
                if (i > saved_2[0] && i < saved_2[1] || i > saved_2[2] && i < saved_2[3]) {
                    saved[j] = saved[j] + symbol;
                } else if (symbol != ' ') {
                    if (symbol != '"') {
                        saved[j] = saved[j] + symbol;
                    }
                } else {
                    j++;
                }
            }
            if (saved[0].length() > 10 && saved[2].length() > 10 && saved[1].length() > 1) throw new Exception();

            mathematicalOperations math = new mathematicalOperations();
            char symbol_1 = saved[1].charAt(0);
            if (symbol_1 != '+' && symbol_1 != '-' && symbol_1 != '*' && symbol_1 != '/') throw new Exception();
            if (symbol_1 == '+') {
                if (k != 4)throw new Exception();
                System.out.println('"' + math.plus(saved[0], saved[2]) + '"');
            } else if (symbol_1 == '/') {
                if (k != 2) throw new Exception();
                System.out.println('"' + math.divisions(saved[0], saved[2]) + '"');
            } else if (symbol_1 == '*') {
                if (k != 2) throw new Exception();
                System.out.println('"' + math.multiplication(saved[0], saved[2]) + '"');
            } else if (symbol_1 == '-') {
                if (k != 4) throw new Exception();
                System.out.println('"' + math.subtractions(saved[0], saved[2]) + '"');
            }
        }catch (Exception e){
            System.out.println("Исключение!");
        }
    }
}
class mathematicalOperations{
    String a;
    static String plus(String a, String b){
        String sum = a + b;
        return sum;
    }
    static String divisions(String a, String b) throws Exception{
        String div = "";
        int number_b = Integer.parseInt(b);
        if (number_b < 1 || number_b > 10) throw new Exception();
        int result;
        result = a.length()/number_b;
        for (int i = 0; i < result; i++){
            div = div + a.charAt(i);
        }
        return div;
    }
    static String multiplication(String a, String b) throws Exception{
        String mult = "";
        int number_b = Integer.parseInt(b);
        if (number_b < 1 || number_b > 10) throw new Exception();
        for (int i = 0; i < number_b; i++){
            mult = mult + a;
        }
        if (mult.length() > 40) {
            String mult_2 = "";
            for (int i = 0; i < 40; i++) {
                char n = mult.charAt(i);
                mult_2 = mult_2 + n;
            }
            mult_2 = mult_2 + "...";
            mult = mult_2;
        }
        return mult;
    }
    static String subtractions(String a, String b){
        String sub = "";
        int number = a.indexOf(b);
        int number_2 = b.length()+number-1;
        if (number != -1) {
            for (int i = 0; i < a.length(); i++) {
                if (i < number || i > number_2) {
                    sub = sub + a.charAt(i);
                }
            }
        }else {
            sub = a;
        }
        return sub;
    }
}