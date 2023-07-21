package data;

import java.math.BigDecimal;
public class customer {

    private BigDecimal customer_id;

    private String name_mag;

    private String email;

    private BigDecimal phone_number;

    private BigDecimal Id;

    public customer(){super();}

    public customer(BigDecimal cid, String nma, String em, BigDecimal nb, BigDecimal id){
        customer_id = cid;
        name_mag = nma;
        email = em;
        phone_number = nb;
        Id = id;
    }

    public BigDecimal getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(BigDecimal customer_id) {
        this.customer_id = customer_id;
    }

    public String getName_mag() {
        return name_mag;
    }

    public void setName_mag(String name_mag) {
        this.name_mag = name_mag;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(BigDecimal phone_number) {
        this.phone_number = phone_number;
    }

    public BigDecimal getId() {
        return Id;
    }

    public void setId(BigDecimal id) {
        Id = id;
    }
}
