package test.io;

public enum Seanson {
    SPRING("1",1),SUMER("2",2),AUTUMN("3",3),WINDER("4",4);

    private String str;
    private Integer Int;

    Seanson(String s, int i) {
      this.str = s;
      this.Int = i;
    }

    private String getStr(){
        return str;
    }

    private Integer getInt(){
        return Int;
    }

    public static String  getStr(Integer a){
        for (Seanson value : Seanson.values()) {
            if(value.getInt().equals(a)){
                return value.str;
            }
        }
        return null;
    }

    public static String  getint(String a){
        for (Seanson value : Seanson.values()) {
            if(value.getStr().equals(a)){
                return value.str;
            }
        }
        return null;
    }
}
