function remoteModal(url) {
	if (!$('#root')[0]) {
		$('body').append($('<div id="root"></div>'));
	}
	$('#root').load(url, function() {
		$('#root .modal').modal({
			backdrop: 'static',
			keyboard: false
		});
	});
}

function parseUrl() {
	var url = window.document.location;
	
}