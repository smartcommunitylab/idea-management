package it.smartcommunitylab.platform.idea.service.persistence;

public interface IdeaFinder {
    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findAllApproved();

    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findAllApproved(
        int begin, int end);

    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCat(
        java.lang.Long categoryId);

    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCat(
        java.lang.Long categoryId, int begin, int end);

    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCatAndTags(
        java.lang.Long categoryId, long[] tagIds);

    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCatAndTags(
        java.lang.Long categoryId, long[] tagIds, int begin, int end);

    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCatAndRatingAndTags(
        java.lang.Long categoryId, long[] tagIds);

    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCatAndRatingAndTags(
        java.lang.Long categoryId, long[] tagIds, int begin, int end);

    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCatAndRating(
        java.lang.Long categoryId);

    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCatAndRating(
        java.lang.Long categoryId, int begin, int end);

    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByRating();

    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByRating(
        int begin, int end);

    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCallAndRating(
        long callId);

    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCallAndRating(
        long callId, int begin, int end);

    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCallAndTags(
        long callId, long[] tagIds);

    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCallAndTags(
        long callId, long[] tagIds, int begin, int end);

    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByTags(
        long[] tagIds);

    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByTags(
        long[] tagIds, int begin, int end);

    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByTagsAndRating(
        long[] tagIds);

    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByTagsAndRating(
        long[] tagIds, int begin, int end);

    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCallAndRatingAndTags(
        long callId, long[] tagIds);

    public java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCallAndRatingAndTags(
        long callId, long[] tagIds, int begin, int end);
}
