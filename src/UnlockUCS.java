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

    public void unlock(){
        findPath(lock.getStart(), lock.getEnd());
        prettyPrintPath();
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

            if(!map.containsKey(to)) {

                map.put(to, from);

                if(to.equals(end))
                    break;

                for (int i = 0; i < 4; i++) {
                    int x = to.charAt(i) - 48;
                    int x1 = (x+10-1)%10;
                    int x2 = (x+10+1)%10;
                    StringBuilder s = new StringBuilder(to);
                    s.setCharAt(i, (char) (x1+48));
                    queue.add(new PathToCombination(to, s.toString(), currCost + lock.getCosts()[i][x1]));
                    s.setCharAt(i, (char) (x2+48));
                    queue.add(new PathToCombination(to, s.toString(), currCost + lock.getCosts()[i][x2]));
                }
            }
        }

        cost = currPath.cost;

        for (String currentComb = end; currentComb != null ; currentComb = map.get(currentComb))
            path.addFirst(currentComb);
    }


}
