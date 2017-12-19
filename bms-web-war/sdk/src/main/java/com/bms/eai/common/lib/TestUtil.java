package com.bms.eai.common.lib;

public class TestUtil {

	//private String FILE_PATH = "E:/Personal_Ws/bms_client_api_routes.xml";
	
	public TestUtil() {
		System.out.println("Comes to Constructor");
		
		try {
			/*JAXBContext jaxbContext = JAXBContext.newInstance(BmsModules.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			BmsModules bm = (BmsModules) jaxbUnmarshaller.unmarshal(new File(FILE_PATH));

			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			*/
			//marshaller.marshal(bm, System.out);
			//System.out.println(bm.getPropertyDetails().getServerDetails().getHostName());
			//System.out.println(bm.getPropertyDetails().getServerDetails().getPortNo());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		new TestUtil();
	}

}
