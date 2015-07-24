/* To avoid CSS expressions while still supporting IE 7 and IE 6, use this script */
/* The script tag referencing this file must be placed before the ending body tag. */

/* Use conditional comments in order to target IE 7 and older:
	<!--[if lt IE 8]><!-->
	<script src="ie7/ie7.js"></script>
	<!--<![endif]-->
*/

(function() {
	function addIcon(el, entity) {
		var html = el.innerHTML;
		el.innerHTML = '<span style="font-family: \'ftn\'">' + entity + '</span>' + html;
	}
	var icons = {
		'ftn-log-in': '&#xe600;',
		'ftn-attachment': '&#xe601;',
		'ftn-ambito': '&#xe602;',
		'ftn-log-out': '&#xe603;',
		'ftn-notification': '&#xe604;',
		'ftn-search': '&#xe605;',
		'ftn-tag': '&#xe606;',
		'ftn-user_tag': '&#xe607;',
		'0': 0
		},
		els = document.getElementsByTagName('*'),
		i, c, el;
	for (i = 0; ; i += 1) {
		el = els[i];
		if(!el) {
			break;
		}
		c = el.className;
		c = c.match(/ftn-[^\s'"]+/);
		if (c && icons[c[0]]) {
			addIcon(el, icons[c[0]]);
		}
	}
}());
