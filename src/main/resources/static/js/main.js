$(document).ready(function() {
	$("input").click(function(event) {
		 var buttonSelected = $(this).attr("value");
		event.preventDefault();
		$.ajax({
			type: "GET",
			contentType: "application/json",
			url: "/playGame?point="+buttonSelected,
			data: "",
			dataType: 'json',
			cache: false,
			timeout: 600000,
			success: function(data) {
				console.log("response====== : ", data);
				$('#feedback').html(data);
			},
			error: function(e) {
				alert("ERROR======= : ", e);
				$('#feedback').html("ERRROR====" + e);
			}
		});
	});
});
