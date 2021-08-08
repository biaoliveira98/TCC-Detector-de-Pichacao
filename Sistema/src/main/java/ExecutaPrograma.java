import flask.EnviaRequisicaoFlask;
import instaloader.RequisicaoInstaloader;
import org.json.JSONException;

import java.io.IOException;

public class ExecutaPrograma {

    public static void main(String[] args) throws IOException, InterruptedException {

        RequisicaoInstaloader requisicaoInstaloader = new RequisicaoInstaloader();
        EnviaRequisicaoFlask enviaRequisicaoFlask = new EnviaRequisicaoFlask();

        requisicaoInstaloader.definindoRequisicaoInstaloader();
        enviaRequisicaoFlask.predictImagem(
                   "C:\\ProjetosJupyterNotebooks\\data\\instaloaderData\\"
                        + requisicaoInstaloader.getNomeDir()
                        + "\\imgs"
        );

    }



}
