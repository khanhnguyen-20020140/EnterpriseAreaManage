/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgis.khukt.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nguyen Quoc Khanh
 */
@Entity
@Table(name = "line_duongbo_4326")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LineDuongbo4326.findAll", query = "SELECT l FROM LineDuongbo4326 l"),
    @NamedQuery(name = "LineDuongbo4326.findByGid", query = "SELECT l FROM LineDuongbo4326 l WHERE l.gid = :gid"),
    @NamedQuery(name = "LineDuongbo4326.findByObjectid", query = "SELECT l FROM LineDuongbo4326 l WHERE l.objectid = :objectid"),
    @NamedQuery(name = "LineDuongbo4326.findByManhandang", query = "SELECT l FROM LineDuongbo4326 l WHERE l.manhandang = :manhandang"),
    @NamedQuery(name = "LineDuongbo4326.findByNgaythunha", query = "SELECT l FROM LineDuongbo4326 l WHERE l.ngaythunha = :ngaythunha"),
    @NamedQuery(name = "LineDuongbo4326.findByNgaycapnha", query = "SELECT l FROM LineDuongbo4326 l WHERE l.ngaycapnha = :ngaycapnha"),
    @NamedQuery(name = "LineDuongbo4326.findByMadoituong", query = "SELECT l FROM LineDuongbo4326 l WHERE l.madoituong = :madoituong"),
    @NamedQuery(name = "LineDuongbo4326.findByLoaiduongb", query = "SELECT l FROM LineDuongbo4326 l WHERE l.loaiduongb = :loaiduongb"),
    @NamedQuery(name = "LineDuongbo4326.findByLoaichatli", query = "SELECT l FROM LineDuongbo4326 l WHERE l.loaichatli = :loaichatli"),
    @NamedQuery(name = "LineDuongbo4326.findByLoaihientr", query = "SELECT l FROM LineDuongbo4326 l WHERE l.loaihientr = :loaihientr"),
    @NamedQuery(name = "LineDuongbo4326.findByLoaiketcau", query = "SELECT l FROM LineDuongbo4326 l WHERE l.loaiketcau = :loaiketcau"),
    @NamedQuery(name = "LineDuongbo4326.findByDorong", query = "SELECT l FROM LineDuongbo4326 l WHERE l.dorong = :dorong"),
    @NamedQuery(name = "LineDuongbo4326.findByTen", query = "SELECT l FROM LineDuongbo4326 l WHERE l.ten = :ten"),
    @NamedQuery(name = "LineDuongbo4326.findByTentuyen1", query = "SELECT l FROM LineDuongbo4326 l WHERE l.tentuyen1 = :tentuyen1"),
    @NamedQuery(name = "LineDuongbo4326.findByTentuyen2", query = "SELECT l FROM LineDuongbo4326 l WHERE l.tentuyen2 = :tentuyen2"),
    @NamedQuery(name = "LineDuongbo4326.findByTentuyen3", query = "SELECT l FROM LineDuongbo4326 l WHERE l.tentuyen3 = :tentuyen3"),
    @NamedQuery(name = "LineDuongbo4326.findByShapeLeng", query = "SELECT l FROM LineDuongbo4326 l WHERE l.shapeLeng = :shapeLeng"),
    @NamedQuery(name = "LineDuongbo4326.findByEnabled", query = "SELECT l FROM LineDuongbo4326 l WHERE l.enabled = :enabled")})
