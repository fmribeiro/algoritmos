import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final int trials;
    private final int[] grid;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        grid = new int[n];
        for (int i = 0; i < grid.length; i++) {
            grid[i] = i;
        }
        this.trials = trials;
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(grid);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(grid);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(trials);
    }

    // test client (see below)
    public static void main(String[] args) {
//        int n = Integer.parseInt(args[0]);
//        int trials = Integer.parseInt(args[1]);
        int n = 1;
        int trials = 100;
        PercolationStats percolationStats = new PercolationStats(n, trials);
        Percolation percolation = new Percolation(n);
        for (int i = 0; i < trials; i++) {
            int row = StdRandom.uniform(1, n + 1);
            int col = StdRandom.uniform(1, n + 1);
            percolation.open(row, col);
        }

//        double threshold = percolation.numberOfOpenSites() / (n * n);
//        percolationThreshold[0] = threshold;

        System.out.println("mean: " + percolationStats.mean());
        System.out.println("stddev: " + percolationStats.stddev());
        System.out.println("95% confidence interval: [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    }
}
