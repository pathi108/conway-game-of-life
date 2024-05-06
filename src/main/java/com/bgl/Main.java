package com.bgl;

import com.bgl.engine.model.Cell;
import com.bgl.util.InputReader;
import com.bgl.util.StateFormatException;

import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            List<Cell> cells = InputReader.readInitialState(args);
            Game game = new Game(cells);
            List<List<Cell>> next100States = game.playGame(100);
            next100States.forEach(Main::printState);
        } catch (IOException e) {
            System.out.println("Cannot find file provided " + args[0]);
        } catch (NumberFormatException | StateFormatException e) {
            System.out.println("Cannot parse csv");
            System.out.println(e.getMessage());
        }

    }



    public static void printState(List<Cell> changedSates){

        StringBuilder nextState = new StringBuilder("[");

        for(Cell changedSate: changedSates ){
            nextState.append(changedSate.toString()).append(",");
        }
        if(changedSates.size()!=0) {
            nextState.deleteCharAt(nextState.length() - 1);
        }

        nextState.append("]");
        System.out.println(nextState.toString());
    }
}
