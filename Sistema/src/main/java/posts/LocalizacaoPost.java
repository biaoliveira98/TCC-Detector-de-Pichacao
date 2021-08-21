package posts;

import entidades.Localizacao;

public class LocalizacaoPost extends Localizacao {

    private String urlMaps;

    @Override
    public String toString() {
        return "Localizacao{" +
                "local='" + super.getLocal() + '\'' +
                ", urlMaps='" + urlMaps + '\'' +
                '}';
    }

    public String getLocal() {
        return super.getLocal();
    }

    public void setLocal(String local) {
        super.setLocal(local);
    }

    public String getUrlMaps() {
        return urlMaps;
    }

    public void setUrlMaps(String urlMaps) {
        this.urlMaps = urlMaps;
    }
}
