package data;

import java.math.BigDecimal;

public class materials {
    private BigDecimal material_id;

    private String name;

    private BigDecimal supplier_id;


    private BigDecimal price;

    private BigDecimal quantity;
    private BigDecimal Id;
    private sup sup;

    public materials(){super();}

    public materials(BigDecimal mid, String nm, BigDecimal sid, BigDecimal pr,BigDecimal col, BigDecimal id, sup sup){
        material_id = mid;
        name = nm;
        supplier_id = sid;
        price = pr;
        quantity = col;
        Id = id;
        sup = sup;
    }

    public BigDecimal getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(BigDecimal material_id) {
        this.material_id = material_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(BigDecimal supplier_id) {
        this.supplier_id = supplier_id;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public data.sup getSup() {
        return sup;
    }

    public void setSup(data.sup sup) {
        this.sup = sup;
    }

    public BigDecimal getId() {
        return Id;
    }

    public void setId(BigDecimal id) {
        Id = id;
    }
}
