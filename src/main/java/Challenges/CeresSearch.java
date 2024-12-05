
package Challenges;
import Main.Main;
public class CeresSearch extends Challenge {
    private final char[][] grid;
    public CeresSearch(int part, int size) {
        assert part == 1 || part == 2;
        solution = 0;
        grid = new char[size][size];
        for (int i = 0; i < size; i++) {
            grid[i] = Main.scanner.nextLine().toCharArray();
        }
        if (part == 1) {
            partOne();
        } else {
            partTwo();
        }
    }
    private void partOne() {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid.length; y++) { // For each coordinate in the grid

                if (grid[y][x] == 'X') { // If the 1st character is 'X'
                    // For each direction a solution could be
                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            try { // Try-ignored my beloved
                                if (
                                        grid[y + dy][x + dx] == 'M' && /* If the 2nd character is 'M' */
                                        grid[y + 2 * dy][x + 2 * dx] == 'A' && /* If the 3rd character is 'A' */
                                        grid[y + 3 * dy][x + 3 * dx] == 'S' /* If the 4th character is 'S' */
                                ) solution++;
                            } catch (ArrayIndexOutOfBoundsException ignored) {} // If we go out of bounds
                        }
                    }

                }

            }
        }
    }
    private void partTwo() {
        for (int x = 1; x + 1 < grid.length; x++) {
            for (int y = 1; y + 1 < grid.length; y++) {
                if (
                        grid[y][x] == 'A' && /* If the central character is 'A' */
                        ((grid[y - 1][x - 1] == 'M' && grid[y + 1][x + 1] == 'S') || (grid[y + 1][x + 1] == 'M' && grid[y - 1][x - 1] == 'S')) && /* If the topleft-downright characters are 'M' & 'S' */
                        ((grid[y + 1][x - 1] == 'M' && grid[y - 1][x + 1] == 'S') || (grid[y - 1][x + 1] == 'M' && grid[y + 1][x - 1] == 'S')) /* If the topright-downleft characters are 'M' & 'S' */
                ) solution++;
            }
        }
    }
}
