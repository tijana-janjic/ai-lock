import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("""
                Odaberite jednu od opcija:
                \t   1. Kreiranje brave po već postojećem fajlu
                \t   2. Kreiranje random brave sa čuvanjem u fajl
                \t   3. Kreiranje random brave bez pisanja u fajl""");

        Scanner in = new Scanner(System.in);
        String op = in.nextLine();
        do {
            switch (op) {
                case "1" -> {

                    System.out.println("Upisite ime fajla: ");
                    String filename = in.nextLine();


                    try {
                        Lock lock = new Lock(filename);

                        System.out.println("\n--------- UCS ---------\n");

                        UnlockUCS unlockUCS = new UnlockUCS(lock);
                        unlockUCS.unlock();

                        System.out.println("\n--------- A* ---------\n");
                        UnlockAStar unlockAStar = new UnlockAStar(lock);
                        unlockAStar.unlock();
                    } catch (FileNotFoundException e) {
                        System.err.println("Fajl nije pronadjen!");
                    } catch (IOException e) {
                        System.err.println("Greska tokom čitanja iz fajla!!");
                    }

                }
                case "2" -> {
                    Generator generator = new Generator();
                    System.out.println("Upisite ime fajla: ");
                    String filename = in.nextLine();
                    generator.generateFile(filename);

                    try {
                        Lock lock = new Lock(filename);
                        System.out.println("\n--------- UCS ---------\n");
                        Unlock unlockUCS = new UnlockUCS(lock);
                        unlockUCS.unlock();

                        System.out.println("\n--------- A* ---------\n");
                        Unlock unlockAStar = new UnlockAStar(lock);
                        unlockAStar.unlock();
                    } catch (FileNotFoundException e) {
                        System.err.println("Fajl nije pronadjen!");
                    } catch (IOException e) {
                        System.err.println("Greska tokom čitanja iz fajla!!");
                    }

                }
                case "3" -> {
                    Generator generator = new Generator();
                    generator.generateLock();

                    Lock lock = generator.generateLock();
                    System.out.println("\n--------- UCS ---------\n");
                    Unlock unlockUCS = new UnlockUCS(lock);
                    unlockUCS.unlock();

                    System.out.println("\n--------- A* ---------\n");
                    Unlock unlockAStar = new UnlockAStar(lock);
                    unlockAStar.unlock();
                }
                default -> System.out.println("Neispravan unos: "+ op );
            }

            System.out.println("""
                            
                Da nastavite, odaberite jednu od opcija:
                    1. Kreiranje brave po već postojećem fajlu
                    2. Kreiranje random brave sa čuvanjem u fajl
                    3. Kreiranje random brave bez pisanja u fajl
                Za kraj, unesite end
                """);

            op = in.nextLine();
        } while(!op.equals("end"));
    }
}
