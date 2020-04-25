package cli.validators;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;

public class NewCargoInput extends Object implements Validator {
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
            try {
                Integer integer = Integer.parseInt(input[3]);
                ZonedDateTime time = ZonedDateTime.now().plusSeconds(integer);
                input[3] = time.toString();
            } catch (Exception exception) {
                this.error = "Einlagerungsdauer als Zahl eingeben!";
                return false;
            }

            // gefahrenstoffe ,...,..
            if (input[4].equals("") ) return  false;

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
            this.owner = input[1];
            this.weight = bigInteger.toString();
            this.timeToStay = input[3];
            this.hazards = input[4];
            this.fragile = input[5];
            this.pressure = input[6];
            this.block = input[7];
        } catch (ArrayIndexOutOfBoundsException error) {
            this.error = "Bitte überprüfe die Syntax!";
            return false;
        }


        return input.length != 1;
    }

    /**
     * @todo use real json parser but it is not allowed here https://mvnrepository.com/artifact/org.json/json
     * @return
     */
    public String getData() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        String[][] keys = {
                {
                        "type", this.type
                }, {
                "owner", this.owner
        }, {
                "weight", this.weight
        }, {
                "expireAt", this.timeToStay
        }, {
                "hazards", this.hazards
        }, {
                "fragile", this.fragile
        }, {
                "pressure", this.pressure
        }, {
                "block", this.block
        }
        };

        StringBuilder json = new StringBuilder("{");
        for (int i = 0; i < keys.length; i++) {
            if ((i + 1) < keys.length) {
                json.append("\"").append(keys[i][0]).append("\":\"").append(keys[i][1]).append("\"").append(" , ");
            } else {
                json.append("\"").append(keys[i][0]).append("\":\"").append(keys[i][1]).append("\"");
            }
        }
        json.append("}");
        return json.toString();
    }
    public String getMessage() {
        return this.error;
    }
}
