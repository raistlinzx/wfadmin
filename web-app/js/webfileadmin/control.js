function deleteFile(path, file) {
	console.debug(path);
	$.ajax({
		type:'POST',
		url: path+'/delete',
		data: {file: file},
		success: function(res) {
			window.location.reload();
		}
	})
}

function newFile(path, file) {
	window.location.href = path + '/create?file=' + file;
}

function saveFile() {
	var $form = $('#fileForm');
	console.debug($form.attr('action'));
	console.debug($form.serialize());
	$.ajax({
		type:'POST',
		url: $form.attr('action'),
		data: $form.serialize(),
		success: function(res) {
			console.debug('success')
			$('div.alert-success').find('span').html(res);
			$('div.alert-success').fadeToggle();
			setTimeout(function(){
				$('div.alert-success').fadeToggle();
			}, 3000)
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) {
			$('div.alert-danger').find('span').html(errorThrown);
			$('div.alert-danger').fadeToggle();	
		}     
	})
}

