package assn05;

public class Main {

    public static void main(String[] args) {
        testP1();
        testP2();
        testP3();
        testP4();
    }

    // test Part 1
    public static void testP1(){
        /*
        Part 1 - Write some tests to convince yourself that your code for Part 1 is working
         */

        SimpleEmergencyRoom simplePQ = new SimpleEmergencyRoom();
        simplePQ.addPatient(5,5);
        simplePQ.addPatient(2,2);
        simplePQ.addPatient(3,3);
        simplePQ.addPatient(1,1);
        simplePQ.addPatient(4,4);

        System.out.println("Simple ER: ");
        while (simplePQ.size() > 0) System.out.println(simplePQ.dequeue().getValue());
        System.out.println();
    }

    // test Part 2
    public static void testP2(){
       /*
       Part 2 - Write some tests to convince yourself that your code for Part 2 is working
        */
        
       MaxBinHeapER binHeap = new MaxBinHeapER();
       binHeap.enqueue(5, 5);
       binHeap.enqueue(2, 2);
       binHeap.enqueue(3, 3);
       binHeap.enqueue(1, 1);
       binHeap.enqueue(4, 4);
        
       System.out.println("MaxBinHeapER: ");
       while (binHeap.size() > 0)
           System.out.println(binHeap.dequeue());
         System.out.println();
    }

    /*
    Part 3
     */
    public static void testP3(){
        MaxBinHeapER transfer = new MaxBinHeapER(makePatients());
        Prioritized[] arr = transfer.getAsArray();
        for(int i = 0; i < transfer.size(); i++) {
            System.out.println("Value: " + arr[i].getValue()
                    + ", Priority: " + arr[i].getPriority());
        }
    }

    /*
    Part 4
     */
    public static void testP4() {
               /*
        Part 4 - Write some tests to convince yourself that your code for Part 4 is working
         */
        System.out.println("Compare runtimes: ");
        double[] results = compareRuntimes();
        System.out.println("Total time: " + results[0] + ", Average time: " + results[1]);
        System.out.println("Total time: " + results[2] + ", Average time: " + results[3]);
    }

    public static void fillER(MaxBinHeapER complexER) {
        for(int i = 0; i < 100000; i++) {
            complexER.enqueue(i);
        }
    }
    public static void fillER(SimpleEmergencyRoom simpleER) {
        for(int i = 0; i < 100000; i++) {
            simpleER.addPatient(i);
        }
    }

    public static Patient[] makePatients() {
        Patient[] patients = new Patient[10];
        for(int i = 0; i < 10; i++) {
            patients[i] = new Patient(i);
        }
        return patients;
    }
    
    public static double[] compareRuntimes() {
    	// Array which you will populate as part of Part 4
    	double[] results = new double[4];
    	
        SimpleEmergencyRoom simplePQ = new SimpleEmergencyRoom();
        fillER(simplePQ);

        // Code for (Task 4.1) Here
        double start = System.nanoTime();
        int size = simplePQ.size();
        for (int i = 0; i < size; i++) {
            simplePQ.dequeue();
        }
        double total = System.nanoTime() - start;
        results[0] = total;
        results[1] = total / size;

        MaxBinHeapER binHeap = new MaxBinHeapER();
        fillER(binHeap);

        // Code for (Task 4.2) Here
        start = System.nanoTime();
        size = binHeap.size();
        for (int i = 0; i < size; i++) {
            binHeap.dequeue();
        }
        total = System.nanoTime() - start;
        results[2] = total;
        results[3] = total / size;

        return results;
    }

}



