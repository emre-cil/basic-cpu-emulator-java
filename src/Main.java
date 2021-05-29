import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        RAM ram = new RAM(256);
        CPU cpu = new CPU(ram);
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

        for (int i = 0; i < operations.size(); i++) {

//            System.out.println(operations.get(i) + " "+ numbers.get(i));
            switch (operations.get(i).toUpperCase()) {
                case "START" -> cpu.start();
                case "LOAD" -> cpu.load(Integer.parseInt(numbers.get(i)));
                case "LOADM" -> cpu.loadM(Integer.parseInt(numbers.get(i)));
                case "STORE" -> cpu.store(Integer.parseInt(numbers.get(i)));
                case "CMPM" -> cpu.cmpm(Integer.parseInt(numbers.get(i)));
                case "CJMP" -> cpu.cjmp(Integer.parseInt(numbers.get(i)));
                case "JMP" -> cpu.jmp(Integer.parseInt(numbers.get(i)));
                case "ADD" -> cpu.add(Integer.parseInt(numbers.get(i)));
                case "ADDM" -> cpu.addM(Integer.parseInt(numbers.get(i)));
                case "SUBM" -> cpu.subM(Integer.parseInt(numbers.get(i)));
                case "SUB" -> cpu.sub(Integer.parseInt(numbers.get(i)));
                case "MUL" -> cpu.mul(Integer.parseInt(numbers.get(i)));
                case "MULM" -> cpu.mulM(Integer.parseInt(numbers.get(i)));
                case "DISP" -> cpu.disp();
                case "HALT" -> cpu.halt();
            }
        }


//        cpu.start();
//        cpu.load(20);
//        cpu.store(200);
//        cpu.load(0);
//        cpu.store(201);
//        cpu.store(202);
//        cpu.cmpm(200);
//        cpu.cjmp(15);
//        cpu.loadM(202);
//        cpu.add(1);
//        cpu.store(201);
//        cpu.jmp(6);
//        cpu.loadM(202);
//        cpu.disp();
//        cpu.halt();
    }
}
