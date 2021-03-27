package com.tj.resolvedemo.domain;

/**
 * @program: resolve-demo
 * @Date: 2021/3/18 13:46
 * @Author: Mr.SU
 * @Description:
 */
//@Entity
//@Proxy(lazy = false) //设置懒加载方式
//@DynamicUpdate   //自动更新
//@DynamicInsert
public class JxlTable {

    /** 主键id. */
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String serialNo; //序号

//    private String cloumZero;
//    private String cloumOne;
//    private String cloumTwo;
//    private String cloumThree;
//    private String cloumFour;
//    private String cloumFive;
//    private String cloumSix;
//    private String cloumSeven;
//    private String cloumEight;
//    private String cloumNine;
//    private String cloumTen;
//    private String cloumEleven;

    private String cloum0;
    private String cloum1;
    private String cloum2;
    private String cloum3;
    private String cloum4;
    private String cloum5;
    private String cloum6;
    private String cloum7;
    private String cloum8;
    private String cloum9;
    private String cloum10;
    private String cloum11;

    @Override
    public String toString() {
        return "JxlTable{" +
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
