/**
 * @author kheseyroon
 * @since 09.06.2021
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class basicCpuEmulator {
    static int AC, flag = 0, PC = 0;
    static int[] ram = new int[256];

    public static void main(String[] args) throws Exception {
        File file = new File("program.txt");
        Scanner input = new Scanner(file);
        ArrayList<String> operations = new ArrayList<>();
        ArrayList<String> numbers = new ArrayList<>();

        while (input.hasNext()) {
            operations.add(input.next());
            if (operations.get(operations.size() - 1).equalsIgnoreCase("START") ||
                    operations.get(operations.size() - 1).equalsIgnoreCase("DISP") ||
                    operations.get(operations.size() - 1).equalsIgnoreCase("HALT"))
                numbers.add("");
            else
                numbers.add(input.next());
        }

        for (int i = PC; ; i = PC) {
            switch (operations.get(i).toUpperCase()) {
                case "START" -> start();
                case "LOAD" -> load(Integer.parseInt(numbers.get(i)));
                case "LOADM" -> loadM(Integer.parseInt(numbers.get(i)));
                case "STORE" -> store(Integer.parseInt(numbers.get(i)));
                case "CMPM" -> cmpm(Integer.parseInt(numbers.get(i)));
                case "CJMP" -> cjmp(Integer.parseInt(numbers.get(i)));
                case "JMP" -> jmp(Integer.parseInt(numbers.get(i)));
                case "ADD" -> add(Integer.parseInt(numbers.get(i)));
                case "ADDM" -> addM(Integer.parseInt(numbers.get(i)));
                case "SUBM" -> subM(Integer.parseInt(numbers.get(i)));
                case "SUB" -> sub(Integer.parseInt(numbers.get(i)));
                case "MUL" -> mul(Integer.parseInt(numbers.get(i)));
                case "MULM" -> mulM(Integer.parseInt(numbers.get(i)));
                case "DISP" -> disp();
                case "HALT" -> halt();
            }
        }
    }

    //Start execution
    public static void start() {
        System.out.println("Program execution is started");
        PC++;
    }

    //Load immediate value
    public static void load(int x) {
        AC = x;
        PC++;
    }

    //Load memory value stored at M[x] to AC
    public static void loadM(int memoryLocation) {
        if (memoryLocationCheck(memoryLocation))
            AC = ram[memoryLocation];
        PC++;
    }

    //Store a value AC to memory
    public static void store(int memoryLocation) {
        if (memoryLocationCheck(memoryLocation))
            ram[memoryLocation] = AC;
        PC++;
    }

    //Compare
    public static void cmpm(int memoryLocation) {
        if (memoryLocationCheck(memoryLocation))
            flag = Integer.compare(AC, ram[memoryLocation]);
        PC++;
    }

    //Conditional jump: Updates PC with x if the F flag value is positive
    public static void cjmp(int x) {
        if (flag == 1)
            PC = x;
        else
            PC++;
    }

    //jump: Updates PC with value x
    public static void jmp(int x) {
        PC = x;
    }

    //add: immediate value of X to AC
    public static void add(int x) {
        AC += x;
        PC++;
    }

    //Addition with memory
    public static void addM(int memoryLocation) {
        if (memoryLocationCheck(memoryLocation))
            AC += ram[memoryLocation];
        PC++;
    }

    //Subtract Memory value of M[x] from AC
    public static void subM(int memoryLocation) {
        if (memoryLocationCheck(memoryLocation))
            AC -= ram[memoryLocation];
        PC++;
    }

    //Subtract immediate value of x from AC
    public static void sub(int x) {
        AC -= x;
        PC++;
    }

    //Multiply AC with immediate value of N
    public static void mul(int n) {
        AC *= n;
        PC++;
    }

    public static void mulM(int memoryLocation) {
        if (memoryLocationCheck(memoryLocation))
            AC *= ram[memoryLocation];
        PC++;
    }

    public static void disp() {
        System.out.println("AC value -> " + AC);
        PC++;
    }

    public static void halt() {
        System.exit(0);
    }

    public static boolean memoryLocationCheck(int memoryLocation) {
        if (memoryLocation < ram.length && memoryLocation >= 0)
            return true;
        else {
            System.out.println("!!!Error: The given memory location (" + memoryLocation + ") is not valid.");
            return false;
        }
    }
}