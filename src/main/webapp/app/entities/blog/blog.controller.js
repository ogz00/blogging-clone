(function() {
    'use strict';

    angular
        .module('bloggingApp')
        .controller('BlogController', BlogController);

    BlogController.$inject = ['$scope', 'Principal', '$state', 'Blog'];

    function BlogController ($scope, Principal, $state, Blog) {
        var vm = this;

        vm.blogs = [];
        vm.account = null;
        vm.isAuthenticated = null;

        getAccount();

        loadAll();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                console.log(account);
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }

        function loadAll() {
            Blog.query(function(result) {
                vm.blogs = result;
            });
        }
    }
})();
