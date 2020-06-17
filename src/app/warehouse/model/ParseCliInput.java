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
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Service
@AutoloadSubscriber
public class ParseCliInput implements Subscriber {

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
            if (user == null) {
                return null; //@todo add error handling
            }
            BigDecimal weight = parseWeight(input[2]);
            ZonedDateTime storeUntil = parseTime(input[3]);
            Collection<Hazard> hazards = parseHazard(input[4]);
            boolean pressure = parsePressure(input[5]);
            boolean fragile = parseFragile(input[6]);
            boolean block = parseBlock(input[7]);

            switch (input[0]) {
                case "LiquidBulkCargo":
                    item = new LiquidBulkCargo(
                            weight,
                            user,
                            hazards,
                            storeUntil,
                            pressure
                    );
                    break;
                case "Item":
                    item = new Item(
                            weight,
                            user,
                            hazards,
                            storeUntil
                    );
                    break;
                case "MixedCargoLiquidBulkAndUnitised":
                    item = new MixedCargoLiquidBulkAndUnitised(
                            weight,
                            user,
                            hazards,
                            storeUntil,
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
    private Collection<Hazard> parseHazard(String input) {
        Collection<Hazard> hazards = new ArrayList<Hazard>();
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

    private ZonedDateTime parseTime(String input) {
        return ZonedDateTime.now().plusSeconds(Long.parseLong(input));
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
}