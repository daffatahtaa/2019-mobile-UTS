package com.daffatahta.uts;

public class Hitung {
    public enum Currency{
        USD,
        SGD,
        IDR
    }
    public static Currency fromString (String text){
        if (text!=null){
            for (Currency currency : Currency.values()){
                if (text.equalsIgnoreCase(currency.toString())){
                    return currency;
                }
            }
        }
        return null;
    }

    private double x;

    public Hitung(Currency from, Currency to){
        double multipler = 1;
        switch (from){
            case IDR: if (to == Currency.IDR){
                multipler = 1;
            }else if (to == Currency.SGD){
                multipler = 10000;
            }else{
                multipler = 15000;
            }
        break;

            case SGD:if (to == Currency.SGD){
                multipler = 1;
            }else if (to == Currency.USD){
                multipler = 1.37236;
            }else if (to == Currency.IDR){
                multipler = 0.0000971327;
            }
            break;

            case USD: if (to == Currency.USD){
                multipler = 1;
            }
            else if (to == Currency.SGD){
                multipler = 1.37236;
            }else {
                multipler = 14.128;
            }
        }x = multipler;
    }
    public double converted (double input){
        return input * x;
    }
}
