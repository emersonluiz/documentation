define(['route', 'systemFactory', 'entityFactory', 'erdFactory', 'modalFactory', 'canvas', 'sortable', 'purify', 'fileinput', 'theme'], function (app, systemFactory, entityFactory, erdFactory, modalFactory, canvas, sortable, purify, fileinput, theme) {

    app.controller('erdController', ['$scope', '$routeParams', 'systemFactory', 'entityFactory', 'erdFactory', 'modalFactory', function($scope, $routeParams, systemFactory, entityFactory, erdFactory, modalFactory) {

        var url = global.documentation;

        $scope.entity = {};
        $scope.system = {};

        $scope.getSystem = function () {
            systemFactory.getSystem($routeParams.systemId).then(function (d) {
                console.log("SUCCESS", d.data);
                $scope.system = d.data;
            }).catch(function(d) {
                console.log("ERROR", d);
            });
        }

        $scope.getEntity = function () {
            entityFactory.getEntity($routeParams.systemId, $routeParams.entityId).then(function (d) {
                console.log("SUCCESS", d.data);
                $scope.entity = d.data;
            }).catch(function(d) {
                console.log("ERROR", d);
            });
        }

        $scope.getErd = function () {
            erdFactory.listErd($routeParams.entityId).then(function (d) {
                console.log("SUCCESS", d.data);
                if (d.data.length > 0) {
                	$scope.start(d.data[0].id);
                }
            }).catch(function(d) {
                console.log("ERROR", d);
            });
        }

        $scope.start = function (id) {
            erdFactory.getErd($routeParams.entityId, id).then(function (d) {
                console.log("SUCCESS", d);

                var blob = new Blob([d.data]);

                if (blob.size > 0) {
	                var urlIMG = window.URL.createObjectURL(blob);
	                var contentDispositionHeader = d.headers('Content-Disposition');
	                var result = contentDispositionHeader.split(';')[2].trim().split('=')[1];
	                var fileName = result.replace(/"/g, '');
	
	                $('#input-id').fileinput('destroy');
	
	                $("#input-id").fileinput(
	            		{uploadUrl: url + '/entity/' + $routeParams.entityId + '/erd', 
	            		 allowedFileExtensions: ['jpg', 'jpeg', 'gif', 'png'],
	            		 maxFileCount: 1,
	            		 validateInitialCount: true,
	            		 overwriteInitial: false,
	            		 initialPreview: ["<img src='"+urlIMG+"' class='kv-preview-data file-preview-image' alt='Desert' title='Desert' style='height:160px'>"],
	    	             initialPreviewConfig: [ {caption: fileName, url: url + '/entity/' + $routeParams.entityId + '/erd/'+id} ],
	                     initialPreviewAsData: false,
	                     initialPreviewFileType: 'image'}
	                );
                }
            }).catch(function(d) {
                console.log("ERROR", d);
            });
        }

        $("#input-id").fileinput(
    		{uploadUrl: url + '/entity/' + $routeParams.entityId + '/erd', 
    		 allowedFileExtensions: ['jpg', 'jpeg', 'gif', 'png'],
    		 maxFileCount: 1
    		}
        );

        $('#input-id').on('fileuploaded', function(event, data) {
            $('#input-id').fileinput('enable');
            modalFactory.successModal("Successfully created erd!", "sm");
            $scope.start(data.response.id);
        });

        $('#input-id').on('filedeleted', function(event, key) {
            modalFactory.successModal("Successfully deleted erd!", "sm");
        });

        $scope.getSystem();
        $scope.getEntity();
        $scope.getErd();

    }]);

});