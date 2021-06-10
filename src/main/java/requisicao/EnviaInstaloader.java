package requisicao;

import java.io.IOException;

public class EnviaInstaloader {


    public void enviaCMD(String requisicaoInstaloader, String nomeDir)
    {
        String req;
        req = "cmd.exe /k start " + requisicaoInstaloader
               + " --dirname-pattern=C:\\ProjetosJupyterNotebooks\\data\\instaloaderData\\"+nomeDir;

        try
        {
            Runtime.getRuntime()
                    .exec(req);
;
        }
        catch(IOException iOException)
        {
            iOException.printStackTrace();
        }
    }

    public void criaDir(String nomeDir){
        String req;
        req = "cmd.exe /k start" + "mkdir C:\\ProjetosJupyterNotebooks\\data\\instaloaderData" + nomeDir;

        try
        {
            Runtime.getRuntime()
                    .exec(req);
            ;
        }
        catch(IOException iOException)
        {
            iOException.printStackTrace();
        }
    }

}
