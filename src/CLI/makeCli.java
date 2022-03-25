package CLI;

import java.util.Scanner;

public class makeCli implements Console {

    Scanner input = new Scanner(System.in);

    @Override
    public String readS() {
        System.out.print("$ ");
        return input.nextLine();
    }

    @Override
    public int readI() {
        System.out.print("$ ");
        return input.nextInt();
    }

    @Override
    public void write(String ww) {
        System.out.println("$ "+ ww);
    }
}
