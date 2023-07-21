package data;

import java.math.BigDecimal;
public class parts {

    private BigDecimal partid = null;

    private String name = null;

    private String description = null;

    private BigDecimal weight = null;

    private BigDecimal materialid = null;

    private BigDecimal cost = null;

    private BigDecimal Id = null;

    public parts(){super();}

    public parts(BigDecimal pid, String nm, String des, BigDecimal wei, BigDecimal mid, BigDecimal stoim, BigDecimal id){

        partid = pid;

        name = nm;

        description = des;

        weight = wei;

        materialid = mid;

        cost = stoim;

        Id = id;

    }

    public BigDecimal getPartid() {
        return partid;
    }

    public void setPartid(BigDecimal partid) {
        this.partid = partid;
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

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getMaterialid() {
        return materialid;
    }

    public void setMaterialid(BigDecimal materialid) {
        this.materialid = materialid;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getId() {
        return Id;
    }

    public void setId(BigDecimal id) {
        Id = id;
    }
}
