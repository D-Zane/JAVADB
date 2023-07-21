package data;

import java.math.BigDecimal;

public class warehouse {

    private BigDecimal warehouse_id;

    private String name;

    private String adress;

    private BigDecimal Id;

    public warehouse(){super();}

    public warehouse(BigDecimal wid, String na, String adr, BigDecimal id){
        warehouse_id = wid;
        name = na;
        adress = adr;
        Id =id;
    }

    public BigDecimal getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(BigDecimal warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public BigDecimal getId() {
        return Id;
    }

    public void setId(BigDecimal id) {
        Id = id;
    }
}
