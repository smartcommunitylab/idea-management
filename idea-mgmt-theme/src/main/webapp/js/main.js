/**
 * 
 */

AUI().ready('liferay-hudcrumbs', 'liferay-navigation-interaction',
		'liferay-sign-in-modal', function(A) {
			var signIn = A.one('li.sign-in a');

			if (signIn && signIn.getData('redirect') !== 'true') {
				signIn.plug(Liferay.SignInModal);
			}
		});

AUI().ready('aui-tooltip', function(Y) {
	new Y.TooltipDelegate({
		trigger : '.idea-button-tooltip-avail',
		position: 'top'
	});
});