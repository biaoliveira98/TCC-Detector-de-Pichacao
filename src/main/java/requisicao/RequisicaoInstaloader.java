package requisicao;

import entidades.Hashtag;
import entidades.Localizacao;
import entidades.Menu;
import entidades.Perfil;

public class RequisicaoInstaloader {

    private String requisicaoInstaloader;
    private Localizacao localizacao;
    private Perfil perfil;
    private Hashtag hashtag;
    private Menu menu;
    private EnviaInstaloader enviaInstaloader;

    public void definindoRequisicaoInstaloader() {

        setLocalizacao(new Localizacao());
        setPerfil(new Perfil());
        setHashtag(new Hashtag());
        setMenu(new Menu());
        setEnviaInstaloader(new EnviaInstaloader());
        Integer op;

        menu.menuIncial(getLocalizacao());

        do{
            op = menu.definindoFiltros(getPerfil(), getHashtag());
        }while(op != 3);

        if(localizacao.getEstado() != null){

            requisicaoInstaloader = "instaloader --geotags --login=" + menu.getPerfilEntrada();

            if(perfil.getNome() != null){
                requisicaoInstaloader = requisicaoInstaloader +" "
                                        + perfil.getNome();
            }
            else if(hashtag.getTag() != null){
                requisicaoInstaloader = requisicaoInstaloader
                                        + " \"" + hashtag.getTag()+ "\"" ;
            }

        }

        System.out.println(requisicaoInstaloader);

        enviaInstaloader.enviaCMD(requisicaoInstaloader);

    }



//Getters and setters

    public String getRequisicaoInstaloader() {
        return requisicaoInstaloader;
    }
    public void setRequisicaoInstaloader(String requisicaoInstaloader) {
        this.requisicaoInstaloader = requisicaoInstaloader;
    }


    public Localizacao getLocalizacao() {
        return localizacao;
    }
    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }


    public Perfil getPerfil() {
        return perfil;
    }
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }


    public Hashtag getHashtag() {
        return hashtag;
    }
    public void setHashtag(Hashtag hashtag) {
        this.hashtag = hashtag;
    }


    public Menu getMenu() {
        return menu;
    }
    public void setMenu(Menu menu) {
        this.menu = menu;
    }


    public EnviaInstaloader getEnviaInstaloader() {
        return enviaInstaloader;
    }
    public void setEnviaInstaloader(EnviaInstaloader enviaInstaloader) {
        this.enviaInstaloader = enviaInstaloader;
    }
}
