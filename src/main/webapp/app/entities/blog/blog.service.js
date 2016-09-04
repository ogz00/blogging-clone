(function() {
    'use strict';
    angular
        .module('bloggingApp')
        .factory('Blog', Blog);

    Blog.$inject = ['$resource'];

    function Blog ($resource) {
        var resourceUrl =  'api/blogs/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'entries': {
                method: 'GET',
                isArray:true,
                url:'api/blogs/:id/entries',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
