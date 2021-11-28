package com.ieszv.ad.archivodevinostoni.util;


import com.ieszv.ad.archivodevinostoni.data.Vino;

public class Csv {

    public static Vino getVino(String str) {
        String[] atributos = str.split(";");
        Vino v = null;
        if(atributos.length >= 7) {
            v = new Vino();
            try {
                v.setId(Integer.parseInt(atributos[0].trim()));
            } catch (NumberFormatException ignored) {
            }
            v.setNombre(atributos[1].trim());
            v.setBodega(atributos[2].trim());
            v.setColor(atributos[3].trim());
            v.setOrigen(atributos[4].trim());
            try {
                v.setGraduacion(Double.parseDouble(atributos[5].trim()));
            } catch (NumberFormatException ignored) {
            }
            try {
                v.setFecha(Integer.parseInt(atributos[6].trim()));
            } catch (NumberFormatException ignored) {
            }
        }
        return v;
    }

    public static String getCsv(Vino v) {
        return v.getId() + ";" +
                v.getNombre() + ";" +
                v.getBodega() + ";" +
                v.getColor() + ";" +
                v.getOrigen() + ";" +
                v.getGraduacion() + ";" +
                v.getFecha() + ";";
    }
}
