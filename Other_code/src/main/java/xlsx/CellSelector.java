package xlsx;

public class CellSelector {
//    public enum CellSelectType { HEADER }
//
//    private Map<String, List<CellCoordinate>> headerGroups;
//
//    public static void main(String[] args) {
//        val obj = new CellSelector();
//
//        val rsl = obj.cellGroupsSelector(""
//                + "1 2 2 3 3 4 4 n "
//                + "1 a a a a a a n ")
//                .doForGroup("1", CellSelector::mergeCellGroup)
//                .doForGroup("2", CellSelector::mergeCellGroup)
//                .doForGroup("3", CellSelector::mergeCellGroup)
//                .doForGroup("4", CellSelector::mergeCellGroup);
////        obj.printMap(rsl);
//
//
//        val workbook = new XSSFWorkbook();
//        workbook.setMissingCellPolicy(Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//        val workSheet = workbook.createSheet("sheet 1");
////        val firstRow = workSheet.createRow(0);
//
//
////        val cell = firstRow.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
////        System.out.println("cell = " + cell.getClass().getSimpleName());
//
////        firstRow.getCell(0).setCellValue("aaa");
////        val cell = firstRow.getCell(0);
////        System.out.println("cell = " + workSheet.getRow(0));
//        System.out.println("row = " + workSheet.getRow(0));
//        System.out.println("row = " + workSheet.row(1));
//
//    }
//
//    public static void mergeCellGroup(List<CellCoordinate> cells) {
//        // TODO : impl
//    }
//
//    public CellSelector cellGroupsSelector(String patternSt) {
//        val cellGroupMap = new LinkedHashMap<String, List<CellCoordinate>>();
//
//        var rowIndex = 0;
//        var colIndex = 0;
//        for (val c : patternSt.split(" ")) {
//            if (c.isEmpty()) continue;
//
//            System.out.printf("c = \"%s\"\r\n", c);
//
//            if ("n".equals(c)) {
//                rowIndex++;
//                colIndex = 0;
//            } else {
//                var cellGroup = cellGroupMap.get(c);
//                if (cellGroup != null) cellGroup.add(new CellCoordinate(rowIndex, colIndex));
//                else {
//                    // Важно что бы этот List был Изменяемым.
//                    cellGroup = new ArrayList<>();
//                    cellGroup.add(new CellCoordinate(rowIndex, colIndex));
//                    cellGroupMap.put(c, cellGroup);
//                }
//                colIndex++;
//            }
//        }
//        this.headerGroups = cellGroupMap;
//        return this;
//    }
//
//    public CellSelector doForGroup(String groupName, Consumer<List<CellCoordinate>> doForGroupFunc) {
//        return this;
//    }
//
//
//    @Data
//    @RequiredArgsConstructor
//    class CellCoordinate {
//        private final int row;
//        private final int col;
//
//        @Override
//        public String toString() {
//            return "(" + row + ":" + col + ")";
//        }
//    }
//
//    private void printMap(Map<?, ?> map) {
//        for (val entry : map.entrySet()) {
//            System.out.println(entry.getKey() + ":" + entry.getValue().toString());
//        }
//    }
}
