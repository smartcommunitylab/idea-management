/**
 * 
 */

AUI().ready(function(A) {
	// get our toggle button
	var navigationToggleBtn = A.one('#nav-toggle');
	// get default navigation ul element
	var navigationUl = A.one(Liferay.Data.NAV_SELECTOR);

	if (navigationToggleBtn && navigationUl) {
		// do nothing when toggle button not present (user not signed in) or if
		// navigation is not present
		navigationToggleBtn.on('click', function(event) {
			// otherwise assign simple function that'll toggle 'open' menu class
			// on default navigation which will cause it to open, same for menu
			// toggle button
			navigationToggleBtn.toggleClass('open');
			navigationUl.toggleClass('open');
		});
	}
});

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
		position : 'top'
	});
});