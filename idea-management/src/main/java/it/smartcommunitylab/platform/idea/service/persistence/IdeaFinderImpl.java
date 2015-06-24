package it.smartcommunitylab.platform.idea.service.persistence;

import it.smartcommunitylab.platform.idea.model.Idea;
import it.smartcommunitylab.platform.idea.model.impl.IdeaImpl;

import java.util.List;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

public class IdeaFinderImpl extends BasePersistenceImpl<Idea> implements
		IdeaFinder {

	public List<Idea> findByCatAndTags(Long categoryId, long[] tagIds) {
		return findByCatAndTags(categoryId, tagIds, -1, -1);
	}

	public List<Idea> findByCatAndTags(Long categoryId, long[] tagIds,
			int begin, int end) {

		Session session = null;
		try {
			session = openSession();
			String sql = CustomSQLUtil.get(FIND_BY_CAT_TAGS);

			SQLQuery q = session.createSQLQuery(sql);
			q.setCacheable(false);
			q.addEntity("IM_Idea", IdeaImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);
			qPos.add(categoryId);
			qPos.add(tagIds);
			List<Idea> res = null;
			if (begin <= 0 || end <= 0) {
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

		return null;
	}

	public List<Idea> findByCatAndRatingAndTags(Long categoryId, long[] tagIds) {
		return findByCatAndRatingAndTags(categoryId, tagIds, -1, -1);
	}

	public List<Idea> findByCatAndRatingAndTags(Long categoryId, long[] tagIds,
			int begin, int end) {

		Session session = null;
		try {
			session = openSession();
			String sql = CustomSQLUtil.get(FIND_BY_CAT_RATING_TAGS);

			SQLQuery q = session.createSQLQuery(sql);
			q.setCacheable(false);
			q.addEntity("IM_Idea", IdeaImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);
			qPos.add(categoryId);
			qPos.add(tagIds);
			List<Idea> res = null;
			if (begin <= 0 || end <= 0) {
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

		return null;
	}

	public List<Idea> findByCatAndRating(Long categoryId) {
		return findByCatAndRating(categoryId, -1, -1);
	}

	public List<Idea> findByCatAndRating(Long categoryId, int begin, int end) {

		Session session = null;
		try {
			session = openSession();
			String sql = CustomSQLUtil.get(FIND_BY_CAT_RATING);

			SQLQuery q = session.createSQLQuery(sql);
			q.setCacheable(false);
			q.addEntity("IM_Idea", IdeaImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);
			qPos.add(categoryId);
			List<Idea> res = null;
			if (begin <= 0 || end <= 0) {
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

		return null;
	}

	public List<Idea> findByRating() {
		return findByRating(-1, -1);
	}

	public List<Idea> findByRating(int begin, int end) {

		Session session = null;
		try {
			session = openSession();
			String sql = CustomSQLUtil.get(FIND_BY_RATING);

			SQLQuery q = session.createSQLQuery(sql);
			q.setCacheable(false);
			q.addEntity("IM_Idea", IdeaImpl.class);

			List<Idea> res = null;
			if (begin <= 0 || end <= 0) {
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

		return null;
	}

	public List<Idea> findByCallAndRating(long callId) {
		return findByCallAndRating(callId, -1, -1);
	}

	public List<Idea> findByCallAndRating(long callId, int begin, int end) {

		Session session = null;
		try {
			session = openSession();
			String sql = CustomSQLUtil.get(FIND_BY_CALL_RATING);

			SQLQuery q = session.createSQLQuery(sql);
			q.setCacheable(false);
			q.addEntity("IM_Idea", IdeaImpl.class);
			QueryPos qPos = QueryPos.getInstance(q);
			qPos.add(callId);

			List<Idea> res = null;
			if (begin <= 0 || end <= 0) {
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

		return null;
	}

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
}
