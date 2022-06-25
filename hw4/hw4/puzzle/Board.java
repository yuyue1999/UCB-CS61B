package hw4.puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Board implements WorldState {
    public int[][] tiles;
    private int[][] goal;
    private int size;
    private ArrayList<WorldState> store;
    private int number;

    /**
     * Returns the string representation of the board.
     * Uncomment this method.
     */
//    Board(tiles): Constructs a board from an N-by-N array of tiles where
//    tiles[i][j] = tile at row i, column j
//    tileAt(i, j): Returns value of tile at row i, column j (or 0 if blank)
//    size():       Returns the board size N
//    neighbors():  Returns the neighbors of the current board
//    hamming():    Hamming estimate described below
//    manhattan():  Manhattan estimate described below
//    estimatedDistanceToGoal(): Estimated distance to goal. This method should
//    simply return the results of manhattan() when submitted to Gradescope.
//    equals(y):    Returns true if this board's tile values are the same
//    position as y's
//    toString():   Returns the string representation of the board. This
//    method is provided in the skeleton
    public Board(int[][] tiles) {
        this.tiles = new int[tiles.length][tiles.length];
        for(int i=0;i<tiles.length;i++){
            this.tiles[i]=new int[tiles.length];
        }
        for(int i=0;i<tiles.length;i++){
            for(int j=0;j<tiles.length;j++){
                this.tiles[i][j]=tiles[i][j];
            }
        }
        this.size = tiles.length;
        store = new ArrayList<>();
        this.number = tiles.length;
        this.goal = new int[size][size];
        for (int i = 0; i < size(); i++) {
            goal[i] = new int[size()];
        }
        int tom = 1;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                goal[i][j] = tom;
                tom++;
            }
        }
        goal[size - 1][size - 1] = 0;
    }

    public int tileAt(int i, int j) {
        if (i < 0 || i >= number || j < 0 || j >= number) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }

    public int size() {
        return size;
    }

    public Iterable<WorldState> neighbors() {
        int[][] temp3 = new int[tiles.length][tiles.length];
        int[][] temp1 = new int[tiles.length][tiles.length];
        int[][] temp2 = new int[tiles.length][tiles.length];
        int[][] temp4 = new int[tiles.length][tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                temp1[i][j] = tiles[i][j];
                temp2[i][j] = tiles[i][j];
                temp3[i][j] = tiles[i][j];
                temp4[i][j] = tiles[i][j];
            }
        }
        if (tiles[0][0] == 0) {
            temp1[0][0] = temp1[0][1];
            temp1[0][1] = 0;
            temp2[0][0] = temp2[1][0];
            temp2[1][0] = 0;
            Board Neighbor1 = new Board(temp1);
            Board Neighbor2 = new Board(temp2);
            store.add(Neighbor1);
            store.add(Neighbor2);
            return store;
        }
        if (tiles[tiles.length - 1][tiles.length - 1] == 0) {
            temp1[number - 1][number - 1] = temp1[number - 1][number - 1 - 1];
            temp1[number - 1][number - 1 - 1] = 0;
            temp2[number - 1][number - 1] = temp2[number - 1 - 1][number - 1];
            temp2[number - 1 - 1][number - 1] = 0;
            Board Neighbor1 = new Board(temp1);
            Board Neighbor2 = new Board(temp2);
            store.add(Neighbor1);
            store.add(Neighbor2);
            return store;
        }
        if (tiles[0][tiles.length - 1] == 0) {
            temp1[0][number - 1] = temp1[0][number - 1 - 1];
            temp1[0][number - 1 - 1] = 0;
            temp2[0][number - 1] = temp2[1][number - 1];
            temp2[1][number - 1] = 0;
            Board Neighbor1 = new Board(temp1);
            Board Neighbor2 = new Board(temp2);
            store.add(Neighbor1);
            store.add(Neighbor2);
            return store;
        }
        if (tiles[tiles.length - 1][0] == 0) {
            temp1[number - 1][0] = temp1[number - 1 - 1][0];
            temp1[number - 1 - 1][0] = 0;
            temp2[number - 1][0] = temp2[number - 1][1];
            temp2[number - 1][1] = 0;
            Board Neighbor1 = new Board(temp1);
            Board Neighbor2 = new Board(temp2);
            store.add(Neighbor1);
            store.add(Neighbor2);
            return store;
        }
        HashMap<Integer, Integer> helper = FindZero(tiles);
        int hang = 0;
        int lie = 0;
        for (int i : helper.keySet()) {
            hang = i;
        }
        for (int j : helper.values()) {
            lie = j;
        }
        if (hang == 0) {
            temp1[0][lie] = temp1[0][lie - 1];
            temp1[0][lie - 1] = 0;
            temp2[0][lie] = temp2[0][lie + 1];
            temp2[0][lie + 1] = 0;
            temp3[0][lie] = temp3[1][lie];
            temp3[1][lie] = 0;
            Board Neighbor1 = new Board(temp1);
            Board Neighbor2 = new Board(temp2);
            Board Neighbor3 = new Board(temp3);
            store.add(Neighbor1);
            store.add(Neighbor2);
            store.add(Neighbor3);
            return store;
        }
        if (hang == number - 1) {
            temp1[number - 1][lie] = temp1[number - 1][lie - 1];
            temp1[number - 1][lie - 1] = 0;
            temp2[number - 1][lie] = temp2[number - 1][lie + 1];
            temp2[number - 1][lie + 1] = 0;
            temp3[number - 1][lie] = temp3[number - 1 - 1][lie];
            temp3[number - 1 - 1][lie] = 0;
            Board Neighbor1 = new Board(temp1);
            Board Neighbor2 = new Board(temp2);
            Board Neighbor3 = new Board(temp3);
            store.add(Neighbor1);
            store.add(Neighbor2);
            store.add(Neighbor3);
            return store;
        }
        if (lie == 0) {
            temp1[hang][0] = temp1[hang + 1][0];
            temp1[hang + 1][0] = 0;
            temp2[hang][0] = temp2[hang - 1][0];
            temp2[hang - 1][0] = 0;
            temp3[hang][0] = temp3[hang][lie + 1];
            temp3[hang][lie + 1] = 0;
            Board Neighbor1 = new Board(temp1);
            Board Neighbor2 = new Board(temp2);
            Board Neighbor3 = new Board(temp3);
            store.add(Neighbor1);
            store.add(Neighbor2);
            store.add(Neighbor3);
            return store;
        }
        if (lie == number - 1) {
            temp1[hang][lie] = temp1[hang + 1][lie];
            temp1[hang + 1][lie] = 0;
            temp2[hang][lie] = temp2[hang - 1][lie];
            temp2[hang - 1][lie] = 0;
            temp3[hang][lie] = temp3[hang][lie - 1];
            temp3[hang][lie - 1] = 0;
            Board Neighbor1 = new Board(temp1);
            Board Neighbor2 = new Board(temp2);
            Board Neighbor3 = new Board(temp3);
            store.add(Neighbor1);
            store.add(Neighbor2);
            store.add(Neighbor3);
            return store;
        }
        temp1[hang][lie] = temp1[hang + 1][lie];
        temp1[hang + 1][lie] = 0;
        temp2[hang][lie] = temp2[hang - 1][lie];
        temp2[hang - 1][lie] = 0;
        temp3[hang][lie] = temp3[hang][lie - 1];
        temp3[hang][lie - 1] = 0;
        temp4[hang][lie] = temp4[hang][lie + 1];
        temp4[hang][lie + 1] = 0;
        Board Neighbor1 = new Board(temp1);
        Board Neighbor2 = new Board(temp2);
        Board Neighbor3 = new Board(temp3);
        Board Neighbor4 = new Board(temp4);
        store.add(Neighbor1);
        store.add(Neighbor2);
        store.add(Neighbor3);
        store.add(Neighbor4);
        return store;
    }

    private HashMap FindZero(int[][] target) {
        HashMap<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            for (int j = 0; j < target.length; j++) {
                if (tiles[i][j] == 0) {
                    result.put(i, j);
                    return result;
                }
            }
        }
        return null;
    }

    public int hamming() {
        int count = 1;
        int result = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (i == size() - 1 && j == size() - 1) {
                    if (tiles[i][j] != 0) {
                        result++;
                    }
                } else {
                    if (tiles[i][j] != count) {
                        result++;
                    }
                }
                count++;
            }
        }
        return result - 1;
    }

    private int[] manhattan_helper(int search) {
        int[] result = new int[2];
        for (int i = 0; i < goal.length; i++) {
            for (int j = 0; j < goal.length; j++) {
                if (goal[i][j] == search) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return null;
    }

    public int manhattan() {
        int count = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (tiles[i][j] == 0) {
                    continue;
                } else {
                    int[] current = manhattan_helper(tiles[i][j]);
                    count = count + Math.abs(i - current[0]) + Math.abs(j - current[1]);
                }
            }
        }
        return count;

    }

    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y == null || getClass() != y.getClass()) {
            return false;
        }

        Board board1 = (Board) y;
        if (this.tiles == null) {
            if (board1.tiles == null) {
                return true;
            }
            return false;
        }
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (this.tiles[i][j] != board1.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }


    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    public static void main(String[] args) {
        int dwdwd = 3;
        int[][] test = new int[dwdwd][dwdwd];
        for (int i = 0; i < dwdwd; i++) {
            test[i] = new int[dwdwd];
        }
        int tom = 1;
        for (int i = 0; i < dwdwd; i++) {
            for (int j = 0; j < dwdwd; j++) {
                test[i][j] = tom;
                tom++;
            }
        }
        test[2][2] = 5;
        test[0][0] = 8;
        test[0][1] = 1;
        test[1][1] = 0;
        test[1][2] = 2;
        test[2][1] = 6;


        Board testB = new Board(test);
        System.out.println(testB.hamming());
        System.out.println(testB.manhattan());
//        for(WorldState t:testB.neighbors()){
//            Board tem=(Board)t;
//            System.out.println(tem);
//            System.out.println(tem.manhattan());
//        }
    }

}

