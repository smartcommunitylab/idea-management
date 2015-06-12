create index IX_61D31779 on IM_Call (userId);
create index IX_1F8A0695 on IM_Call (uuid_);
create index IX_27F57E73 on IM_Call (uuid_, companyId);
create unique index IX_45A105B5 on IM_Call (uuid_, groupId);

create index IX_53AE2F12 on IM_Idea (groupId);
create index IX_3C485E1C on IM_Idea (uuid_);
create index IX_B658FC8C on IM_Idea (uuid_, companyId);
create unique index IX_66CF360E on IM_Idea (uuid_, groupId);