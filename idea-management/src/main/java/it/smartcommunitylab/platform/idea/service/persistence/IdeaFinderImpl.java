package it.smartcommunitylab.platform.idea.service.persistence;

import it.smartcommunitylab.platform.idea.model.Idea;
import it.smartcommunitylab.platform.idea.model.impl.IdeaImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

public class IdeaFinderImpl extends BasePersistenceImpl<Idea> implements
		IdeaFinder {

	public List<Idea> findByCat(Long categoryId) {
		return findByCat(categoryId, -1, -1);
	}

	public List<Idea> findByCat(Long categoryId, int begin, int end) {
		List<Object> params = new ArrayList<Object>();
		params.add(categoryId);
		return execQuery(FIND_BY_CAT, params, begin, end);
	}

	public List<Idea> findByCatAndTags(Long categoryId, long[] tagIds) {
		return findByCatAndTags(categoryId, tagIds, -1, -1);
	}

	public List<Idea> findByCatAndTags(Long categoryId, long[] tagIds,
			int begin, int end) {
		List<Object> params = new ArrayList<Object>();
		params.add(categoryId);
		params.add(tagIds);
		return execQuery(FIND_BY_CAT_TAGS, params, begin, end);
	}

	public List<Idea> findByCatAndRatingAndTags(Long categoryId, long[] tagIds) {
		return findByCatAndRatingAndTags(categoryId, tagIds, -1, -1);
	}

	public List<Idea> findByCatAndRatingAndTags(Long categoryId, long[] tagIds,
			int begin, int end) {
		List<Object> params = new ArrayList<Object>();
		params.add(categoryId);
		params.add(tagIds);
		return execQuery(FIND_BY_CAT_RATING_TAGS, params, begin, end);
	}

	public List<Idea> findByCatAndRating(Long categoryId) {
		return findByCatAndRating(categoryId, -1, -1);
	}

	public List<Idea> findByCatAndRating(Long categoryId, int begin, int end) {
		List<Object> params = new ArrayList<Object>();
		params.add(categoryId);
		return execQuery(FIND_BY_CAT_RATING, params, begin, end);
	}

	public List<Idea> findByRating() {
		return findByRating(-1, -1);
	}

	public List<Idea> findByRating(int begin, int end) {
		List<Object> params = new ArrayList<Object>();
		return execQuery(FIND_BY_RATING, params, begin, end);
	}

	public List<Idea> findByCallAndRating(long callId) {
		return findByCallAndRating(callId, -1, -1);
	}

	public List<Idea> findByCallAndRating(long callId, int begin, int end) {
		List<Object> params = new ArrayList<Object>();
		params.add(callId);
		return execQuery(FIND_BY_CALL_RATING, params, begin, end);
	}

	public List<Idea> findByCallAndTags(long callId, long[] tagIds) {
		return findByCallAndTags(callId, tagIds, -1, -1);
	}

	public List<Idea> findByCallAndTags(long callId, long[] tagIds, int begin,
			int end) {
		List<Object> params = new ArrayList<Object>();
		params.add(callId);
		params.add(tagIds);
		return execQuery(FIND_BY_CALL_TAGS, params, begin, end);
	}

	public List<Idea> findByCallAndRatingAndTags(long callId, long[] tagIds) {
		return findByCallAndRatingAndTags(callId, tagIds, -1, -1);
	}

	public List<Idea> findByCallAndRatingAndTags(long callId, long[] tagIds,
			int begin, int end) {
		List<Object> params = new ArrayList<Object>();
		params.add(callId);
		params.add(tagIds);
		return execQuery(FIND_BY_CALL_RATING_TAGS, params, begin, end);
	}

	private List<Idea> execQuery(String queryName, List<Object> params,
			int begin, int end) {
		Session session = null;
		try {
			session = openSession();
			String sql = CustomSQLUtil.get(queryName);

			SQLQuery q = session.createSQLQuery(sql);
			q.setCacheable(false);
			q.addEntity("IM_Idea", IdeaImpl.class);
			QueryPos qPos = QueryPos.getInstance(q);
			for (Object param : params) {
				if (param instanceof long[]) {
					qPos.add(StringUtil.merge((long[]) param));
				} else {
					qPos.add(param);
				}
			}

			List<Idea> res = null;
			if (begin < 0 || end <= 0) {
				res = (List<Idea>) q.list();
			} else {
				res = (List<Idea>) QueryUtil.list(q, getDialect(), begin, end);
			}
			return res;
		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				se.printStackTrace();
			}
		} finally {
			closeSession(session);
		}

		return Collections.emptyList();
	}

	public static final String FIND_BY_CAT = IdeaFinder.class.getName()
			+ ".findByCat";

	public static final String FIND_BY_CAT_TAGS = IdeaFinder.class.getName()
			+ ".findByCatAndTags";

	public static final String FIND_BY_CAT_RATING_TAGS = IdeaFinder.class
			.getName() + ".findByCatAndRatingAndTags";

	public static final String FIND_BY_CAT_RATING = IdeaFinder.class.getName()
			+ ".findByCatAndRating";

	public static final String FIND_BY_RATING = IdeaFinder.class.getName()
			+ ".findByRating";

	public static final String FIND_BY_CALL_RATING = IdeaFinder.class.getName()
			+ ".findByCallAndRating";

	public static final String FIND_BY_CALL_TAGS = IdeaFinder.class.getName()
			+ ".findByCallAndTags";

	public static final String FIND_BY_CALL_RATING_TAGS = IdeaFinder.class
			.getName() + ".findByCallAndRatingAndTags";

}
