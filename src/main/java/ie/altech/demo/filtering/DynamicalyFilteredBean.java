package ie.altech.demo.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("FilteredBean")
public class DynamicalyFilteredBean {

    private String field_1;
    private String field_2;
    private String field_3;
    private String field_4;
    private String field_5;
    private String field_6;

    public DynamicalyFilteredBean(String field_1, String field_2, String field_3, String field_4, String field_5, String field_6) {
        this.field_1 = field_1;
        this.field_2 = field_2;
        this.field_3 = field_3;
        this.field_4 = field_4;
        this.field_5 = field_5;
        this.field_6 = field_6;
    }

    public String getField_1() {
        return field_1;
    }

    public void setField_1(String field_1) {
        this.field_1 = field_1;
    }

    public String getField_2() {
        return field_2;
    }

    public void setField_2(String field_2) {
        this.field_2 = field_2;
    }

    public String getField_3() {
        return field_3;
    }

    public void setField_3(String field_3) {
        this.field_3 = field_3;
    }

    public String getField_4() {
        return field_4;
    }

    public void setField_4(String field_4) {
        this.field_4 = field_4;
    }

    public String getField_5() {
        return field_5;
    }

    public void setField_5(String field_5) {
        this.field_5 = field_5;
    }

    public String getField_6() {
        return field_6;
    }

    public void setField_6(String field_6) {
        this.field_6 = field_6;
    }
}
