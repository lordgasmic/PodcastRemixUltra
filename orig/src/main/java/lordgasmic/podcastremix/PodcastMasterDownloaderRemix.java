package lordgasmic.podcastremix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import lordgasmic.common.atomic.Poison;
import lordgasmic.common.util.Jbom;
import lordgasmic.podcastremix.utils.APodcast;
import lordgasmic.podcastremix.utils.PodcastDto;
import lordgasmic.podcastremix.utils.PodcastFactory;

public class PodcastMasterDownloaderRemix implements Runnable {

	private List<Thread> threads;
	private List<APodcast> podcasts;
	
	private boolean running;
	private int nbr;

	private ConcurrentLinkedQueue<PodcastDto> podcastQueue;
	private ConcurrentLinkedQueue<Poison> poisonQueue;
	
	public PodcastMasterDownloaderRemix(){
		nbr = 4;

		running = true;
		
		threads = new ArrayList<Thread>();
		podcasts = new ArrayList<APodcast>();

		podcastQueue = new ConcurrentLinkedQueue<PodcastDto>();		
		poisonQueue = new ConcurrentLinkedQueue<Poison>();		

		podcasts.add(PodcastFactory.createMajorSpoilers(podcastQueue, poisonQueue));
		//podcasts.add(PodcastFactory.createNightAttack(podcastQueue, poisonQueue));
		//podcasts.add(PodcastFactory.createTwit(podcastQueue, poisonQueue));
		//podcasts.add(PodcastFactory.createRev3(podcastQueue, poisonQueue));
		//podcasts.add(PodcastFactory.createMyExtraLife(podcastQueue, poisonQueue));
		//podcasts.add(PodcastFactory.createFeedBurner(podcastQueue, poisonQueue));
	}
	
	public static void main(String[] args) throws InterruptedException, IOException {
		//Authenticator.setDefault(new ProxyAuthentication(LgcConstants.GFS_E_ID, LgcConstants.GFS_PASS));
		
		PodcastMasterDownloaderRemix pmd = new PodcastMasterDownloaderRemix();
		
		pmd.execute();

		System.out.println("\nComplete. Press Return to continue.");
		System.in.read();
	}
	
	public void execute() throws InterruptedException{
		for (int i = 0; i < nbr; i++){
			threads.add(new Thread(this));
		}
		
		for (APodcast ap : podcasts) {
			ap.execute();
		}
		
		for (Thread t : threads){
			t.start();
		}
		
		for (Thread t : threads){
			t.join();
		}
		
		writeDeltas();
	}
	
	public void run() {
		while(running) {
			PodcastDto dto = podcastQueue.poll();
			
			if (dto == null) continue;

			Jbom.download(dto.getUrl(), dto.getPath(), dto.getFileName());
			
			if (poisonQueue.size() == podcasts.size() && podcastQueue.size() == 0 )
				running = false;
		}
	}
	
	private void writeDeltas() {
		for(APodcast ap : podcasts) {
			ap.writeDeltas();
		}
	}
	
}
