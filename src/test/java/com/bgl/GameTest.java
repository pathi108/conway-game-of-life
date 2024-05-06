package com.bgl;

import com.bgl.engine.GameEngine;
import com.bgl.engine.model.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {








    @Test
    public void testPayGame() {

        GameEngine gameEngine = Mockito.spy(GameEngine.getInstance());
        List<Cell> cells = new ArrayList<>();

        cells.add(new Cell(5, 5));
        cells.add(new Cell(6, 5));
        cells.add(new Cell(7, 5));
        cells.add(new Cell(5, 6));
        cells.add(new Cell(6, 6));
        cells.add(new Cell(7, 6));

        Map<String, Cell> aliveCellsById = cells.stream().collect(Collectors.toMap(Cell::getId, Function.identity()));

        Game game = new Game(cells);


        Mockito.when(gameEngine.getNextState(aliveCellsById)).thenReturn(cells);
        List<List<Cell>> nextStates= game.playGame(1);

        List<String> answersAsString = nextStates.get(0)
                .stream()
                .map(Cell::toString)
                .collect(Collectors.toList());
        assertThat(nextStates).isNotEmpty();
        assertThat(answersAsString).hasSameElementsAs(Arrays.asList("[5,5]", "[5,6]", "[6,4]", "[6,7]", "[7,5]", "[7,6]"));
    }

}
