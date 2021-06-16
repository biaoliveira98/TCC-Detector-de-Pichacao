package requisicao;

import java.io.IOException;

public class EnviaInstaloader {


    public void enviaCMD(Integer opcao, String requisicaoInstaloader, String nomeDir)
    {
        String linhaComando = "";

        if(opcao == 1)
            linhaComando = enviaReq(requisicaoInstaloader,nomeDir);
        else if(opcao == 2)
            linhaComando = criaDir(nomeDir);
        else if(opcao == 3)
            linhaComando = organizaDir(nomeDir);
        try
        {
            Runtime.getRuntime()
                    .exec(linhaComando);
;
        }
        catch(IOException iOException)
        {
            iOException.printStackTrace();
        }
    }


    public String enviaReq(String requisicaoInstaloader, String nomeDir){
        String req;
        req = "cmd.exe /k start " + requisicaoInstaloader
                + " --dirname-pattern=C:\\ProjetosJupyterNotebooks\\data\\instaloaderData\\"+nomeDir;
        return req;
    }

    public String criaDir(String nomeDir){
        String req;
        req = "cmd.exe /k start" + "mkdir C:\\ProjetosJupyterNotebooks\\data\\instaloaderData" + nomeDir;
        return req;
    }

    public String organizaDir(String nomeDir){
        String req;
        req = "cmd.exe /k start"
               + "move *.jpg C:\\ProjetosJupyterNotebooks\\data\\instaloaderData\\"
               + nomeDir;
        return req;
    }

}
