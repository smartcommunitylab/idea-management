package it.smartcommunitylab.platform.idea.service.persistence;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

import it.smartcommunitylab.platform.idea.model.Call;
import it.smartcommunitylab.platform.idea.service.CallLocalServiceUtil;

/**
 * @author mirko perillo
 * @generated
 */
public abstract class CallActionableDynamicQuery
    extends BaseActionableDynamicQuery {
    public CallActionableDynamicQuery() throws SystemException {
        setBaseLocalService(CallLocalServiceUtil.getService());
        setClass(Call.class);

        setClassLoader(it.smartcommunitylab.platform.idea.service.ClpSerializer.class.getClassLoader());

        setPrimaryKeyPropertyName("callId");
    }
}
