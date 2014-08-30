package scan;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
/**
 *
 * @author Marcel Patek
 * @version 1
 * @since July 2013
 */

public class JAXBWriter {    
    
    public static String Writer(String id1, String id2, String id3, String id4, String id5, String id6, String id7) {
 
          Settings settings = new Settings();
	  settings.setId1(id1);
	  settings.setId2(id2);
	  settings.setId3(id3);
          settings.setId4(id4);
          settings.setId5(id5);
          settings.setId6(id6);
          settings.setId7(id7);
          
	  try {
                //JAXB.marshal(settings, new File("C:/file.xml"));
		
                File file = new File("nboscan_settings.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Settings.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
 
		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
 
		jaxbMarshaller.marshal(settings, file);
		//jaxbMarshaller.marshal(settings, System.out);
 
	      } catch (JAXBException e) {
		e.printStackTrace();
	      }
        return null;

	}
}
    

