// Reads xml file and returns different tags in separate methods. 
// For more tags, use an array.
package scan;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/*
 * @author Marcel Patek
 * @version 1
 * @since July 2013
 * 
 */
public class JAXBReader {

    String Reader1() {
        try {
            //Settings settings = JAXB.unmarshall(new File("file.xml"), Settings.class);
            File file = new File("nboscan_settings.xml");

            JAXBContext jaxbContext = JAXBContext.newInstance(Settings.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Settings settings = (Settings) jaxbUnmarshaller.unmarshal(file);
            return settings.getId1();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    String Reader2() {
        try {
            File file = new File("nboscan_settings.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Settings.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Settings settings = (Settings) jaxbUnmarshaller.unmarshal(file);
            return settings.getId2();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    String Reader3() {
        try {
            File file = new File("nboscan_settings.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Settings.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Settings settings = (Settings) jaxbUnmarshaller.unmarshal(file);
            return settings.getId3();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    String Reader4() {
        try {
            File file = new File("nboscan_settings.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Settings.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Settings settings = (Settings) jaxbUnmarshaller.unmarshal(file);
            return settings.getId4();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    String Reader5() {
        try {
            File file = new File("nboscan_settings.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Settings.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Settings settings = (Settings) jaxbUnmarshaller.unmarshal(file);
            return settings.getId5();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    String Reader6() {
        try {
            File file = new File("nboscan_settings.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Settings.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Settings settings = (Settings) jaxbUnmarshaller.unmarshal(file);
            return settings.getId6();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    String Reader7() {
        try {
            File file = new File("nboscan_settings.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Settings.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Settings settings = (Settings) jaxbUnmarshaller.unmarshal(file);
            return settings.getId7();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
