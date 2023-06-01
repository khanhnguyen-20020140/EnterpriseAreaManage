/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgis.khukt.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nguyen Quoc Khanh
 */
@Entity
@Table(name = "tbl_duan_xaydunghatang")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblDuanXaydunghatang.findAll", query = "SELECT t FROM TblDuanXaydunghatang t"),
    @NamedQuery(name = "TblDuanXaydunghatang.findById", query = "SELECT t FROM TblDuanXaydunghatang t WHERE t.id = :id"),
    @NamedQuery(name = "TblDuanXaydunghatang.findByTen", query = "SELECT t FROM TblDuanXaydunghatang t WHERE t.ten = :ten"),
    @NamedQuery(name = "TblDuanXaydunghatang.findBySoqdCapphep", query = "SELECT t FROM TblDuanXaydunghatang t WHERE t.soqdCapphep = :soqdCapphep"),
    @NamedQuery(name = "TblDuanXaydunghatang.findByVondautuDangky", query = "SELECT t FROM TblDuanXaydunghatang t WHERE t.vondautuDangky = :vondautuDangky"),
    @NamedQuery(name = "TblDuanXaydunghatang.findByMasoDuan", query = "SELECT t FROM TblDuanXaydunghatang t WHERE t.masoDuan = :masoDuan"),
    @NamedQuery(name = "TblDuanXaydunghatang.findByNgayQuyetdinhCapphep", query = "SELECT t FROM TblDuanXaydunghatang t WHERE t.ngayQuyetdinhCapphep = :ngayQuyetdinhCapphep"),
    @NamedQuery(name = "TblDuanXaydunghatang.findByVontuongduongUsd", query = "SELECT t FROM TblDuanXaydunghatang t WHERE t.vontuongduongUsd = :vontuongduongUsd"),
    @NamedQuery(name = "TblDuanXaydunghatang.findByQuymoCongsuat", query = "SELECT t FROM TblDuanXaydunghatang t WHERE t.quymoCongsuat = :quymoCongsuat"),
    @NamedQuery(name = "TblDuanXaydunghatang.findByNgayHetphepHoatdong", query = "SELECT t FROM TblDuanXaydunghatang t WHERE t.ngayHetphepHoatdong = :ngayHetphepHoatdong"),
    @NamedQuery(name = "TblDuanXaydunghatang.findByIdLoaihinhDuan", query = "SELECT t FROM TblDuanXaydunghatang t WHERE t.idLoaihinhDuan = :idLoaihinhDuan"),
    @NamedQuery(name = "TblDuanXaydunghatang.findByTongmucDautu", query = "SELECT t FROM TblDuanXaydunghatang t WHERE t.tongmucDautu = :tongmucDautu")})
