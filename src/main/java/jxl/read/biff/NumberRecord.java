package jxl.read.biff;

/**
 * @program: resolve-demo
 * @Date: 2021/3/23 16:45
 * @Author: Mr.SU
 * @Description: 覆盖jar包中的类NumberRecord
 */
import java.text.DecimalFormat;
import java.text.NumberFormat;
import jxl.CellType;
import jxl.NumberCell;
import jxl.biff.DoubleHelper;
import jxl.biff.FormattingRecords;
import jxl.common.Logger;
import jxl.read.biff.CellValue;
import jxl.read.biff.Record;
import jxl.read.biff.SheetImpl;

class NumberRecord extends CellValue implements NumberCell {
    private static Logger logger = Logger.getLogger(NumberRecord.class);
    private double value;
    private NumberFormat format;
    private static DecimalFormat defaultFormat = new DecimalFormat("#.#########");

    public NumberRecord(Record t, FormattingRecords fr, SheetImpl si) {
        super(t, fr, si);
        byte[] data = this.getRecord().getData();
        this.value = DoubleHelper.getIEEEDouble(data, 6);
        this.format = fr.getNumberFormat(this.getXFIndex());
        if (this.format == null) {
            this.format = defaultFormat;
        }

    }

    public double getValue() {
        return this.value;
    }

    public String getContents() {
        return this.defaultFormat.format(this.value);
    }

    public CellType getType() {
        return CellType.NUMBER;
    }

    public NumberFormat getNumberFormat() {
        return this.format;
    }
}
