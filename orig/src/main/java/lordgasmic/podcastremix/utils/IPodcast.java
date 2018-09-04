package lordgasmic.podcastremix.utils;

import java.util.List;

import lordgasmic.common.util.Executable;


public interface IPodcast extends Executable {

	public List<PodcastDto> getLinks();
	public void writeDeltas();
	
}
 