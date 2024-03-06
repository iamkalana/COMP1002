import java.util.Scanner;

public class TowerOfHanoi {

    static int movCount = 0; //To count moves

    public static void main(String[] args) {

        int recLvlCount = 1; //To count recursion level

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of disks you want: ");
        int n = sc.nextInt();

        System.out.println("");
        System.out.println("        towers(" + n + "," + 1 + "," + 3 + ")");

        toh(n, 1, 3, recLvlCount);

        System.out.println(" # There are " + movCount + " moves for this problem.");
        sc.close();

    }

    public static void moveDisk(int disk, int src, int dest, int pRecLvl) {
        indenting(pRecLvl);
        System.out.println("Recursion Level = " + pRecLvl);
        indenting(pRecLvl);
        System.out.println("Moving disk " + disk + " from Source " + src + " to Destination " + dest);
        indenting(pRecLvl);
        System.out.println("Disk=" + disk + ", src=" + src + ", dest=" + dest);
        System.out.println("");

    }

    public static void toh(int n, int src, int dest, int recLvl) {

        movCount = movCount + 1;

        if (n == 1)
            moveDisk(n, src, dest, recLvl);
        else {
            int tmp = 6 - src - dest;
            toh((n - 1), src, tmp, recLvl + 1);
            moveDisk(n, src, dest, recLvl);
            toh((n - 1), tmp, dest, recLvl + 1);
        }
    }

    //To add indenting
    public static void indenting(int spaces) {
        for (int i = 0; i < spaces; i++) {
            System.out.print("        ");
        }
    }



}
