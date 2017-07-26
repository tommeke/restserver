(function (global) {

//namespace for the ajax util class
var ajaxUtils = {};

// make GET request
ajaxUtils.sendGetRequest = function( requestUrl, responseHandler) {
  var request = new XMLHttpRequest();
  request.onreadystatechange = function () {
    if ((request.readyState == 4) && (request.status == 200)) {
      responseHandler(request.responseText);
    }
  };
  request.open( "GET", requestUrl, true);
  // send parameter is only used for POST
  request.send( null);
};

global.$ajaxUtils = ajaxUtils;

})(window);
