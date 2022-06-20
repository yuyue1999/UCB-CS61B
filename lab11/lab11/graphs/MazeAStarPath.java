package lab11.graphs;

import java.util.PriorityQueue;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        //Math.abs(sourceX - targetX) + Math.abs(sourceY - targetY);
        int sourceVx=maze.toX(v);
        int sourceVy=maze.toY(v);
        int targetX=maze.toX(t);
        int targetY=maze.toY(t);

        return Math.abs(sourceVx-targetX)+Math.abs(sourceVy-targetY);
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // TODO
        ArrayHeap<Integer> fringe=new ArrayHeap<>();
        marked[s]=true;
        announce();
        for(int i=0;i<maze.V();i++){
            if(i==s){
                fringe.insert(s,distTo[s]);
            }else {
                fringe.insert(i,distTo[i]);
            }
        }
        while(!fringe.isEmpty()){
            int v=fringe.removeMin();
            for(int w:maze.adj(v)){
                if(!marked[w]){
                    relax(w,v,fringe);
                    if(w==t){
                        return;
                    }
                }
            }
        }

    }
    private void relax(int w,int v,ArrayHeap fringe){
        if(distTo[w]>distTo[v]+1){
            distTo[w]=distTo[v]+1;
            edgeTo[w]=v;
            marked[w]=true;
            if(fringe.getNode(w)!=null){
                fringe.changePriority(w,distTo[w]+h(w));
            }
            announce();
//            if(w==t){
//                return;
//            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

