package data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.GregorianCalendar;

public class book {

    private BigDecimal order_id;

    private java.sql.Date order_date;

    private BigDecimal num_doc;

    private BigDecimal total_price;

    private String type_of_operation;

    private BigDecimal customer_id;



    private BigDecimal Id;

    public book(){super();}

    public book(BigDecimal oid,  Integer year, Integer mon, Integer day , BigDecimal ndc, BigDecimal tp, String too, BigDecimal cid, BigDecimal id){
        order_id = oid;
        GregorianCalendar calendar = new GregorianCalendar(year, mon - 1, day);
        order_date = new java.sql.Date(calendar.getTime().getTime());
        num_doc = ndc;
        total_price = tp;
        type_of_operation = too;
        customer_id = cid;
        Id = id;
    }

    public BigDecimal getOrder_id() {
        return order_id;
    }

    public void setOrder_id(BigDecimal order_id) {
        this.order_id = order_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public BigDecimal getNum_doc() {
        return num_doc;
    }

    public void setNum_doc(BigDecimal num_doc) {
        this.num_doc = num_doc;
    }

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }

    public String getType_of_operation() {
        return type_of_operation;
    }

    public void setType_of_operation(String type_of_operation) {
        this.type_of_operation = type_of_operation;
    }

    public BigDecimal getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(BigDecimal customer_id) {
        this.customer_id = customer_id;
    }


    public BigDecimal getId() {
        return Id;
    }

    public void setId(BigDecimal id) {
        Id = id;
    }
}
