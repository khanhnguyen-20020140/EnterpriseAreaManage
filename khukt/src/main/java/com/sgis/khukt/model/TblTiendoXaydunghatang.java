/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgis.khukt.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nguyen Quoc Khanh
 */
@Entity
@Table(name = "tbl_tiendo_xaydunghatang")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblTiendoXaydunghatang.findAll", query = "SELECT t FROM TblTiendoXaydunghatang t"),
    @NamedQuery(name = "TblTiendoXaydunghatang.findById", query = "SELECT t FROM TblTiendoXaydunghatang t WHERE t.id = :id"),
    @NamedQuery(name = "TblTiendoXaydunghatang.findByNgaybaocao", query = "SELECT t FROM TblTiendoXaydunghatang t WHERE t.ngaybaocao = :ngaybaocao")})
public class TblTiendoXaydunghatang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ngaybaocao")
    @Temporal(TemporalType.DATE)
    private Date ngaybaocao;
    @JoinColumn(name = "duan_xdht_id", referencedColumnName = "id")
    @ManyToOne
    private TblDuanXaydunghatang duanXdhtId;

    @JoinColumn(name = "duan_dtsx_id", referencedColumnName = "id")
    @ManyToOne
    private TblDuanDautusanxuat duanDtsxId;
    
    public TblTiendoXaydunghatang() {
    }

    public TblTiendoXaydunghatang(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getNgaybaocao() {
        return ngaybaocao;
    }

    public void setNgaybaocao(Date ngaybaocao) {
        this.ngaybaocao = ngaybaocao;
    }

    public TblDuanDautusanxuat getDuanDtsxId() {
        return duanDtsxId;
    }

    public void setDuanDtsxId(TblDuanDautusanxuat duanDtsxId) {
        this.duanDtsxId = duanDtsxId;
    }

    public TblDuanXaydunghatang getDuanXdhtId() {
        return duanXdhtId;
    }

    public void setDuanXdhtId(TblDuanXaydunghatang duanXdhtId) {
        this.duanXdhtId = duanXdhtId;
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
        if (!(object instanceof TblTiendoXaydunghatang)) {
            return false;
        }
        TblTiendoXaydunghatang other = (TblTiendoXaydunghatang) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sgis.khukt.model.TblTiendoXaydunghatang[ id=" + id + " ]";
    }
    
}
