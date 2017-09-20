package ua.kiev.prog;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws Exception {
        File file = new File("trains.xml");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.now();

        Trains trains = new Trains();
        trains.addTrain(new Train("Kyiv","Odessa",(dtf.format(localDate)),"12:00"));
        trains.addTrain(new Train("Odessa","Kyiv",(dtf.format(localDate)),"16:00"));

        addTrainsToXML(trains, file);

        readTrainsFromXML(trains, file);

        trainCheck(file);
    }

    //Using JAXB and Marshaller.
    public static void addTrainsToXML(Trains trains, File file) {
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(Trains.class);
            Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(trains, file);
            //marshaller.marshal(trains, System.out);


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //Same, JAXB and Marshaller.
    public static void readTrainsFromXML(Trains trains, File file) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Trains.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            trains = (Trains)unmarshaller.unmarshal(file);
            System.out.println(trains);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //Simple XML nodes parsing.
    public static void trainCheck(File file) throws ParseException {
        try {
            Document xmlDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);

            Element root = xmlDoc.getDocumentElement();
            NodeList nodeList = root.getChildNodes();

            for(int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String departure = element.getElementsByTagName("departure")
                            .item(0).getChildNodes()
                            .item(0).getNodeValue();

                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    Date depTime = sdf.parse(departure);

                    String from = element.getElementsByTagName("from")
                            .item(0).getChildNodes()
                            .item(0).getNodeValue();
                    String to = element.getElementsByTagName("to")
                            .item(0).getChildNodes()
                            .item(0).getNodeValue();
                    String date = element.getElementsByTagName("date")
                            .item(0).getChildNodes()
                            .item(0).getNodeValue();

                    if(depTime.after(sdf.parse("15:00")) && depTime.before(sdf.parse("19:00"))) {
                        System.out.println("You need the train from " +
                        from + " to " + to + " which departures " + date + " at " + departure + ".");
                    }

                }
            }

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
