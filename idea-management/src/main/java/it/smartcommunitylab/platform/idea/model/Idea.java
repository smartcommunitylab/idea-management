package it.smartcommunitylab.platform.idea.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the Idea service. Represents a row in the &quot;IM_Idea&quot; database table, with each column mapped to a property of this class.
 *
 * @author mirko perillo
 * @see IdeaModel
 * @see it.smartcommunitylab.platform.idea.model.impl.IdeaImpl
 * @see it.smartcommunitylab.platform.idea.model.impl.IdeaModelImpl
 * @generated
 */
public interface Idea extends IdeaModel, PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link it.smartcommunitylab.platform.idea.model.impl.IdeaImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public java.util.Date discussionDeadline();
}
