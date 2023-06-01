/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgis.khukt.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nguyen Quoc Khanh
 */
@Entity
@Table(name = "tbl_ketqua_sanxuatkinhdoanh")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblKetquaSanxuatkinhdoanh.findAll", query = "SELECT t FROM TblKetquaSanxuatkinhdoanh t"),
    @NamedQuery(name = "TblKetquaSanxuatkinhdoanh.findByIdKetqua", query = "SELECT t FROM TblKetquaSanxuatkinhdoanh t WHERE t.idKetqua = :idKetqua"),
//    @NamedQuery(name = "TblKetquaSanxuatkinhdoanh.findByIdDuanSanxuatkinhdoanh", query = "SELECT t FROM TblKetquaSanxuatkinhdoanh t WHERE t.idDuanSanxuatkinhdoanh.idKhuChuyennganh.id = :idDuanSanxuatkinhdoanh"),
    @NamedQuery(name = "TblKetquaSanxuatkinhdoanh.findByDoanhthuTinhTudaunam", query = "SELECT t FROM TblKetquaSanxuatkinhdoanh t WHERE t.doanhthuTinhTudaunam = :doanhthuTinhTudaunam"),
    @NamedQuery(name = "TblKetquaSanxuatkinhdoanh.findByNopNgansachTinhTudaunam", query = "SELECT t FROM TblKetquaSanxuatkinhdoanh t WHERE t.nopNgansachTinhTudaunam = :nopNgansachTinhTudaunam"),
    @NamedQuery(name = "TblKetquaSanxuatkinhdoanh.findByTongsoNhancong", query = "SELECT t FROM TblKetquaSanxuatkinhdoanh t WHERE t.tongsoNhancong = :tongsoNhancong"),
    @NamedQuery(name = "TblKetquaSanxuatkinhdoanh.findByTongsoNhancongNu", query = "SELECT t FROM TblKetquaSanxuatkinhdoanh t WHERE t.tongsoNhancongNu = :tongsoNhancongNu"),
    @NamedQuery(name = "TblKetquaSanxuatkinhdoanh.findByTuoiTrungbinhNhancong", query = "SELECT t FROM TblKetquaSanxuatkinhdoanh t WHERE t.tuoiTrungbinhNhancong = :tuoiTrungbinhNhancong"),
    @NamedQuery(name = "TblKetquaSanxuatkinhdoanh.findByNhancongTrungcap", query = "SELECT t FROM TblKetquaSanxuatkinhdoanh t WHERE t.nhancongTrungcap = :nhancongTrungcap"),
    @NamedQuery(name = "TblKetquaSanxuatkinhdoanh.findByNhancongCaodang", query = "SELECT t FROM TblKetquaSanxuatkinhdoanh t WHERE t.nhancongCaodang = :nhancongCaodang"),
    @NamedQuery(name = "TblKetquaSanxuatkinhdoanh.findByNhancongDaihoc", query = "SELECT t FROM TblKetquaSanxuatkinhdoanh t WHERE t.nhancongDaihoc = :nhancongDaihoc"),
    @NamedQuery(name = "TblKetquaSanxuatkinhdoanh.findByNhancongTrendaihoc", query = "SELECT t FROM TblKetquaSanxuatkinhdoanh t WHERE t.nhancongTrendaihoc = :nhancongTrendaihoc"),

        @NamedQuery(name = "TblKetquaSanxuatkinhdoanh.findByLuongTrungbinh", query = "SELECT t FROM TblKetquaSanxuatkinhdoanh t WHERE t.luongTrungbinh = :luongTrungbinh")})
public class TblKetquaSanxuatkinhdoanh implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ketqua")
    private Integer idKetqua;
//    @Column(name = "id_duan_sanxuatkinhdoanh")
    @JoinColumn(name = "id_duan_sanxuatkinhdoanh",referencedColumnName = "id")
    @ManyToOne
    private TblDuanDautusanxuat idDuanSanxuatkinhdoanh;


