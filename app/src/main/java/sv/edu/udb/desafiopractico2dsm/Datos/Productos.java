package sv.edu.udb.desafiopractico2dsm.Datos;

import java.security.Key;

public class Productos {
    private String Nomb;
    private String Pres;
    private String Labo;
    private String Desc;
    private String Prec ;
    private String key;
    public Productos(){}
    public Productos( String Nomb,String Pres,String Labo,String Desc,String Prec)
    {
        this.setNomb(Nomb);
        this.setPres(Pres);
        this.setLabo(Labo);
        this.setDesc(Desc);
        this.setPrec(Prec);
    }

    public String getNomb() {
        return Nomb;
    }

    public void setNomb(String nomb) {
        Nomb = nomb;
    }

    public String getPres() {
        return Pres;
    }

    public void setPres(String pres) {
        Pres = pres;
    }

    public String getLabo() {
        return Labo;
    }

    public void setLabo(String labo) {
        Labo = labo;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getPrec() {
        return Prec;
    }

    public void setPrec(String prec) {
        Prec = prec;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
