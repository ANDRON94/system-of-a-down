package com.util;

import com.controller.planner.PlannerController;

import java.io.*;
import java.net.URL;

/**
 * Created by root on 22.04.15.
 */
public class TodayManipulator {
    public static String readToday(){
        BufferedReader br = null;
        try {

            String sCurrentLine;
            URL url = PlannerController.class.getResource("/today.txt");
            File file = new File(url.getPath());
            br = new BufferedReader(new FileReader(file));

            sCurrentLine = br.readLine();
            return sCurrentLine;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
    public static void writeToday(String date){
        try {
            URL url = PlannerController.class.getResource("/today.txt");
            FileWriter fw = new FileWriter(new File(url.getPath()),false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(date);
            bw.close();
            System.out.println();
            System.out.println(readToday());
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
