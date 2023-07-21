package data;

import javax.swing.plaf.basic.BasicDesktopIconUI;
import java.math.BigDecimal;

public class detailing {
    private BigDecimal detailing;

    private BigDecimal inventory_id;

    private BigDecimal quantity;

    private BigDecimal product_id;

    private BigDecimal Id;

    public detailing(){super();}

    public detailing(BigDecimal det, BigDecimal iid, BigDecimal qua, BigDecimal pid, BigDecimal id){
        detailing = det;
        inventory_id = iid;
        quantity = qua;
        product_id = pid;
        Id = id;
    }

    public BigDecimal getDetailing() {
        return detailing;
    }

    public void setDetailing(BigDecimal detailing) {
        this.detailing = detailing;
    }

    public BigDecimal getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(BigDecimal inventory_id) {
        this.inventory_id = inventory_id;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getProduct_id() {
        return product_id;
    }

    public void setProduct_id(BigDecimal product_id) {
        this.product_id = product_id;
    }

    public BigDecimal getId() {
        return Id;
    }

    public void setId(BigDecimal id) {
        Id = id;
    }
}
