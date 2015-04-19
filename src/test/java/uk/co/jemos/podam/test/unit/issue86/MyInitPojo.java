package uk.co.jemos.podam.test.unit.issue86;

import uk.co.jemos.podam.common.PodamExclude;

import java.io.Serializable;

/**
 * Created by tedonema on 14/04/2015.
 */
public class MyInitPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String string1;

    private String string2;

    @PodamExclude
    private String backupString1;

    @PodamExclude
    private String backupString2;

    public MyInitPojo() {}

    /**
     * It re-initialise the strings
     * @param s1 String 1
     * @param s2 String 2
     */
    public void init(String s1, String s2) {

        this.string1 = s1;
        this.string2 = s2;
    }

    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1;
        this.backupString1 = string1;
    }

    public String getString2() {
        return string2;
    }

    public void setString2(String string2) {
        this.string2 = string2;
        this.backupString2 = string2;
    }

    public String getBackupString1() {
        return backupString1;
    }

    public String getBackupString2() {
        return backupString2;
    }
}
