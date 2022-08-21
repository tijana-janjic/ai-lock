public class Main {

    public static void main(String[] args) {

//        System.out.println("\n--------- UCS ---------\n");
//        Lock lock = new Lock("data.txt");
//        UnlockUCS unlockUCS = new UnlockUCS(lock);
//        unlockUCS.unlock();
//
//        System.out.println("\n--------- A* ---------\n");
//        lock = new Lock("data.txt");
//        UnlockAStar unlockAStar = new UnlockAStar(lock);
//        unlockAStar.unlock();

        Generator generator = new Generator();
        generator.generateFile("data.txt");

        Lock lock = new Lock("data.txt");

        System.out.println("\n--------- UCS ---------\n");
        Unlock unlockUCS = new UnlockUCS(lock);
        unlockUCS.unlock();

        lock = new Lock("data.txt");
        System.out.println("\n--------- A* ---------\n");
        Unlock unlockAStar = new UnlockAStar(lock);
        unlockAStar.unlock();
    }
}
