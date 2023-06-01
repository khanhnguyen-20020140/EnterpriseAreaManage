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
@Table(name = "tbl_tiendo_xaydunghatang_chitiet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblTiendoXaydunghatangChitiet.findAll", query = "SELECT t FROM TblTiendoXaydunghatangChitiet t"),
    @NamedQuery(name = "TblTiendoXaydunghatangChitiet.findById", query = "SELECT t FROM TblTiendoXaydunghatangChitiet t WHERE t.id = :id"),
    @NamedQuery(name = "TblTiendoXaydunghatangChitiet.findByThanhphan", query = "SELECT t FROM TblTiendoXaydunghatangChitiet t WHERE t.thanhphan = :thanhphan"),
    @NamedQuery(name = "TblTiendoXaydunghatangChitiet.findByTiendoCapvon", query = "SELECT t FROM TblTiendoXaydunghatangChitiet t WHERE t.tiendoCapvon = :tiendoCapvon"),
    @NamedQuery(name = "TblTiendoXaydunghatangChitiet.findByTiendoGiaingan", query = "SELECT t FROM TblTiendoXaydunghatangChitiet t WHERE t.tiendoGiaingan = :tiendoGiaingan"),
    @NamedQuery(name = "TblTiendoXaydunghatangChitiet.findByKhoiluongHoanthanh", query = "SELECT t FROM TblTiendoXaydunghatangChitiet t WHERE t.khoiluongHoanthanh = :khoiluongHoanthanh")})
public class TblTiendoXaydunghatangChitiet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 200)
    @Column(name = "thanhphan")
    private String thanhphan;
    @Column(name = "tiendo_capvon")
    private Integer tiendoCapvon;
    @Column(name = "tiendo_giaingan")
    private Integer tiendoGiaingan;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "khoiluong_hoanthanh")
    private BigDecimal khoiluongHoanthanh;
    @JoinColumn(name = "id_trangthai", referencedColumnName = "id")
    @ManyToOne
    private TblDmTrangthai idTrangthai;
    @JoinColumn(name = "id_tiendo", referencedColumnName = "id")
    @ManyToOne
    private TblTiendoXaydunghatang idTiendo;

    public TblTiendoXaydunghatangChitiet() {
    }

    public TblTiendoXaydunghatangChitiet(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getThanhphan() {
        return thanhphan;
    }

    public void setThanhphan(String thanhphan) {
        this.thanhphan = thanhphan;
    }

    public Integer getTiendoCapvon() {
        return tiendoCapvon;
    }

    public void setTiendoCapvon(Integer tiendoCapvon) {
        this.tiendoCapvon = tiendoCapvon;
    }

    public Integer getTiendoGiaingan() {
        return tiendoGiaingan;
    }

    public void setTiendoGiaingan(Integer tiendoGiaingan) {
        this.tiendoGiaingan = tiendoGiaingan;
    }

    public BigDecimal getKhoiluongHoanthanh() {
        return khoiluongHoanthanh;
    }

    public void setKhoiluongHoanthanh(BigDecimal khoiluongHoanthanh) {
        this.khoiluongHoanthanh = khoiluongHoanthanh;
    }

    public TblDmTrangthai getIdTrangthai() {
        return idTrangthai;
    }

    public void setIdTrangthai(TblDmTrangthai idTrangthai) {
        this.idTrangthai = idTrangthai;
    }

    public TblTiendoXaydunghatang getIdTiendo() {
        return idTiendo;
    }

    public void setIdTiendo(TblTiendoXaydunghatang idTiendo) {
        this.idTiendo = idTiendo;
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
        if (!(object instanceof TblTiendoXaydunghatangChitiet)) {
            return false;
        }
        TblTiendoXaydunghatangChitiet other = (TblTiendoXaydunghatangChitiet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sgis.khukt.model.TblTiendoXaydunghatangChitiet[ id=" + id + " ]";
    }
    
}
