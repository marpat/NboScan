package scan;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
* @author Marcel Patek
* @version 1
* @since July 2013
* */

@XmlRootElement
public class Settings {

    String id1;
    String id2;
    String id3;
    String id4;
    String id5;
    String id6;
    String id7;

    public String getId1() {
        return id1;
    }

    @XmlElement
    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getId2() {
        return id2;
    }

    @XmlElement
    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getId3() {
        return id3;
    }

    @XmlElement
    public void setId3(String id3) {
        this.id3 = id3;
    }

    public String getId4() {
        return id4;
    }

    @XmlElement
    public void setId4(String id4) {
        this.id4 = id4;
    }

    public String getId5() {
        return id5;
    }

    @XmlElement
    public void setId5(String id5) {
        this.id5 = id5;
    }

    public String getId6() {
        return id6;
    }

    @XmlElement
    public void setId6(String id6) {
        this.id6 = id6;
    }
    
    public String getId7() {
        return id7;
    }

    @XmlElement
    public void setId7(String id7) {
        this.id7 = id7;
    }
    //  public String toOut1() {
    //      return this.id1; // returns first field - full array follows here  //"Settings [id1="+this.id1+", id2="+this.id2+", id3=100]";
    //  }
}
