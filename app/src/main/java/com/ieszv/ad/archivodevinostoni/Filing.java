package com.ieszv.ad.archivodevinostoni;

import static com.ieszv.ad.archivodevinostoni.MainActivity.fileName;

import com.ieszv.ad.archivodevinostoni.data.Vino;
import com.ieszv.ad.archivodevinostoni.util.Csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Filing {
    public static ArrayList<Vino> listaVinos = new ArrayList<>();

    /**
     * Metodo para Escribir un archivo
     * @param file
     * @param string
     * @return
     */
    public static boolean writeFile(File file, String string) {
        File f = new File(file, string);
        FileWriter fw;
        boolean ok = true;
        try {
            fw = new FileWriter(f, true);
            fw.write(string);
            fw.write("\n");
            fw.flush();
            fw.close();
        } catch (IOException e) {
            ok = false;
        }
        return ok;
    }

    /**
     * LEE EL ARCHIVO Y LO PASA A UN ARRAY LIST
     *
     */
    public static void readFileToArrayList(File file){
        File f = new File(file, fileName);
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String linea;
            while ((linea = br.readLine()) != null) {
                listaVinos.add(Csv.getVino(linea));
            }
            br.close();
        } catch (Exception ignored){

        }
    }

    /**
     * METODO QUE LO UTILIZAREMOS PARA SETEAR EL TEXTO EN LAS LISTA
     * @param file
     * @param
     * @return
     */
    public static String ReadFile(File file) {
        File f = new File(file, fileName);
        StringBuilder texto = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String linea;
            while ((linea = br.readLine()) != null) {
                texto.append(linea).append("\n");

            }
            br.close();
        } catch (IOException e) {
            texto = null;
        }
        assert texto != null;
        return texto.toString();
    }

    /**
     * METODO PARA CHECKEAR LA ID
     * @param id
     * @param listaVinos
     * @return
     */
    public static  boolean checkId(long id , ArrayList<Vino> listaVinos){
        boolean ok=false;
        if (listaVinos.size() > 0) {
            for (Vino vino : listaVinos){
                if (id == vino.getId()) {
                    ok = true;

                }
            }
        }
        return ok;

    }

    /**
     * Reescribe los vinos
     * @param id
     * @param vinito
     */
    public static  void exchangeTheWine(long id, Vino vinito){
        for (int i = 0; i < listaVinos.size(); i++) {
            if (listaVinos.get(i).getId() ==  id){
                listaVinos.set(i, vinito);
            }
        }
    }

    /**
     * Borra el vino
     * @param id
     * @return
     */
    public static boolean deleteTheWine(long id){
        for (int i = 0; i < listaVinos.size(); i++) {
            if (listaVinos.get(i).getId() ==  id){
                listaVinos.remove(i);
                return true;

            }
        }
        return false;
    }

    /**
     * Busca el vino
     * @param idLong
     * @return
     */
    public static Vino searchTheWine(long idLong){
        Vino v = new Vino();
        for (Vino vinillos: listaVinos){
            if(vinillos.getId() == idLong){
                v=vinillos;
            }

        }
        return  v;
    }

    /**
     * REESCRIBE EL ARCHIVO
     * @param file
     * @param string
     * @return
     */
    public static boolean rewritetheFile(File file, String string) {
        File f = new File(file, fileName);

        boolean ok = true;
        if (f.exists()){
            f.delete();
            f = new File(file,fileName);
            FileWriter fw;
            String csv;
            for (int i = 0; i <listaVinos.size() ; i++) {
                if(listaVinos.get(i).getId() != 0){
                    csv = Csv.getCsv(listaVinos.get(i));
                    try {

                        fw = new FileWriter(f, true);
                        fw.write(string);
                        fw.write(csv +"\n");
                        fw.flush();
                        fw.close();
                    } catch (IOException e) {
                        ok = false;
                    }

                }
            }
        }

        return ok;
    }


}