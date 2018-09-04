package lordgasmic.podcastremix.utils;

import java.util.concurrent.ConcurrentLinkedQueue;

import lordgasmic.common.atomic.Poison;
import lordgasmic.podcastremix.process.FeedBurnerProcess;
import lordgasmic.podcastremix.process.MajorSpoilersProcess;
import lordgasmic.podcastremix.process.MyExtraLifeProcess;
import lordgasmic.podcastremix.process.NightAttackProcess;
import lordgasmic.podcastremix.process.Rev3Process;
import lordgasmic.podcastremix.process.TwitProcess;

public class PodcastFactory {
 
	public static APodcast createTwit(ConcurrentLinkedQueue<PodcastDto> podcastQueue, ConcurrentLinkedQueue<Poison> poisonQueue) {
		APodcast proc = new TwitProcess();
		proc.setPodcastQueue(podcastQueue);
		proc.setPoisonQueue(poisonQueue);
		
		return proc;
	}	
	
	public static APodcast createRev3(ConcurrentLinkedQueue<PodcastDto> podcastQueue, ConcurrentLinkedQueue<Poison> poisonQueue) {
		APodcast proc = new Rev3Process();
		proc.setPodcastQueue(podcastQueue);
		proc.setPoisonQueue(poisonQueue);
		
		return proc;
	}
	
	public static APodcast createMyExtraLife(ConcurrentLinkedQueue<PodcastDto> podcastQueue, ConcurrentLinkedQueue<Poison> poisonQueue) {
		APodcast proc = new MyExtraLifeProcess();
		proc.setPodcastQueue(podcastQueue);
		proc.setPoisonQueue(poisonQueue);
		
		return proc;
	}
	
	public static APodcast createFeedBurner(ConcurrentLinkedQueue<PodcastDto> podcastQueue, ConcurrentLinkedQueue<Poison> poisonQueue) {
		APodcast proc = new FeedBurnerProcess();
		proc.setPodcastQueue(podcastQueue);
		proc.setPoisonQueue(poisonQueue);
		
		return proc;
	}
	
	public static APodcast createMajorSpoilers(ConcurrentLinkedQueue<PodcastDto> podcastQueue, ConcurrentLinkedQueue<Poison> poisonQueue) {
		APodcast proc = new MajorSpoilersProcess();
		proc.setPodcastQueue(podcastQueue);
		proc.setPoisonQueue(poisonQueue);
		
		return proc;
	}
	
	public static APodcast createNightAttack(ConcurrentLinkedQueue<PodcastDto> podcastQueue, ConcurrentLinkedQueue<Poison> poisonQueue) {
		APodcast proc = new NightAttackProcess(); 
		proc.setPodcastQueue(podcastQueue);
		proc.setPoisonQueue(poisonQueue);
		
		return proc;
	}
}
