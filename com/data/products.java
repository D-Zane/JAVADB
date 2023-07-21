package data;

import java.math.BigDecimal;

public class products {

    private BigDecimal product_id;

    private String name;

    private String description;

    private BigDecimal unit_price;

    private BigDecimal material_id;

    private BigDecimal workshop_id;



    private BigDecimal Id;

    public products(){super();}

    private products(BigDecimal pid, String nm, String des, BigDecimal up,BigDecimal mid ,BigDecimal wid,BigDecimal id){
        product_id = pid;
        name = nm;
        description = des;
        unit_price = up;
        material_id = mid;
        workshop_id = wid;
        Id = id;
    }

    public BigDecimal getProduct_id() {
        return product_id;
    }

    public void setProduct_id(BigDecimal product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public BigDecimal getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(BigDecimal material_id) {
        this.material_id = material_id;
    }

    public BigDecimal getWorkshop_id() {
        return workshop_id;
    }

    public void setWorkshop_id(BigDecimal workshop_id) {
        this.workshop_id = workshop_id;
    }


    public BigDecimal getId() {
        return Id;
    }

    public void setId(BigDecimal id) {
        Id = id;
    }
}
