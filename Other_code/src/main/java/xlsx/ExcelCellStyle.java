package xlsx;

import lombok.Builder;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;

import java.awt.*;
import java.util.Calendar;
import java.util.Date;

@Builder
public class ExcelCellStyle {
    public static final ExcelCellStyle EMPTY = ExcelCellStyle.builder().build();

    private final XSSFCellStyle cellStyleInner;
    private final XSSFDataFormat dataFormatHelper;
    /**
     * 0 - будет Числовой формат (без знаков после запятой)
     * 0.00 - будет Числовой формат (2 знака после запятой)
     * <p>
     * Далее действительны только с: {@link Calendar}, {@link Date})
     * dd.MM.yy - будет Дата формат
     * HH:ss - будет Время формат
     * dd.MM.yy HH:ss - (другие форматы, работает так же, как Время или Дата)
     */
    private final String format;
    private final Color foregroundColor;
    private final FillPatternType fillPattern;
    private final HorizontalAlignment horizontalAlignment;


    public CellStyle terminate() {
        if (format != null) cellStyleInner.setDataFormat(dataFormatHelper.getFormat(format));
        if (foregroundColor != null) cellStyleInner.setFillForegroundColor(new XSSFColor(foregroundColor));
        if (fillPattern != null) cellStyleInner.setFillPattern(fillPattern);
        if (horizontalAlignment != null) cellStyleInner.setAlignment(horizontalAlignment);
        return cellStyleInner;
    }

}
