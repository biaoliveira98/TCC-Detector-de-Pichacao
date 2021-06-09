package requisicao;

import java.io.IOException;

public class EnviaInstaloader {

    public void enviaCMD(String requisicaoInstaloader)
    {
        try
        {
            Runtime.getRuntime()
                    .exec("cmd.exe /k start " + requisicaoInstaloader + " --dirname-pattern=C:\\ProjetosJupyterNotebooks\\data\\instaloaderData\\");

        }
        catch(IOException iOException)
        {
            iOException.printStackTrace();
        }
    }

}
