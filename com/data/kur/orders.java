package data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.GregorianCalendar;
public class orders {

    private BigDecimal orderid = null;

    private java.sql.Date orderdate = null;

    private String customername = null;

    private String customeraddress = null;

    private BigDecimal customerphone = null;

    private BigDecimal ordertotal = null;

    private BigDecimal  Id = null;

    public orders() {super();}




    public  orders(BigDecimal odid, Integer year, Integer mon, Integer day , String nm, String adr, BigDecimal num, BigDecimal cena, BigDecimal id){
        orderid = odid;
        GregorianCalendar calendar = new GregorianCalendar(year, mon - 1, day);
        orderdate = new java.sql.Date(calendar.getTime().getTime());
        customername = nm;
        customeraddress = adr;
        customerphone = num;
        ordertotal = cena;
        Id = id;
    }
    public BigDecimal getOrderid() {
        return orderid;
    }

    public void setOrderid(BigDecimal orderid) {
        this.orderid = orderid;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCustomeraddress() {
        return customeraddress;
    }

    public void setCustomeraddress(String customeraddress) {
        this.customeraddress = customeraddress;
    }

    public BigDecimal getCustomerphone() {
        return customerphone;
    }

    public void setCustomerphone(BigDecimal customerphone) {
        this.customerphone = customerphone;
    }

    public BigDecimal getOrdertotal() {
        return ordertotal;
    }

    public void setOrdertotal(BigDecimal ordertotal) {
        this.ordertotal = ordertotal;}

    public BigDecimal getId() {
        return Id;
    }

    public void setId(BigDecimal id) {
        Id = id;
    }


}
