
//Function to populate State
($(document).ready(function() {
	$("#state").change(function() {
		sendAjaxRequest();
	});
}));

//
$('select').each(
		function() {
			var $this = $(this), numberOfOptions = $(this).children(
					'option').length;

			$this.addClass('select-hidden');
			$this.wrap('<div class="select"></div>');
			$this.after('<div class="select-styled"></div>');

			var $styledSelect = $this.next('div.select-styled');
			$styledSelect.text($this.children('option').eq(0).text());

			var $list = $('<ul />', {
				'class' : 'select-options'
			}).insertAfter($styledSelect);

			for (var i = 0; i < numberOfOptions; i++) {
				$('<li />', {
					text : $this.children('option').eq(i).text(),
					rel : $this.children('option').eq(i).val()
				}).appendTo($list);
			}

			var $listItems = $list.children('li');

			$styledSelect.click(function(e) {
				e.stopPropagation();
				$('div.select-styled.active').not(this).each(
						function() {
							$(this).removeClass('active').next(
									'ul.select-options').hide();
						});
				$(this).toggleClass('active').next('ul.select-options')
						.toggle();
			});

			$listItems.click(function(e) {
				e.stopPropagation();
				$styledSelect.text($(this).text()).removeClass('active');
				$this.val($(this).attr('rel'));
				$list.hide();
				//console.log($this.val());
			});

			$(document).click(function() {
				$styledSelect.removeClass('active');
				$list.hide();
			});

		});
//

$(document).ready(function() {
	$("#station").change(function() {
		google.charts.load('current', {
			'packages' : [ 'bar' ]
		});
		google.charts.setOnLoadCallback(drawChart);

		function drawChart() {
			
			var station = $("#station").val();
			var city = $("#region").val();


			
			$.ajax({
				type : "GET",
				contentType : "application/json",
				url : "getPollutionData?station=" + station,
				dataType : 'json',				
				success : function(data) {
					
					var chartData = JSON.parse(JSON.stringify(data));
					
					var data = google.visualization.arrayToDataTable([
							[ 'Pollutant Id', 'Pollutant Min', 'Pollutant Max',
									'Pollutant Avg' ], [chartData[0].pollutionId, chartData[0].pollutionMin, chartData[0].pollutionMax, chartData[0].pollutionAvg ],
							[ chartData[1].pollutionId, chartData[1].pollutionMin, chartData[1].pollutionMax, chartData[1].pollutionAvg ],
							[ chartData[2].pollutionId, chartData[2].pollutionMin, chartData[2].pollutionMax, chartData[2].pollutionAvg ],
							[ chartData[3].pollutionId, chartData[3].pollutionMin, chartData[3].pollutionMax, chartData[3].pollutionAvg ],
							[ chartData[4].pollutionId, chartData[4].pollutionMin, chartData[4].pollutionMax, chartData[4].pollutionAvg ],
							[ chartData[5].pollutionId, chartData[5].pollutionMin, chartData[5].pollutionMax, chartData[5].pollutionAvg ],
							[ chartData[6].pollutionId, chartData[6].pollutionMin, chartData[6].pollutionMax, chartData[6].pollutionAvg ]]);

					var options = {
						chart : {
							title : 'City : ' + city,
							subtitle : 'Region : ' + station + ' | Last Updated : ' + chartData[0].lastUpdated,
						},
						//bars : 'horizontal' // Required for Material Bar Charts.
					};

					var chart = new google.charts.Bar(document
							.getElementById('barchart_material'));

					chart.draw(data, google.charts.Bar.convertOptions(options));
					
					
				}
				});
				}
			});
		});

//

function sendAjaxRequest() {
	var state = $("#state").val();
	$.get("/getCitiesData?state=" + state, function(data) {
		$("#region").empty();
		$("#station").empty();
		data.forEach(function(item) {
			var option = "<option th:value = " + item + ">" + item
					+ "</option>";
			$("#region").append(option);
		});
	});
};

//

$(document).ready(function() {
	$("#region").change(function() {
		getStationData();
	});
});

//

function getStationData() {
	var city = $("#region").val();
	$.get("/getStationData?city=" + city, function(data) {
		$("#station").empty();
		data.forEach(function(item) {
			var option = "<option th:value = " + item + ">" + item
					+ "</option>";
			$("#station").append(option);
		});
	});
};