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
	description VARCHAR(75) null,
	deadline DATE null,
	publicationDeadline DATE null,
	realizationDeadline DATE null,
	userGroupId LONG
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
	longDesc VARCHAR(75) null,
	shortDesc VARCHAR(75) null,
	userGroupId LONG,
	callId LONG,
	state_ VARCHAR(75) null,
	stateJudgement VARCHAR(75) null,
	deadlineConstraints VARCHAR(75) null,
	discussionLimit INTEGER,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null
);