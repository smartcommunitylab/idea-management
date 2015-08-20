#Idea Management

Idea management is a Liferay plugin for managing ideas, contests (calls for ideas), the associated user groups and events.

##Setup

### Required plugins
- Calendar CE
- Google Login
- Cookie Notification Usage

### Required Liferay Configuration Properties

####1. In portal-ext.properties:
```
# to enable services in templates
velocity.engine.restricted.classes=
velocity.engine.restricted.variables=
freemarker.engine.restricted.classes=
freemarker.engine.restricted.variables=
# User public page: use idea management theme
layout.user.public.layouts.enabled=true
layout.user.public.layouts.auto.create=true
default.user.public.layout.regular.theme.id=ideamgmt_WAR_ideamgmttheme
default.user.public.layout.friendly.url=/profile
default.user.public.layout.template.id=1_column
default.user.public.layout.column-1=userprofile_WAR_ideamanagement
# User private page: disable
layout.user.private.layouts.enabled=false
layout.user.private.layouts.auto.create=false
#default.user.private.layout.regular.theme.id=idea-mgmt-theme_WAR_idea-mgmt-theme
# Terms of Use
terms.of.use.journal.article.id=[web_content_id]
terms.of.use.journal.article.group.id=[web_content_group_id]
# Cookie Law
layout.static.portlets.all=cookiesnotification_WAR_cookiesnotificationportlet
cookies.accepted.journalarticle.id=[web_content_id]
cookies.accepted.journalarticle.groupid=[web_content_group_id]
# disable password reminder question
users.reminder.queries.enabled=false
# to change default user image (can be found in theme/WEB-INF/classes). put the image in the main classpath: ROOT/WEB-INF/classes
image.default.user.female.portrait=placeholder_user.png
image.default.user.male.portrait=placeholder_user.png
# custom discussion notification email: see resources folder
discussion.email.body=smartcommunitylab/discussion_email_body.tmpl
discussion.email.subject=smartcommunitylab/discussion_email_subject.tmpl
# search portlet
search.facet.configuration=it/smartcommunitylab/platform/idea/search/facet_configuration.json
```
To change the theme for already created users:
  - Set 
  
  ```
  layout.user.public.layouts.enabled=false
  layout.user.public.layouts.auto.create=false
  ```
  - restart the server
  - logout or login and logout
  - login again
  - set
  
   ```
  layout.user.public.layouts.enabled=true
  layout.user.public.layouts.auto.create=true
  ```
  - restart the server
  - logout
  - login

####2. Portal Configuration
- default language
- default user associations
  * create Site Manager site role
  * create Site Moderator site role
  * create Blacklisted regular role
  * create Manager user, set Manager role, Site Content Reviewer
  * create Moderator user, set Moderator role
- enable google/facebook login
- configure mail server
- configure workflow definitions
- in control panel / configuration /users set 'Last Name Required' true
- in 'site / content management / Documents and Media' delete unnecessary data types (contracts, etc.)
- configure the mail message templates
- deploy portal-customization ext plugin
- restart the server

### Data Structures

#### 2. Create category data structure

- Create a Web content structure
- call it `Idea category`
- copy content of idea-category-structure.xml in source of structure
- Create a Web content template
- call it idea cat struct
- bind to structure `Idea category`
- language Freemarker
- copy content of idea-cat-struct.ftl in source of template

### Site Layout

Apply Idea-mgmt-theme to the site.

- **HOME** (visible, 1 column)
  * Nested portlet (1 - 2 columns (70/30))
    * TOP: Web content (for introduction)
    * LEFT: tag cloud (asset type: Idea, display template: resources/tag-cloud.ftl, open links in 'tags' page)
    * RIGHT: event management portlet
  * Idea management portlet (visualization: simple, hide add button: true, type: recent, hide filters: true, pagination: 5, open links in 'detail' page)
  * Idea management portlet (visualization: simple, hide add button: true, type: popular, hide filters: true, pagination: 5, open links in 'detail' page)
- **detail** (invisible, 2 columns (70/30))
  * LEFT: idea management portlet
  * RIGHT: event management portlet
- **tags** (invisible, parent HOME, 1 column)
  * idea management portlet (visualization: extended, hide add button: false, type: recent, hide filters: false, pagination: 10, open links in 'detail' page)
- **AMBITI** (visible, 1 column)
  * Asset Publisher (asset type: Web Content Article, subtype: Idea category, template: resources/idea-cat-cards.ftl, open links in 'category' page)
  * Idea management portlet (visualization: simple, hide add button: true, type: recent, hide filters: true, pagination: 5, open links in 'detail' page)
  * Idea management portlet (visualization: simple, hide add button: true, type: popular, hide filters: true, pagination: 5, open links in 'detail' page)
- **category** (invisible, parent AMBITI, 1 column)
  * Nested portlet (2 columns (70/30))
    * LEFT: Asset Publisher (asset type: Web Content Article, subtype: Idea category, template: resources/idea-cat-struct.ftl)
    * RIGHT: event management portlet
  * idea management portlet (visualization: extended, hide add button: false, type: recent, hide filters: false, pagination: 10, open links in 'detail' page)
- **SFIDE** (visible, 1 column)
  * Call management portlet (type: open, open links in 'call' page)
  * Call management portlet (type: in discussion, open links in 'call' page)
  * Call management portlet (type: closed, open links in 'call' page)
- **call** (invisible, 1 )  
  * Nested portlet (2 columns (70/30))
    * LEFT: call management portley
    * RIGHT: event management portlet
  * idea management portlet (visualization: extended, hide add button: false, type: recent, hide filters: false, pagination: 10, open links in 'detail' page)

  

