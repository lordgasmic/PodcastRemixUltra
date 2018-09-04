package lordgasmic.podcastremix.utils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import lordgasmic.common.atomic.Poison;
import lordgasmic.common.util.Jbom;
import lordgasmic.podcastremix.jaxb.podcast.ChannelType;
import lordgasmic.podcastremix.jaxb.podcast.ItemType;
import lordgasmic.podcastremix.jaxb.podcast.RssType;

public abstract class APodcast implements IPodcast {

	private ConcurrentLinkedQueue<PodcastDto> podcastQueue;
	private ConcurrentLinkedQueue<Poison> poisonQueue;
	private PodcastHistory podcastHistory;

	protected String url;
	protected String path;
	protected String fname;
	private String shortName;
	private String longName;
	private String html;

	private File rss;
	protected File historyFile;

	private Map<File, List<PodcastDto>> historyLinks;
	protected List<PodcastDto> links;

	public APodcast() {
		historyLinks = new HashMap<>();
	}

	public void execute() {
		grabLinks();
		getPoisonQueue().add(getPoison());
	}

	protected List<PodcastDto> getLinks(String shortName) {
		return getLinks(shortName, null, null);
	}

	protected List<PodcastDto> getLinks(String shortName, String longName) {
		return getLinks(shortName, longName, null);
	}

	protected List<PodcastDto> getLinks(String shortName, URL html) {
		return getLinks(shortName, null, html.toString());
	}

	// all processes use the same steps, abstract them
	private List<PodcastDto> getLinks(String shortName, String longName, String html) {
		this.shortName = shortName;
		this.longName = longName;
		this.html = html;

		init();
		downloadXml();
		parseXml();
		removeExtraLinks();
		addHistoryLinks();

		return links;
	}

	private void init() {
		path = PodcastConstant.Path + '/' + shortName + '/';
		fname = shortName + ".xml";

		links = new ArrayList<PodcastDto>();

		rss = new File(path + fname);
		historyFile = new File(path + "." + fname);

		makeUrl();
	}

	// no special config per environment here, private
	private void downloadXml() {
		Jbom.download(url, path, fname);
	}

	private void parseXml() {

		try {
			makeLinks(rss);

			rss.delete();
		} 
		catch (JAXBException e) {
			System.out.println("Error for " + shortName);
			e.printStackTrace();
		}
	}

	// remove existing links
	private void removeExtraLinks() {
		List<PodcastDto> load = PodcastHistory.load(historyFile);

		links.removeAll(load);
	}

	private void addHistoryLinks() {
		historyLinks.put(historyFile, links);
	}

	public void writeDeltas() {
		Iterator<Map.Entry<File, List<PodcastDto>>> it;
		it = historyLinks.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry<File, List<PodcastDto>> pod = it.next();
			PodcastHistory.add(pod.getKey(), pod.getValue());
		}
	}
	
	protected void makeLinks(File rssFile) throws JAXBException {
		RssType rssType;

		JAXBContext ctx = JAXBContext.newInstance(RssType.class);

		rssType = (RssType) ctx.createUnmarshaller().unmarshal(rssFile);

		ChannelType ct = rssType.getChannel();
		List<ItemType> items = ct.getItem();

		for (ItemType it : items) {

			PodcastDto dto = new PodcastDto();

			String link = parseItemType(it);
			String fileName = link.substring(link.lastIndexOf('/') + 1, link.length());
			dto.setUrl(link);
			dto.setFileName(fileName);
			dto.setPath(path);
			dto.setDate(new Date());

			links.add(dto);
		}
	}

	protected abstract void makeUrl();

	protected abstract void grabLinks();

	protected abstract String parseItemType(ItemType it);
	

	// -------------------------------------------------------------------------
	// --------------------- Getters/Setters Section ---------------------------
	// -------------------------------------------------------------------------

	public Poison getPoison() {
		return new Poison() {
			private static final long serialVersionUID = 1L;
		};
	}

	public ConcurrentLinkedQueue<PodcastDto> getPodcastQueue() {
		if (podcastQueue == null) {
			podcastQueue = new ConcurrentLinkedQueue<>();
		}
		return podcastQueue;
	}

	public void setPodcastQueue(ConcurrentLinkedQueue<PodcastDto> podcastQueue) {
		this.podcastQueue = podcastQueue;
	}
	
	public ConcurrentLinkedQueue<Poison> getPoisonQueue() {
		if (poisonQueue == null) {
			poisonQueue = new ConcurrentLinkedQueue<>();
		}
		return poisonQueue;
	}

	public void setPoisonQueue(ConcurrentLinkedQueue<Poison> poisonQueue) {
		this.poisonQueue = poisonQueue;
	}

	public PodcastHistory getPodcastHistory() {
		return podcastHistory;
	}

	public void setPodcastHistory(PodcastHistory podcastHistory) {
		this.podcastHistory = podcastHistory;
	}

	public String getShortName() {
		return shortName;
	}

	protected String getLongName() {
		return longName;
	}

	protected void setLongName(String longName) {
		this.longName = longName;
	}

	protected String getHtml() {
		return html;
	}

	protected void setHtml(String html) {
		this.html = html;
	}

	public List<PodcastDto> getLinks() {
		return links;
	}
}
