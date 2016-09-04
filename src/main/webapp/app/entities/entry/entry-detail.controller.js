(function() {
    'use strict';

    angular
        .module('bloggingApp')
        .controller('EntryDetailController', EntryDetailController);

    EntryDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Entry', 'Blog', 'Tag', 'User'];

    function EntryDetailController($scope, $rootScope, $stateParams, previousState, entity, Entry, Blog, Tag, User) {
        var vm = this;

        vm.entry = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('bloggingApp:entryUpdate', function(event, result) {
            vm.entry = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
