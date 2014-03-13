
$(document).ready(function() {
  $('#results').hide()
  $('#run').on('click', function() {
    console.log('Running simulation')
    runSimulation({ url: $('#url').val(),
	  	    users: $('#users').val() },
		  function(data) {
		    console.log('Success', data)
		    $('#results').fadeIn().fadeOut().fadeIn() 
		  })
  })

  function runSimulation(data, success) {
    $.ajax({ type: 'POST',
	     url: '/api/runSimulation/',
	     data: JSON.stringify(data),
	     contentType: "application/json; charset=utf-8",
	     dataType: "json",
	     success: success,
	     failure: function (errMsg) { console.log('error', errMsg) }
	     })
  }
})
