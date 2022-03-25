package Request;

import CLI.makeCli;

public class User extends makeCli {
    StringBuilder reqTemplate = null;

    public User() {
        reqTemplate = new StringBuilder("insert into student values(default, ");
    }

    public String requestHandler() {
        String inp = readS();

        switch (inp) {
            case "help", "-h" -> {
                System.out.println("Enter 'inp' in the console");
                System.out.println("Enter 'exit' to exit out");
                System.out.println("Enter 'get' to get current table view ");
                return ".h";
            }
            case "inp" -> System.out.println("Enter your Name and age");

            case "exit" -> {return ".ex";}

            case "get" -> {return ".get";}

            default -> {
                System.out.println("Enter INV entry... ");
                return ".inv";
            }
        }
        String inp1 = readS();
        int inp2 = readI();
        reqTemplate.append("'").append(inp1).append("', ").append(Integer.toString(inp2)).append(");");


        return reqTemplate.toString();

    }

}
