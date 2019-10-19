
//Function to populate State
($(document).ready(function() {
	$("#state").change(function() {
		sendAjaxRequest();
		$("#region").show();
		$("#station").show();
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
		
		var station = $("#station").val();


		function drawChart() {
			
			var city = $("#region").val();


			
			$.ajax({
				type : "GET",
				contentType : "application/json",
				url : "getPollutionData?station=" + station,
				dataType : 'json',				
				success : function(data) {
					
					var chartData = JSON.parse(JSON.stringify(data));
					
					var data = new google.visualization.DataTable();
					
					data.addColumn('string', 'Pollutant Id');
					data.addColumn('number', 'Pollutant Min');
					data.addColumn('number', 'Pollutant Max');
					data.addColumn('number', 'Pollutant Avg');
					
					
					for (var i = 0; i < chartData.length; i++) {
					     data.addRow([chartData[i].pollutionId,chartData[i].pollutionMin,chartData[i].pollutionMax,chartData[i].pollutionAvg]);
					}

					var options = {
						chart : {
							title : 'City : ' + city,
							subtitle : 'Region : ' + station + ' | Last Updated : ' + chartData[0].lastUpdated							
						},
						backgroundColor: '#ffffff',
						is3D: true
						//bars : 'horizontal' // Required for Material Bar Charts.
					};

					var chart = new google.charts.Bar(document
							.getElementById('barchart_material'));

					chart.draw(data, google.charts.Bar.convertOptions(options));
					
					
				}
				});
				}
		
		 google.charts.load('current', {'packages':['gauge']});
		    google.charts.setOnLoadCallback(drawGauge);
		    
		    var gaugeOptions = {min: 0, max: 500, greenFrom: 0, greenTo: 100, yellowFrom: 100, yellowTo: 250,
		    	      redFrom: 250, redTo: 500, minorTicks: 5};
		    
		    var gauge;

		    function drawGauge() {
		    	$.ajax({
					type : "GET",
					contentType : "application/json",
					url : "getAQI?station=" + station,
					dataType : 'json',				
					success : function(data) {
					    gaugeData = new google.visualization.DataTable();
				        gaugeData.addColumn('number', 'AQI');
				        gaugeData.addRows(1);
				        gaugeData.setCell(0, 0, data);
				        
				        gauge = new google.visualization.Gauge(document.getElementById('aqiChart'));
				        gauge.draw(gaugeData, gaugeOptions);
					}
					});
		    
		      }
		    
		    
		    
		    google.charts.load('current', {packages: ['corechart', 'line']});
		    google.charts.setOnLoadCallback(drawTrend);
		    
		    function drawTrend() {
		    	$.ajax({
					type : "GET",
					contentType : "application/json",
					url : "getTrendData?station=" + station,
					dataType : 'json',				
					success : function(data) {
						
						var chartData = JSON.parse(JSON.stringify(data));

						
					      var data = new google.visualization.DataTable();
					      
					      data.addColumn('string', 'X');
					      
					      for (var i = 0; i < chartData.length; i++) {
							     data.addColumn('number', chartData[i].pollutionId);
					      }
					      

					      for (var i = 0; i < 5; i++) {  
						      var chartTrendData = [];
					    	  
						      
					    	  for (var j = 0; j < chartData.length; j++) {
					    		  if(j==0){
								      chartTrendData.push(chartData[j].time[i]);

					    		  }
					    		  chartTrendData.push(chartData[j].pollutionAvg[i]);
					    	  }
					    	  					    	  
					    	  data.addRow(chartTrendData);
					      }
					      var options = {
					    	        pointSize: 3
					    	      };
					      
					      var chart = new google.visualization.LineChart(document.getElementById('trendChart'));
					      chart.draw(data, options);
					    
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

function onLoadhidedropdowns() {
			$("#region").hide();
			$("#station").hide();
			};