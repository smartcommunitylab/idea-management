SITE LAYOUT

Define the following structures:
- Create a Web content structure
- call it `Idea category`
- copy content of idea-category-structure.xml in source of structure
- Create a Web content template
- call it idea cat struct
- bind to structure `Idea category`
- language Freemarker
- copy content of idea-cat-struct.ftl in source of template


Define the following pages:
- HOME (visible in menu)
    - apply Idea Mgmt Theme 
    - add a idea management instance
    - configure instance
        * type of visualization: Simple
        * selection type: recent
        * flag Hide add button
        * flag Hide search filters
        * flag Enable pagination
        * set 5 elements per page
   - add a idea management instance
   - configure instance
        * type of visualization: Simple
        * selection type: popular
        * flag Hide add button
        * flag Hide search filters
        * flag Enable pagination
        * set 5 elements per page

- VIEW-CATEGORY (hide in menu)
    - apply Idea Mgmt Theme 
    - add `category` asset publisher
        * show full content, without asset title, hide add button
        * limit to Idea Category Structure
    - add idea-management instance
    - configure instance
        * type of visualization: Extended
        * selection type: recent
        * flag Enable pagination
        * set 5 elements per page

- VIEW-TEMA (hide in menu)
    - apply Idea Mgmt Theme 
    - add a call-management instance
    - configure instance
        * selection type: aperti
        * set 5 elements per page
    - add a idea-management instance
        * type of visualization: Extended
        * selection type: recent
        * flag Enable pagination
        * set 5 elements per page


- AMBITI (visible in menu)
    - apply Idea Mgmt Theme 
    - add ‘categories’ asset publisher
      * limit to Idea Category Structure
      * in display setting add a new template named idea cat cards
      * copy content of ‘idea-cat-cards.ftl’ in source of template
      * go in look and feel and set Link Portlet URLs to Page  to VIEW-CATEGORY
      
- TEMI (visible in menu)
    - add a call-management instance
    - configure instance
        * selection type: aperti
        * set 5 elements per page
    - go in look and feel and set Link Portlet URLs to Page  to VIEW-TEMA
    - add a call-management instance
    - configure instance
        * selection type: in discussione
        * set 5 elements per page
    - go in look and feel and set Link Portlet URLs to Page  to VIEW-TEMA
    - add a call-management instance
    - configure instance
        * selection type: chiusi
        * set 5 elements per page
    - go in look and feel and set Link Portlet URLs to Page  to VIEW-TEMA


	

