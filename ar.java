public class ar {
    public static void main(String[] args){
        String str = "Hello Java 2026!";
        char[] chars = str.toCharArray();

        for (char c: chars){
            if(Character.isUpperCase(c)){
                System.out.print(Character.toLowerCase(c));
            }else{
                System.out.print(c);
            }
        }
    }
}
