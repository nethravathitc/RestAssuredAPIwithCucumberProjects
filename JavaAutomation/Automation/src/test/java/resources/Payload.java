package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Location;
import pojo.addPlacePojo;

public class Payload {
	
	public addPlacePojo addplace(String name, String address, String language)
	{
		addPlacePojo p= new addPlacePojo();
		Location l= new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		p.setLocation(l);
		p.setAccuracy(50);
		p.setName(name);
		p.setPhone_number("123456789");
		p.setAddress(address);
		
		List<String> t= new ArrayList<>();
		t.add("shoe park\"");
		t.add("shop");
		p.setTypes(t);
		
		p.setWebsite("http://wwww.dfdd.com");
		p.setLanguage(language);
		return p;
	}

}
