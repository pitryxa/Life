package pitryxa.service;

import pitryxa.model.cell.AbsoluteCell;
import pitryxa.model.cell.CellVersion2;
import pitryxa.model.cell.mapper.CellMapper;
import pitryxa.repository.CellRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CellService {

    private final CellRepository cellRepository = new CellRepository();
    private final CellMapper mapper = new CellMapper();

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

    public Set<CellVersion2> getCells() {
        return cellRepository.getCells();
    }

    public Set<AbsoluteCell> getAbsoluteCells() {
        return cellRepository.getCells()
                .parallelStream()
                .map(mapper::toAbsoluteCell)
                .collect(Collectors.toSet());
    }

}
