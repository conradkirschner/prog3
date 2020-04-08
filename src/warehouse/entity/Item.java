package warehouse.entity;

import app.events.Event;
import storageContract.administration.Customer;
import storageContract.cargo.Cargo;
import storageContract.cargo.Hazard;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Item implements Cargo, JSONConvertable, Event {
    protected BigDecimal weight;
    protected Customer owner;
    protected Collection<Hazard> hazards;
    protected ZonedDateTime expireDate;
    protected ZonedDateTime storageDate;
    protected Date inspectDate;
    protected String id;

    public Item(BigDecimal weight, Customer owner, Collection<Hazard> hazards, ZonedDateTime expireDate) {
        this.weight = weight;
        this.owner = owner;
        this.hazards = hazards;
        this.expireDate = expireDate;
        this.storageDate = ZonedDateTime.now();
        this.inspectDate = new Date();
        this.id = "4";
    }

    @Override
    public Customer getOwner() {
        return this.owner;
    }

    @Override
    public BigDecimal getValue() {
        return this.weight;
    }

    @Override
    public Duration getDurationOfStorage() {
        return Duration.between(ZonedDateTime.now(), expireDate);
    }

    @Override
    public Collection<Hazard> getHazards() {
        return this.hazards;
    }

    @Override
    public Date getLastInspectionDate() {
        return this.inspectDate;
    }

    @Override
    public String getJson() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        String[][] keys = {
                {
                    "weight",this.weight.toString()
                },{
                    "owner",this.owner.getName()
                },{
                    "hazards", this.hazards.stream().map(Enum::toString).collect(Collectors.joining(","))
                },{
                    "expireDate",this.expireDate.toString()
                },{
                    "storageDate",this.storageDate.toString()
                },{
                    "inspectDate",formatter.format(this.inspectDate)
                }
        };

        StringBuilder json = new StringBuilder("{");
        for (int i = 0; i < keys.length; i++) {
            if ( (i+1) < keys.length) {
                json.append("\"").append(keys[i][0]).append("\":\"").append(keys[i][1]).append("\"").append(",");
            } else {
                json.append("\"").append(keys[i][0]).append("\":\"").append(keys[i][1]).append("\"");
            }
        }
        json.append("}");
        return json.toString();
    }

    public void restoreFromJson(String json) throws ParseException {
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
                case "owner":
                    this.owner = new user.entity.Customer(entryMapping[1]);
                    break;
                case "hazards":
                    break;
                case "expireDate":
                    this.expireDate = ZonedDateTime.parse(entryMapping[1]);
                    break;
                case "storageDate":
                    this.storageDate = ZonedDateTime.parse(entryMapping[1]);
                    break;
                case "inspectDate":
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
                    //Parsing the given String to Date object

                    this.inspectDate = formatter.parse(entryMapping[1]);
                    break;
            }
        }
    }

    public String getId() {
        return this.id;
    }
}
