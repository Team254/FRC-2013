min_graph = 0;
max_graph = 0;
options = {
  series: { shadowSize: 0 }, // drawing is faster without shadows
  xaxis: { show: false },
  legend: { show: true }
};

$(function () {
    var lowV = 1000;
    var lowS = 1000;
    var maxPoints = 5 * 50;
    var dataSetpoint = [];
    var dataValue = [];
    var dataControl = [];
    lowest = 1000000;
    highest = 0;
    function getData() {
      var resS = [];
      var resV = [];
      var resC = [];
      var min = 0;
      var max = 0;

      for (var i = 0; i < maxPoints; ++i)
         if( i > dataSetpoint.length)
           resS.push([i, 0]);
         else {
           resS.push([i, dataSetpoint[i]]);
           min = (dataSetpoint[i] < min) ? dataSetpoint[i] : min;
           max = (dataSetpoint[i] > max) ? dataSetpoint[i] : max;
         }
      for (var i = 0; i < maxPoints; ++i)
         if( i > dataValue.length)
           resV.push([i, 0]);
         else {
           resV.push([i, dataValue[i]]);
           min = (dataValue[i] < min) ? dataValue[i] : min;
           max = (dataValue[i] > max) ? dataValue[i] : max;
         }

      for (var i = 0; i < maxPoints; ++i)
         if( i > dataControl.length)
           resC.push([i, 0]);
         else {
           resC.push([i, dataControl[i]]);
           min = (dataControl[i] < min) ? dataControl[i] : min;
           max = (dataControl[i] > max) ? dataControl[i] : max;
         }
      min_graph = min;
      max_graph = max;

      var res = [{label: "S", data: resS},
                 {label: "V", data: resV},
                 {label: "C", data: resC}]; //console.log(res);
      return res;
    }
    // setup plot


    function makeplot() {
      var plotWidth = $(window).width() * .9;
      var plotHeight = $(window).height() * .8;

      $('#pid_plot').css('width', function(index) {
         return Math.round(plotWidth);
      });
      $('#pid_plot').css('height', function(index) {
          return Math.round(plotHeight);
      });

      // center the graph horizontally and vertically on the page
      // round down to make it slightly biased towards the left and top
      $('#pid_plot').css('margin-left', function(index) {
        return Math.floor(($(window).width() - plotWidth) / 2);
      });
      $('#pid_plot').css('margin-top', function(index) {
        return Math.floor(($(window).height() - plotHeight) / 2);
      });
      plot = $.plot($("#pid_plot"), getData(), options);
    }
    makeplot();
    $(window).resize(makeplot);

    function push(newS, newV, newC) {
      var min;
      if (dataSetpoint.length > 0)
          dataSetpoint = dataSetpoint.slice(1);

      if (newS < lowest) {
          lowest = newS;
      }
      if (newS > highest) {
          highest = newS;
      }
      while (dataSetpoint.length < maxPoints) {
          var y = newS;
          dataSetpoint.push(y);
      }

      if (dataValue.length > 0)
          dataValue = dataValue.slice(1);

      while (dataValue.length < maxPoints) {
          var y = newV;
          dataValue.push(y);
      }

      if (dataControl.length > 0)
          dataControl = dataControl.slice(1);

      while (dataControl.length < maxPoints) {
          //var y = (newC * ((max_graph-5 - (min_graph+5))/2)) + ((max_graph-5 - (min_graph+5))/2) ;
          var y = newC;
           dataControl.push(y);
      }

      plot.setupGrid();
      plot.setData(getData());
      plot.draw();
    }
    var socket = io.connect(window.location.href);
    socket.on('update', function (dataIn) {
      push(dataIn["S"], dataIn["V"],dataIn["C"]);
      console.log(dataIn);
    });
});
