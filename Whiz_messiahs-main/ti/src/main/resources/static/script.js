$(document).ready(function(){
//	$.ajax({
//    url: '/ti/test',
//	async: false,
//	cache: false,
//    success: function(data) {
//        alert(data);
//    },
//    error: function (request,status, error) {
//        console.log(error);
//        alert(status);
//    }
//	});

$("#profile-div").hide();
$("#result-div").hide();

});

function search() {
	
//	$('#modal-h1').html("Searching Social Profiles.....");
//	$('#myModal').modal('show');

	$.ajax({
    url: '/ti/search?searchTerm='+$("#searchTerm").val(),
	async: false,
	cache: false,
    success: function(data) {
        var social = data.social;
		var searchTerm = data.searchTerm;
		var id = searchTerm.id;
		console.log(social);
		console.log(searchTerm);
		console.log("id -> "+id);
		$("#btn-analyze").attr('searchTermId',id);
		$("#social-tbody").html("");
		for(var i = 0; i < social.length; i++){
		    console.log(social[i]);
			$("#social-tbody").append("<tr><td>"+social[i]+"</td></tr>");	
		}
		$("#profile-div").show();
    },
    error: function (request,status, error) {
        console.log(error);
        alert(status);
    }
	});
	
}

function analyze() {
//	alert($("#btn-analyze").attr('searchTermId'));
	
	$.ajax({
    	url: '/ti/analyze?searchTermId='+$("#btn-analyze").attr('searchTermId'),
		async: false,
		cache: false,
	    success: function(data) {
	        console.log(data);
			$("#analyze-tbody").html("");
			for(var i = 0; i < data.length; i++){
				$("#analyze-tbody").append("<tr><td>"+data[i].data+"</td><td>"+data[i].dateTime+"</td><td>"+data[i].token+"</td><td>"+data[i].result+"</td></tr>");
			}
			$("#result-div").show();
	    },
	    error: function (request,status, error) {
	        console.log(error);
	        alert(status);
	    }
	});
}