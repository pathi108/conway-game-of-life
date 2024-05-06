package com.bgl.engine.model;

import com.bgl.engine.GameEngine;

import java.util.ArrayList;
import java.util.List;

public class Cell {


    private final String id;
    private final int xCoordinate;
    private final int yCoordinate;
    private List<String> neighbours = new ArrayList<>();


    public Cell(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        id = xCoordinate+"-"+yCoordinate;
    }


    public String getId() {
        return id;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }


    public List<String> getNeighbours() {
        if(!neighbours.isEmpty()){
            return neighbours;
        }
        else {

            neighbours = GameEngine.getInstance().getNeighbours(xCoordinate,yCoordinate);
            return neighbours;

        }
    }

    public String toString(){
         return "["+xCoordinate+","+yCoordinate+"]";
    }
}
