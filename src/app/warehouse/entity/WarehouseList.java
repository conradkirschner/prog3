package app.warehouse.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="WarehouseList")
public class WarehouseList {
    @XmlElement(name="warehouse")
    private List<Warehouse> warehouseList;

    public List<Warehouse> getWarehouseList() {
        return warehouseList;
    }

    public void setWarehouseList(List<Warehouse> WarehouseList) {
        this.warehouseList = WarehouseList;
    }

    @Override
    public String toString() {
        return warehouseList + "";
    }

}