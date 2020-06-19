package app.cli.validators;

import app.cli.validators.Validator;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.EventHandler;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Service
public class NewCargoInput extends Object implements Validator {


    @Inject
    private EventHandler eventHandler;

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
        try {

            // customer name
            if (input[1].equals("")) return false;
            //weight
            if (input[2].equals("") ) return  false;
            try {
                BigDecimal bigDecimal = new BigDecimal(input[2]);
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
            // type
            switch (input[0]) {
                case "LiquidBulkCargo":
                case "Item":
                case "MixedCargoLiquidBulkAndUnitised":
                case "UnitisedCargo":
                    break;
                default:
                    return false;
            }
        } catch (ArrayIndexOutOfBoundsException error) {
            this.error = "Bitte überprüfe die Syntax!";
            return false;
        }
        return true;
    }
    public String getMessage() {
        return this.error;
    }
}
