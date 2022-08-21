import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Lock {

    private String start;
    private String end;
    private HashSet<String> blocking;
    private int[][] costs; // cena pokretanja i-tog tockica do j-te cifre
    private int minCost;

    public Lock() {
        costs = new int[4][10];
    }

    public Lock(String filename) throws IOException {
        this();
        readFile(filename);
    }

    private void readFile(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filename));
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
