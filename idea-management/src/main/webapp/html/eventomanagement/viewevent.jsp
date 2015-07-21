<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="event-view-container">
  <div class="event-view-category">Sostenibilita'</div>
  <div class="event-view-context">Idea 1</div>
  <div class="event-view-header">
    <span class="event-view-title">Evento 1</span>
    <span class="event-viewd-date">9 Giugno 2015</span>    
  </div>
  <div class="event-view-time">Orario: 10:00</div>
  <div class="event-view-description">
  ESCRIZIONE - Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiu-
smod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
SFIDE RECENTI
Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat
nulla pariatur. Excepteur sint oc
  </div>
  <div>
  <aui:button-row  cssClass="formbutton-row">
    <aui:button cssClass="formbutton-cancel" type="cancel" onClick="Liferay.Util.getWindow().hide();" value="Close"></aui:button>
    <aui:button cssClass="formbutton-primary" type="button" value="Vedi idea"></aui:button>
  </aui:button-row>
  </div>
</div>