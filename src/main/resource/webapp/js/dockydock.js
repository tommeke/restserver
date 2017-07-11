/**

The custom docky dock js code talking to the REST server

*/

(function($){

  var config = {
      content: [{
          type: 'row',
          content:[{
              type: 'component',
              componentName: 'testComponent',
              componentState: { label: 'WHAT IS GOING ON HERE' }
          },{
              type: 'column',
              content:[{
                  type: 'component',
                  componentName: 'testComponent',
                  componentState: { label: 'B' }
              },{
                  type: 'component',
                  componentName: 'testComponent',
                  componentState: { label: 'C' }
              }]
          }]
      }]
  };

  var myLayout = new GoldenLayout( config );

  myLayout.registerComponent( 'testComponent', function( container, componentState ){
      container.getElement().html( '<h2>' + componentState.label + '</h2>' );
  });

  myLayout.init();



})(window.$);
