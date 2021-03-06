package edu.upm.midas.model.response;

import java.util.Date;

public class Disease {

    private String diseaseId;
    private String name;
    private String albumId;
    private Date snapshot;
    private String sourceId;
    private String sourceName;
    private String url;
    private String urlId;
    private boolean partlyIrrelevant;
    private boolean totallyIrrelevant;


    public String getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(String diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public Date getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(Date snapshot) {
        this.snapshot = snapshot;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlId() {
        return urlId;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId;
    }

    public boolean isPartlyIrrelevant() {
        return partlyIrrelevant;
    }

    public void setPartlyIrrelevant(boolean partlyIrrelevant) {
        this.partlyIrrelevant = partlyIrrelevant;
    }

    public boolean isTotallyIrrelevant() {
        return totallyIrrelevant;
    }

    public void setTotallyIrrelevant(boolean totallyIrrelevant) {
        this.totallyIrrelevant = totallyIrrelevant;
    }
}
