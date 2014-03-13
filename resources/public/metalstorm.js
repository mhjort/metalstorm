
$(document).ready(function() {
  $('#run').on('click', function() {
    console.log('Running simulation')
    runSimulation({ url: 'http://www.google.fi',
	  	    users: '5'},
		  function(data) {console.log('Success', data)})
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