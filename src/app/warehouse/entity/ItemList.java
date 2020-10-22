package app.warehouse.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="ItemList")
public class ItemList {
    private static final long serialVersionUID = 6529685098267757890L;

    @XmlElement(name="Item")
    private List<Item> itemList;

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> ItemList) {
        this.itemList = ItemList;
    }

    @Override
    public String toString() {
        return itemList + "";
    }

}