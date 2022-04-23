public class Test {
    public static void main(String[] args) {
        // System.out.println(doStuff(2, -1)[0]);
        // System.out.println(doStuff7(2, 3));
        // System.out.println(doStuff8(3, 1));
        // System.out.println(doStuff9(4, 1));
        System.out.println(doStuff10(3, 1));
    }

    public static int[] doStuff1(int a, int b) {
        int[] c = new int[a - b];

        for (int i = c.length - 1; i > 0; i--) {
            c[i] = a - b;
            a++;
        }
        System.out.println("a: " + a);
        return c;
    }

    public static int doStuff7(int a, int b) {
        int c = 0;
        int count = 1;
        for (int i = a; i < 4; i++) {
            for (int j = b; j < 4; j++) {
                c = c - i + j;
                count++;
            }
        }
        System.out.println(count);
        return c;
    }

    public static int doStuff8(int a, int b) {
        int c = a - b;
        int count = 0;
        if(++ b == c) {
            a++;
            b++;
            count++;
        }
        if(-- a == b) {
            b--;
            c--;
            count++;
        }
        System.out.println(count);
        return c;
    }

    public static int doStuff9(int a, int b) {
        int count = 0;
        for (int i = a; i > b; i--) {
            count++;
            if(a > b) {
                a--;
            }
        }
        System.out.println(count);
        return a;
    }

    public static int doStuff10(int a, int b) {
        int count = 1;
        while (a + b > 2) {
            if (a > b) {
                a--;
                count++;
            }
            else {
                b--;
            }
        }
        System.out.println(count);
        return b;
    }
}
