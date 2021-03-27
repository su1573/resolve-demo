package com.tj.resolvedemo.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

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
public class Gzb {

    /** 主键id. */
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String serialNo; //序号

    private Date FDate;

    private String FKmbm;
    private String FKmmc;
    private Double FHqjg;
    private String FHqbz;
    private Double FZqsl;
    private Double FZqcb;
    private Double FZqsz;
    private Double FGz_zz;
    private String FCb_Jz_bl;
    private String FSz_Jz_bl;
    private String FTpxx;
    private String FQyxx;
    private Double FBJSJ;
    private Double FSJSJ;
    private Double FBJSJ_SZ;
    private Double FSJSJ_SZ;
    private Double FBJSJ_GZZZ;
    private Double FSJSJ_GZZZ;
    private Double FCurJyFy;

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

    public Date getFDate() {
        return FDate;
    }

    public void setFDate(Date FDate) {
        this.FDate = FDate;
    }

    public String getFKmbm() {
        return FKmbm;
    }

    public void setFKmbm(String FKmbm) {
        this.FKmbm = FKmbm;
    }

    public String getFKmmc() {
        return FKmmc;
    }

    public void setFKmmc(String FKmmc) {
        this.FKmmc = FKmmc;
    }

    public Double getFHqjg() {
        return FHqjg;
    }

    public void setFHqjg(Double FHqjg) {
        this.FHqjg = FHqjg;
    }

    public String getFHqbz() {
        return FHqbz;
    }

    public void setFHqbz(String FHqbz) {
        this.FHqbz = FHqbz;
    }

    public Double getFZqsl() {
        return FZqsl;
    }

    public void setFZqsl(Double FZqsl) {
        this.FZqsl = FZqsl;
    }

    public Double getFZqcb() {
        return FZqcb;
    }

    public void setFZqcb(Double FZqcb) {
        this.FZqcb = FZqcb;
    }

    public Double getFZqsz() {
        return FZqsz;
    }

    public void setFZqsz(Double FZqsz) {
        this.FZqsz = FZqsz;
    }

    public Double getFGz_zz() {
        return FGz_zz;
    }

    public void setFGz_zz(Double FGz_zz) {
        this.FGz_zz = FGz_zz;
    }

    public String getFCb_Jz_bl() {
        return FCb_Jz_bl;
    }

    public void setFCb_Jz_bl(String FCb_Jz_bl) {
        this.FCb_Jz_bl = FCb_Jz_bl;
    }

    public String getFSz_Jz_bl() {
        return FSz_Jz_bl;
    }

    public void setFSz_Jz_bl(String FSz_Jz_bl) {
        this.FSz_Jz_bl = FSz_Jz_bl;
    }

    public String getFTpxx() {
        return FTpxx;
    }

    public void setFTpxx(String FTpxx) {
        this.FTpxx = FTpxx;
    }

    public String getFQyxx() {
        return FQyxx;
    }

    public void setFQyxx(String FQyxx) {
        this.FQyxx = FQyxx;
    }

    public Double getFBJSJ() {
        return FBJSJ;
    }

    public void setFBJSJ(Double FBJSJ) {
        this.FBJSJ = FBJSJ;
    }

    public Double getFSJSJ() {
        return FSJSJ;
    }

    public void setFSJSJ(Double FSJSJ) {
        this.FSJSJ = FSJSJ;
    }

    public Double getFBJSJ_SZ() {
        return FBJSJ_SZ;
    }

    public void setFBJSJ_SZ(Double FBJSJ_SZ) {
        this.FBJSJ_SZ = FBJSJ_SZ;
    }

    public Double getFSJSJ_SZ() {
        return FSJSJ_SZ;
    }

    public void setFSJSJ_SZ(Double FSJSJ_SZ) {
        this.FSJSJ_SZ = FSJSJ_SZ;
    }

    public Double getFBJSJ_GZZZ() {
        return FBJSJ_GZZZ;
    }

    public void setFBJSJ_GZZZ(Double FBJSJ_GZZZ) {
        this.FBJSJ_GZZZ = FBJSJ_GZZZ;
    }

    public Double getFSJSJ_GZZZ() {
        return FSJSJ_GZZZ;
    }

    public void setFSJSJ_GZZZ(Double FSJSJ_GZZZ) {
        this.FSJSJ_GZZZ = FSJSJ_GZZZ;
    }

    public Double getFCurJyFy() {
        return FCurJyFy;
    }

    public void setFCurJyFy(Double FCurJyFy) {
        this.FCurJyFy = FCurJyFy;
    }

    @Override
    public String toString() {
        return "Gzb{" +
                "id=" + id +
                ", serialNo='" + serialNo + '\'' +
                ", FDate=" + FDate +
                ", FKmbm='" + FKmbm + '\'' +
                ", FKmmc='" + FKmmc + '\'' +
                ", FHqjg='" + FHqjg + '\'' +
                ", FHqbz='" + FHqbz + '\'' +
                ", FZqsl='" + FZqsl + '\'' +
                ", FZqcb='" + FZqcb + '\'' +
                ", FZqsz='" + FZqsz + '\'' +
                ", FGz_zz='" + FGz_zz + '\'' +
                ", FCb_Jz_bl='" + FCb_Jz_bl + '\'' +
                ", FSz_Jz_bl='" + FSz_Jz_bl + '\'' +
                ", FTpxx='" + FTpxx + '\'' +
                ", FQyxx='" + FQyxx + '\'' +
                ", FBJSJ='" + FBJSJ + '\'' +
                ", FSJSJ='" + FSJSJ + '\'' +
                ", FBJSJ_SZ='" + FBJSJ_SZ + '\'' +
                ", FSJSJ_SZ='" + FSJSJ_SZ + '\'' +
                ", FBJSJ_GZZZ='" + FBJSJ_GZZZ + '\'' +
                ", FSJSJ_GZZZ='" + FSJSJ_GZZZ + '\'' +
                ", FCurJyFy='" + FCurJyFy + '\'' +
                '}';
    }
}
