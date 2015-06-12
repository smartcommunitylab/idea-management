package it.smartcommunitylab.platform.idea.search;

import it.smartcommunitylab.platform.idea.model.Call;
import it.smartcommunitylab.platform.idea.permission.CallPermission;
import it.smartcommunitylab.platform.idea.service.CallLocalServiceUtil;
import it.smartcommunitylab.platform.idea.service.persistence.IdeaActionableDynamicQuery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import javax.portlet.PortletURL;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;

public class CallIndexer extends BaseIndexer {

	public static final String[] CLASS_NAMES = { Call.class.getName() };

	public static final String PORTLET_ID = "call-management";

	public CallIndexer() {

		setPermissionAware(true);
	}

	@Override
	public String[] getClassNames() {

		return CLASS_NAMES;
	}

	@Override
	public String getPortletId() {

		return PORTLET_ID;
	}

	@Override
	public boolean hasPermission(PermissionChecker permissionChecker,
			String entryClassName, long entryClassPK, String actionId)
			throws Exception {
		return CallPermission.contains(permissionChecker, entryClassPK,
				ActionKeys.VIEW);
	}

	@Override
	protected void doDelete(Object obj) throws Exception {

		Call entry = (Call) obj;

		deleteDocument(entry.getCompanyId(), entry.getCallId());
	}

	@Override
	protected Document doGetDocument(Object obj) throws Exception {

		Call entry = (Call) obj;

		Document document = getBaseModelDocument(PORTLET_ID, entry);

		document.addDate(Field.MODIFIED_DATE, entry.getModifiedDate());
		document.addText(Field.CONTENT, entry.getDescription());
		document.addText(Field.TITLE, entry.getTitle());
		document.addKeyword(Field.GROUP_ID, getSiteGroupId(entry.getGroupId()));
		document.addKeyword(Field.SCOPE_GROUP_ID, entry.getGroupId());

		return document;
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale,
			String snippet, PortletURL portletURL) throws Exception {

		Summary summary = createSummary(document);

		summary.setMaxContentLength(200);

		return summary;
	}

	@Override
	protected void doReindex(Object obj) throws Exception {

		Call entry = (Call) obj;

		Document document = getDocument(entry);

		SearchEngineUtil.updateDocument(getSearchEngineId(),
				entry.getCompanyId(), document);
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {

		Call entry = CallLocalServiceUtil.getCall(classPK);

		doReindex(entry);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {

		long companyId = GetterUtil.getLong(ids[0]);

		reindexEntries(companyId);
	}

	@Override
	protected String getPortletId(SearchContext searchContext) {

		return PORTLET_ID;
	}

	protected void reindexEntries(long companyId) throws PortalException,
			SystemException {

		final Collection<Document> documents = new ArrayList<Document>();

		ActionableDynamicQuery actionableDynamicQuery = new IdeaActionableDynamicQuery() {

			protected void addCriteria(
					com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
			};

			@Override
			protected void performAction(Object object) throws PortalException,
					SystemException {
				Call entry = (Call) object;

				Document document = getDocument(entry);

				documents.add(document);

			}
		};

		actionableDynamicQuery.setCompanyId(companyId);

		actionableDynamicQuery.performActions();

		SearchEngineUtil.updateDocuments(getSearchEngineId(), companyId,
				documents);
	}

}