@Column(name = "doanhthu_tinh_tudaunam")
    private BigDecimal doanhthuTinhTudaunam;
    @Column(name = "nop_ngansach_tinh_tudaunam")
    private BigDecimal nopNgansachTinhTudaunam;
    @Column(name = "tongso_nhancong")
    private Integer tongsoNhancong;

    @Column(name = "tongso_nhancong_nam")
    private Integer tongsoNhancongNam;
    @Column(name = "tongso_nhancong_nu")
    private Integer tongsoNhancongNu;
    @Column(name = "tuoi_trungbinh_nhancong")
    private Integer tuoiTrungbinhNhancong;
    @Column(name = "nhancong_trungcap")
    private Integer nhancongTrungcap;
    @Column(name = "nhancong_caodang")
    private Integer nhancongCaodang;
    @Column(name = "nhancong_daihoc")
    private Integer nhancongDaihoc;
    @Column(name = "nhancong_trendaihoc")
    private Integer nhancongTrendaihoc;
    @Column(name = "luong_trungbinh")
    private BigDecimal luongTrungbinh;

    @Column(name = "moc_baocao")
    @Temporal(TemporalType.DATE)
    private Date mocBaocao;


    public TblKetquaSanxuatkinhdoanh() {
    }

    public TblKetquaSanxuatkinhdoanh(Integer idKetqua) {
        this.idKetqua = idKetqua;
    }

    public Integer getIdKetqua() {
        return idKetqua;
    }

    public void setIdKetqua(Integer idKetqua) {
        this.idKetqua = idKetqua;
    }

    public TblDuanDautusanxuat getIdDuanSanxuatkinhdoanh() {
        return idDuanSanxuatkinhdoanh;
    }

    public void setIdDuanSanxuatkinhdoanh(TblDuanDautusanxuat idDuanSanxuatkinhdoanh) {
        this.idDuanSanxuatkinhdoanh = idDuanSanxuatkinhdoanh;
    }

    public BigDecimal getDoanhthuTinhTudaunam() {
        return doanhthuTinhTudaunam;
    }

    public void setDoanhthuTinhTudaunam(BigDecimal doanhthuTinhTudaunam) {
        this.doanhthuTinhTudaunam = doanhthuTinhTudaunam;
    }

    public BigDecimal getNopNgansachTinhTudaunam() {
        return nopNgansachTinhTudaunam;
    }

    public void setNopNgansachTinhTudaunam(BigDecimal nopNgansachTinhTudaunam) {
        this.nopNgansachTinhTudaunam = nopNgansachTinhTudaunam;
    }

    public Integer getTongsoNhancong() {
        return tongsoNhancong;
    }

    public void setTongsoNhancong(Integer tongsoNhancong) {
        this.tongsoNhancong = tongsoNhancong;
    }

    public Integer getTongsoNhancongNam() {
        return tongsoNhancongNam;
    }

    public void setTongsoNhancongNam(Integer tongsoNhancongNam) {
        this.tongsoNhancongNam = tongsoNhancongNam;
    }

    public Integer getTongsoNhancongNu() {
        return tongsoNhancongNu;
    }

    public void setTongsoNhancongNu(Integer tongsoNhancongNu) {
        this.tongsoNhancongNu = tongsoNhancongNu;
    }


    public Integer getTuoiTrungbinhNhancong() {
        return tuoiTrungbinhNhancong;
    }

    public void setTuoiTrungbinhNhancong(Integer tuoiTrungbinhNhancong) {
        this.tuoiTrungbinhNhancong = tuoiTrungbinhNhancong;
    }

    public Integer getNhancongTrungcap() {
        return nhancongTrungcap;
    }

    public void setNhancongTrungcap(Integer nhancongTrungcap) {
        this.nhancongTrungcap = nhancongTrungcap;
    }

    public Integer getNhancongCaodang() {
        return nhancongCaodang;
    }

    public void setNhancongCaodang(Integer nhancongCaodang) {
        this.nhancongCaodang = nhancongCaodang;
    }

    public Integer getNhancongDaihoc() {
        return nhancongDaihoc;
    }

    public void setNhancongDaihoc(Integer nhancongDaihoc) {
        this.nhancongDaihoc = nhancongDaihoc;
    }

    public Integer getNhancongTrendaihoc() {
        return nhancongTrendaihoc;
    }

    public void setNhancongTrendaihoc(Integer nhancongTrendaihoc) {
        this.nhancongTrendaihoc = nhancongTrendaihoc;
    }

    public BigDecimal getLuongTrungbinh() {
        return luongTrungbinh;
    }

    public void setLuongTrungbinh(BigDecimal luongTrungbinh) {
        this.luongTrungbinh = luongTrungbinh;
    }

    public Date getMocBaocao() {
        return mocBaocao;
    }

    public void setMocBaocao(Date mocBaocao) {
        this.mocBaocao = mocBaocao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKetqua != null ? idKetqua.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblKetquaSanxuatkinhdoanh)) {
            return false;
        }
        TblKetquaSanxuatkinhdoanh other = (TblKetquaSanxuatkinhdoanh) object;
        if ((this.idKetqua == null && other.idKetqua != null) || (this.idKetqua != null && !this.idKetqua.equals(other.idKetqua))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sgis.khukt.model.TblKetquaSanxuatkinhdoanh[ idKetqua=" + idKetqua + " ]";
    }
    
}
