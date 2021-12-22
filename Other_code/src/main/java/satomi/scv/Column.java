package satomi.scv;

import java.util.ArrayList;
import java.util.List;

public class Column {
    private final String header;
    private final List<String> cells = new ArrayList<>();
    
    public Column(String header) {this.header = header;}
    
    public void addCell(String cell) {
        cells.add(cell);
    }
    public String getCell(int cellIndex) {
        return cells.get(cellIndex);
    }
    
    
    public String getHeader() {
        return header;
    }
    
    public List<String> getCells() {
        return cells;
    }
}
