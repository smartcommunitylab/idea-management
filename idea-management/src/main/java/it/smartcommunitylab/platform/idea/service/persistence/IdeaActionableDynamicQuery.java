package it.smartcommunitylab.platform.idea.service.persistence;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

import it.smartcommunitylab.platform.idea.model.Idea;
import it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil;

/**
 * @author mirko perillo
 * @generated
 */
public abstract class IdeaActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public IdeaActionableDynamicQuery() throws SystemException {
        setBaseLocalService(IdeaLocalServiceUtil.getService());
        setClass(Idea.class);

        setClassLoader(it.smartcommunitylab.platform.idea.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("ideaId");
    }
}
