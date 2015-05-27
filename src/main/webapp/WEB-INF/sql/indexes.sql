create index IX_53AE2F12 on IM_Idea (groupId);
create index IX_3C485E1C on IM_Idea (uuid_);
create index IX_B658FC8C on IM_Idea (uuid_, companyId);
create unique index IX_66CF360E on IM_Idea (uuid_, groupId);