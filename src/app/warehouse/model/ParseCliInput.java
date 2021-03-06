package app.warehouse.model;


import app.cli.events.GetInputEvent;
import app.user.entity.User;
import app.user.events.GetUserEvent;
import app.warehouse.entity.Item;
import app.warehouse.entity.LiquidBulkCargo;
import app.warehouse.entity.MixedCargoLiquidBulkAndUnitised;
import app.warehouse.entity.UnitisedCargo;
import app.warehouse.events.StoreItemEvent;
import famework.annotation.AutoloadSubscriber;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.*;
import storageContract.cargo.Hazard;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Service
@AutoloadSubscriber
public class ParseCliInput implements Subscriber {

    private boolean active = true;

    @Inject
    EventHandler eventHandler;

    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new GetInputEvent(), 1000));
        return events;
    }

    @Override
    public Event update(Event event) {
        if (event instanceof GetInputEvent) {
            if (!((GetInputEvent) event).isValidated()) {
                return null;
            }
            Item item = null;
            String[] input = ((GetInputEvent) event).getContent().split(" ");
            User user = null;

            try {
                user = parseUser(input[1]);
            } catch (Exception e) {
                return null;
            }
            BigDecimal weight = parseWeight(input[2]);
            Date storeUntil = parseTime(input[3]);
            ArrayList<Hazard> hazards = parseHazard(input[4]);
            boolean pressure = parsePressure(input[5]);
            boolean fragile = parseFragile(input[6]);
            boolean block = parseBlock(input[7]);
            String warehouse = null;
            Integer storagePlace = null;

            switch (input[0]) {
                case "LiquidBulkCargo":
                    item = new LiquidBulkCargo(
                            weight,
                            user,
                            hazards,
                            storeUntil,
                            warehouse,
                            storagePlace,
                            pressure
                    );
                    break;
                case "Item":
                    item = new Item(
                            weight,
                            user,
                            hazards,
                            storeUntil,
                            warehouse,
                            storagePlace
                    );
                    break;
                case "MixedCargoLiquidBulkAndUnitised":
                    item = new MixedCargoLiquidBulkAndUnitised(
                            weight,
                            user,
                            hazards,
                            storeUntil,
                            warehouse,
                            storagePlace,
                            pressure,
                            fragile
                    );
                    break;
                case "UnitisedCargo":
                    item = new UnitisedCargo(
                            weight,
                            user,
                            hazards,
                            storeUntil,
                            warehouse,
                            storagePlace,
                            fragile
                    );
                    break;
                default:
                    return null;
            }
            this.eventHandler.push(new StoreItemEvent(item));

            return null;
        }
        return null;
    }
    private ArrayList<Hazard> parseHazard(String input) {
        ArrayList<Hazard> hazards = new ArrayList<Hazard>();
        String[] hazardInputs = input.split(",");
        for(String hazzard:hazardInputs) {
            switch (hazzard) {
                case "explosive":
                    hazards.add(Hazard.explosive);
                    break;
                case "radioactive":
                    hazards.add(Hazard.radioactive);
                    break;
                case "toxic":
                    hazards.add(Hazard.toxic);
                    break;
                case "fammable":
                    hazards.add(Hazard.flammable);
                    break;
            }
        }
        return hazards;
    }

    private User parseUser(String input) {
        GetUserEvent getUserEvent = new GetUserEvent();
        getUserEvent.setFilterByName(input);
        GetUserEvent user = (GetUserEvent) eventHandler.push(getUserEvent);
        return user.getUsers().get(0);
    }

    private BigDecimal parseWeight(String input) {
        return new BigDecimal(input);
    }

    private Date parseTime(String input) {
        return parseTime(new Date(), Integer.parseInt(input));
    }

    private boolean parsePressure(String input) {
        return (input.equals("y"));
    }
    private boolean parseFragile(String input) {
        return (input.equals("y"));
    }
    private boolean parseBlock(String input) {
        return (input.equals("y"));
    }

    public Date parseTime(Date date, int secounds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int countCompleteSecounds = secounds/60;
        for (int i = 0; i < countCompleteSecounds-1; i++) {
            calendar.add(Calendar.SECOND, 60);

        }
        calendar.add(Calendar.SECOND, secounds%60);
        return calendar.getTime();
    }

    @Override
    public String getName() {
        return "warehouse:ParseCliInput";
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }
}
