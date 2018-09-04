package lordgasmic.podcastremix.process;

import lordgasmic.podcastremix.jaxb.podcast.ItemType;
import lordgasmic.podcastremix.utils.APodcast;

public class FeedBurnerProcess extends APodcast {

	private final String urlStart = "http://feeds.feedburner.com/";
	private final String urlEnd = "?format=xml";

	protected void grabLinks() {
		getPodcastQueue().addAll(getLinks("snl", "TheSwordAndLaser"));
		getPodcastQueue().addAll(getLinks("fsl", "FslTonight"));
		getPodcastQueue().addAll(getLinks("forcast", "fourcastpodcast/hxMY"));
	}

	// set up global objects
	protected void makeUrl() {
		url = urlStart + getLongName() + urlEnd;
	}

	@Override
	protected String parseItemType(ItemType it) {
		if (null == it.getEnclosure())
			return null;
		return it.getEnclosure().getUrl();
	}
 
}
