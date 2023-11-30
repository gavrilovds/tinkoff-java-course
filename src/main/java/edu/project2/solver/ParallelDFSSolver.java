package edu.project2.solver;

import edu.project2.model.Coordinates;
import edu.project2.model.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import lombok.SneakyThrows;

public class ParallelDFSSolver extends AbstractSolver {

    private static final int LOCK_TIME = 60;
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public List<Coordinates> solve(Maze maze, Coordinates start, Coordinates end) {
        if (!isCoordinatesValid(maze, start, end)) {
            throw new IllegalArgumentException("Invalid coordinates");
        }
        initSolver(maze);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        boolean result = forkJoinPool.invoke(new ParallelDFSTask(start, end));
        if (!result) {
            return Collections.emptyList();
        }
        path.add(end);
        return path;
    }

    private class ParallelDFSTask extends RecursiveTask<Boolean> {

        private final Coordinates current;
        private final Coordinates end;

        ParallelDFSTask(Coordinates current, Coordinates end) {
            this.current = current;
            this.end = end;
        }

        @Override
        @SneakyThrows
        protected Boolean compute() {
            List<Coordinates> coordinatesForFork = new ArrayList<>();
            coordinatesForFork.add(current);
            lock.tryLock(LOCK_TIME, TimeUnit.SECONDS);
            try {
                visited[current.row()][current.column()] = true;
            } finally {
                lock.unlock();
            }
            if (current.equals(end)) {
                return true;
            }
            List<ParallelDFSTask> forks = new ArrayList<>();
            List<Coordinates> neighbours = getNeighbours(current);
            for (Coordinates neighbour : neighbours) {
                if (!visited[neighbour.row()][neighbour.column()]) {
                    ParallelDFSTask task = new ParallelDFSTask(neighbour, end);
                    task.fork();
                    forks.add(task);
                }
            }
            for (ParallelDFSTask task : forks) {
                if (task.join()) {
                    path.addAll(coordinatesForFork);
                    return true;
                }
            }
            return false;
        }
    }
}
