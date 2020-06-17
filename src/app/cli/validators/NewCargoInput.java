package app.cli.validators;

import app.user.events.GetUserEvent;
import app.warehouse.entity.Item;
import app.warehouse.entity.LiquidBulkCargo;
import app.warehouse.entity.MixedCargoLiquidBulkAndUnitised;
import app.warehouse.entity.UnitisedCargo;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.EventHandler;
import storageContract.cargo.Hazard;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class NewCargoInput extends Object implements Validator {


    @Inject
    private EventHandler eventHandler;

    private Item item;
    public String error;
    private String type;
    private String owner;
    private String weight;
    private String timeToStay;
    private String hazards;
    private String fragile;
    private String pressure;
    private String block;

    public Boolean isValid(String[] input) {
        item = null;
        try {

            // customer name
            if (input[1].equals("")) return  false;
            // weight
            BigDecimal bigInteger;

            if (input[2].equals("") ) return  false;
            try {
                bigInteger = new BigDecimal(input[2]);
            } catch (Exception exception) {
                this.error = "Gewicht als Zahl eingeben!";
                return false;
            }
            // einlagerungsdauer
            if (input[3].equals("") ) return  false;
            int integer = Integer.parseInt(input[3]);
            ZonedDateTime time = ZonedDateTime.now().plusSeconds(integer);
            try {
                input[3] = time.toString();
            } catch (Exception exception) {
                this.error = "Einlagerungsdauer als Zahl eingeben!";
                return false;
            }

            // gefahrenstoffe ,...,..
            if (input[4].equals(" ") ) return  false;

            // zerbrechlich (y/n)
            if (input[5].equals("y") == false || !input[5].equals("n") == false) {
                this.error = "Bitte y oder n angeben für den Zustand des Drucks";
            }
            //  Druck (y/n)
            if (input[6].equals("y") == false || !input[6].equals("n") == false) {
                this.error = "Bitte y oder n angeben für den Zustand der Fracht";
            }
            // fest  (y/n)
            if (input[7].equals("y") == false || !input[7].equals("n") == false) {
                this.error = "Bitte y oder n angeben für den Zustand der Zerbrechlichkeit";
            }
            this.type = input[0];
            GetUserEvent getUserEvent = new GetUserEvent();
            getUserEvent.setFilterByName(input[1]);
            GetUserEvent user = (GetUserEvent) eventHandler.push(getUserEvent);
            if (user.getUsers().size() == 0) {
                return false;
            }
            this.weight = bigInteger.toString();
            Collection<Hazard> hazards = new ArrayList<Hazard>();
            String[] hazardInputs = input[4].split(",");
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
            this.hazards = input[4];
            this.fragile = input[5];
            boolean pressure = false;
            if (input[6].equals("y")) {
                pressure = true;
            }
            boolean fragile = false;
            if (input[7].equals("y")) {
                fragile = true;
            }
            this.block = input[7];
            // type
            /**
             * @Todo move this to warehouse event
             */
            switch (input[0]) {
                case "LiquidBulkCargo":
                    this.item = new LiquidBulkCargo(
                            new BigDecimal(this.weight),
                            user.getUsers().get(0),
                            hazards,
                            time,
                            pressure
                    );
                case "Item":
                    this.item = new Item(
                            new BigDecimal(this.weight),
                            user.getUsers().get(0),
                            hazards,
                            time
                    );
                case "MixedCargoLiquidBulkAndUnitised":
                    this.item = new MixedCargoLiquidBulkAndUnitised(
                            new BigDecimal(this.weight),
                            user.getUsers().get(0),
                            hazards,
                            time,
                            pressure,
                            fragile
                    );
                case "UnitisedCargo":
                    this.item = new UnitisedCargo(
                            new BigDecimal(this.weight),
                            user.getUsers().get(0),
                            hazards,
                            time,
                            fragile
                    );
                    break;
                default:
                    return false;
            }
        } catch (ArrayIndexOutOfBoundsException error) {
            this.error = "Bitte überprüfe die Syntax!";
            return false;
        }


        return input.length != 1;
    }
    public String getMessage() {
        return this.error;
    }

    public Item getItem() {
        return this.item;
    }
}