public class TblDuanXaydunghatang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 200)
    @Column(name = "ten")
    private String ten;
    @Size(max = 100)
    @Column(name = "soqd_capphep")
    private String soqdCapphep;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "vondautu_dangky")
    private BigDecimal vondautuDangky;
    @Size(max = 2147483647)
    @Column(name = "maso_duan")
    private String masoDuan;
    @Column(name = "ngay_quyetdinh_capphep")
    @Temporal(TemporalType.DATE)
    private Date ngayQuyetdinhCapphep;
    @Column(name = "vontuongduong_usd")
    private BigDecimal vontuongduongUsd;
    @Size(max = 2147483647)
    @Column(name = "quymo_congsuat")
    private String quymoCongsuat;
    @Column(name = "ngay_hetphep_hoatdong")
    @Temporal(TemporalType.DATE)
    private Date ngayHetphepHoatdong;
    @JoinColumn(name = "id_loaihinh_duan", referencedColumnName = "id")
    @ManyToOne
    private TblDmLoaihinhDuan idLoaihinhDuan;
    @Column(name = "tongmuc_dautu")
    private BigDecimal tongmucDautu;    
    @JoinColumn(name = "id_trangthai_duan", referencedColumnName = "id")
    @ManyToOne
    private TblDmTrangthai idTrangthaiDuan;
    @JoinColumn(name = "id_doanhnghiep", referencedColumnName = "id")
    @ManyToOne
    private TblDoanhnghiep idDoanhnghiep;
    @JoinColumn(name = "id_khu_chuyennganh", referencedColumnName = "id")
    @ManyToOne
    private TblKhuChuyennganh idKhuChuyennganh;
    @JoinColumn(name = "id_nguonvon", referencedColumnName = "id")
    @ManyToOne
    private TblNguonvonDautu idNguonvon;

    public TblDuanXaydunghatang() {
    }

    public TblDuanXaydunghatang(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSoqdCapphep() {
        return soqdCapphep;
    }

    public void setSoqdCapphep(String soqdCapphep) {
        this.soqdCapphep = soqdCapphep;
    }

    public BigDecimal getVondautuDangky() {
        return vondautuDangky;
    }

    public void setVondautuDangky(BigDecimal vondautuDangky) {
        this.vondautuDangky = vondautuDangky;
    }

    public String getMasoDuan() {
        return masoDuan;
    }

    public void setMasoDuan(String masoDuan) {
        this.masoDuan = masoDuan;
    }

    public Date getNgayQuyetdinhCapphep() {
        return ngayQuyetdinhCapphep;
    }

    public void setNgayQuyetdinhCapphep(Date ngayQuyetdinhCapphep) {
        this.ngayQuyetdinhCapphep = ngayQuyetdinhCapphep;
    }

    public BigDecimal getVontuongduongUsd() {
        return vontuongduongUsd;
    }

    public void setVontuongduongUsd(BigDecimal vontuongduongUsd) {
        this.vontuongduongUsd = vontuongduongUsd;
    }

    public String getQuymoCongsuat() {
        return quymoCongsuat;
    }

    public void setQuymoCongsuat(String quymoCongsuat) {
        this.quymoCongsuat = quymoCongsuat;
    }

    public Date getNgayHetphepHoatdong() {
        return ngayHetphepHoatdong;
    }

    public void setNgayHetphepHoatdong(Date ngayHetphepHoatdong) {
        this.ngayHetphepHoatdong = ngayHetphepHoatdong;
    }

    public TblDmLoaihinhDuan getIdLoaihinhDuan() {
        return idLoaihinhDuan;
    }

    public void setIdLoaihinhDuan(TblDmLoaihinhDuan idLoaihinhDuan) {
        this.idLoaihinhDuan = idLoaihinhDuan;
    }

    public BigDecimal getTongmucDautu() {
        return tongmucDautu;
    }

    public void setTongmucDautu(BigDecimal tongmucDautu) {
        this.tongmucDautu = tongmucDautu;
    }

    
    public TblDmTrangthai getIdTrangthaiDuan() {
        return idTrangthaiDuan;
    }

    public void setIdTrangthaiDuan(TblDmTrangthai idTrangthaiDuan) {
        this.idTrangthaiDuan = idTrangthaiDuan;
    }

    public TblDoanhnghiep getIdDoanhnghiep() {
        return idDoanhnghiep;
    }

    public void setIdDoanhnghiep(TblDoanhnghiep idDoanhnghiep) {
        this.idDoanhnghiep = idDoanhnghiep;
    }

    public TblKhuChuyennganh getIdKhuChuyennganh() {
        return idKhuChuyennganh;
    }

    public void setIdKhuChuyennganh(TblKhuChuyennganh idKhuChuyennganh) {
        this.idKhuChuyennganh = idKhuChuyennganh;
    }

    public TblNguonvonDautu getIdNguonvon() {
        return idNguonvon;
    }

    public void setIdNguonvon(TblNguonvonDautu idNguonvon) {
        this.idNguonvon = idNguonvon;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblDuanXaydunghatang)) {
            return false;
        }
        TblDuanXaydunghatang other = (TblDuanXaydunghatang) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sgis.mavenproject1.TblDuanXaydunghatang[ id=" + id + " ]";
    }
    
}
