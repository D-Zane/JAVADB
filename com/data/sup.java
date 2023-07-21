package data;

import java.math.BigDecimal;

public class sup {
    private BigDecimal supplier_id = null;
    private String supplier_name = null;

    private BigDecimal phone = null;

    private String adress = null;



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
    public String toString(){
        return supplier_name;
    }
}
