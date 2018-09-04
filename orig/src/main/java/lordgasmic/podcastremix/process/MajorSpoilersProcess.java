package lordgasmic.podcastremix.process;

import lordgasmic.podcastremix.jaxb.podcast.ItemType;
import lordgasmic.podcastremix.utils.APodcast;

public class MajorSpoilersProcess extends APodcast {

	private String urlStart = "http://www.majorspoilers.com/media/?.xml";

	protected void grabLinks() {
		getPodcastQueue().addAll(getLinks("criticalhit"));
	}

	// set up global objects
	protected void makeUrl() {
		url = urlStart.replace("?", getShortName());
	}

	@Override
	protected String parseItemType(ItemType it) {
		return it.getEnclosure().getUrl();
	}

}
