package it.smartcommunitylab.platform.idea;

import java.util.List;

import org.junit.Test;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portlet.ratings.model.RatingsEntry;

public class AssetQuery {

	@SuppressWarnings("unchecked")
	@Test
	public void searchByRating() {
		DynamicQuery dq = DynamicQueryFactoryUtil
				.forClass(RatingsEntry.class, "rating")
				.setProjection(ProjectionFactoryUtil.property("classPK"))
				.setProjection(
						ProjectionFactoryUtil.alias(
								ProjectionFactoryUtil.avg("score"), "ascore"))
				.addOrder(OrderFactoryUtil.desc("ascore"));
		List<RatingsEntry> res = dq.list();
		for (RatingsEntry r : res) {
			System.out.println(r.getClassPK());
		}

	}
}
