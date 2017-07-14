/**

The custom docky dock js code talking to the REST server

var obj = JSON.parse (jsonString);

var str = JSON.stringify (obj);

*/

(function($){

/*
  // header layout generated and fetched from the rest server
  var headerDOM = global.document.getElementById("header-content");
  global.$ajaxUtils.sendGetRequest( "/layout/list",
      function(res) {
        var layoutList = JSON.parse (res);
        // generate header buttons
        for ( var i = 0; i < layoutList.length; i++) {
          var button = global.document.createElement( 'button');
          var typeAttr = global.document.createAttribute ( 'type');
          typeAttr.value = 'button';
          var classAttr = global.document.createAttribute( 'class');
          classAttr.value = 'btn btn-primary';
          button.setAttributeNode( typeAttr);
          button.setAttributeNode( classAttr);
          button.appendChild( global.document.createTextNode( layoutList[i].name));
          headerDOM.appendChild( button);
        }
      }
  );
*/
  // docking layout
  var config = {
      content: [{
          type: 'row',
          content: [{
              type:'component',
              componentName: 'example',
              componentState: { text: 'Component 1' }
          },
          {
              type:'component',
              componentName: 'example',
              componentState: { text: 'Component 2' }
          }]
      }]
  };

  var myLayout = new window.GoldenLayout( config, $('#layoutContainer') );

  myLayout.registerComponent( 'example', function( container, state ){
      container.getElement().html( '<h2>' + state.text + '</h2>');
  });

    myLayout.init();

  var addMenuItem = function( text ) {
      var element = $( '<li>' + text + '</li>' );
      $( '#menuContainer' ).append( element );

      //insertion code will go here
      var newItemConfig = {
          type: 'component',
          componentName: 'example',
          componentState: { text: text }
      };

      myLayout.createDragSource( element, newItemConfig );
  };

  addMenuItem( 'User added component A' );
  addMenuItem( 'User added component B' );

})(window.$);
