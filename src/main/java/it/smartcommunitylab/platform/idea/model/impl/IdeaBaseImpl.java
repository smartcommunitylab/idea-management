package it.smartcommunitylab.platform.idea.model.impl;

import com.liferay.portal.kernel.exception.SystemException;

import it.smartcommunitylab.platform.idea.model.Idea;
import it.smartcommunitylab.platform.idea.service.IdeaLocalServiceUtil;

/**
 * The extended model base implementation for the Idea service. Represents a row in the &quot;IM_Idea&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link IdeaImpl}.
 * </p>
 *
 * @author mirko perillo
 * @see IdeaImpl
 * @see it.smartcommunitylab.platform.idea.model.Idea
 * @generated
 */
public abstract class IdeaBaseImpl extends IdeaModelImpl implements Idea {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a idea model instance should use the {@link Idea} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IdeaLocalServiceUtil.addIdea(this);
        } else {
            IdeaLocalServiceUtil.updateIdea(this);
        }
    }
}
