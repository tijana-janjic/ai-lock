import java.util.LinkedList;

public abstract class Unlock {

    protected final Lock lock;
    protected int cost;
    protected final LinkedList<String> path;
    protected int steps = 0;

    public Unlock(Lock lock) {
        this.lock = lock;
        path = new LinkedList<>();
    }

    protected void prettyPrintPath() {
        if (path.size() == 1) {
            System.out.println("Nije moguce naci put!");
            return;
        }

        System.out.println("Pocetni: " + lock.getStart());
        System.out.println("Krajnji: " + lock.getEnd());
        System.out.println("Broj koraka: " + (path.size()-1) );
        System.out.println("Cena: " + cost );
        System.out.println("Minimalna cena: " + lock.getMinCost() );
        System.out.println("Broj koraka: " + steps );
        System.out.println();

        for (String s : path) System.out.println("\t" + s);

    }

    public void unlock() {
        findPath(lock.getStart(), lock.getEnd());
        prettyPrintPath();
    }

    protected abstract void findPath(String start, String end);


}
