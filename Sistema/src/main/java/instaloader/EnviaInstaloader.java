package instaloader;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EnviaInstaloader {


    public void enviaCMD(String requisicaoInstaloader, String nomeDir)
    {
        String[] cmds = {
                requisicaoInstaloader
                        + " --dirname-pattern=C:\\ProjetosJupyterNotebooks\\data\\instaloaderData\\"+nomeDir
        };

        try {
            ProcessBuilder builder = new ProcessBuilder("cmd", "/c",
                    String.join("& ", cmds));

            builder.redirectErrorStream(true);

            Process p = builder.start();

            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;

            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }

                System.out.println(line);
            }
        } catch(Exception e) {
            System.err.println(e);
        }
    }



}
