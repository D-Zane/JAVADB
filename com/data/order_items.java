package data;

import java.math.BigDecimal;

public class order_items {

    private BigDecimal id_position;

    private BigDecimal quantity;

    private BigDecimal order_id;

    private BigDecimal product_id;

    private BigDecimal Id;

    public order_items(){super();}

    public order_items(BigDecimal idp, BigDecimal qua, BigDecimal oid, BigDecimal pid, BigDecimal id){
        id_position = idp;
        quantity = qua;
        order_id = oid;
        product_id = pid;
        Id = id;
    }

    public BigDecimal getId_position() {
        return id_position;
    }

    public void setId_position(BigDecimal id_position) {
        this.id_position = id_position;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getOrder_id() {
        return order_id;
    }

    public void setOrder_id(BigDecimal order_id) {
        this.order_id = order_id;
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