public class LineDuongbo4326 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gid")
    private Integer gid;
    @Column(name = "objectid")
    private BigInteger objectid;
    @Size(max = 18)
    @Column(name = "manhandang")
    private String manhandang;
    @Column(name = "ngaythunha")
    @Temporal(TemporalType.DATE)
    private Date ngaythunha;
    @Column(name = "ngaycapnha")
    @Temporal(TemporalType.DATE)
    private Date ngaycapnha;
    @Size(max = 4)
    @Column(name = "madoituong")
    private String madoituong;
    @Column(name = "loaiduongb")
    private Integer loaiduongb;
    @Column(name = "loaichatli")
    private Integer loaichatli;
    @Column(name = "loaihientr")
    private Integer loaihientr;
    @Column(name = "loaiketcau")
    private Integer loaiketcau;
    @Column(name = "dorong")
    private BigInteger dorong;
    @Size(max = 150)
    @Column(name = "ten")
    private String ten;
    @Size(max = 150)
    @Column(name = "tentuyen1")
    private String tentuyen1;
    @Size(max = 150)
    @Column(name = "tentuyen2")
    private String tentuyen2;
    @Size(max = 150)
    @Column(name = "tentuyen3")
    private String tentuyen3;
    @Column(name = "shape_leng")
    private BigInteger shapeLeng;
    @Column(name = "enabled")
    private Integer enabled;
    @Lob
    @Column(name = "geom")
    private Object geom;

    public LineDuongbo4326() {
    }

    public LineDuongbo4326(Integer gid) {
        this.gid = gid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public BigInteger getObjectid() {
        return objectid;
    }

    public void setObjectid(BigInteger objectid) {
        this.objectid = objectid;
    }

    public String getManhandang() {
        return manhandang;
    }

    public void setManhandang(String manhandang) {
        this.manhandang = manhandang;
    }

    public Date getNgaythunha() {
        return ngaythunha;
    }

    public void setNgaythunha(Date ngaythunha) {
        this.ngaythunha = ngaythunha;
    }

    public Date getNgaycapnha() {
        return ngaycapnha;
    }

    public void setNgaycapnha(Date ngaycapnha) {
        this.ngaycapnha = ngaycapnha;
    }

    public String getMadoituong() {
        return madoituong;
    }

    public void setMadoituong(String madoituong) {
        this.madoituong = madoituong;
    }

    public Integer getLoaiduongb() {
        return loaiduongb;
    }

    public void setLoaiduongb(Integer loaiduongb) {
        this.loaiduongb = loaiduongb;
    }

    public Integer getLoaichatli() {
        return loaichatli;
    }

    public void setLoaichatli(Integer loaichatli) {
        this.loaichatli = loaichatli;
    }

    public Integer getLoaihientr() {
        return loaihientr;
    }

    public void setLoaihientr(Integer loaihientr) {
        this.loaihientr = loaihientr;
    }

    public Integer getLoaiketcau() {
        return loaiketcau;
    }

    public void setLoaiketcau(Integer loaiketcau) {
        this.loaiketcau = loaiketcau;
    }

    public BigInteger getDorong() {
        return dorong;
    }

    public void setDorong(BigInteger dorong) {
        this.dorong = dorong;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTentuyen1() {
        return tentuyen1;
    }

    public void setTentuyen1(String tentuyen1) {
        this.tentuyen1 = tentuyen1;
    }

    public String getTentuyen2() {
        return tentuyen2;
    }

    public void setTentuyen2(String tentuyen2) {
        this.tentuyen2 = tentuyen2;
    }

    public String getTentuyen3() {
        return tentuyen3;
    }

    public void setTentuyen3(String tentuyen3) {
        this.tentuyen3 = tentuyen3;
    }

    public BigInteger getShapeLeng() {
        return shapeLeng;
    }

    public void setShapeLeng(BigInteger shapeLeng) {
        this.shapeLeng = shapeLeng;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Object getGeom() {
        return geom;
    }

    public void setGeom(Object geom) {
        this.geom = geom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gid != null ? gid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LineDuongbo4326)) {
            return false;
        }
        LineDuongbo4326 other = (LineDuongbo4326) object;
        if ((this.gid == null && other.gid != null) || (this.gid != null && !this.gid.equals(other.gid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sgis.khukt.model.LineDuongbo4326[ gid=" + gid + " ]";
    }
    
}
