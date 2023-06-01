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
@Table(name = "tbl_dm_nguoidung")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblDmNguoiDung.findAll", query = "SELECT t FROM TblDmNguoiDung t"),
    @NamedQuery(name = "TblDmNguoiDung.findById", query = "SELECT t FROM TblDmNguoiDung t WHERE t.id = :id"),
    @NamedQuery(name = "TblDmNguoiDung.findByTendangnhap", query = "SELECT t FROM TblDmNguoiDung t WHERE t.tendangnhap = :tendangnhap"),
    @NamedQuery(name = "TblDmNguoiDung.findByTennguoidung", query = "SELECT t FROM TblDmNguoiDung t WHERE t.tennguoidung = :tennguoidung"),
    @NamedQuery(name = "TblDmNguoiDung.findByMatkhau", query = "SELECT t FROM TblDmNguoiDung t WHERE t.matkhau = :matkhau"),
    @NamedQuery(name = "TblDmNguoiDung.findByQuyenxemthsx", query = "SELECT t FROM TblDmNguoiDung t WHERE t.quyen_xem_thsx = :quyen_xem_thsx"),
    @NamedQuery(name = "TblDmNguoiDung.findByQuyensoanthaothsx", query = "SELECT t FROM TblDmNguoiDung t WHERE t.quyen_soanthao_thsx = :quyen_soanthao_thsx")})
  
public class TblDmNguoiDung implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "tendangnhap")
    private String tendangnhap;
    @Size(max = 50)
    @Column(name = "tennguoidung")
    private String tennguoidung;
    @Size(max = 50)
    @Column(name = "matkhau")
    private String matkhau;
    @Size(max = 150)
    @Column(name = "quyen_xem_thsx")
    private String quyen_xem_thsx;
    @Size(max = 50)
    @Column(name = "quyen_soanthao_thsx")
    private String quyen_soanthao_thsx;
    @Size(max = 50)
    
   @JoinColumn(name = "id_khu_kinhte", referencedColumnName = "id")
   @ManyToOne
   private TblKhuKinhte idKhukinhte;
   
   @JoinColumn(name = "id_doanhnghiep", referencedColumnName = "id")
   @ManyToOne
   private TblDoanhnghiep idDoanhnghiep; 
   
   @JoinColumn(name = "id_duan_sanxuatkinhdoanh",referencedColumnName = "id")
   @ManyToOne
   private TblDuanDautusanxuat idDuanSanxuatkinhdoanh;    
    
    

    public TblDmNguoiDung() {
    }

    public TblKhuKinhte getIdKhuKinhte() {
        return idKhukinhte;
    }

    public void setIdKhuKinhte(TblKhuKinhte idKhukinhte) {
        this.idKhukinhte = idKhukinhte;
    }


    public TblDoanhnghiep getIdDoanhnghiep() {
        return idDoanhnghiep;
    }

    public void setIdDoanhnghiep(TblDoanhnghiep idDoanhnghiep) {
        this.idDoanhnghiep = idDoanhnghiep;
    }


    public TblDuanDautusanxuat getIdDuanSanxuatkinhdoanh() {
        return idDuanSanxuatkinhdoanh;
    }

    public void setIdDuanSanxuatkinhdoanh(TblDuanDautusanxuat idDuanSanxuatkinhdoanh) {
        this.idDuanSanxuatkinhdoanh = idDuanSanxuatkinhdoanh;
    }


    public TblDmNguoiDung(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getTennguoidung() {
        return tennguoidung;
    }

    public void setTennguoidung(String tennguoidung) {
        this.tennguoidung = tennguoidung;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getQuyenxemthsx() {
        return quyen_xem_thsx;
    }

    public void setQuyenxemthsx(String quyen_xem_thsx) {
        this.quyen_xem_thsx = quyen_xem_thsx;
    }
    
    public String getQuyensoanthaothsx() {
        return quyen_soanthao_thsx;
    }

    public void setQuyensoanthaothsx(String quyen_soanthao_thsx) {
        this.quyen_soanthao_thsx = quyen_soanthao_thsx;
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
        if (!(object instanceof TblDmNguoiDung)) {
            return false;
        }
        TblDmNguoiDung other = (TblDmNguoiDung) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sgis.khukt.model.TblDmNguoiDung[ id=" + id + " ]";
    }
    
}
