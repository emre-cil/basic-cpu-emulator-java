import java.util.Random;

public class Instruction {
    int AC;
    int PC;
    int flag;
    RAM ram;


    public Instruction(RAM ram) {
        this.ram = ram;
        flag = 0;
    }

    //Start execution
    public void start() {
        System.out.println("Program execution is started");
    }

    //Load immediate value
    public void load(int x) {
        AC = x;
    }

    //Load memory value stored at M[x] to AC
    public void loadM(int memoryLocation) {
        if (memoryLocationCheck(memoryLocation))
            AC = ram.storage[memoryLocation];
    }

    //Store a value AC to memory
    public void store(int memoryLocation) {
        if (memoryLocationCheck(memoryLocation))
            ram.storage[memoryLocation] = AC;
    }

    //Compare
    public void cmpm(int memoryLocation) {
        if (memoryLocationCheck(memoryLocation))
            flag = Integer.compare(AC, ram.storage[memoryLocation]);
    }

    //Conditional jump: Updates PC with x if the F flag value is positive
    public void cjmp(int x) {
        if (flag == 1)
            PC = x;
        else
            System.out.println("!!!Error: F flag is not positive.");
    }

    //jump: Updates PC with value x
    public void jmp(int x) {
        PC = x;
    }

    //add: immediate value of X to AC
    public void add(int x) {
        AC += x;
    }

    //Addition with memory
    public void addM(int memoryLocation) {
        if (memoryLocationCheck(memoryLocation))
            AC += ram.storage[memoryLocation];
    }

    //Subtract Memory value of M[x] from AC
    public void subM(int memoryLocation) {
        if (memoryLocationCheck(memoryLocation))
            AC -= ram.storage[memoryLocation];
    }

    //Subtract immediate value of x from AC
    public void sub(int x) {
        AC -= x;
    }

    //Multiply AC with immediate value of N
    public void mul(int n) {
        AC *= n;
    }

    public void mulM(int memoryLocation) {
        if (memoryLocationCheck(memoryLocation))
            AC *= ram.storage[memoryLocation];
    }

    public void disp() {
        System.out.println("AC value -> " + AC);
    }

    public void halt() {
        System.out.println("Program execution is stopped");
    }

    public boolean memoryLocationCheck(int memoryLocation) {
        if (memoryLocation < ram.storage.length && memoryLocation >= 0)
            return true;
        else {
            System.out.println("!!!Error: The given memory location (" + memoryLocation + ") is not valid.");
            return false;
        }

    }
}
