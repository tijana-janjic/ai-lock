import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;

public class Generator {

    Random random = new Random();

    public void generateFile(String filename) {
        FileWriter out = null;
        try {
            out = new FileWriter(filename);

            out.append(getString(random.nextInt(10000))).append("\n");
            out.append(getString(random.nextInt(10000))).append("\n");

            out.append("---blocking---").append("\n");
            int nBlock = random.nextInt(100);
            for (int i = 0; i < nBlock; i++)
                out.append(getString(random.nextInt(10000))).append("\n");

            out.append("---costs---").append("\n");

            int minCost = Integer.MAX_VALUE;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 10; j++) {
                    int val = random.nextInt(1000);
                    out.append(String.valueOf(val)).append("\n");
                    if(val < minCost)
                        minCost = val;
                }
            }

        } catch (IOException e) {
            System.err.println("Greska tokom pisanja u fajl!");
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                System.err.println("Greska tokomzatvaranja fajla!");
            }
        }

    }

    public Lock generateLock(){

        Lock lock = new Lock();

        lock.setStart(getString(random.nextInt(10000)));
        lock.setEnd(getString(random.nextInt(10000)));

        int nBlock = random.nextInt(100);
        HashSet<String> blocking = new HashSet<>();
        for (int i = 0; i < nBlock; i++)
            blocking.add(getString(random.nextInt(10000)));
        lock.setBlocking(blocking);

        int[][] costs = new int[4][10];
        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                int val = random.nextInt(10000000 - 100) + 100;
                costs[i][j] = val;
                if(val < minCost)
                    minCost = val;
            }
        }
        lock.setCosts(costs);
        lock.setMinCost(minCost);

        return lock;
    }

    private String getString(int i) {
        if (i < 0 || i > 9999) {
            System.err.println("Greska pri konverziji broja u string!");
            return "error";
        }
        if (i > 999)
            return "" + i;
        else if (i > 99)
            return "0" + i;
        else if (i > 9)
            return "00" + i;
        return "000" + i;
    }

}
