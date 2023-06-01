/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgis.khukt.model;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nguyen Quoc Khanh
 */
@Entity
@Table(name = "tbl_thongke_laodong")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblThongkeLaodong.findAll", query = "SELECT t FROM TblThongkeLaodong t"),
    @NamedQuery(name = "TblThongkeLaodong.findById", query = "SELECT t FROM TblThongkeLaodong t WHERE t.id = :id"),
    @NamedQuery(name = "TblThongkeLaodong.findByTongsoLaodong", query = "SELECT t FROM TblThongkeLaodong t WHERE t.tongsoLaodong = :tongsoLaodong"),
    @NamedQuery(name = "TblThongkeLaodong.findByNam", query = "SELECT t FROM TblThongkeLaodong t WHERE t.nam = :nam"),
    @NamedQuery(name = "TblThongkeLaodong.findByNu", query = "SELECT t FROM TblThongkeLaodong t WHERE t.nu = :nu"),
    @NamedQuery(name = "TblThongkeLaodong.findByLaodongTrungcap", query = "SELECT t FROM TblThongkeLaodong t WHERE t.laodongTrungcap = :laodongTrungcap"),
    @NamedQuery(name = "TblThongkeLaodong.findByLaodongCaodang", query = "SELECT t FROM TblThongkeLaodong t WHERE t.laodongCaodang = :laodongCaodang"),
    @NamedQuery(name = "TblThongkeLaodong.findByLaodongTudaihocTrolen", query = "SELECT t FROM TblThongkeLaodong t WHERE t.laodongTudaihocTrolen = :laodongTudaihocTrolen"),
    @NamedQuery(name = "TblThongkeLaodong.findByNgaybaocao", query = "SELECT t FROM TblThongkeLaodong t WHERE t.ngaybaocao = :ngaybaocao")})
public class TblThongkeLaodong implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "tongso_laodong")
    private Integer tongsoLaodong;
    @Column(name = "nam")
    private Integer nam;
    @Column(name = "nu")
    private Integer nu;
    @Column(name = "laodong_trungcap")
    private Integer laodongTrungcap;
    @Column(name = "laodong_caodang")
    private Integer laodongCaodang;
    @Column(name = "laodong_tudaihoc_trolen")
    private Integer laodongTudaihocTrolen;
    @Column(name = "ngaybaocao")
    private Integer ngaybaocao;
    @JoinColumn(name = "doanhnghiep_id", referencedColumnName = "id")
    @ManyToOne
    private TblDoanhnghiep doanhnghiepId;

    public TblThongkeLaodong() {
    }

    public TblThongkeLaodong(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTongsoLaodong() {
        return tongsoLaodong;
    }

    public void setTongsoLaodong(Integer tongsoLaodong) {
        this.tongsoLaodong = tongsoLaodong;
    }

    public Integer getNam() {
        return nam;
    }

    public void setNam(Integer nam) {
        this.nam = nam;
    }

    public Integer getNu() {
        return nu;
    }

    public void setNu(Integer nu) {
        this.nu = nu;
    }

    public Integer getLaodongTrungcap() {
        return laodongTrungcap;
    }

    public void setLaodongTrungcap(Integer laodongTrungcap) {
        this.laodongTrungcap = laodongTrungcap;
    }

    public Integer getLaodongCaodang() {
        return laodongCaodang;
    }

    public void setLaodongCaodang(Integer laodongCaodang) {
        this.laodongCaodang = laodongCaodang;
    }

    public Integer getLaodongTudaihocTrolen() {
        return laodongTudaihocTrolen;
    }

    public void setLaodongTudaihocTrolen(Integer laodongTudaihocTrolen) {
        this.laodongTudaihocTrolen = laodongTudaihocTrolen;
    }

    public Integer getNgaybaocao() {
        return ngaybaocao;
    }

    public void setNgaybaocao(Integer ngaybaocao) {
        this.ngaybaocao = ngaybaocao;
    }

    public TblDoanhnghiep getDoanhnghiepId() {
        return doanhnghiepId;
    }

    public void setDoanhnghiepId(TblDoanhnghiep doanhnghiepId) {
        this.doanhnghiepId = doanhnghiepId;
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
        if (!(object instanceof TblThongkeLaodong)) {
            return false;
        }
        TblThongkeLaodong other = (TblThongkeLaodong) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sgis.khukt.model.TblThongkeLaodong[ id=" + id + " ]";
    }
    
}
