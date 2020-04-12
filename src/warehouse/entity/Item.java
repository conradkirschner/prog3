package warehouse.entity;

import app.events.Event;
import storageContract.administration.Customer;
import storageContract.cargo.Cargo;
import storageContract.cargo.Hazard;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Item implements Cargo, JSONConvertable, Event {
    public String type = "item";

    protected BigDecimal weight;
    protected Customer owner;
    protected Collection<Hazard> hazards;
    protected ZonedDateTime expireDate;
    protected ZonedDateTime storageDate;
    protected Date inspectDate;
    protected String id;

    public Item(
            BigDecimal weight,
            Customer owner,
            Collection<Hazard> hazards,
            ZonedDateTime expireDate
    ) {
        UUID uuid = UUID.randomUUID();


        this.weight = weight;
        this.owner = owner;
        this.hazards = hazards;
        this.expireDate = expireDate;
        this.storageDate = ZonedDateTime.now();
        this.inspectDate = new Date();
        this.id = uuid.toString();
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

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public void setHazards(Collection<Hazard> hazards) {
        this.hazards = hazards;
    }

    public void setExpireDate(ZonedDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public void setStorageDate(ZonedDateTime storageDate) {
        this.storageDate = storageDate;
    }

    public void setInspectDate(Date inspectDate) {
        this.inspectDate = inspectDate;
    }

    @Override
    public Date getLastInspectionDate() {
        return this.inspectDate;
    }

    /**
     * @todo use real json parser but it is not allowed here https://mvnrepository.com/artifact/org.json/json
     * @return
     */
    @Override
    public String getJson() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        String[][] keys = {
                {
                    "id", this.id
                },
                {
                    "weight",this.weight.toString()
                },{
                    "owner",this.owner.getName()
                },{
                    "hazards", (this.hazards == null)?"":this.hazards.stream().map(Enum::toString).collect(Collectors.joining(","))
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
    /**
     * @todo use real json parser but it is not allowed here https://mvnrepository.com/artifact/org.json/json
     * @return
     */
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
                case "id":
                    this.id = entryMapping[1];
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
