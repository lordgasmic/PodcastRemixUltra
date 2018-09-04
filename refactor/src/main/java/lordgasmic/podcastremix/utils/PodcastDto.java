package lordgasmic.podcastremix.utils;

import java.io.Serializable;
import java.util.Date;

public class PodcastDto implements Serializable {

	private static final long serialVersionUID = -7158907144031629172L;

	private String fileName;
	private String path;
	private String url;
	private Date date;

	public void setFileName(String fileName) {
		this.fileName = fileName;
	} 

	public String getFileName() {
		return fileName;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "PodcastDto [fileName=" + fileName + ", path=" + path + ", url="
				+ url + ", date=" + date + "]";
	}

	public boolean equals(Object o) {
		if (o == null || !(o instanceof PodcastDto)) {
			return false;
		}

		PodcastDto obj = (PodcastDto) o;
		if (this.fileName.equals(obj.fileName)) {
			if (this.url.equals(obj.url)) {
				return true;
			}
		}

		return false;
	}

}
