/**
 * Class to represent an arithmetic table.
 * @author Alex Hosseini
 * @version 1.0
 */
public class ArithmeticTable {
    /**
     * int 3 for arg checking.
     */
    private static final int THREE = 3;
    /**
     * int 100 for bounds checking.
     */
    private static final int HUNDRED = 100;

    /**
     * Table can either be of type ADD(addition), or MULT (multiplication).
     */
    private enum TableType {
        ADD, MULT
    }
    /**
     * first int to start the two dimensional array.
     */
    private int start;

    /**
     * last int to end the two dimensional array.
     */
    private int end;
    /**
     * enum of the table type.
     */
    private TableType tableType;
    /**
     * Two dimensional array to be used.
     */
    private double[][] arr;

    /**
     * A method to create the table from command line args.
     *
     * @param begin     the start int in the table
     * @param finish    the last int in the table
     * @param tblType   the type of table; either * table or + table
     */
    public void createTable(int begin, int finish, TableType tblType) {
        int length = finish - begin + 1;
        arr = new double[length][length];
        int a = begin;
        int b = begin;

        if (tblType == TableType.ADD) {
            for (int i = 0; i < length; i++, a++) {
                for (int j = 0; j < length; j++, b++) {
                    arr[i][j] = a + b;
                }
                b = begin;
            }
        } else {
            a = begin;
            b = begin;
            for (int i = 0; i < length; i++, a++) {
                for (int j = 0; j < length; j++, b++) {
                    arr[i][j] = a * b;
                }
                b = begin;
            }
        }
    }

    /**
     * Prints the table in the class in proper format.
     */
    public void printTable() {
        int length = end - start;
        int a = start;
        for (int i = 0; i <= length; i++) {
            System.out.println();
            if (a == start) {
                if (tableType == TableType.ADD) {
                    System.out.print("+      ");
                } else {
                    System.out.print("*      ");
                }
                for (int q = start; q <= end; q++) {
                    System.out.printf("%5d", q);
                }
                System.out.println();
                System.out.print("        ");
                for (int q = start; q <= end; q++) {
                    System.out.print("-----");
                }
                System.out.println();
                a = 0;
            }


            for (int j = 0; j <= length; j++) {
                if (j == 0) {
                    System.out.printf("%5d", i + start);
                    System.out.print(" |");
                }
                System.out.printf("%5.0f", arr[i][j]);
            }
        }

    }

    /**
     * Checks to verify that input args is == 3 and within the bounds 1 to 100.
     * @param args command line args
     * @return true if the requirements are met; false else
     */
    public boolean argumentCheck(String[] args) {
        if (args.length != THREE) {
            printError();
        }

        if (args[0].charAt(0) == '+') {
            tableType = TableType.ADD;
        } else {
            tableType = TableType.MULT;
        }
        int sta;
        int sto;

        try {
            sta = Integer.parseInt(args[1]);
            sto = Integer.parseInt(args[2]);
        } catch (NumberFormatException ex) {
            return printError();
        }

        if ((sta < 1 || sta > HUNDRED) || ((sto < 1 || sto > HUNDRED))) {
            return printError();
        }

        if (sta > sto) {
            return printError();
        }

        start = sta;
        end = sto;
        return true;
    }

    /**
     * Method to print the error message and return false if error detected.
     * @return false if error detected; true else
     */
    private boolean printError() {
        System.err.println("Usage: Main <type> <start> <stop>");
        System.err.println("\tWhere <type> is one of +, -, \"*\", /");
        System.err.println("\tand <start> is between 1 and 100");
        System.err.println("\tand <stop> is between 1 and 100");
        System.err.println("\tand start < stop");
        return false;
    }

    /**
     * Drive the program.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        ArithmeticTable table = new ArithmeticTable();
        if (table.argumentCheck(args)) {
            table.createTable(table.start, table.end, table.tableType);
            table.printTable();
        }
    }
}
