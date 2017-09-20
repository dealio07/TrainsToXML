package ua.kiev.prog;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlRootElement(namespace = "trains")
public class Trains {

    @XmlElement(namespace = "train")
    private List<Train> trains = new ArrayList<>();

    public void addTrain(Train train) {
        trains.add(train);
    }

    @Override
    public String toString() {
        return Arrays.deepToString(trains.toArray());
    }
}
