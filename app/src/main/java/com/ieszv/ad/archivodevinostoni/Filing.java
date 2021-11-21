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
    public static void readFileArrayList(File file){
        File f = new File(file, fileName);
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String linea;
            while ((linea = br.readLine()) != null) {
                listaVinos.add((Vino) Csv.getVino(linea));
            }
            br.close();
        } catch (Exception ignored){

        }
    }

    public static String ReadFile(File file, String fileName) {
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
    public static  boolean checkId(long id , ArrayList<Vino> listaVinos){
        boolean ok=false;
        long idtoCompare;
        if (listaVinos.size() > 0) {
            for (int i = 0; i < listaVinos.size(); i++) {
                 idtoCompare = listaVinos.get(i).getId();
                if (idtoCompare == id)
                    ok = true;
            }
        }
        return ok;

    }
    public static  void exChangeVinos(long id, Vino vinito){
        for (int i = 0; i < listaVinos.size(); i++) {
            if (listaVinos.get(i).getId() ==  id){
                listaVinos.set(i, vinito);
            }
        }
    }
    public static boolean borrado(long id){
        for (int i = 0; i < listaVinos.size(); i++) {
            if (listaVinos.get(i).getId() ==  id){
                listaVinos.remove(i);
                return true;

            }
        }
        return false;
    }

    public static Vino buscaVino(long idLong){
        Vino v = new Vino();
        for (Vino vinillos: listaVinos){
            long idToCompare;
            idToCompare = vinillos.getId();
            if(idToCompare == idLong){
                v=vinillos;
            }

        }
        return  v;
    }
    public static boolean rewriteFile(File file, String string) {
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
