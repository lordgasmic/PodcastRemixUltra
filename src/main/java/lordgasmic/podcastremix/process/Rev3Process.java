package lordgasmic.podcastremix.process;

import lordgasmic.podcastremix.jaxb.podcast.ItemType;
import lordgasmic.podcastremix.utils.APodcast;

public class Rev3Process extends APodcast {

	private String urlStart = "http://revision3.com/?/feed/mp4-small";

	protected void grabLinks() {
		getPodcastQueue().addAll(getLinks("geekbeattv"));
		getPodcastQueue().addAll(getLinks("hak5"));
		getPodcastQueue().addAll(getLinks("nixiepixel"));
		getPodcastQueue().addAll(getLinks("osalt"));
		getPodcastQueue().addAll(getLinks("tekzilla"));
		getPodcastQueue().addAll(getLinks("deathbattle"));
		getPodcastQueue().addAll(getLinks("destructoid"));
		getPodcastQueue().addAll(getLinks("variant"));
		getPodcastQueue().addAll(getLinks("scamschool"));
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
