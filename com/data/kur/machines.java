package data;

import javax.lang.model.element.Name;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.GregorianCalendar;
public class machines {

    private BigDecimal machineid = null;

    private String name = null;

    private String type = null;

    private java.sql.Date datepurchased = null;

    private String status = null;

    private BigDecimal Id = null;

    public machines(){super();}

    public machines(BigDecimal mid, String nm, String tp, Integer year, Integer mon, Integer day, String st, BigDecimal id){

        machineid = mid;

        name = nm;

        type = tp;

        GregorianCalendar calendar = new GregorianCalendar(year, mon - 1, day);
        datepurchased = new java.sql.Date(calendar.getTime().getTime());

        status = st;

        Id = id;

    }

    public BigDecimal getMachineid() {
        return machineid;
    }

    public void setMachineid(BigDecimal machineid) {
        this.machineid = machineid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDatepurchased() {
        return datepurchased;
    }

    public void setDatepurchased(Date datepurchased) {
        this.datepurchased = datepurchased;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getId() {
        return Id;
    }

    public void setId(BigDecimal id) {
        Id = id;
    }
}
