/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgis.khukt.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nguyen Quoc Khanh
 */
@Entity
@Table(name = "tbl_thanhphan_xaydunghatang")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblThanhphanXaydunghatang.findAll", query = "SELECT t FROM TblThanhphanXaydunghatang t"),
    @NamedQuery(name = "TblThanhphanXaydunghatang.findById", query = "SELECT t FROM TblThanhphanXaydunghatang t WHERE t.id = :id"),
    @NamedQuery(name = "TblThanhphanXaydunghatang.findByTen", query = "SELECT t FROM TblThanhphanXaydunghatang t WHERE t.ten = :ten"),
    @NamedQuery(name = "TblThanhphanXaydunghatang.findByKhoiluong", query = "SELECT t FROM TblThanhphanXaydunghatang t WHERE t.khoiluong = :khoiluong"),
    @NamedQuery(name = "TblThanhphanXaydunghatang.findByDonvitinh", query = "SELECT t FROM TblThanhphanXaydunghatang t WHERE t.donvitinh = :donvitinh"),
    @NamedQuery(name = "TblThanhphanXaydunghatang.findByMucdautu", query = "SELECT t FROM TblThanhphanXaydunghatang t WHERE t.mucdautu = :mucdautu")})
public class TblThanhphanXaydunghatang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 150)
    @Column(name = "ten")
    private String ten;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "khoiluong")
    private BigDecimal khoiluong;
    @Size(max = 100)
    @Column(name = "donvitinh")
    private String donvitinh;
    @Column(name = "mucdautu")
    private BigDecimal mucdautu;
    @JoinColumn(name = "id_duan_xdht", referencedColumnName = "id")
    @ManyToOne
    private TblDuanXaydunghatang idDuanXdht;
    @JoinColumn(name = "nhomdm_id", referencedColumnName = "id")
    @ManyToOne
    private TblNhomdmXdht nhomdmId;

    public TblThanhphanXaydunghatang() {
    }

    public TblThanhphanXaydunghatang(Integer id) {
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

    public BigDecimal getKhoiluong() {
        return khoiluong;
    }

    public void setKhoiluong(BigDecimal khoiluong) {
        this.khoiluong = khoiluong;
    }

    public String getDonvitinh() {
        return donvitinh;
    }

    public void setDonvitinh(String donvitinh) {
        this.donvitinh = donvitinh;
    }

    public BigDecimal getMucdautu() {
        return mucdautu;
    }

    public void setMucdautu(BigDecimal mucdautu) {
        this.mucdautu = mucdautu;
    }

    public TblDuanXaydunghatang getIdDuanXdht() {
        return idDuanXdht;
    }

    public void setIdDuanXdht(TblDuanXaydunghatang idDuanXdht) {
        this.idDuanXdht = idDuanXdht;
    }

    public TblNhomdmXdht getNhomdmId() {
        return nhomdmId;
    }

    public void setNhomdmId(TblNhomdmXdht nhomdmId) {
        this.nhomdmId = nhomdmId;
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
        if (!(object instanceof TblThanhphanXaydunghatang)) {
            return false;
        }
        TblThanhphanXaydunghatang other = (TblThanhphanXaydunghatang) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sgis.khukt.model.TblThanhphanXaydunghatang[ id=" + id + " ]";
    }
    
}
