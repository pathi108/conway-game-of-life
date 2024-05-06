package com.bgl.engine;

import com.bgl.engine.model.Cell;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Game engine that has the rules of the game
 */
public class GameEngine {

    private static GameEngine instance = null;

    private static final int NUM_OF_ROWS_X = 200;
    private static final int NUM_OF_ROWS_Y = 200;


    private GameEngine() {

    }

    public static synchronized GameEngine getInstance() {
        if (instance == null)
            instance = new GameEngine();

        return instance;
    }

    /**
     * Returns the next state of the game
     * @param aliveCellsById alive clients in a given step
     * @return alive clients after that step
     */
    public synchronized List<Cell> getNextState(Map<String, Cell> aliveCellsById) {

        MultiValuedMap<String, String> affectedNeighboursWithAliveMembers = getAffectedNeighbours(aliveCellsById.values());

        Set<String> affectedNeighbours = affectedNeighboursWithAliveMembers.keySet();

        List<String> aliveNeighbours = new ArrayList<>();
        for (String affectedNeighbourId : affectedNeighbours) {
            List<String> neighbours = (List<String>) affectedNeighboursWithAliveMembers.get(affectedNeighbourId);
            boolean isNeighbourAlive = aliveCellsById.get(affectedNeighbourId) != null;
            if (isCellAliveInNextRun(affectedNeighbourId, neighbours, isNeighbourAlive)) {
                aliveNeighbours.add(affectedNeighbourId);
            }
        }

        return aliveNeighbours
                .stream()
                .sorted()
                .map(aliveNeighbour -> aliveNeighbour.split("-"))
                .map(as -> new Cell(Integer.parseInt(as[0]), Integer.parseInt(as[1])))
                .collect(Collectors.toList());

    }

    /**
     * When given a set of alive cells returns a possible neighbours that might be affected by them
     * @param aliveCells alive cells
     * @return affected neighbours
     */
    private synchronized MultiValuedMap<String, String> getAffectedNeighbours(Collection<Cell> aliveCells) {
        MultiValuedMap<String, String> affectedNeighbours = new ArrayListValuedHashMap<>();
        aliveCells.forEach(cell -> {
            cell.getNeighbours()
                    .forEach(c -> affectedNeighbours.put(c, cell.getId()));
        });
        return affectedNeighbours;
    }

    private boolean isCellAliveInNextRun(String id, List<String> neighbours, boolean isNeighbourAlive) {
        if (isNeighbourAlive) {
            return neighbours.size() >= 2 && neighbours.size() <= 3;
        } else {
            return neighbours.size() == 3;
        }

    }


    /**
     * Given a cell find the neighbours abound it.
     */
    public synchronized List<String> getNeighbours(int xCoordinate, int yCoordinate) {
        List<String> neighbours = new ArrayList<>();

        int neighbourX1 = xCoordinate - 1;
        int neighbourX2 = xCoordinate + 1;

        int neighbourY1 = yCoordinate - 1;
        int neighbourY2 = yCoordinate + 1;

        if (neighbourX1 >= 0 && neighbourY1 >= 0) {
            neighbours.add(neighbourX1 + "-" + neighbourY1);
        }
        if (neighbourX1 >= 0) {
            neighbours.add(neighbourX1 + "-" + yCoordinate);
        }
        if (neighbourY1 >= 0) {
            neighbours.add(xCoordinate + "-" + neighbourY1);
        }
        if (neighbourX2 < NUM_OF_ROWS_X && neighbourY2 < NUM_OF_ROWS_Y) {
            neighbours.add(neighbourX2 + "-" + neighbourY2);
        }

        if (neighbourX2 < NUM_OF_ROWS_X) {
            neighbours.add(neighbourX2 + "-" + yCoordinate);
        }
        if (neighbourY2 < NUM_OF_ROWS_Y) {
            neighbours.add(xCoordinate + "-" + neighbourY2);
        }

        if (neighbourX1 >= 0 && neighbourY2 < NUM_OF_ROWS_Y) {
            neighbours.add(neighbourX1 + "-" + neighbourY2);
        }
        if (neighbourX2 < NUM_OF_ROWS_X && neighbourY1 >= 0) {
            neighbours.add(neighbourX2 + "-" + neighbourY1);
        }

        return neighbours;
    }


}
