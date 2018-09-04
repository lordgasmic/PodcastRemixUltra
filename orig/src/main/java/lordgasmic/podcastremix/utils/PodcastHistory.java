package lordgasmic.podcastremix.utils;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PodcastHistory {

	@SuppressWarnings("unchecked")
	public static List<PodcastDto> load(File historyFile){
		List<PodcastDto> dto = new ArrayList<PodcastDto>();
		
		try {
			XMLDecoder xml = new XMLDecoder(new FileInputStream(historyFile));
			dto = (List<PodcastDto>)xml.readObject();
			xml.close();
		}
		catch (FileNotFoundException e){
			System.out.println("No history file found, starting new.");
		}
		
		return dto;
	}
	
	private static void store(File historyFile, List<PodcastDto> dto){
		try {
			XMLEncoder xml = new XMLEncoder(new FileOutputStream(historyFile));
			xml.writeObject(dto);
			xml.close();
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public static void add(File historyFile, List<PodcastDto> dto){
		List<PodcastDto> dtos = load(historyFile);
		dtos.addAll(dto);
		store(historyFile, dtos);
	}
}
