package warehouse.input;


import storageContract.administration.Customer;
import storageContract.cargo.Hazard;
import warehouse.entity.Item;
import warehouse.entity.LiquidBulkCargo;
import warehouse.entity.MixedCargoLiquidBulkAndUnitised;
import warehouse.entity.UnitisedCargo;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;

public class NewItemInput {
    protected String type;
    protected Customer owner;
    protected BigDecimal weight;
    protected ZonedDateTime expireAt;
    protected Collection<Hazard> hazards;
    protected Boolean fragile;
    protected Boolean pressure;
    protected Boolean block;

    public Item getItem(String json) throws ParseException {
        String[] jsonMapping = json.split(",");
        for (String entry : jsonMapping){
            String[] entryMapping = entry
                    .replace("\"", "")
                    .replace("{", "")
                    .replace("}", "")
                    .split(":", 2);
            switch (entryMapping[0]) {
                case "weight":
                    this.weight = new BigDecimal(entryMapping[1]);
                    break;
                case "type":
                    this.type = entryMapping[1];
                    break;
                case "owner":
                    this.owner = new user.entity.Customer(entryMapping[1]);
                    break;
                case "expireAt":
                    this.expireAt = ZonedDateTime.parse(entryMapping[1]);
                    break;
                case "hazards":
                    break;
                case "fragile":
                    this.fragile = entryMapping[1].equals("true");
                    break;
                case "pressure":
                    this.pressure = entryMapping[1].equals("true");
                    break;
                case "block":
                    this.block = entryMapping[1].equals("true");
                    break;

            }
        }
        Item item = null;
        switch (this.type) {
            case "LiquidBulkCargo":
                item = new LiquidBulkCargo(
                        this.weight,
                        this.owner,
                        this.hazards,
                        this.expireAt,
                        this.pressure
                );
                break;
            case "MixedCargoLiquidBulkAndUnitised":
                item = new MixedCargoLiquidBulkAndUnitised(
                        this.weight,
                        this.owner,
                        this.hazards,
                        this.expireAt,
                        this.pressure,
                        this.fragile
                );
                break;
            case "UnitisedCargo":
                item = new UnitisedCargo(
                        this.weight,
                        this.owner,
                        this.hazards,
                        this.expireAt,
                        this.fragile
                );
                break;
            default:
                item = new Item(
                        this.weight,
                        this.owner,
                        this.hazards,
                        this.expireAt
                );
        }
        return item;
    }

}
