require.config({
   
   paths: {
    
    // External Libraries
        
        // JQuery
        'jquery' : '../../resources/assets/libs/jquery/dist/jquery.min',

        // Angular
        'angular' : '../../resources/assets/libs/angular/angular.min',
        'angular-ui' : '../../resources/assets/libs/angular-ui/build/angular-ui.min',
        'angular-amd': '../../resources/assets/libs/angularAMD/angularAMD.min',
        'angular-route' : '../../resources/assets/libs/angular-route/angular-route.min',
        'angular-cookies' : '../../resources/assets/libs/angular-cookies/angular-cookies.min',
        'angular-resource' : '../../resources/assets/libs/angular-resource/angular-resource.min',
        'angular-sanitize' : '../../resources/assets/libs/angular-sanitize/angular-sanitize.min',
        'angular-bootstrap' : '../../resources/assets/libs/angular-bootstrap/ui-bootstrap-tpls.min',
        
        // Bootstrap
        'bootstrap-js' : '../../resources/assets/libs/bootstrap/dist/js/bootstrap.min',
        
        'canvas' : '../../resources/assets/libs/bootstrap-fileinput-master/js/plugins/canvas-to-blob.min',
        'sortable' : '../../resources/assets/libs/bootstrap-fileinput-master/js/plugins/sortable.min',
        'purify' : '../../resources/assets/libs/bootstrap-fileinput-master/js/plugins/purify.min',
        'fileinput' : '../../resources/assets/libs/bootstrap-fileinput-master/js/fileinput.min',
        'theme' : '../../resources/assets/libs/bootstrap-fileinput-master/themes/fa/theme',

        'systemFactory' : '../../resources/app/components/scripts/systemFactory',
        'entityFactory' : '../../resources/app/components/scripts/entityFactory',
        'restFactory' : '../../resources/app/components/scripts/restFactory',
        'jsonFactory' : '../../resources/app/components/scripts/jsonFactory',
        'jsonPropertyFactory' : '../../resources/app/components/scripts/jsonPropertyFactory',
        'erdFactory' : '../../resources/app/components/scripts/erdFactory',
        
        // Modal Controller
        'modalFactory' : '../../resources/app/components/modal/scripts/modalFactory',

        // Modal Factory
        'modalController' : '../../resources/app/components/modal/scripts/modalController',

        // Config scripts
        'scripts-config' : '../../resources/app/config'

    // Factories
        //'human-resources-factory' : '../../app/cms/components/humanResources/scripts/humanResourcesFactory',
        //'time-card-factory'       : '../../app/cms/components/timeCard/scripts/timeCardFactory',
        //'person-factory'          : '../../app/cms/components/register/person/scripts/personFactory',
        //'organization-factory'          : '../../app/cms/components/register/organization/scripts/organizationFactory',
        //'departament-factory'          : '../../app/cms/components/register/departament/scripts/departamentFactory'

    },
    shim: { 
       /* 'scripts-general' : {
            deps: ['angular-amd']
        },*/
        'bootstrap-js' : {
            deps: ['jquery']
        },
        'angular' : {
            deps: ['jquery']
        },
        'angular-amd' : {
            deps: ['angular']
        }, 
        'angular-route' : {
            deps: ['angular']
        },
        'angular-bootstrap' : {
            deps: ['angular']
        },
        'angular-cookies' : { 
            deps: ['angular']
        },
        'angular-resource' : {
            deps: ['angular']
        },
        'angular-sanitize' : {
            deps: ['angular']
        }, 
        'angular-ui' : {
            deps: ['angular']
        }
    },

    // kick start application
    deps: ['route']
    
});
