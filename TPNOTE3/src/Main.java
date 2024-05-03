public class Main {
    public static void main(String[] args) {
        String input = "Hello, Java";
        String reversed = nonRecurseInverserString(input);
        System.out.println("Chaîne originale: " + input);
        System.out.println("Chaîne inversée: " + reversed);
    }

    private static String nonRecurseInverserString(String input) {
        StringBuilder stringInverse = new StringBuilder();
            for (int i = 0; i < input.length() ; i++){
                stringInverse.append(input.charAt(input.length()-i-1));
            }
        return stringInverse.toString();
    }


    private static String InverserString(String input) {
        StringBuilder stringInverse = new StringBuilder();
        int n;
        if (n == input.length()-1){

        }
        return stringInverse.toString();
    }


    private static String NonInverserString(String input) {
        StringBuilder stringInverse = new StringBuilder();
        for (int i = 0; i < input.length() ; i++){
            stringInverse.append(input.charAt(input.length()-i-1));
        }
        return stringInverse.toString();
    }
}