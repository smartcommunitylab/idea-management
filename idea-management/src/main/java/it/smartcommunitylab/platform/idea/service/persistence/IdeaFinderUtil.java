package it.smartcommunitylab.platform.idea.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;


public class IdeaFinderUtil {
    private static IdeaFinder _finder;

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findAllApproved() {
        return getFinder().findAllApproved();
    }

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findAllApproved(
        int begin, int end) {
        return getFinder().findAllApproved(begin, end);
    }

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCat(
        java.lang.Long categoryId) {
        return getFinder().findByCat(categoryId);
    }

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCat(
        java.lang.Long categoryId, int begin, int end) {
        return getFinder().findByCat(categoryId, begin, end);
    }

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCatAndTags(
        java.lang.Long categoryId, long[] tagIds) {
        return getFinder().findByCatAndTags(categoryId, tagIds);
    }

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCatAndTags(
        java.lang.Long categoryId, long[] tagIds, int begin, int end) {
        return getFinder().findByCatAndTags(categoryId, tagIds, begin, end);
    }

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCatAndRatingAndTags(
        java.lang.Long categoryId, long[] tagIds) {
        return getFinder().findByCatAndRatingAndTags(categoryId, tagIds);
    }

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCatAndRatingAndTags(
        java.lang.Long categoryId, long[] tagIds, int begin, int end) {
        return getFinder()
                   .findByCatAndRatingAndTags(categoryId, tagIds, begin, end);
    }

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCatAndRating(
        java.lang.Long categoryId) {
        return getFinder().findByCatAndRating(categoryId);
    }

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCatAndRating(
        java.lang.Long categoryId, int begin, int end) {
        return getFinder().findByCatAndRating(categoryId, begin, end);
    }

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByRating() {
        return getFinder().findByRating();
    }

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByRating(
        int begin, int end) {
        return getFinder().findByRating(begin, end);
    }

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCallAndRating(
        long callId) {
        return getFinder().findByCallAndRating(callId);
    }

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCallAndRating(
        long callId, int begin, int end) {
        return getFinder().findByCallAndRating(callId, begin, end);
    }

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCallAndTags(
        long callId, long[] tagIds) {
        return getFinder().findByCallAndTags(callId, tagIds);
    }

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCallAndTags(
        long callId, long[] tagIds, int begin, int end) {
        return getFinder().findByCallAndTags(callId, tagIds, begin, end);
    }

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByTags(
        long[] tagIds) {
        return getFinder().findByTags(tagIds);
    }

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByTags(
        long[] tagIds, int begin, int end) {
        return getFinder().findByTags(tagIds, begin, end);
    }

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByTagsAndRating(
        long[] tagIds) {
        return getFinder().findByTagsAndRating(tagIds);
    }

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByTagsAndRating(
        long[] tagIds, int begin, int end) {
        return getFinder().findByTagsAndRating(tagIds, begin, end);
    }

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCallAndRatingAndTags(
        long callId, long[] tagIds) {
        return getFinder().findByCallAndRatingAndTags(callId, tagIds);
    }

    public static java.util.List<it.smartcommunitylab.platform.idea.model.Idea> findByCallAndRatingAndTags(
        long callId, long[] tagIds, int begin, int end) {
        return getFinder().findByCallAndRatingAndTags(callId, tagIds, begin, end);
    }

    public static IdeaFinder getFinder() {
        if (_finder == null) {
            _finder = (IdeaFinder) PortletBeanLocatorUtil.locate(it.smartcommunitylab.platform.idea.service.ClpSerializer.getServletContextName(),
                    IdeaFinder.class.getName());

            ReferenceRegistry.registerReference(IdeaFinderUtil.class, "_finder");
        }

        return _finder;
    }

    public void setFinder(IdeaFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(IdeaFinderUtil.class, "_finder");
    }
}
