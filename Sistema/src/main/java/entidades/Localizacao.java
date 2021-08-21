package entidades;

public class Localizacao {

    private String local;

    @Override
    public String toString() {
        return "Localizacao{" +
                "local='" + local + '\'' +
                '}';
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }



}
