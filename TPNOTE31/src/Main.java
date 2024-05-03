public class Main {
    public static void main(String[] args) {
        evenNumbers("1234567890");
        evenNumbers("371830");
    }

    private static void evenNumbers(String number) {
        StringBuilder numbers = new StringBuilder();
        for (int i = 0; i <= number.length()-1 ; i++){
            if (number.charAt(i)%2 == 0 && number.length()-1 >0){
                numbers.append(number.charAt(i));
                numbers.append(" ");
            }
            if (number.charAt(i)%2 == 0 && number.length()-1 == 0){
                numbers.append(number.charAt(i));
            }
        }
    System.out.println(numbers);
    }
}