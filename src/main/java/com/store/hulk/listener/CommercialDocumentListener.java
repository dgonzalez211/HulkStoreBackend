
package com.store.hulk.listener;

import com.store.hulk.model.document.CommercialDocument;

import javax.persistence.PrePersist;
import java.math.BigDecimal;
import java.util.regex.Pattern;

public class CommercialDocumentListener {

    @PrePersist
    public void setValueTotal(CommercialDocument document) {

        double total = 0;
        total = document.getDetails().stream().map(d -> (d.getUnitValue().longValue() * d.getQuantity())).reduce(total, Double::sum);
        document.setTotalValue(new BigDecimal(total));
        document.setTotalLetters(convert((long) total));

    }


    private final String[] UNITS = {"", "un ", "dos ", "tres ", "cuatro ", "cinco ", "seis ", "siete ", "ocho ", "nueve "};

    private final String[] DOZENS = {"diez ", "once ", "doce ", "trece ", "catorce ", "quince ", "dieciseis ",
            "diecisiete ", "dieciocho ", "diecinueve", "veinte ", "treinta ", "cuarenta ",
            "cincuenta ", "sesenta ", "setenta ", "ochenta ", "noventa "};

    private final String[] HUNDREDS = {"", "ciento ", "doscientos ", "trecientos ", "cuatrocientos ", "quinientos ", "seiscientos ",
            "setecientos ", "ochocientos ", "novecientos "};

    private String convert(long value) {
        String literal = "";
        String decimal_part;
        String number = String.valueOf(value);
        number = number.replace(".", ",");
        if (!number.contains(",")) {
            number = number + ",00";
        }
        if (Pattern.matches("\\d{1,9},\\d{1,2}", number)) {
            String Num[] = number.split(",");
            decimal_part = "pesos";
            if (Integer.parseInt(Num[0]) == 0) {
                literal = "cero ";
            } else if (Integer.parseInt(Num[0]) > 999999) {
                literal = getMillions(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 999) {
                literal = getThousands(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 99) {
                literal = getHundreds(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 9) {
                literal = getTens(Num[0]);
            } else {
                literal = getUnits(Num[0]);
            }

            return (literal + decimal_part).toUpperCase();

        } else {
            return literal = null;
        }
    }

    private String getUnits(String number) {
        String num = number.substring(number.length() - 1);
        return UNITS[Integer.parseInt(num)];
    }

    private String getTens(String num) {
        int n = Integer.parseInt(num);
        if (n < 10) {
            return getUnits(num);
        } else if (n > 19) {
            String u = getUnits(num);
            int i = Integer.parseInt(num.substring(0, 1)) + 8;
            if (u.equals("")) {
                return DOZENS[i];
            } else {
                return DOZENS[i] + "y " + u;
            }
        } else {
            return DOZENS[n - 10];
            
        }
    }

    private String getHundreds(String num) {
        if (Integer.parseInt(num) > 99) {
            if (Integer.parseInt(num) == 100) {
                return " cien ";
            } else {
                return HUNDREDS[Integer.parseInt(num.substring(0, 1))] + getTens(num.substring(1));
            }
        } else {
            return getTens(Integer.parseInt(num) + "");
        }
    }

    private String getThousands(String number) {
        String c = number.substring(number.length() - 3);
        String m = number.substring(0, number.length() - 3);
        String n = "";
        if (Integer.parseInt(m) > 0) {
            n = getHundreds(m);
            return n + "mil " + getHundreds(c);
        } else {
            return "" + getHundreds(c);
        }

    }

    private String getMillions(String number) {
        String miles = number.substring(number.length() - 6);
        String million = number.substring(0, number.length() - 6);
        String n = "";
        if (Integer.parseInt(million) > 1) {
            n = getHundreds(million) + "millones ";
        } else {
            n = getUnits(million) + "millon ";
        }
        return n + getThousands(miles);
    }
}
