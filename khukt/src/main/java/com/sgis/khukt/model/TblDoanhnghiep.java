/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgis.khukt.model;

import java.io.Serializable;
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
@Table(name = "tbl_doanhnghiep")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblDoanhnghiep.findAll", query = "SELECT t FROM TblDoanhnghiep t"),
    @NamedQuery(name = "TblDoanhnghiep.findById", query = "SELECT t FROM TblDoanhnghiep t WHERE t.id = :id"),
    @NamedQuery(name = "TblDoanhnghiep.findByTen", query = "SELECT t FROM TblDoanhnghiep t WHERE t.ten = :ten"),
    @NamedQuery(name = "TblDoanhnghiep.findByMaDangky", query = "SELECT t FROM TblDoanhnghiep t WHERE t.maDangky = :maDangky"),
    @NamedQuery(name = "TblDoanhnghiep.findByMasothue", query = "SELECT t FROM TblDoanhnghiep t WHERE t.masothue = :masothue"),
    @NamedQuery(name = "TblDoanhnghiep.findByDiachi", query = "SELECT t FROM TblDoanhnghiep t WHERE t.diachi = :diachi"),
    @NamedQuery(name = "TblDoanhnghiep.findByGiamdoc", query = "SELECT t FROM TblDoanhnghiep t WHERE t.giamdoc = :giamdoc"),
    @NamedQuery(name = "TblDoanhnghiep.findByLinhvucKinhdoanh", query = "SELECT t FROM TblDoanhnghiep t WHERE t.linhvucKinhdoanh = :linhvucKinhdoanh"),
    @NamedQuery(name = "TblDoanhnghiep.findByQuocgia", query = "SELECT t FROM TblDoanhnghiep t WHERE t.quocgia = :quocgia"),
    @NamedQuery(name = "TblDoanhnghiep.findByNgayThanhlap", query = "SELECT t FROM TblDoanhnghiep t WHERE t.ngayThanhlap = :ngayThanhlap")})
public class TblDoanhnghiep implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "ten")
    private String ten;
    @Size(max = 50)
    @Column(name = "ma_dangky")
    private String maDangky;
    @Size(max = 50)
    @Column(name = "masothue")
    private String masothue;
    @Size(max = 150)
    @Column(name = "diachi")
    private String diachi;
    @Size(max = 50)
    @Column(name = "giamdoc")
    private String giamdoc;
    @Size(max = 50)
    @Column(name = "quocgia")
    private String quocgia;
    @Size(max = 1000)
    @Column(name = "linhvuc_kinhdoanh")
    private String linhvucKinhdoanh;
    @Column(name = "ngay_thanhlap")
    @Temporal(TemporalType.DATE)
    private Date ngayThanhlap;    
    @JoinColumn(name = "id_linhvuc_sanxuatkinhdoanh", referencedColumnName = "id_linhvuc_sanxuatkinhdoanh")
    @ManyToOne
    private TblDmLinhvucSanxuatkinhdoanh idLinhvucSanxuatkinhdoanh;
    @JoinColumn(name = "id_loai_doanhnghiep", referencedColumnName = "id_loai_doanhnghiep")
    @ManyToOne
    private TblDmLoaiDoanhnghiep idLoaiDoanhnghiep;    

    public TblDoanhnghiep() {
    }

    public TblDoanhnghiep(Integer id) {
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

    public String getMaDangky() {
        return maDangky;
    }

    public void setMaDangky(String maDangky) {
        this.maDangky = maDangky;
    }

    public String getMasothue() {
        return masothue;
    }

    public void setMasothue(String masothue) {
        this.masothue = masothue;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
    
    public String getQuocgia() {
        return quocgia;
    }

    public void setQuocgia(String quocgia) {
        this.quocgia = quocgia;
    }

    public String getGiamdoc() {
        return giamdoc;
    }

    public void setGiamdoc(String giamdoc) {
        this.giamdoc = giamdoc;
    }

    public String getLinhvucKinhdoanh() {
        return linhvucKinhdoanh;
    }

    public void setLinhvucKinhdoanh(String linhvucKinhdoanh) {
        this.linhvucKinhdoanh = linhvucKinhdoanh;
    }

    public Date getNgayThanhlap() {
        return ngayThanhlap;
    }

    public void setNgayThanhlap(Date ngayThanhlap) {
        this.ngayThanhlap = ngayThanhlap;
    }

    public TblDmLinhvucSanxuatkinhdoanh getIdLinhvucSanxuatkinhdoanh() {
        return idLinhvucSanxuatkinhdoanh;
    }

    public void setIdLinhvucSanxuatkinhdoanh(TblDmLinhvucSanxuatkinhdoanh idLinhvucSanxuatkinhdoanh) {
        this.idLinhvucSanxuatkinhdoanh = idLinhvucSanxuatkinhdoanh;
    }

    public TblDmLoaiDoanhnghiep getIdLoaiDoanhnghiep() {
        return idLoaiDoanhnghiep;
    }

    public void setIdLoaiDoanhnghiep(TblDmLoaiDoanhnghiep idLoaiDoanhnghiep) {
        this.idLoaiDoanhnghiep = idLoaiDoanhnghiep;
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
        if (!(object instanceof TblDoanhnghiep)) {
            return false;
        }
        TblDoanhnghiep other = (TblDoanhnghiep) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sgis.khukt.model.TblDoanhnghiep[ id=" + id + " ]";
    }
    
}
