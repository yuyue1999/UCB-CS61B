package lab11.graphs;

import java.util.Stack;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private static final int s=0;
    private Maze maze;
    private boolean iscycle;

    public MazeCycles(Maze m) {
        super(m);
        maze=m;
        distTo[s] = 0;
        edgeTo[s] = s;
        iscycle=false;
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        Stack<Integer> fringe=new Stack<>();
        fringe.add(s);
        marked[s]=true;
        announce();
        while(!fringe.isEmpty()){
            int v=fringe.pop();
            int count=0;
            for(int w:maze.adj(v)){
                if(marked[w]){
                    count++;
                }
                if(!marked[w]){
                    fringe.add(w);
                    marked[w]=true;
                    edgeTo[w]=v;
                    distTo[w]=distTo[v]+1;
                    announce();
                }
            }
            if(count>1){
                iscycle=true;
                return;
            }
        }
    }

    // Helper methods go here
}

