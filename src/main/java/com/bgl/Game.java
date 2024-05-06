package com.bgl;

import com.bgl.engine.GameEngine;
import com.bgl.engine.model.Cell;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Class used to play the game
 */
public class Game {

    private Map<String, Cell> aliveCellsById;


    public Game(List<Cell> aliveCells) {
        aliveCellsById = aliveCells.stream().collect(Collectors.toMap(Cell::getId, Function.identity()));

    }

    public List<List<Cell>> playGame(int numberOfRounds) {
        List<List<Cell>> newStates = new ArrayList<>();

        for (int i = 0; i < numberOfRounds; i++) {
            List<Cell> changedSates = GameEngine.getInstance()
                    .getNextState(aliveCellsById);
            newStates.add(changedSates);
            aliveCellsById = changedSates.stream()
                    .collect(Collectors.toMap(Cell::getId, Function.identity()));

        }
        return newStates;
    }



}
