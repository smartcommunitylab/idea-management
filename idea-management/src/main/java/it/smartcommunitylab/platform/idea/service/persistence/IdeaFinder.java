package it.smartcommunitylab.platform.idea.service.persistence;

public interface IdeaFinder {
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCatAndRating(
        java.lang.Long categoryId);

    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCatAndRating(
        java.lang.Long categoryId, int begin, int end);
}
