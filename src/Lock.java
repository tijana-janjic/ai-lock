import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Lock {

    private String start;
    private String end;
    private HashSet<String> blocking;
    private int[][] costs; // cena pokretanja i-tog tockica do j-te cifre
    private int minCost;

    public Lock() {
        costs = new int[4][10];
    }

    public Lock(String filename) {
        this();
        readFile(filename);
    }

    private void readFile(String filename) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(filename));
            String line;

            start = getFull(in.readLine());
            end = getFull(in.readLine());

            in.readLine(); // blocking nodes starts

            blocking = new HashSet<>();
            while ( !(line = in.readLine()).startsWith("---") )
                blocking.add(line);

            minCost = Integer.MAX_VALUE;
            for (int i = 0; i < 4; i++){
                for (int j = 0; j < 10; j++){
                    int val = Integer.parseInt(in.readLine());
                    costs[i][j] = val;
                    if(val < minCost)
                        minCost = val;
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("Fajl nije pronadjen!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                System.err.println("Greska pri zatvaranju fajla!");
            }
        }
    }


    private String getFull(String string) {
        while(string.length() < 4)
            string = "0" + string;
        return string;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public HashSet<String> getBlocking() {
        return blocking;
    }

    public int[][] getCosts() {
        return costs;
    }

    public int getMinCost() {
        return minCost;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setBlocking(HashSet<String> blocking) {
        this.blocking = blocking;
    }

    public void setCosts(int[][] costs) {
        this.costs = costs;
    }

    public void setMinCost(int minCost) {
        this.minCost = minCost;
    }
}
