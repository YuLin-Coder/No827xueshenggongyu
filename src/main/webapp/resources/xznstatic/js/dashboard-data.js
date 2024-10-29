/*Dashboard Init*/
 
"use strict"; 

/*****Ready function start*****/
$(document).ready(function(){
	$('#statement').DataTable({
		"bFilter": false,
		"bLengthChange": false,
		"bPaginate": false,
		"bInfo": false,
	});
});
/*****Ready function end*****/

/*****E-Charts function start*****/
var echartsConfig = function() { 
	if( $('#e_chart_1').length > 0 ){
		var eChart_1 = echarts.init(document.getElementById('e_chart_1'));
		var rawData = [
			[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
			[0, 49, 67, 16, 0, 19, 19, 0, 0, 1, 10, 5, 6, 1, 1, 0, 25, 0, 0, 0],
			[0, 6, 3, 34, 0, 16, 1, 0, 0, 1, 6, 0, 1, 56, 0, 2, 0, 2, 0, 0],
			[0, 8, 13, 15, 0, 12, 23, 0, 0, 1, 0, 1, 0, 0, 6, 0, 0, 1, 0, 1],
			[0, 9, 28, 0, 91, 6, 1, 0, 0, 0, 7, 18, 0, 9, 16, 0, 1, 0, 0, 0],
			[0, 3, 42, 36, 21, 0, 1, 0, 0, 0, 0, 16, 30, 1, 4, 62, 55, 1, 0, 0],
			[0, 7, 13, 12, 64, 5, 0, 0, 0, 8, 17, 3, 72, 1, 1, 53, 1, 0, 0, 0],
			[1, 14, 13, 7, 8, 8, 7, 0, 1, 1, 14, 6, 44, 8, 7, 17, 21, 1, 0, 0],
			[0, 6, 14, 2, 14, 1, 0, 0, 0, 0, 2, 2, 7, 15, 6, 3, 0, 0, 0, 0],
			[0, 9, 11, 3, 0, 8, 0, 0, 14, 2, 0, 1, 1, 1, 7, 13, 2, 1, 0, 0],
			[0, 7, 5, 10, 8, 21, 0, 0, 130, 1, 2, 18, 6, 1, 5, 1, 4, 1, 0, 7],
			[0, 2, 15, 1, 5, 5, 0, 0, 6, 0, 0, 0, 4, 1, 3, 1, 17, 0, 0, 9],
			[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
			[6, 27, 26, 1, 0, 11, 1, 0, 0, 0, 1, 1, 2, 0, 0, 9, 1, 0, 0, 0],
			[31, 81, 11, 6, 11, 0, 0, 0, 0, 0, 0, 0, 3, 2, 0, 3, 14, 0, 0, 12],
			[19, 53, 6, 20, 0, 4, 37, 0, 30, 86, 43, 7, 5, 7, 17, 19, 2, 0, 0, 5],
			[0, 22, 14, 6, 10, 24, 18, 0, 13, 21, 5, 2, 13, 35, 7, 1, 8, 0, 0, 1],
			[0, 56, 5, 0, 0, 0, 0, 0, 7, 24, 0, 17, 7, 0, 0, 3, 0, 0, 0, 8],
			[18, 29, 3, 6, 11, 0, 15, 0, 12, 42, 37, 0, 3, 3, 13, 8, 0, 0, 0, 1],
			[32, 39, 37, 3, 33, 21, 6, 0, 4, 17, 0, 11, 8, 2, 3, 0, 23, 0, 0, 17],
			[72, 15, 28, 0, 0, 0, 0, 0, 1, 3, 0, 35, 0, 9, 17, 1, 9, 1, 0, 8],
			[11, 15, 4, 2, 0, 18, 10, 0, 20, 3, 0, 0, 2, 0, 0, 2, 2, 30, 0, 0],
			[14, 29, 19, 3, 2, 17, 13, 0, 7, 12, 2, 0, 6, 0, 0, 1, 1, 34, 0, 1],
			[1, 1, 7, 6, 1, 1, 15, 1, 1, 2, 1, 3, 1, 1, 9, 1, 1, 25, 1, 72]
		];

		var labels = [
			'The Sea and Cake',
			'Andrew Bird',
			'Laura Veirs',
			'Brian Eno',
			'Christopher Willits',
			'Wilco',
			'Edgar Meyer',
			'B\xc3\xa9la Fleck',
			'Fleet Foxes',
			'Kings of Convenience',
			'Brett Dennen',
			'Psapp',
			'The Bad Plus',
			'Feist',
			'Battles',
			'Avishai Cohen',
			'Rachael Yamagata',
			'Norah Jones',
			'B\xc3\xa9la Fleck and the Flecktones',
			'Joshua Redman'
		];
		var data = [];
		for (var i = 0; i < rawData.length; i++) {
			for (var j = 0; j < rawData[i].length; j++) {
				var label = labels[i];
				data.push([
					j, rawData[i][j], label
				]);
			}
		}
		var option = {
			tooltip: {
				trigger: 'axis',
				backgroundColor: 'rgba(33,33,33,1)',
				borderRadius:0,
				padding:10,
				axisPointer: {
					type: 'cross',
					label: {
						backgroundColor: 'rgba(33,33,33,1)'
					}
				},
				textStyle: {
					color: '#fff',
					fontStyle: 'normal',
					fontWeight: 'normal',
					fontFamily: "'Montserrat', sans-serif",
					fontSize: 12
				}	
			},
			grid:{
				show:false,
			},

			legend: {
				show:false,
			},

			singleAxis: {
				top: 0,
				bottom: 20,
				axisTick: {
					show:false
				},
				axisPointer: {
					animation: true,
					label: {
						show: true
					}
				},
				axisLabel: {
					textStyle: {
						color: '#878787',
						fontStyle: 'normal',
						fontWeight: 'normal',
						fontFamily: "'Montserrat', sans-serif",
						fontSize: 12
					}
				},
				axisLine: {
						show:false
				},
				splitLine: {
					show: false,
				}
			},

			series: [
				{
					type: 'themeRiver',
					color: ['#e6b034', '#71aa68', '#ed8739'],
					label: {
						normal: {
							show: false
						}
					},
					itemStyle: {
						emphasis: {
							shadowBlur: 5,
							shadowColor: 'rgba(0, 0, 0, 0.5)'
						}
					},
					data: data
				}
			]
		};
		eChart_1.setOption(option);
		eChart_1.resize();
	}
	if( $('#e_chart_2').length > 0 ){
		var eChart_2 = echarts.init(document.getElementById('e_chart_2'));
		var option1 = {
			  series: [{
				type: 'liquidFill',
				data: [0.7],
				color: [ '#ed8739'],
				backgroundStyle: {
					borderWidth: 0,
					color: 'rgba(255,255,255,0)',
					shadowBlur: 0
				},
				itemStyle: {
					normal: {
						shadowBlur: 5,
						shadowColor: 'rgba(0, 0, 0, .5)'
					}
				},
				 shape: 'container',
				outline: {
					show: false
				},
				label: {
					normal: {
						fontSize: 20
					}
				}
			}]
		};
		eChart_2.setOption(option1);
		eChart_2.resize();
	}
	if( $('#e_chart_3').length > 0 ){
		var eChart_3 = echarts.init(document.getElementById('e_chart_3'));
		var option3 = {
			tooltip : {
				trigger: 'item',
				formatter: "{a} <br/>{b} : {c} ({d}%)",
				backgroundColor: 'rgba(33,33,33,1)',
				borderRadius:0,
				padding:10,
				textStyle: {
					color: '#fff',
					fontStyle: 'normal',
					fontWeight: 'normal',
					fontFamily: "'Montserrat', sans-serif",
					fontSize: 12
				}	
			},
			legend: {
				show:false
			},
			toolbox: {
				show : false,
			},
			calculable : true,
			itemStyle: {
				 normal: {
					 shadowBlur: 5,
					 shadowColor: 'rgba(0, 0, 0, 0.5)'
				 }
			},
			series : [
				{
					name:'Advertising',
					type:'pie',
					radius : '80%',
					center : ['50%', '50%'],
					roseType : 'radius',
					color: ['#71aa68', '#e6b034', '#ed8739'],
					label: {
						normal: {
							fontFamily: "'Montserrat', sans-serif",
							fontSize: 12
						}
					},
					data:[
						{value:335, name:'Active'},
						{value:510, name:'Closed'},
						{value:274, name:'Hold'},
					].sort(function (a, b) { return a.value - b.value; }),
				},
			],
			animationType: 'scale',
			animationEasing: 'elasticOut',
			animationDelay: function (idx) {
				return Math.random() * 1000;
			}	
		};
		eChart_3.setOption(option3);
		eChart_3.resize();
	}
}
/*****E-Charts function end*****/

/*****Sparkline function start*****/
var sparklineLogin = function() { 
	if( $('#sparkline_1').length > 0 ){
		$("#sparkline_1").sparkline([2,4,4,6,8,5,6,4,8,6,6,2 ], {
			type: 'bar',
			width: '100%',
			height: '35',
			barWidth: '5',
			barSpacing: '5',
			barColor: '#ed8739',
			highlightSpotColor: '#ed8739'
		});
	}	
	if( $('#sparkline_2').length > 0 ){
		$("#sparkline_2").sparkline([0,2,8,6,8,5,3,5,7,2,4], {
			type: 'bar',
			width: '100%',
			height: '35',
			barWidth: '5',
			barSpacing: '5',
			barColor: '#ed8739',
			highlightSpotColor: '#ed8739'
		});
	}	
	if( $('#sparkline_3').length > 0 ){
		$("#sparkline_3").sparkline([0, 23, 43, 35, 44, 45, 56, 37, 40, 45, 56, 7, 10], {
			type: 'bar',
			width: '100%',
			height: '35',
			barWidth: '5',
			barSpacing: '5',
			barColor: '#ed8739',
			highlightSpotColor: '#ed8739'
		});
	}
	if( $('#sparkline_4').length > 0 ){
		$("#sparkline_4").sparkline([0,2,8,6,8,5,6,4,8,6,6,2 ], {
			type: 'line',
			width: '100%',
			height: '50',
			lineColor: '#ed8739',
			fillColor: '#ed8739',
			minSpotColor: '#ed8739',
			maxSpotColor: '#ed8739',
			spotColor: '#ed8739',
			highlightLineColor: '#ed8739',
			highlightSpotColor: '#ed8739'
		});
	}	
}
/*****Sparkline function end*****/

/*****Resize function start*****/
var sparkResize,echartResize;
$(window).on("resize", function () {
	/*Sparkline Resize*/
	clearTimeout(sparkResize);
	sparkResize = setTimeout(sparklineLogin, 200);
	
	/*E-Chart Resize*/
	clearTimeout(echartResize);
	echartResize = setTimeout(echartsConfig, 200);
}).resize(); 
/*****Resize function end*****/

/*****Function Call start*****/
sparklineLogin();
echartsConfig();
/*****Function Call end*****/