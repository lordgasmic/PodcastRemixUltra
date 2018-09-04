package lordgasmic.podcastremix.process;

import lordgasmic.podcastremix.jaxb.podcast.ItemType;
import lordgasmic.podcastremix.utils.APodcast;

public class NightAttackProcess extends APodcast {
	
	private String urlString = "http://nightattack.tv/feed/audio";

	@Override
	protected void makeUrl() {
		url = urlString;
	}

	@Override
	protected void grabLinks() {
		getPodcastQueue().addAll(getLinks("nightattack"));
	}

	@Override
	protected String parseItemType(ItemType it) {
		return it.getEnclosure().getUrl();
	}

}
