package com.ieszv.ad.archivodevinostoni;

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


    public static boolean writeFile(File file,String filename , String string){
        File f = new File(file, filename);
        FileWriter fw = null; //FileWriter(File f,boolean append)
        boolean ok = true;
        try {
            fw = new FileWriter(f, true);
            fw.write(string + "\n");
            fw.flush();
            fw.close();
        } catch (IOException e) {
            ok = false;
        }
        return ok;
    }
    public static  boolean readFileBooleano(File file,String fileName){
        File f = new File(file, fileName);
        boolean readok = true;
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String linea;
            while ((linea = br.readLine()) != null) {
                listaVinos.add((Vino) Csv.getVino(linea));
            }
            br.close();
        } catch (Exception e){
            readok = false;
        }
        return readok;
    }



    public static  String readFile(File file, String filename){
        File f = new File(file, filename);
        String texto = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String linea;
            while((linea = br.readLine()) != null) {
                texto +=  linea + "\n";
            }
            br.close();
        } catch (Exception e){
            texto = null;
        }
        return texto;
    }
    public  static  boolean isFileCreated(File file, String filename){
        File f = new File(file, filename);
        boolean readok = true;
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(";")){
                    listaVinos.add((Vino) Csv.getVino(linea));
                }
            }
            br.close();
        } catch (Exception e){
            readok = false;
        }
        return readok;
    }



    public static Vino searchWine(int id){
        Vino vinillo = new Vino();
        for (Vino v: listaVinos) {
            int idABuscar = (int) v.getId();
            if (idABuscar == id){
                vinillo = v;
            }
        }
        return vinillo;
    }


    public static void reWriteWine(int id, Vino wine){
        for (int i = 0; i < listaVinos.size(); i++) {
            if (listaVinos.get(i).getId() == (long) id){
                listaVinos.set(i, wine);
            }
        }
    }
    public static boolean deleteWine(int id){
        boolean ok = false;
        for (int i = 0; i < listaVinos.size(); i++) {
            if (listaVinos.get(i).getId() == (long) id){
                listaVinos.remove(i);
                ok = true;
            }
        }
        return ok;
    }
    public static boolean checkIDAdd(long id){
        boolean x = false;
        if (listaVinos.size() > 0) {
            for (int i = 0; i < listaVinos.size(); i++) {
                if(listaVinos.get(i) != null){
                    if(listaVinos.get(i).getClass().getSimpleName().equals("Vino")){
                        Long idToCompare = listaVinos.get(i).getId();
                        if (idToCompare.equals(id)) {
                            x = true;
                        }
                    }
                }


            }
        }
        return x;
    }
    public  static  boolean isFileWritten(File file , String fileName){
        File f = new File(file, fileName);
        boolean ok = true;
        if (f.exists()) {
            f.delete();
            f = new File(file, fileName);
            FileWriter fw = null; //FileWriter(File f,boolean append)
            String vcsv;
            for (int i = 0; i < listaVinos.size(); i++) {
                if (listaVinos.get(i).getId() != 0){
                    vcsv = Csv.getCsv(listaVinos.get(i));
                    try {
                        fw = new FileWriter(f, true);
                        fw.write( vcsv + "\n");
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