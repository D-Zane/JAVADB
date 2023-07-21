package data;

import java.math.BigDecimal;
public class materialss {

    private  BigDecimal materialid = null;

    private String name = null;

    private BigDecimal supplierid = null;

    private BigDecimal unitprice = null;

    private BigDecimal unitsinstock = null;

    private BigDecimal unitsonorder = null;

    private BigDecimal Id = null;

    public materialss(){super();}

    public materialss(BigDecimal mid, String nm, BigDecimal sid, BigDecimal upr, BigDecimal ust, BigDecimal uor, BigDecimal id){

        materialid = mid;

        name = nm;

        supplierid = sid;

        unitprice = upr;

        unitsinstock = ust;

        unitsonorder = uor;

        Id = id;

    }

    public BigDecimal getMaterialid() {
        return materialid;
    }

    public void setMaterialid(BigDecimal materialid) {
        this.materialid = materialid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(BigDecimal supplierid) {
        this.supplierid = supplierid;
    }

    public BigDecimal getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(BigDecimal unitprice) {
        this.unitprice = unitprice;
    }

    public BigDecimal getUnitsinstock() {
        return unitsinstock;
    }

    public void setUnitsinstock(BigDecimal unitsinstock) {
        this.unitsinstock = unitsinstock;
    }

    public BigDecimal getUnitsonorder() {
        return unitsonorder;
    }

    public void setUnitsonorder(BigDecimal unitsonorder) {
        this.unitsonorder = unitsonorder;
    }

    public BigDecimal getId() {
        return Id;
    }

    public void setId(BigDecimal id) {
        Id = id;
    }
}
