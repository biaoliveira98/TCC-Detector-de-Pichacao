package requisicao;

import entidades.*;

public class RequisicaoInstaloader {

    private String requisicaoInstaloader;
    private Localizacao localizacao;
    private Perfil perfil;
    private Hashtag hashtag;
    private Menu menu;
    private EnviaInstaloader enviaInstaloader;
    private Data data;
    private String nomeDir;

    public void definindoRequisicaoInstaloader() {

        setLocalizacao(new Localizacao());
        setPerfil(new Perfil());
        setHashtag(new Hashtag());
        setMenu(new Menu());
        setEnviaInstaloader(new EnviaInstaloader());
        setData(new Data());
        Integer op;

        menu.menuIncial(getLocalizacao());

        do{
            op = menu.definindoFiltros(getPerfil(), getHashtag(), getData());
        }while(op != 4);

        if(localizacao.getEstado() != null && localizacao.getCidade() != null){

            requisicaoInstaloader = "instaloader -V --geotags --login=" + menu.getPerfilEntrada();

            if(perfil.getNome() != null){
                requisicaoInstaloader = requisicaoInstaloader +" "
                                        + perfil.getNome();
                nomeDir = perfil.getNome();
            }
            else if(hashtag.getTag() != null){
                requisicaoInstaloader = "instaloader -V"
                                        + " \"" + hashtag.getTag()+ "\"" ;
                nomeDir = hashtag.getTag();
            }

            if (data.getDia() != null && data.getMes() != null && data.getAno() != null){
                requisicaoInstaloader = requisicaoInstaloader
                                        + " --post-filter=\"date_utc";
                 if(data.getOperador() == 1){ //a partir de / depois de
                     requisicaoInstaloader = requisicaoInstaloader + ">=";
                 }else if(data.getOperador() == 2){ //antes de
                     requisicaoInstaloader = requisicaoInstaloader + "<=";
                 }else if(data.getOperador() == 3){ //referentes a
                     requisicaoInstaloader = requisicaoInstaloader + "==";
                 }else{
                     System.out.println("Operação inválida");
                 }
                requisicaoInstaloader = requisicaoInstaloader +
                                        "datetime(" +
                                        + data.getAno() +"," + data.getMes()+ ","+ data.getDia() + ")\"";
            }

            System.out.println(requisicaoInstaloader);

            enviaInstaloader.enviaCMD(2, requisicaoInstaloader, ("\\" +nomeDir + "\\imgs"));
           // enviaInstaloader.enviaCMD(2, requisicaoInstaloader, ( "\\" +nomeDir + "\\imgs"));
            enviaInstaloader.enviaCMD(1, requisicaoInstaloader, nomeDir);
           // enviaInstaloader.enviaCMD(3, requisicaoInstaloader, (nomeDir + "\\imgs"));
        }
        else
        {
            System.out.println("Operação invalida!");
        }

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


    public Data getData() {
        return data;
    }
    public void setData(Data data) {
        this.data = data;
    }

    public EnviaInstaloader getEnviaInstaloader() {
        return enviaInstaloader;
    }
    public void setEnviaInstaloader(EnviaInstaloader enviaInstaloader) {
        this.enviaInstaloader = enviaInstaloader;
    }
}
