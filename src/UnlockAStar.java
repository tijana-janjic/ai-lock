import java.util.*;

public class UnlockAStar extends Unlock {

    public UnlockAStar(Lock lock) {
        super(lock);
    }

    private static class PathToCombination implements Comparable<PathToCombination> {

        private final String from;
        private final String to;
        private final int costFromStart;
        private final int costToEnd;

        public PathToCombination(String from, String to, int costFromStart, int costToEnd) {
            this.from = from;
            this.to = to;
            this.costFromStart = costFromStart;
            this.costToEnd = costToEnd;

        }

        public int getTotalCost() {
            return costFromStart + costToEnd;
        }

        @Override
        public int compareTo(PathToCombination o) {
            return getTotalCost() - o.getTotalCost();
        }

        @Override
        public String toString() {
            return " " + getTotalCost();
        }
    }

    public void unlock(){
        findPath(lock.getStart(), lock.getEnd());
        prettyPrintPath();
    }


    protected void findPath(String start, String end) {

        HashMap<String, String> map = new HashMap<>();

        PriorityQueue<PathToCombination> queue = new PriorityQueue<>();
        PathToCombination currPath = new PathToCombination(null, start, 0, 0);
        queue.add(currPath);
        while (!queue.isEmpty()) {
            steps++;
            currPath = queue.poll();

            String from = currPath.from;
            String to = currPath.to;

            int currCostFromStart = currPath.costFromStart;

            if(!map.containsKey(to)) {

                map.put(to, from);

                if(to.equals(end))
                    break;

                for (int i = 0; i < to.length(); i++) {
                                        PathToCombination path;
                    if ((path = getUpper(end, to, currCostFromStart, i)) != null) queue.add(path);
                    if ((path = getBottom(end, to, currCostFromStart, i)) != null) queue.add(path);

                }

            }
        }
        cost = currPath.getTotalCost();

        for (String currentComb = end; currentComb != null ; currentComb = map.get(currentComb))
            path.addFirst(currentComb);
    }



    private PathToCombination createPath(String end, String to, int currCostFromStart, int i, int newVal) {
        StringBuilder s = new StringBuilder(to);
        s.setCharAt(i, (char) (newVal + 48));

        String currNeighbor = s.toString();

        if(lock.getBlocking().contains(currNeighbor))
            return null;

        int toNeighborCost = currCostFromStart + lock.getCosts()[i][newVal];
        int fromNeighborCost = findEstimate(currNeighbor, end);

        return new PathToCombination(to, currNeighbor, toNeighborCost, fromNeighborCost);
    }

    private PathToCombination getUpper(String end, String to, int currCostFromStart, int i) {
        int newVal = ((to.charAt(i) - 48) + 10 + 1) % 10;
        return createPath(end, to, currCostFromStart, i, newVal);
    }

    private PathToCombination getBottom(String end, String to, int currCostFromStart, int i) {
        int newVal = ((to.charAt(i) - 48) + 10 - 1) % 10;
        return createPath(end, to, currCostFromStart, i, newVal);
    }

    private int findEstimate(String start, String end) {
        int res = 0;
        for (int i = 0; i < 4; i++) {
            int x = Math.abs(start.charAt(i) - end.charAt(i));
            res += Math.min(x, 10 - x);
        }
        return res * lock.getMinCost();
    }

}


