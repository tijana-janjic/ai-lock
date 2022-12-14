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

    public void unlock() {
        if(lock.getBlocking().contains(lock.getStart())){
            System.out.println("Početna kombinacija blokira bravu!");
            return;
        }
        if(lock.getBlocking().contains(lock.getEnd())){
            System.out.println("Krajnja kombinacija blokira bravu!");
            return;
        }
        findPath(lock.getStart(), lock.getEnd());
        prettyPrintPath();
    }

    protected abstract void findPath(String start, String end);

    protected void prettyPrintPath() {
        if (path.size() <= 1) {
            System.out.println("Nije moguće pronaći put!");
            return;
        }

        System.out.println("Početna: " + lock.getStart());
        System.out.println("Krajnja: " + lock.getEnd());
        System.out.println("Broj potrebnih poteza: " + (path.size()-1) );
        System.out.println("Cena: " + cost );
        System.out.println("Minimalna cena: " + lock.getMinCost() );
        System.out.println("Broj koraka algoritma: " + steps );
        System.out.println();

        for (String s : path) System.out.println("\t" + s);

    }


}
