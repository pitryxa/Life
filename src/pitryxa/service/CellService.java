package pitryxa.service;

import pitryxa.model.CellVersion2;
import pitryxa.model.CoordinateSystem;
import pitryxa.repository.CellRepository;

import java.util.List;

public class CellService {

    private final CoordinateSystem coordinateSystem = new CoordinateSystem();
    private final CellRepository cellRepository = new CellRepository();

    public CellService() {
        startInitCells();
    }

    private void startInitCells() {
        List.of(
                new CellVersion2(0, 0)
                , new CellVersion2(-1, 0)
                , new CellVersion2(0, 1)
                , new CellVersion2(-1, 2)
                , new CellVersion2(-2, 0)
        ).parallelStream().forEach(cellRepository::addCell);
    }

    public void calculateNextGeneration() {

    }
}
