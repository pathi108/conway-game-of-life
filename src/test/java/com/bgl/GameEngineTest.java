package com.bgl;

import com.bgl.engine.GameEngine;
import com.bgl.engine.model.Cell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class GameEngineTest {

    Map<String, Cell> aliveCellsById = new HashMap<>();
    Cell cell55 = new Cell(5, 5);

    @BeforeEach
    public void init() {


        Cell cell65 = new Cell(6, 5);
        Cell cell75 = new Cell(7, 5);
        Cell cell56 = new Cell(5, 6);
        Cell cell66 = new Cell(6, 6);
        Cell cell76 = new Cell(7, 6);


        aliveCellsById.put(cell55.getId(), cell55);
        aliveCellsById.put(cell65.getId(), cell65);
        aliveCellsById.put(cell75.getId(), cell75);
        aliveCellsById.put(cell56.getId(), cell56);
        aliveCellsById.put(cell66.getId(), cell66);
        aliveCellsById.put(cell76.getId(), cell76);


    }


    @Test
    public void testGetNextState() {

        List<String> nextSate = new ArrayList<>();
        nextSate.add("[5,5]");
        nextSate.add("[5,6]");
        nextSate.add("[6,4]");
        nextSate.add("[6,7]");
        nextSate.add("[7,5]");
        nextSate.add("[7,6]");

        List<Cell> answer = GameEngine.getInstance().getNextState(aliveCellsById);
        List<String> answersAsString = answer
                .stream()
                .map(Cell::toString)
                .collect(Collectors.toList());
        assertThat(answersAsString).hasSameElementsAs(answersAsString);
    }

    @Test
    public void testGetNextStateOneElement() {

        Map<String, Cell> singleElement = new HashMap<>();
        singleElement.put(cell55.getId(), cell55);
        List<Cell> answer = GameEngine.getInstance().getNextState(singleElement);
        assertThat(answer).isEmpty();
    }

    @Test
    public void testGetNextStateEmptyList() {

        Map<String, Cell> singleElement = new HashMap<>();
        List<Cell> answer = GameEngine.getInstance().getNextState(singleElement);
        assertThat(answer).isEmpty();
    }

    @Test
    public void testGetNeighbours() {
        List<String> neighbours = GameEngine.getInstance().getNeighbours(5,5);
        Assertions.assertEquals(neighbours.size(),8);
        assertThat(neighbours).hasSameElementsAs(Arrays.asList("4-4", "4-5", "5-4", "6-6", "6-5", "5-6", "4-6", "6-4"));
    }

    @Test
    public void testGetNeighboursLeftEdge() {
        List<String> neighbours = GameEngine.getInstance().getNeighbours(0,0);
        Assertions.assertEquals(neighbours.size(),3);
        assertThat(neighbours).hasSameElementsAs(Arrays.asList("1-1", "1-0", "0-1"));
    }

    @Test
    public void testGetNeighboursRightEdge() {
        List<String> neighbours = GameEngine.getInstance().getNeighbours(200,0);
        Assertions.assertEquals(neighbours.size(),3);
        assertThat(neighbours).hasSameElementsAs(Arrays.asList("199-0", "200-1", "199-1"));
    }

    @Test
    public void testGetNeighboursLeftEdgeLower() {
        List<String> neighbours = GameEngine.getInstance().getNeighbours(0,200);
        Assertions.assertEquals(neighbours.size(),3);
        assertThat(neighbours).hasSameElementsAs(Arrays.asList("0-199", "1-200", "1-199"));
    }


    @Test
    public void testGetNeighboursRightEdgeLower() {
        List<String> neighbours = GameEngine.getInstance().getNeighbours(200,200);
        Assertions.assertEquals(neighbours.size(),3);
        assertThat(neighbours).hasSameElementsAs(Arrays.asList("199-199", "199-200", "200-199"));
    }

    @Test
    public void testGetNeighboursUpperMiddle() {
        List<String> neighbours = GameEngine.getInstance().getNeighbours(0,100);
        Assertions.assertEquals(neighbours.size(),5);
        assertThat(neighbours).hasSameElementsAs(Arrays.asList("0-99", "1-101", "1-100", "0-101", "1-99"));
    }

    @Test
    public void testGetNeighboursLowerMiddle() {
        List<String> neighbours = GameEngine.getInstance().getNeighbours(200,100);
        Assertions.assertEquals(neighbours.size(),5);
        assertThat(neighbours).hasSameElementsAs(Arrays.asList("199-99", "199-100", "200-99", "200-101", "199-101"));
    }

    @Test
    public void testGetNeighboursLeftMiddle() {
        List<String> neighbours = GameEngine.getInstance().getNeighbours(0,100);
        Assertions.assertEquals(neighbours.size(),5);
        assertThat(neighbours).hasSameElementsAs(Arrays.asList("0-99", "1-101", "1-100", "0-101", "1-99"));
    }

    @Test
    public void testGetNeighboursRightMiddle() {
        List<String> neighbours = GameEngine.getInstance().getNeighbours(100,200);
        Assertions.assertEquals(neighbours.size(),5);
        assertThat(neighbours).hasSameElementsAs(Arrays.asList("99-199", "99-200", "100-199", "101-200", "101-199"));
    }

}
