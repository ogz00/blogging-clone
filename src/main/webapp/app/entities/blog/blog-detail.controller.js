(function() {
    'use strict';

    angular
        .module('bloggingApp')
        .controller('BlogDetailController', BlogDetailController);

    BlogDetailController.$inject = ['$scope', '$rootScope', 'Principal', '$stateParams', 'previousState', 'entity', 'Blog', 'Entry', 'User'];

    function BlogDetailController($scope, $rootScope, Principal, $stateParams, previousState, entity, Blog, Entry, User) {
        var vm = this;

        vm.blog = entity;
        console.log(vm.blog);
        vm.previousState = previousState.name;

        vm.account = null;
        vm.isAuthenticated = null;

        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }

        getEntries();
        function getEntries() {
            Blog.entries({id: vm.blog.id}, function (response) {
                console.log(response);
                vm.blog.entries = response;
            })
        }

        var unsubscribe = $rootScope.$on('bloggingApp:blogUpdate', function(event, result) {
            vm.blog = result;
        });

        var unsubscribe2 = $rootScope.$on('bloggingApp:entryUpdate', function(event, result) {
            vm.entry = result;
        });
        $scope.$on('$destroy', unsubscribe);
        $scope.$on('$destroy', unsubscribe2);
    }
})();
