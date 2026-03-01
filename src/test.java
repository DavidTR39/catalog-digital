public class test {
    public static void main(String[] args) {
        int test[] = new int[6];
        for (int i = 0; i < test.length; i++) {
            test[i] = (int)(Math.random() * 9);
        }
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i]);
        }
    }
}
