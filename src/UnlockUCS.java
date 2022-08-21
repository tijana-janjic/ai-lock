import java.util.*;

public class UnlockUCS extends Unlock {

    public UnlockUCS(Lock lock) {
        super(lock);
    }

    private static class PathToCombination implements Comparable<PathToCombination> {

        private final String from;
        private final String to;
        private final int cost;

        public PathToCombination(String from, String to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(PathToCombination o) {
            return cost - o.cost;
        }

    }

    protected void findPath(String start, String end) {

        HashMap<String, String> map = new HashMap<>();

        PriorityQueue<PathToCombination> queue = new PriorityQueue<>();
        PathToCombination currPath = new PathToCombination(null, start, 0);
        queue.add(currPath);
        while (!queue.isEmpty()) {
            steps++;
            currPath = queue.poll();

            String from = currPath.from;
            String to = currPath.to;

            int currCost = currPath.cost;

            if (!map.containsKey(to)) {

                map.put(to, from);
                if (to.equals(end))
                    break;

                for (int i = 0; i < 4; i++) {
                    PathToCombination path;
                    if ((path = getUpper(to, currCost, i)) != null) queue.add(path);
                    if ((path = getBottom(to, currCost, i)) != null) queue.add(path);
                }
            }
        }

        cost = currPath.cost;

        for (String currentComb = end; currentComb != null; currentComb = map.get(currentComb)){
            path.addFirst(currentComb);
        }
    }

    private PathToCombination getUpper(String to, int currCostFromStart, int i) {
        int newVal = ((to.charAt(i) - 48) + 10 + 1) % 10;
        return createPath(to, currCostFromStart, i, newVal);
    }

    private PathToCombination getBottom(String to, int currCostFromStart, int i) {
        int newVal = ((to.charAt(i) - 48) + 10 - 1) % 10;
        return createPath(to, currCostFromStart, i, newVal);
    }

    private PathToCombination createPath(String to, int currCostFromStart, int i, int newVal) {
        StringBuilder s = new StringBuilder(to);
        s.setCharAt(i, (char) (newVal + 48));

        String currNeighbor = s.toString();

        if(lock.getBlocking().contains(currNeighbor))
            return null;

        int cost = currCostFromStart + lock.getCosts()[i][newVal];

        return new PathToCombination(to, currNeighbor, cost);
    }


}
