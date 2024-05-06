package com.bgl.util;

import com.bgl.engine.model.Cell;
import com.bgl.engine.validate.InputValidator;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class InputReader {

    public static List<Cell> readInitialState(String... args) throws IOException, StateFormatException {
        Reader in = new FileReader(args[0]);

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setSkipHeaderRecord(true)
                .build();

        Iterable<CSVRecord> records = csvFormat.parse(in);
        List<Cell> cells = new ArrayList<>();
        for (CSVRecord record : records) {

            String xCoordinate = record.get(0).trim();
            String yCoordinate = record.get(1).trim();

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
        return cells;
    }
}
