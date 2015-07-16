package it.smartcommunitylab.platform.idea.model.impl;

import java.util.Calendar;
import java.util.Date;

/**
 * The extended model implementation for the Idea service. Represents a row in the &quot;IM_Idea&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link it.smartcommunitylab.platform.idea.model.Idea} interface.
 * </p>
 *
 * @author mirko perillo
 */
public class IdeaImpl extends IdeaBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a idea model instance should use the {@link it.smartcommunitylab.platform.idea.model.Idea} interface instead.
     */
    public IdeaImpl() {
    }
    
    public Date discussionDeadline() {
    	Calendar c = Calendar.getInstance();
    	c.setTimeInMillis(getCreateDate().getTime());
    	c.add(Calendar.DATE, getDiscussionLimit());
    	return c.getTime();
    }
}
