package lordgasmic.podcastremix.process;

import lordgasmic.podcastremix.jaxb.podcast.ItemType;
import lordgasmic.podcastremix.utils.APodcast;

public class MyExtraLifeProcess extends APodcast {

	private String urlStart = "http://myextralife.com/ftp/radio/?.xml";

	protected void grabLinks() {
		getPodcastQueue().addAll(getLinks("morningstream"));
		getPodcastQueue().addAll(getLinks("comicdorks"));
		getPodcastQueue().addAll(getLinks("finalscore"));
	}

	// set up global objects
	protected void makeUrl() {
		url = urlStart.replace("?", getShortName());
	}

	@Override
	protected String parseItemType(ItemType it) {
		return it.getLink();
	}

}
