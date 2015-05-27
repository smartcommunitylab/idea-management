create table IM_Idea (
	uuid_ VARCHAR(75) null,
	ideaId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	title VARCHAR(75) null,
	longDesc TEXT null,
	shortDesc TEXT null
);