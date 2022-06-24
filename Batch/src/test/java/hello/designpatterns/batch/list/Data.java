package hello.designpatterns.batch.list;

public class Data {
    private Integer v1;

    private String v2;

    public Data (Integer v1, String v2) {
        setV1(v1);
        setV2(v2);
    }

    public Integer getV1() {
        return v1;
    }

    public void setV1(Integer v1) {
        if (v1 != null) {
            this.v1 = v1;
        }
    }

    public String getV2() {
        return v2;
    }

    public void setV2(String v2) {
        if (v2 != null) {
            this.v2 = v2;
        }
    }
}
