package com.bgl.util;

import com.bgl.engine.model.Cell;
import com.bgl.engine.validate.InputValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InputReader {

    public static List<Cell> readInitialState(String... args) throws IOException, StateFormatException {
        List<Cell> cells = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] record = line.split(",");


                String xCoordinate = record[0].trim();
                String yCoordinate = record[1].trim();

                if (!InputValidator.isPositiveInteger(xCoordinate) || !InputValidator.isPositiveInteger(yCoordinate)) {
                    throw new StateFormatException("Entered number Should be a positive integer " + xCoordinate + "," + yCoordinate);
                }

                int x = Integer.parseInt(xCoordinate);
                int y = Integer.parseInt(yCoordinate);

                if (!InputValidator.isWithinTheGameLimit(x) || !InputValidator.isWithinTheGameLimit(y)) {
                    throw new StateFormatException("Entered number Should be a lower than 200 " + xCoordinate + "," + yCoordinate);
                }

                cells.add(new Cell(x, y));

            }
        }
        return cells;
    }
}
