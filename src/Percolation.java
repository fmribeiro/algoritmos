import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Random;

public class Percolation {

    private int grid[][];
    private int openSites[][];
    private int virtualTopSite = 0;
    private int sitesByRow;
    private QuickFindUF unionFind;
//    private WeightedQuickUnionUF unionFind;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }

        this.sitesByRow = n;
        this.unionFind = new QuickFindUF((n * n) + 2);
//        this.unionFind = new WeightedQuickUnionUF((n * n) + 2);
        grid = new int[n][n];
        openSites = new int[n][n];

        int siteNumber = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = siteNumber;
                if (i == 0) {
                    unionFind.union(this.virtualTopSite, this.grid[i][j]);
//                    unionFind.union(this.grid[i][j], this.virtualTopSite);
                }
                if (i == this.sitesByRow - 1) {
                    unionFind.union((n * n) + 1, this.grid[i][j]);
//                    unionFind.union( this.grid[i][j], (n * n) + 1);
                }
                openSites[i][j] = 0;
                siteNumber++;
            }
        }
    }

    private void isOutOfRange(int row, int col) {
        if (row < 0 || row > this.sitesByRow || col < 0 || col >= this.sitesByRow) {
            throw new IllegalArgumentException();
        }
    }

    public void open(int row, int col) {
        isOutOfRange(row, col);
        this.openSites[row][col] = 1;

        //top
        if (row - 1 >= 0 && this.openSites[row - 1][col] == 1) {
//            this.openSites[row - 1][col] = 1;
            unionFind.union(this.grid[row][col], this.grid[row - 1][col]);
        }

        //bottom
        if (row + 1 < this.sitesByRow && this.openSites[row + 1][col] == 1) {
//            this.openSites[row + 1][col] = 1;
            unionFind.union(this.grid[row][col], this.grid[row + 1][col]);
        }

        //left
        if (col - 1 >= 0 && this.openSites[row][col - 1] == 1) {
//            this.openSites[row][col - 1] = 1;
            unionFind.union(this.grid[row][col], this.grid[row][col - 1]);
        }

        //right
        if (col + 1 < this.sitesByRow && this.openSites[row][col + 1] == 1) {
//            this.openSites[row][col + 1] = 1;
            unionFind.union(this.grid[row][col], this.grid[row][col + 1]);
        }
    }

    public boolean isOpen(int row, int col) {
        return this.openSites[row][col] == 1;
    }

    public boolean isFull(int row, int col) {
        isOutOfRange(row, col);
        int id = this.unionFind.find(this.grid[row][col]);
        for (int i = 0; i < this.sitesByRow; i++) {
            if (this.unionFind.find(this.grid[0][i]) == id) {
//                System.out.println("isFull");
                System.out.println("row: " + row + " col:" + col);
                System.out.println("top row: " + 0 + " top col:" + i);
                return true;
            }
        }
        return false;
    }

    public int numberOfOpenSites() {
        int openCounter = 0;
        for (int i = 0; i < openSites.length; i++) {
            for (int j = 0; j < openSites.length; j++) {
                if (openSites[i][j] == 1) {
                    openCounter++;
                }
            }
        }
        return openCounter;
    }

    public boolean percolates() {
        int topPlus1 = (this.sitesByRow * this.sitesByRow) + 1;
        if (this.unionFind.find(0) == this.unionFind.find(topPlus1)) {
            System.out.println("Percolates!!! id: " + this.unionFind.find(0));
            return true;
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = 10;
//        int numberOfSites = Math.multiplyExact(n, n);
        System.out.println("Length: " + n);
        Percolation percolation = new Percolation(n);
//        percolation.open(0, 1);
//        percolation.open(2, 1);
//        percolation.open(0, 5);
//        percolation.open(0, 9);
//        percolation.open(2, 5);
//        percolation.open(4, 5);
//        percolation.open(5, 5);
//        percolation.open(6, 5);
//        percolation.open(8, 5);
//        percolation.open(9, 5);

        int openedCounter = 0;
        Random random = new Random();
        while (openedCounter < 80) {
            int row = random.nextInt(percolation.sitesByRow);
            int col = random.nextInt(percolation.sitesByRow);
            percolation.open(row, col);
//            System.out.println("find: row " + row + " col " + col + " -> " + percolation.unionFind.find(percolation.grid[row][col]));
            openedCounter++;
        }

//        QuickFindUF weightedQuickUnionUF = percolation.unionFind;
//        System.out.println("find: " + weightedQuickUnionUF.find(percolation.grid[2][1]));
//        System.out.println("find: " + weightedQuickUnionUF.find(percolation.grid[0][1]));
        System.out.println("Is open: " + percolation.isOpen(0, 2));
        System.out.println("Is Full: " + percolation.isFull(3, 1));
        System.out.println("Is Full: " + percolation.isFull(7, 2));
        System.out.println("Open sites: " + percolation.numberOfOpenSites());

        System.out.println("Percolates: " + percolation.percolates());

    }

}

// opens the site (row, col) if it is not open already
//    public void open(int row, int col) {
//        isOutOfRange(row, col);
//
//        //top
//        if (row - 1 >= 0) {
//            unionFind.union(this.grid[row][col], this.grid[row - 1][col]);
//        }
//
//        //bottom
//        if (row + 1 < this.sitesByRow) {
//            unionFind.union(this.grid[row][col], this.grid[row + 1][col]);
//        }
//
//        //left
//        if (col - 1 >= 0) {
//            unionFind.union(this.grid[row][col], this.grid[row][col - 1]);
//        }
//
//        //right
//        if (col + 1 < this.sitesByRow) {
//            unionFind.union(this.grid[row][col], this.grid[row][col + 1]);
//        }
//    }


// is the site (row, col) open?
//    public boolean isOpen(int row, int col) {
//        isOutOfRange(row, col);
//        int rowCol = this.unionFind.find(this.grid[row][col]);
//
//        //left
//        if (col - 1 >= 0 && this.unionFind.find(this.grid[row][col - 1]) == rowCol) {
//            return true;
//        }
//
//        //right
//        if (col + 1 < this.sitesByRow && this.unionFind.find(this.grid[row][col + 1]) == rowCol) {
//            return true;
//        }
//        return false;
//    }

// is the site (row, col) full?
//    public boolean isFull(int row, int col) {
//        isOutOfRange(row, col);
//        if (isOpen(row, col) && this.unionFind.find(this.grid[row][col]) < this.sitesByRow) {
//            return true;
//        }
//        return false;
//    }

// returns the number of open sites
//    public int numberOfOpenSites() {
//        int openCounter = 0;
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid.length; j++) {
//                if (isOpen(i, j)) {
//                    openCounter++;
//                }
//            }
//        }
//        return openCounter;
//    }

// does the system percolate?
//    public boolean percolates() {
//        for (int i = this.sitesByRow - 1; i < this.sitesByRow; i++) {
//            for (int j = 0; j < this.sitesByRow; j++) {
//                if (this.unionFind.find(this.grid[i][j]) < this.sitesByRow) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }