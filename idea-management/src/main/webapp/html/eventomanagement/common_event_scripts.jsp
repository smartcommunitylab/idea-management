<aui:script>
	Liferay.provide(window, 'showPopup', function(url, title) {
		var instance = this;

		Liferay.Util.openWindow({
			cache : false,
			dialog : {
				destroyOnClose : true,
				align : Liferay.Util.Window.ALIGN_CENTER,
				width : 550,
				height : 800,
				cssClass : 'event-ui-dialog',
				constrain2view : true,
				modal : true
			},
			dialogIframe : {
				id : 'showEventFormIframe',
				uri : url
			},
			title : title,
			uri : url
		});
	}, [ 'liferay-util-window' ]);

	Liferay.provide(window, 'refreshPortlet', function() {
		var curPortlet = '#p_p_id<portlet:namespace/>';
		Liferay.Portlet.refresh(curPortlet);
	}, [ 'aui-dialog', 'aui-dialog-iframe' ]);
	
	Liferay.provide(window, 'changeContextPortlet', 
		  	function(url) {
		    	this.document.location.href = url;
		    },
		    ['aui-dialog','aui-dialog-iframe']
		  );	
</aui:script>