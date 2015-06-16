package it.smartcommunitylab.platform.idea.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;


public class IdeaFinderUtil {
    private static IdeaFinder _finder;

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
