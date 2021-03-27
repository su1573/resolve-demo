package com.tj.resolvedemo.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * @program: resolve-demo
 * @Date: 2021/3/18 13:46
 * @Author: Mr.SU
 * @Description:
 */
public class JxlTableEntity extends BaseRowModel {

    /** 主键id. */
    private Integer id;

    private String serialNo; //序号

    @ExcelProperty(index = 0)
    private String cloum0;
    @ExcelProperty(index = 1)
    private String cloum1;
    @ExcelProperty(index = 2)
    private String cloum2;
    @ExcelProperty(index = 3)
    private String cloum3;
    @ExcelProperty(index = 4)
    private String cloum4;
    @ExcelProperty(index = 5)
    private String cloum5;
    @ExcelProperty(index = 6)
    private String cloum6;
    @ExcelProperty(index = 7)
    private String cloum7;
    @ExcelProperty(index = 8)
    private String cloum8;
    @ExcelProperty(index = 9)
    private String cloum9;
    @ExcelProperty(index = 10)
    private String cloum10;
    @ExcelProperty(index = 11)
    private String cloum11;

    //日期类型只支持Date，不支持LocalDate和LocalDateTime   @Date

    @Override
    public String toString() {
        return "JxlTableEntity{" +
                "id=" + id +
                ", serialNo='" + serialNo + '\'' +
                ", cloum0='" + cloum0 + '\'' +
                ", cloum1='" + cloum1 + '\'' +
                ", cloum2='" + cloum2 + '\'' +
                ", cloum3='" + cloum3 + '\'' +
                ", cloum4='" + cloum4 + '\'' +
                ", cloum5='" + cloum5 + '\'' +
                ", cloum6='" + cloum6 + '\'' +
                ", cloum7='" + cloum7 + '\'' +
                ", cloum8='" + cloum8 + '\'' +
                ", cloum9='" + cloum9 + '\'' +
                ", cloum10='" + cloum10 + '\'' +
                ", cloum11='" + cloum11 + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getCloum0() {
        return cloum0;
    }

    public void setCloum0(String cloum0) {
        this.cloum0 = cloum0;
    }

    public String getCloum1() {
        return cloum1;
    }

    public void setCloum1(String cloum1) {
        this.cloum1 = cloum1;
    }

    public String getCloum2() {
        return cloum2;
    }

    public void setCloum2(String cloum2) {
        this.cloum2 = cloum2;
    }

    public String getCloum3() {
        return cloum3;
    }

    public void setCloum3(String cloum3) {
        this.cloum3 = cloum3;
    }

    public String getCloum4() {
        return cloum4;
    }

    public void setCloum4(String cloum4) {
        this.cloum4 = cloum4;
    }

    public String getCloum5() {
        return cloum5;
    }

    public void setCloum5(String cloum5) {
        this.cloum5 = cloum5;
    }

    public String getCloum6() {
        return cloum6;
    }

    public void setCloum6(String cloum6) {
        this.cloum6 = cloum6;
    }

    public String getCloum7() {
        return cloum7;
    }

    public void setCloum7(String cloum7) {
        this.cloum7 = cloum7;
    }

    public String getCloum8() {
        return cloum8;
    }

    public void setCloum8(String cloum8) {
        this.cloum8 = cloum8;
    }

    public String getCloum9() {
        return cloum9;
    }

    public void setCloum9(String cloum9) {
        this.cloum9 = cloum9;
    }

    public String getCloum10() {
        return cloum10;
    }

    public void setCloum10(String cloum10) {
        this.cloum10 = cloum10;
    }

    public String getCloum11() {
        return cloum11;
    }

    public void setCloum11(String cloum11) {
        this.cloum11 = cloum11;
    }
}
