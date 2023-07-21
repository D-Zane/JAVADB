package data;

import java.math.BigDecimal;

public class suppliers {
    private BigDecimal supplier_id;
    private String supplier_name;
    private BigDecimal phone;
    private String adress;
    private BigDecimal Id;

    public suppliers(){super();}

    public suppliers(BigDecimal sid, String sna, BigDecimal pho, String adr, BigDecimal id){
        supplier_id = sid;
        supplier_name = sna;
        phone = pho;
        adress = adr;
        Id = id;
    }

    public BigDecimal getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(BigDecimal supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public BigDecimal getPhone() {
        return phone;
    }

    public void setPhone(BigDecimal phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public BigDecimal getId() {
        return Id;
    }

    public void setId(BigDecimal id) {
        Id = id;
    }
}
