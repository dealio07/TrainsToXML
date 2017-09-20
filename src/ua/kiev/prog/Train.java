package ua.kiev.prog;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.ParseException;

@XmlRootElement(name = "train")
public class Train {

    private String from;
    private String to;
    private String date;
    private String departure;

    public Train() {}

    public Train(String from, String to, String date, String departure) throws ParseException {
        this.from = from;
        this.to = to;
        this.date = date;
        this.departure = departure;
    }

    @Override
    public String toString() {
        return "["+from+", "+to+", "+date+", "+departure+"]";
    }

    @XmlElement(name = "from")
    public void setFrom(String from) {
        this.from = from;
    }

    @XmlElement(name = "to")
    public void setTo(String to) {
        this.to = to;
    }

    @XmlElement(name = "date")
    public void setDate(String date) {
        this.date = date;
    }

    @XmlElement(name = "departure")
    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDate() {
        return date;
    }

    public String getDeparture() {
        return departure;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
