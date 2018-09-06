package sample;

import java.io.*;
public class saveScoreToFile {

    String everything =null;
    int scr=0;
        public void save(Game game, String fileName){

            try
            {
                BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
                out.write(Integer.toString(game.temp));
                out.close();

            }
            catch (IOException e){ System.out.println("Exception "+e); }
        }
        public void load(String fileName){
            try{
            BufferedReader brr = new BufferedReader(new FileReader(fileName));

            StringBuilder sb = new StringBuilder();
            String line = brr.readLine();

            while (line != null) {
                sb.append(line);
                line = brr.readLine();
            }
            everything = sb.toString();
            scr=Integer.parseInt(everything);

        }
            catch (IOException e){ System.out.println("Exception "+e); }
        }
    }

