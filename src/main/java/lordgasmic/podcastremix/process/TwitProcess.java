package lordgasmic.podcastremix.process;

import lordgasmic.podcastremix.jaxb.podcast.ItemType;
import lordgasmic.podcastremix.utils.APodcast;

public class TwitProcess extends APodcast {

	private String urlStart = "http://leoville.tv/podcasts/";

	protected void grabLinks() {
		getPodcastQueue().addAll(getLinks("twit"));
		getPodcastQueue().addAll(getLinks("sn"));
		getPodcastQueue().addAll(getLinks("tnt"));
		getPodcastQueue().addAll(getLinks("tri"));
		getPodcastQueue().addAll(getLinks("twich"));
		getPodcastQueue().addAll(getLinks("twig"));
		getPodcastQueue().addAll(getLinks("ww"));
		getPodcastQueue().addAll(getLinks("aaa"));
		getPodcastQueue().addAll(getLinks("kh"));
		getPodcastQueue().addAll(getLinks("twiet"));
	}

	// set up global objects
	protected void makeUrl() {
		url = urlStart + fname;
	}

	@Override
	protected String parseItemType(ItemType it) {
		return it.getLink();
	}

}
