import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int[][] grid;
    private final int[][] openSites;
    private final int sitesByRow;
    private int openCounter;
    //    private QuickFindUF unionFind;
    private final WeightedQuickUnionUF unionFind;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        final int virtualTopSite = 0;
        this.sitesByRow = n;
//        this.unionFind = new QuickFindUF((n * n) + 2);
        this.unionFind = new WeightedQuickUnionUF((n * n) + 2);
        grid = new int[n][n];
        openSites = new int[n][n];

        int siteNumber = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = siteNumber;
                if (i == 0) {
                    unionFind.union(virtualTopSite, this.grid[i][j]);
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

//        while(!percolates()){
//            int row = StdRandom.uniform(1,this.sitesByRow + 1);
//            int col = StdRandom.uniform(1,this.sitesByRow + 1);
//            open(row, col);
//        }
    }

    private void isOutOfRange(int row, int col) {
        if (row < 0 || row >= this.sitesByRow || col < 0 || col >= this.sitesByRow) {
            throw new IllegalArgumentException();
        }
    }

    private int updateIndex(int index) {
        return index - 1;
    }

    public void open(int row, int col) {
        row = updateIndex(row);
        col = updateIndex(col);
        isOutOfRange(row, col);

        if (!this.isOpen(row + 1, col + 1)) {
            this.openCounter++;
        }
        this.openSites[row][col] = 1;

        // top
        if (row - 1 >= 0 && this.openSites[row - 1][col] == 1) {
            unionFind.union(this.grid[row][col], this.grid[row - 1][col]);
        }

        // bottom
        if (row + 1 < this.sitesByRow && this.openSites[row + 1][col] == 1) {
            unionFind.union(this.grid[row][col], this.grid[row + 1][col]);
        }

        // left
        if (col - 1 >= 0 && this.openSites[row][col - 1] == 1) {
            unionFind.union(this.grid[row][col], this.grid[row][col - 1]);
        }

        // right
        if (col + 1 < this.sitesByRow && this.openSites[row][col + 1] == 1) {
            unionFind.union(this.grid[row][col], this.grid[row][col + 1]);
        }
    }

    public boolean isOpen(int row, int col) {
        row = updateIndex(row);
        col = updateIndex(col);
        isOutOfRange(row, col);

        return this.openSites[row][col] == 1;
    }

    public boolean isFull(int row, int col) {
        row = updateIndex(row);
        col = updateIndex(col);
        isOutOfRange(row, col);

        if (this.isOpen(row + 1, col + 1) && this.unionFind.find(0) == this.unionFind.find(this.grid[row][col])) {
            return true;
        }
        return false;
    }

    public int numberOfOpenSites() {
        return this.openCounter;
    }

    public boolean percolates() {
        int topPlus1 = (this.sitesByRow * this.sitesByRow) + 1;
        if (this.unionFind.find(0) == this.unionFind.find(topPlus1)) {
            return true;
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = 10;
        System.out.println("Length: " + n);
        Percolation percolation = new Percolation(n);

        int openedCounter = 0;
        while (openedCounter < 80) {
            int row = StdRandom.uniform(percolation.sitesByRow);
            int col = StdRandom.uniform(percolation.sitesByRow);
            percolation.open(row, col);
            openedCounter++;
        }

        System.out.println("Is open: " + percolation.isOpen(1, 1));
        System.out.println("Percolates: " + percolation.percolates());
    }

}
