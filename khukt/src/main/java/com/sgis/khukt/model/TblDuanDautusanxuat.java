/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgis.khukt.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
@Table(name = "tbl_duan_dautusanxuat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblDuanDautusanxuat.findAll", query = "SELECT t FROM TblDuanDautusanxuat t"),
    @NamedQuery(name = "TblDuanDautusanxuat.findById", query = "SELECT t FROM TblDuanDautusanxuat t WHERE t.id = :id"),
    @NamedQuery(name = "TblDuanDautusanxuat.findByTen", query = "SELECT t FROM TblDuanDautusanxuat t WHERE t.ten = :ten"),
    @NamedQuery(name = "TblDuanDautusanxuat.findBySoquyetdinhCapphep", query = "SELECT t FROM TblDuanDautusanxuat t WHERE t.soquyetdinhCapphep = :soquyetdinhCapphep"),
//    @NamedQuery(name = "TblDuanDautusanxuat.findBySoquyetdinhCapphep", query = "SELECT t FROM TblDuanDautusanxuat t WHERE t.soquyetdinhCapphep = :soquyetdinhCapphep"),


        @NamedQuery(name = "TblDuanDautusanxuat.findByIdKhuChuyennganh", query = "SELECT t FROM TblDuanDautusanxuat t WHERE t.idKhuChuyennganh = :idKhuChuyennganh"),
    @NamedQuery(name = "TblDuanDautusanxuat.findByNoidungSanxuatKinhdoanh", query = "SELECT t FROM TblDuanDautusanxuat t WHERE t.noidungSanxuatKinhdoanh = :noidungSanxuatKinhdoanh"),
    @NamedQuery(name = "TblDuanDautusanxuat.findByIdLinhvucSanxuatkinhdoanh", query = "SELECT t FROM TblDuanDautusanxuat t WHERE t.idLinhvucSanxuatkinhdoanh = :idLinhvucSanxuatkinhdoanh")})
public class TblDuanDautusanxuat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 200)
    @Column(name = "ten")
    private String ten;

    @JoinColumn(name = "id_doanhnghiep", referencedColumnName = "id")
    @ManyToOne
    private TblDoanhnghiep idDoanhnghiep;

    @Size(max = 2147483647)
    @Column(name = "soquyetdinh_capphep")
    private String soquyetdinhCapphep;

    @Size(max = 2147483647)
    @Column(name = "vondautu_dangky")
    private Double vondautuDangky;

    @JoinColumn(name = "id_nguonvon", referencedColumnName = "id")
    @ManyToOne
    private TblNguonvonDautu idNguonvon;

    @JoinColumn(name = "id_khu_chuyennganh", referencedColumnName = "id")
    @ManyToOne
    private TblKhuChuyennganh idKhuChuyennganh;

    @Size(max = 2147483647)
    @Column(name = "noidung_sanxuat_kinhdoanh")
    private String noidungSanxuatKinhdoanh;

    @JoinColumn(name = "id_linhvuc_sanxuatkinhdoanh", referencedColumnName = "id_linhvuc_sanxuatkinhdoanh")
    @ManyToOne
    private TblDmLinhvucSanxuatkinhdoanh idLinhvucSanxuatkinhdoanh;

    @Size(max = 2147483647)
    @Column(name = "maso_duan")
    private String masoDuan;

    @Column(name = "ngay_quyetdinh_capphep")
    @Temporal(TemporalType.DATE)
    private Date ngayQuyetdinhCapphep;

    @Size(max = 2147483647)
    @Column(name = "vontuongduong_usd")
    private Double vontuongduongUSD;

    @Column(name = "tongmuc_dautu")
    private BigDecimal tongmucDautu;


    @Size(max = 2147483647)
    @Column(name = "quymo_congsuat")
    private String quymoCongsuat;

    @Column(name = "ngay_hetphep_hoatdong")
    @Temporal(TemporalType.DATE)
    private Date ngayHetphepHoatdong;

    @JoinColumn(name = "id_trangthai_duan", referencedColumnName = "id")
    @ManyToOne
    private TblDmTrangthai idTrangthaiDuan;

    @JoinColumn(name = "id_loaihinh_duan", referencedColumnName = "id")
    @ManyToOne
    private TblDmLoaihinhDuan idLoaihinhDuan;
   

    public TblDuanDautusanxuat() {
    }

    public TblDuanDautusanxuat(Integer id) {
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

    public String getSoquyetdinhCapphep() {
        return soquyetdinhCapphep;
    }

    public void setSoquyetdinhCapphep(String soquyetdinhCapphep) {
        this.soquyetdinhCapphep = soquyetdinhCapphep;
    }

    
    public String getNoidungSanxuatKinhdoanh() {
        return noidungSanxuatKinhdoanh;
    }

    public void setNoidungSanxuatKinhdoanh(String noidungSanxuatKinhdoanh) {
        this.noidungSanxuatKinhdoanh = noidungSanxuatKinhdoanh;
    }

    public TblDmLinhvucSanxuatkinhdoanh getIdLinhvucSanxuatkinhdoanh() {
        return idLinhvucSanxuatkinhdoanh;
    }

    public void setIdLinhvucSanxuatkinhdoanh(TblDmLinhvucSanxuatkinhdoanh idLinhvucSanxuatkinhdoanh) {
        this.idLinhvucSanxuatkinhdoanh = idLinhvucSanxuatkinhdoanh;
    }

    public TblDoanhnghiep getIdDoanhnghiep() {
        return idDoanhnghiep;
    }

    public void setIdDoanhnghiep(TblDoanhnghiep idDoanhnghiep) {
        this.idDoanhnghiep = idDoanhnghiep;
    }

    public TblNguonvonDautu getIdNguonvon() {
        return idNguonvon;
    }

    public BigDecimal getTongmucDautu() {
        return tongmucDautu;
    }

    public void setTongmucDautu(BigDecimal tongmucDautu) {
        this.tongmucDautu = tongmucDautu;
    }

    public void setIdNguonvon(TblNguonvonDautu idNguonvon) {
        this.idNguonvon = idNguonvon;
    }

    public TblKhuChuyennganh getIdKhuChuyennganh() {
        return idKhuChuyennganh;
    }

    public void setIdKhuChuyennganh(TblKhuChuyennganh idKhuChuyennganh) {
        this.idKhuChuyennganh = idKhuChuyennganh;
    }

    public Double getVondautuDangky() {
        return vondautuDangky;
    }

    public void setVondautuDangky(Double vondautuDangky) {
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

    public TblDmTrangthai getIdTrangthaiDuan() {
        return idTrangthaiDuan;
    }

    public void setIdTrangthaiDuan(TblDmTrangthai idTrangthaiDuan) {
        this.idTrangthaiDuan = idTrangthaiDuan;
    }

    public TblDmLoaihinhDuan getIdLoaihinhDuan() {
        return idLoaihinhDuan;
    }

    public void setIdLoaihinhDuan(TblDmLoaihinhDuan idLoaihinhDuan) {
        this.idLoaihinhDuan = idLoaihinhDuan;
    }

    public Double getVontuongduongUSD() {
        return vontuongduongUSD;
    }

    public void setVontuongduongUSD(Double vontuongduongUSD) {
        this.vontuongduongUSD = vontuongduongUSD;
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
        if (!(object instanceof TblDuanDautusanxuat)) {
            return false;
        }
        TblDuanDautusanxuat other = (TblDuanDautusanxuat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sgis.khukt.model.TblDuanDautusanxuat[ id=" + id + " ]";
    }

}
