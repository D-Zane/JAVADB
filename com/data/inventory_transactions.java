package data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.GregorianCalendar;


public class inventory_transactions {
    private BigDecimal inventory_id;

    private java.sql.Date inventory_date;

    private BigDecimal num_doc;

    private BigDecimal warehouse_id;

    private BigDecimal Id;

    public inventory_transactions(){super();}

    public inventory_transactions(BigDecimal iid, Integer year, Integer mon, Integer day, BigDecimal ndoc, BigDecimal wid, BigDecimal id ){
        inventory_id = iid;
        GregorianCalendar calendar = new GregorianCalendar(year, mon - 1, day);
        inventory_date = new java.sql.Date(calendar.getTime().getTime());
        num_doc = ndoc;
        warehouse_id = wid;
        Id = id;
    }

    public BigDecimal getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(BigDecimal inventory_id) {
        this.inventory_id = inventory_id;
    }

    public Date getInventory_date() {
        return inventory_date;
    }

    public void setInventory_date(Date inventory_date) {
        this.inventory_date = inventory_date;
    }

    public BigDecimal getNum_doc() {
        return num_doc;
    }

    public void setNum_doc(BigDecimal num_doc) {
        this.num_doc = num_doc;
    }

    public BigDecimal getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(BigDecimal warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public BigDecimal getId() {
        return Id;
    }

    public void setId(BigDecimal id) {
        Id = id;
    }
}
