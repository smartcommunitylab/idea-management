create table IM_Call (
	uuid_ VARCHAR(75) null,
	title VARCHAR(75) null,
	callId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	description TEXT null,
	deadline DATE null
);

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
	shortDesc VARCHAR(75) null
);