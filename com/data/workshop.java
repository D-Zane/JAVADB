package data;

import java.math.BigDecimal;
public class workshop {
    private BigDecimal workshop_id;

    private String name;

    private String contacts;

    private BigDecimal Id;
    public workshop(){super();}

    public workshop(BigDecimal wid, String nm, String cont, BigDecimal id){
        workshop_id = wid;
        name = nm;
        contacts = cont;
        Id = id;
    }

    public BigDecimal getWorkshop_id() {
        return workshop_id;
    }

    public void setWorkshop_id(BigDecimal workshop_id) {
        this.workshop_id = workshop_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public BigDecimal getId() {
        return Id;
    }

    public void setId(BigDecimal id) {
        Id = id;
    }
}
