package it.smartcommunitylab.platform.idea.model;

import java.sql.Blob;

/**
 * The Blob model class for lazy loading the shortDesc column in Idea.
 *
 * @author mirko perillo
 * @see Idea
 * @generated
 */
public class IdeaShortDescBlobModel {
    private long _ideaId;
    private Blob _shortDescBlob;

    public IdeaShortDescBlobModel() {
    }

    public IdeaShortDescBlobModel(long ideaId) {
        _ideaId = ideaId;
    }

    public IdeaShortDescBlobModel(long ideaId, Blob shortDescBlob) {
        _ideaId = ideaId;
        _shortDescBlob = shortDescBlob;
    }

    public long getIdeaId() {
        return _ideaId;
    }

    public void setIdeaId(long ideaId) {
        _ideaId = ideaId;
    }

    public Blob getShortDescBlob() {
        return _shortDescBlob;
    }

    public void setShortDescBlob(Blob shortDescBlob) {
        _shortDescBlob = shortDescBlob;
    }
}
