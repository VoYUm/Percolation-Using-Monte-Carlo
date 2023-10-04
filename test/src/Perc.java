import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Perc {
    //amortized running time inverse ackermann
    public Perc(int n) {
        // default constructor, all points unopened
        if (n <= 0) throw new IllegalArgumentException("fuck you bitch");
        grid = new boolean[n][n];
        sideLength = n;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                grid[i][j] = false;
            }
        }
        disjoint_set = new int[n * n + 2];
        for (int i = 0; i < n * n + 2; ++i) {
            disjoint_set[i] = i;
        }
        disjoint_set_size = new int[n * n + 2];
        for (int i = 0; i < n * n + 2; ++i) {
            disjoint_set_size[i] = 1;
        }
    }

    public Perc(int n, int numOpen) {
        //constructor initializes a grid of sideLength n with numOpen points opened at random
        if (n <= 0) throw new IllegalArgumentException("fuck you bitch");
        grid = new boolean[n][n];
        sideLength = n;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                grid[i][j] = false;
            }
        }
        disjoint_set = new int[n * n + 2];
        for (int i = 0; i < n * n + 2; ++i) {
            disjoint_set[i] = i;
        }
        disjoint_set_size = new int[n * n + 2];
        for (int i = 0; i < n * n + 2; ++i) {
            disjoint_set_size[i] = 1;
        }

        int[] toOpen = generateRandomArray(numOpen, 1, n * n);
        for (int i = 0; i < toOpen.length; ++i) {
            if (toOpen[i] % n == 0) open(toOpen[i] / n, n);
            else open(toOpen[i] / n + 1, toOpen[i] % n);
        }


    }

    public int getSideLength() {
        return sideLength;
    }

    public void open(int row, int col) {
        // open a single point in grid
        if (row <= 0 || row > sideLength || col <= 0 || col > sideLength) {
            throw new IllegalArgumentException("pussycat");
        }
        grid[row - 1][col - 1] = true;
        if (col - 2 >= 0 && grid[row - 1][col - 2])
            union((row - 1) * sideLength + col - 1, (row - 1) * sideLength + col - 2);
        if (col < sideLength && grid[row - 1][col])
            union((row - 1) * sideLength + col - 1, (row - 1) * sideLength + col);
        if (row - 2 >= 0 && grid[row - 2][col - 1])
            union((row - 1) * sideLength + col - 1, (row - 2) * sideLength + col - 1);
        if (row < sideLength && grid[row][col - 1]) union((row - 1) * sideLength + col - 1, row * sideLength + col - 1);
        if (row - 1 == 0) union(col - 1, sideLength * sideLength);
        if (row - 1 == sideLength - 1) union((row - 1) * sideLength + col - 1, sideLength * sideLength + 1);
    }

    public boolean isOpen(int row, int col) {
        //check if a single point in grid is open
        if (row <= 0 || row > sideLength || col <= 0 || col > sideLength) throw new IllegalArgumentException("pussy");
        return grid[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        //check if a single point is open and percolated (full)
        if (row <= 0 || row > sideLength || col <= 0 || col > sideLength) throw new IllegalArgumentException("pussy");
        return isConnected((row - 1) * sideLength + col - 1, sideLength * sideLength);
    }

    public int numberOfOpenSites() {
        //return number of open sites in grid
        int count = 0;
        for (int i = 1; i <= sideLength; ++i) {
            for (int j = 1; j <= sideLength; ++j) {
                if (isOpen(i, j)) count++;
            }
        }
        return count;
    }

    public boolean percolates() {
        //check if the grid satisfies the physics definition of percolation
        return isConnected(sideLength * sideLength, sideLength * sideLength + 1);
    }

    public void displayGrid(int n) {
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (isOpen(i, j)) {
                    System.out.print("o  ");
                } else {
                    System.out.print("x  ");
                }
            }
            System.out.print('\n');
        }
    }

    private int find(int p) {
        //returns root of p
        while (disjoint_set[p] != p) {
            disjoint_set[p] = disjoint_set[disjoint_set[p]];
            p = disjoint_set[p];
        }
        return p;
    }

    private void union(int p, int q) {
        //union p and q in the disjoint set
        int i = find(p);
        int j = find(q);
        if (i == j) {
            return;
        }
        if (disjoint_set_size[i] > disjoint_set_size[j]) {
            disjoint_set[j] = disjoint_set[i];
            disjoint_set_size[i] += disjoint_set_size[j];
        } else {
            disjoint_set[i] = disjoint_set[j];
            disjoint_set_size[j] += disjoint_set_size[i];
        }
    }

    private boolean isConnected(int p, int q) {
        //check whether points p and q are connected in the disjoint set
        return find(p) == find(q);
    }

    private int[] generateRandomArray(int length, int min, int max) {
        //generate an array of 'length' unique random numbers from min to max, both inclusive

        if (length > (max - min + 1)) {
            throw new IllegalArgumentException("Cannot generate " + length + " unique integers in the given range.");
        }

        int[] randomArray = new int[length];
        Set<Integer> uniqueNumbers = new HashSet<>();

        Random rand = new Random();
        int index = 0;

        while (index < length) {
            int randomNum = rand.nextInt(max - min + 1) + min;
            if (!uniqueNumbers.contains(randomNum)) {
                uniqueNumbers.add(randomNum);
                randomArray[index] = randomNum;
                index++;
            }
        }

        return randomArray;
    }

    public static void main(String[] args) {
        int sideLength = 5;
        int numOpenSites = 13;
        Perc p = new Perc(sideLength, numOpenSites);
        p.displayGrid(sideLength);
        System.out.println(p.percolates());
        System.out.flush();
    }


    private final boolean[][] grid;
    private final int sideLength;
    private final int[] disjoint_set;
    private final int[] disjoint_set_size;

}
